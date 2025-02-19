import random
import string
import requests
import bs4
import time
from dataclasses import dataclass
from hashlib import md5
import logging

@dataclass
class LoginDetails:
    handle: str
    password: str

@dataclass
class SubmissionResult:
    sid: str
    verdict: str
    tim: int
    mem: int

class Language:
    KT    = 88
    CPP20 = 89

class Codeforces:
    def gen_rand(self):
        return "".join(random.choices(string.ascii_letters + string.digits, k=18))

    def __init__(self, login_details: LoginDetails = None):
        self.logged_in = False
        self.login_details = login_details
        self.session = requests.Session()
        self.ftaa = self.gen_rand()
        self.bfaa = md5(self.gen_rand().encode('utf8')).hexdigest()

    def get_csrf(self, page):
        soup = bs4.BeautifulSoup(page.text, 'html.parser')
        logging.debug(
            f"Getting csrf from {page.url} => {page.text}"
        )
        return soup.find('span', {'class': 'csrf-token'}).attrs['data-csrf']
    
    def get_tta(self):
        cookie = self.session.cookies.get('39ce7', None)
        if cookie is None:
            raise Exception('Cookie not found: ' + str(self.session.cookies.get_dict()))
        tta = 0
        P = 1009
        for i in range(len(cookie)):
            tta = (tta + (i + 1) % P * (i + 2) % P * ord(cookie[i]) % P) % P;
            if i % 3 == 0:
                tta = (tta+11)%P
            if i % 2 == 0:
                tta = (tta*2)%P
            if i > 0:
                tta = (tta - ord(cookie[i // 2]) // 2 * (tta % 5) % P + P)%P
        return tta

    def login(self):
        csrf = self.get_csrf(self.session.get('https://codeforces.com/enter'))
        response = self.session.post('https://codeforces.com/enter', data={
            'handleOrEmail': self.login_details.handle,
            'ftaa': self.ftaa,
            'bfaa': self.bfaa,
            'password': self.login_details.password,
            'remember': 'on',
            'action': 'enter',
            'csrf_token': csrf,
            '_tta': self.get_tta(),
        })
        if response.status_code != 200:
            raise RuntimeError('Failed to login: ' + response.text)
        self.logged_in = True

    def submit(self, contest, problem, language, code, group=None):
        if not self.logged_in:
            self.login()
        if group is not None:
            url = f'https://codeforces.com/group/{group}/contest/{contest}/submit'
            target = f'/group/{group}/contest/{contest}/problem/{problem}'
        elif contest < 10000:
            url = f'https://codeforces.com/problemset/submit'
            target = f'/problemset/problem/{contest}/{problem}'
        else:
            url = f'https://codeforces.com/gym/{contest}/submit'
            target = f'/gym/{contest}/problem/{problem}'
        csrf = self.get_csrf(self.session.get(url))
        response = self.session.post(url, data={
            'csrf_token': csrf,
            'ftaa': self.ftaa,
            'bfaa': self.bfaa,
            'action': 'submitSolutionFormSubmitted',
            'submittedProblemIndex': problem,
            'contestId': contest,
            'programTypeId': language,
            'source': code,
            'tabSize': 4,
            'sourceFile': '',
            '_tta': self.get_tta(),
        }, params={
            'csrf_token': csrf,
        })
        if response.status_code != 200:
            raise RuntimeError('Failed to submit: ' + response.text)
        soup = bs4.BeautifulSoup(response.text, 'html.parser')
        candidates = []
        for el in soup.find_all('td', {'class': 'time-consumed-cell'}):
            id = el.parent.attrs['data-submission-id']
            problem_url = el.parent.find_all('td')[3].find('a').attrs['href']
            if problem_url.endswith(target):
                candidates.append(id)
        if len(candidates) == 0:
            raise Exception('No submissions found')
        return candidates[0]
    
    def status(self, submission_id):
        result = SubmissionResult(submission_id, 'TESTING', 0, 0)
        page = self.session.get(f'https://codeforces.com/submissions/test_download')
        soup = bs4.BeautifulSoup(page.text, 'html.parser')
        cell = soup.find('td', {'submissionid': submission_id, 'class': 'status-verdict-cell'})
        if cell is None:
            return result
        if cell.text.strip() == 'In queue':
            return result
        cell = cell.find('span', {'class': 'submissionVerdictWrapper', 'submissionid': submission_id})
        if cell is None:
            return result
        
        verdict = cell.attrs['submissionverdict']
        tim = int(cell.parent.parent.find('td', {'class': 'time-consumed-cell'}).text.replace('&nbsp;', '').replace('ms', '').strip())
        mem = int(cell.parent.parent.find('td', {'class': 'memory-consumed-cell'}).text.replace('&nbsp;', '').replace('KB', '').strip())
        return SubmissionResult(submission_id, verdict, tim, mem)
    
    def wait_for_result(self, submission_id, timeout=1000):
        now = time.time()
        sleeptime = 0.05
        factor = 1.5
        last_iteration = False
        while True:
            status = self.status(submission_id)
            if status.verdict != 'TESTING':
                return status
            if last_iteration:
                raise Exception('Timeout')
            if time.time() + sleeptime > now + timeout:
                last_iteration = True
                time.sleep(timeout + now - time.time())
            else:
                time.sleep(sleeptime)
                sleeptime *= factor
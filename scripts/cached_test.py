from scripts.database import Database
from scripts.codeforces import Codeforces, Language
import pathlib
import logging

def showtime(ms):
    if ms < 1000:
        return f"{ms}ms".ljust(5)
    else:
        digs = str(ms%1000//10).ljust(2, '0')
        return f"{ms//1000}.{digs}s".ljust(5)

def test_code(db: Database, cf: Codeforces, code_path: pathlib.Path):
    code = code_path.read_text()
    prefix = '// CODEFORCES '
    codeforces = list(filter(lambda x: x.startswith(prefix), code.splitlines()))

    logging.info(f'Found {len(codeforces)} codeforces snippets in {code_path}')
    logging.debug(f'Codeforces snippets: {codeforces}')

    if len(codeforces) == 1:
        descriptor = codeforces[0].removeprefix(prefix)
        ks = descriptor.split(' ')
        
        logging.debug(f"Descriptor = {descriptor} => ks = {ks}")

        
        
        if len(ks) == 2:
            contest, problem = ks
            group = None
        else:
            contest, problem, group = ks
        logging.info(f'Testing {code_path}... ')

        randomized_code = "// " + cf.gen_rand() + "\n" + code
        def getver():
            submission_id = cf.submit(int(contest), problem, Language.KT, randomized_code, group)
            return cf.wait_for_result(submission_id)
        
        result = db.cached_submit(code, descriptor, getver)
        message = f'{result.verdict}\033[0m \033[94msub={result.sid} time={showtime(result.tim)} mem={result.mem}KB\033[0m'
        if result.verdict == 'OK':
            logging.info(message)
        else:
            logging.error(message)
        
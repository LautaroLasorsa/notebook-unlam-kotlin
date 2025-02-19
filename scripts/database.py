import sqlite3
from scripts.codeforces import SubmissionResult, LoginDetails
import json
import os

class Database:
    def __init__(self):
        self.conn = sqlite3.connect('submissions.db')
        self.conn.execute('CREATE TABLE IF NOT EXISTS submissions (id INTEGER PRIMARY KEY, code TEXT, problem TEXT, result TEXT)')
        self.conn.execute('CREATE TABLE IF NOT EXISTS login (id INTEGER PRIMARY KEY, handle TEXT, password TEXT)')

    def close(self):
        self.conn.close()
    
    def cached_submit(self, code: str, problem: str, calculate):
        cursor = self.conn.cursor()
        cursor.execute('SELECT result FROM submissions WHERE code = ? AND problem = ?', (code, problem))
        result = cursor.fetchone()
        if result is None:
            result: SubmissionResult = calculate()
            cursor.execute('INSERT INTO submissions (code, problem, result) VALUES (?, ?, ?)', (code, problem, json.dumps(result.__dict__)))
            self.conn.commit()
            return result
        else:
            self.conn.commit()
            return SubmissionResult(**json.loads(result[0]))

    def cached_get_login(self):
        cursor = self.conn.cursor()
        cursor.execute('SELECT handle, password FROM login')
        result = cursor.fetchone()
        if result is None:
            handle = os.environ["CODEFORCES_USERNAME"] # input('Enter you codeforces credentials:\n username: ')
            password = os.environ["CODEFORCES_PASSWORD"] #input(' password: ')
            cursor.execute('INSERT INTO login (handle, password) VALUES (?, ?)', (handle, password))
            self.conn.commit()
            return LoginDetails(handle, password)
        else:
            self.conn.commit()
            return LoginDetails(*result)
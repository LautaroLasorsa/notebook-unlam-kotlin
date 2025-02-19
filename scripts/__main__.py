#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import pathlib
from scripts.codeforces import Codeforces
from scripts.cached_test import test_code
from scripts.code_generator import gen_kotlin_code
from scripts.database import Database
import dotenv
import logging
dotenv.load_dotenv()

logging.basicConfig(
    format="%(asctime)s \t %(levelname)s \t %(message)s",
    level=logging.DEBUG,
    handlers=[
        logging.FileHandler("tests.log"),
        logging.StreamHandler()
    ]
)

def main():
    db = Database()
    cf = Codeforces(db.cached_get_login())
    for path in pathlib.Path().glob('**/*_test_problem_*.kt'):
        logging.info(f"Running {path}")
        gen_kotlin_code(path)
        test_code(db, cf, pathlib.Path("temporal.kt"))
    db.close()
    
if __name__ == '__main__':
    main()

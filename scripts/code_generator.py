import pathlib
from dataclasses import dataclass
import logging 

def update_code(code_path: pathlib.Path):
    """
    Search for a `namespace Book {}` block and replace whatever is inside it with the code from the book, which is assumed to be located in a file with the same name as the directory (plus the extension `.cpp`).

    Any //r directive in the code is interpreted as a changes to be made to the code of the book before replacing it in. A directive of the form //r A%B means that any line containing A will be replaced by B. This can be used, for example, to change the value of constants like MAXN.
    """
    relpath = code_path
    code_path = code_path.absolute()
    book_path = code_path.parent / (code_path.parent.name + '.cpp')

    code = code_path.read_text()
    if 'Book' not in code:
        return

    book = book_path.read_text().split('\n\n', maxsplit=1)[-1]

    @dataclass
    class Replacement:
        condition: str
        replacement: str

    def find_replacements(code: str):
        for line in str.splitlines(code):
            line = line.strip()
            if line.startswith("//r "):
                yield Replacement(*line[4:].split('%'))
    replacements = list(find_replacements(code))

    replaced_book = ""
    for line in book.splitlines():
        for replacement in replacements:
            if replacement.condition in line:
                line = replacement.replacement
        if line != '':
            line = '\t' + line
        replaced_book += line + '\n'

    begin_string = 'namespace Book {'
    end_string = '\n}'

    prefix, suffix = code.split(begin_string)

    updated_code = prefix + begin_string + replaced_book + end_string + suffix.split(end_string, maxsplit=1)[1]

    if updated_code != code:
        print(f'Updating {relpath}')
        code_path.write_text(updated_code)

def gen_kotlin_code(code_path: pathlib.Path):
    code = code_path.read_text().split("\n")
    dependences = code[0].split(" ")[1:]
    problem = code[1:3]
    
    logging.debug(f"Writting code for {code_path}", extra={"problem":problem,"dependences":dependences})

    add_endline = lambda x : map(lambda y: y+"\n",x)

    with open("temporal.kt","w") as temp:
        temp.writelines(add_endline(problem))

        for dependence in dependences:
            code_dependence = code_path.parent.joinpath(dependence)
            logging.debug(f"Getting dependence: {dependence} = {code_dependence}")
            temp.write(code_dependence.read_text()+"\n")
        
        temp.writelines(add_endline(code[3:]))

    
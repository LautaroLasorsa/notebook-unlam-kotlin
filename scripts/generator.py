try:
    from fpdf import FPDF
except ImportError:
    print('This script requires the fpdf2 package to be installed. Run `pip install fpdf2`.')
    exit(1)

try:
    pdf = FPDF()
    pdf.add_page(format=(100, 100))
except TypeError:
    print('fpdf is installed instead of fpdf2')
    print('run `pip uninstall fpdf` and then `pip install fpdf2` and retry')

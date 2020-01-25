import os
from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.pdfpage import PDFPage
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from io import StringIO
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize


class Transformer():

    def __init__(self):
        self.urlFileInput = str(os.getcwd()) + '\Input\demo.pdf'
        self.urlFileOutput = 'ahahah'
        self.contenido = 'ahahah'

    def extraerContenido(self):
        rsrcmgr = PDFResourceManager()
        sio = StringIO()
        device = TextConverter(rsrcmgr, sio, codec='utf-8', laparams=LAParams())
        interpreter = PDFPageInterpreter(rsrcmgr, device)

        fp = open(self.urlFileInput, 'rb')
        for page in PDFPage.get_pages(fp):
            interpreter.process_page(page)
        fp.close()

        self.contenido = sio.getvalue()

        device.close()
        sio.close()

    def minarContenido(self):
        self.extraerContenido()
        # nltk.download('all')
        word_tokens = word_tokenize(self.contenido)

        tokens = set()
        result = []
        stop_words = set(stopwords.words('english'))

        for word in word_tokens:
            if word not in stop_words:
                if word.isalnum():
                    if word not in tokens:
                        try:
                            int(word)
                        except:
                            tokens.add(word)
                            result.append(word)

        print(result)


t = Transformer()
t.minarContenido()

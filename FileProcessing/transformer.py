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
        self.urlFileInput = str(os.getcwd()) + '\Input\documento.pdf'
        self.contenido = 'No se pudo procesar'

    def extraerContenido(self):
        rsrcmgr = PDFResourceManager()
        sio = StringIO()
        device = TextConverter(rsrcmgr, sio, laparams=LAParams())
        interpreter = PDFPageInterpreter(rsrcmgr, device)

        fp = open(self.urlFileInput, 'rb')
        for page in PDFPage.get_pages(fp):
            interpreter.process_page(page)
        fp.close()

        self.contenido = sio.getvalue()

        device.close()
        sio.close()

    def traducirToEnglish(self):
        pass

    def minarContenido(self):
        self.extraerContenido()
        # nltk.download('all')
        word_tokens = word_tokenize(self.contenido)

        tokens = set()
        stop_words = set(stopwords.words())
        jsonRetornar = '{'
        for word in word_tokens:
            if word not in stop_words:
                if word.isalnum():
                    if word not in tokens:
                        try:
                            int(word)
                        except:
                            tokens.add(word)
                            jsonRetornar += '"palabra":"' + str(word) + '"'
        jsonRetornar += '}'

        return jsonRetornar


if __name__ == '__main__':
    t = Transformer()
    t.minarContenido()

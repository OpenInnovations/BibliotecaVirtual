import os
from pdfminer.pdfinterp import PDFResourceManager, PDFPageInterpreter
from pdfminer.pdfpage import PDFPage
from pdfminer.converter import TextConverter
from pdfminer.layout import LAParams
from io import StringIO
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from textblob import TextBlob


class Transformer():

    def __init__(self):
        self.urlFileInput = os.path.join(os.path.dirname(__file__), 'Input', 'documento.pdf')
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

        self.contenido = sio.getvalue().lower()

        device.close()
        sio.close()

        del rsrcmgr, sio, device, interpreter

    def sentimientos(self):
        tb = TextBlob(text=self.contenido)
        # tb.translate(to="en")
        return tb.sentiment

    def minarContenido(self):
        self.extraerContenido()
        word_tokens = word_tokenize(self.contenido)

        tokens = set()
        stop_words = set(stopwords.words())
        result = []
        for word in word_tokens:
            if word not in stop_words:
                if word.isalnum():
                    if word not in tokens:
                        try:
                            int(word)
                        except:
                            tokens.add(word)
                            result.append(word)

        sentimiento = self.sentimientos()

        json_devolver = {
            'keywords': result,
            'sentiment': {
                'polarity': sentimiento.polarity,
                'subjectivity': sentimiento.subjectivity
            }
        }

        del sentimiento, self.contenido, result, tokens, word_tokens

        return json_devolver


if __name__ == '__main__':
    t = Transformer()
    t.minarContenido()

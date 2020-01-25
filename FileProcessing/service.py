from storage import Storage
from transformer import Transformer
from flask import Flask
from flask import request
from flask import Response
import json

app = Flask(__name__)

# http://127.0.0.1:3000/contenido
"""
#Enviar este json via post
{
"url":"gs://biblioteca-virtual/1579977243433-Programa_lectivo.pdf"
}
"""


@app.route('/contenido', methods=['POST'])
def procesar():
    jsonRecibido = request.get_json(force=True)
    descargarDocumento(url=jsonRecibido['url'])
    procesar = Transformer()
    procesar.extraerContenido()
    return Response(json.dumps(procesar.minarContenido(), ensure_ascii=False), mimetype='application/json')


def descargarDocumento(url: str):
    storage = Storage()
    storage.url = url
    storage.descargarArchivo()


if __name__ == '__main__':
    app.run(
        debug=True,
        port=3000
    )

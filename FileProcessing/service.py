from transformer import Transformer
from flask import Flask, request, jsonify
import os
import requests

app = Flask(__name__)

# http://127.0.0.1:3000/contenido
"""
#Enviar este json via post
{
"url":"https://00e9e64bac262a84c8a13d50efc1705e855124fbd09e1f1ce5-apidata.googleusercontent.com/download/storage/v1/b/biblioteca-virtual/o/1579909279799-FormularioCompleto.pdf?qk=AD5uMEvU0jl11Zf2S6QwFmDv3G4Cg5-g0MdRcohcYrZ_veVtqSjF9VlMEfw-4csGzLHTwxvl6fxrK-ja-h5rVQh95j4Ys_ecQ1i-xxKRKD_hVo1RWR0LkGpjQOTcWsggZECvrmx_mwctUhCOhDE5NASkt4RP-GrcmkZS6jydQHXFXY6YH0FiacDfvQTdvQTjoT71Hp-v1foBFheXyS8EL7pBr9ZZN5Z6Wh2y--sqGCTlldfVKutUj6cP3OqSUTr4JuJ2jWReNvZU-6sqDM8seewKKBB38bp8qJ5zKCEti4gn8HgYAzVMnvcImZzw6iFIE0qnYr2eg0Oj7KLfYq3zcdFNTLI42Xn0qn-QJ12O9jMeBHSE_F-28_dna9Hr9Y11ouy2sptXY1eJPiU6GV-X-5E85-V7cxB6gfuNqkRza--XmGw26PYKxZBm5PXvkUbgPUdeE3tQ9taDcRIY3N5eRo6KXUibE5GWhm7PXN8ifOiNxISBCXC9NUeHLeQJ26VeLSjQYpQ-xBjGZ_9IFkhHjisZTCcXcFpaUDcvLGTyNn-di-p9ZMY4xqK9UWg03wigQUGhVNAE3abqO6KanjA-xEoaAOBNT7EnwvrkV5CHqZte3KJawcxLCTFtUOIPBiNEzKVbOtzyX6iKFT0IjlZSwJQFpbgq0ORsbxDaC6m5keh2xDSi2bXYwCQlmDJ8adF7FWOYpXvZYpPsFKdKwHOc7imbHKFWaUszKzmvup2XzltfBtWWNMjZM_FK33vB4R04U8szt0Sv7a6Gm-vObcOtMrC3HTXaKos1hTzsvYv4mXAH0J--4X0HF_IhtsJpu00Qf09jWSoMqXey"
}
"""


@app.route('/contenido', methods=['POST'])
def procesar():
    jsonRecibido = request.get_json(force=True)
    descargarDocumento(url=jsonRecibido['url'])
    procesar = Transformer()
    procesar.extraerContenido()
    return jsonify(procesar.minarContenido())


def descargarDocumento(url: str):
    documento = requests.get(url=url, allow_redirects=True)
    print(documento.headers.get('content-type'))
    print(documento.headers.get('content'))

    open(str(os.getcwd()) + '\Input\documento.pdf', 'wb').write(documento.content)


if __name__ == '__main__':
    app.run(
        debug=True,
        port=3000
    )

from google.cloud import storage
from os import environ
from google.oauth2 import service_account
import os


class Storage():
    def __init__(self):
        environ["GOOGLE_APPLICATION_CREDENTIALS"] = os.path.join(os.path.dirname(__file__), 'Recursos',
                                                                 'credenciales.json')
        credenciales = service_account.Credentials.from_service_account_file(
            os.path.join(os.path.dirname(__file__), 'Recursos', 'credenciales.json')
        )
        self.storage_client = storage.Client(credentials=credenciales)
        self.storage_client.bucket(bucket_name='biblioteca-virtual')
        self.url = ''

    def descargarArchivo(self):
        ruta = open(os.path.join(os.path.dirname(__file__), 'Input', 'documento.pdf'), 'wb')
        self.storage_client.download_blob_to_file(self.url, ruta)
        ruta.close()

from google.cloud import storage
from os import getcwd
from os import environ
from google.oauth2 import service_account


class Storage():
    def __init__(self):
        environ["GOOGLE_APPLICATION_CREDENTIALS"] = str(getcwd()) + '\Recursos\credenciales.json'
        credenciales = service_account.Credentials.from_service_account_file(
            str(getcwd()) + '\Recursos\credenciales.json'
        )
        self.storage_client = storage.Client(credentials=credenciales)
        self.storage_client.bucket(bucket_name='biblioteca-virtual')
        self.url = ''

    def descargarArchivo(self):
        ruta = open(str(getcwd()) + '\Input\documento.pdf', 'wb')
        self.storage_client.download_blob_to_file(self.url, ruta)

FROM python:3.7-alpine

LABEL Name=file-processing Version=0.0.1
EXPOSE 3001

ADD . /

RUN apk add --no-cache --virtual .py_deps build-base python3-dev libffi-dev openssl-dev

#Dependencias app
#RUN python3 -m pip install cryptography
RUN python3 -m pip install google-cloud-storage
RUN python3 -m pip install -r requirements.txt

#Recursos nltk
RUN [ "python3", "-c", "import nltk; nltk.download('stopwords'); nltk.download('punkt');" ]

ENTRYPOINT ["python3","./service.py"]

#sudo docker build -t file-processing .
#sudo docker run -p 3001:3001 -dit --name="file-processing" "file-processing"
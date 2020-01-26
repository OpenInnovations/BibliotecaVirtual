FROM python:3.7-alpine

LABEL Name=fileprocessing Version=0.0.1
EXPOSE 3001

WORKDIR /app
ADD . /app

#Dependencias app
RUN py -m pip install -r requirements.txt

#Recursos nltk
RUN py -m nltk.downloader all

CMD ["py","./service.py"]

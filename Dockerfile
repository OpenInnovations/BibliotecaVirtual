FROM node:12.14.1-alpine

# ENV DB_HOST IP_DB
# ENV DB_PORT PUERTO_DB
# ENV DB_NAME NOMBRE_DB
# ENV DB_USER USUARIO_DB
# ENV DB_PASS PASSWORD_DB

COPY ["package.json", "package-lock.json", "/usr/src/"]

WORKDIR /usr/src

RUN npm install

COPY [".", "/usr/src/"]

EXPOSE 3000

ENTRYPOINT ["npm","start"]
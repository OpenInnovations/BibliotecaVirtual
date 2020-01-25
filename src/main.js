require('./config/env');
const express = require('express');
const app = express();
var router = require('./routes/index.js');
var mongoose = require('mongoose');

app.use('/', router);

// Creamos la variable PORT para indicar el puerto en el que va a funcionar el servidor
var port = 3800;

// Le indicamos a Mongoose que haremos la conexión con Promesas
mongoose.Promise = global.Promise;


// Usamos el método connect para conectarnos a nuestra base de datos
mongoose.connect(process.env.NODE_ENV, {useNewUrlParser: true})
    .then(() => {

        // CREAR EL SERVIDOR WEB CON NODEJS
        app.listen(port, () => {
            console.log("servidor corriendo en http://localhost:3000");
        });

    })
    // Si no se conecta correctamente escupimos el error
    .catch(err => console.log(err));
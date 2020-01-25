const config = require('./config/env');
const express = require('express');
const app = express();
var mongoose = require('mongoose');

//Configurando Json
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

//routers
app.use(require('./routes/index.js'));

//Configuración para la conexión con Mongo
const { db: { host, port, name } } = config;
const connectionString = `mongodb://${host}:${port}`;

//Conexión con Mongo
(async ()=>{
    try {     
        // Nos conectamos a la base de datos
        await mongoose.connect(connectionString, {
            useNewUrlParser: true,
            dbName: name,
            // user: user,
            // pass: pass
        });    

        //Levantamos el servidor en el puerto especificado en la configuración
        app.listen(config.app.port, () => {
            console.log(`servidor corriendo en http://localhost:${config.app.port}`);
        });

    } catch (error) {
        console.log(error);
    }
})();

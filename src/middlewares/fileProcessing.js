const axios = require('axios');
const fs = require('fs');
const path = require('path');
const config= require('./../config/env');
exports.processing = async (req, res, next) => {

    console.log("Iniciando processing")

    try {


        /** -- AXIOS --*/
        console.log(req.processingUrl, config.processing.url);
        let body = req.processingUrl;
        //let body = { url: "gs://biblioteca-virtual/1579977243433-Programa_lectivo.pdf" }
        const response = await axios.post('http://35.237.68.44:3001/contenido', body);
        console.log(response.data);
        /*-- END AXIOS -- */


        /** Reemplazar por axios */
        // let ruta = path.normalize(path.join(__dirname, "../resource/processing.json"));
        // let response = fs.readFileSync(ruta, 'utf-8');
        /**END Reemplazar por axios*/

        req.processing = response.data;
        
        next();

    } catch (error) {
        next(error);
    }


}
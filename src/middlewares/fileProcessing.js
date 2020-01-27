const axios = require('axios');
const fs = require('fs');
const path = require('path');

exports.processing = async (req, res, next) => {

    try {


        /** -- AXIOS --
         
        let body = { url: "gs://biblioteca-virtual/1579977243433-Programa_lectivo.pdf" }
        const response = await axios.post('/user', body);
        
        -- END AXIOS -- */


        /** Reemplazar por axios */
        let ruta = path.normalize(path.join(__dirname, "../resource/processing.json"));
        let response = fs.readFileSync(ruta, 'utf-8');
        /**END Reemplazar por axios*/

        req.processing = JSON.parse(response);
        
        next();

    } catch (error) {
        next(error);
    }


}
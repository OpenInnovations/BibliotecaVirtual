const express = require('express');
const gcsMiddlewares = require("../middlewares/google-cloud-storage");
const app = express();

const Multer = require('Multer');

var router = express.Router();

const multer = Multer({
    storage: Multer.MemoryStorage,
    limits: {
        fileSize: 10 * 1024 * 1024, // Maximum file size is 10MB
    },
});

router.get('/', function (req, res) {
    res.send('Transfer Controller by Jose Quispe')
  })

router.post(
    '/upload',
    multer.single('test'),
    gcsMiddlewares.sendUploadToGCS,
    (req, res, next) => {
        if (req.file && req.file.gcsUrl) {
            return res.send(req.file.gcsUrl);
        }

        return res.status(500).send('Unable to upload');
    },
);

app.use('/', router);

module.exports = router;
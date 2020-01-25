const express = require('express');
const router = express.Router();

const Multer = require('Multer');

const gcsMiddlewares = require("../middlewares/google-cloud-storage");
const bookMiddlewares = require("../middlewares/book");
const processingMiddlewares = require('./../middlewares/fileProcessing')

const multer = Multer({
    storage: Multer.MemoryStorage,
    limits: {
        fileSize: 10 * 1024 * 1024, // Maximum file size is 10MB
    },
});

router.delete(
    '/delete/:id',
    gcsMiddlewares.deleteFileGCS,
    bookMiddlewares.deleteBook,
    async (req, res) => {
        req.response.message = "Deleting was successful";
        return res
            .status(200)
            .json(req.response);
    }
);

router.post(
    '/upload',
    multer.single('test'),
    gcsMiddlewares.sendUploadToGCS,
    processingMiddlewares.processing,
    bookMiddlewares.saveBook,
    (req, res, next) => {
        if (req.file && req.file.gcsUrl) {
            console.log("enviado respuesta despues de guardar");
            return res
                .status(200)
                .json({
                    message: "Upload was successful",
                    book: req.book
                })
        }

        return res.status(500).send('Unable to upload');
    },
);


module.exports = router;
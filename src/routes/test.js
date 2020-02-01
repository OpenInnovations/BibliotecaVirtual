const express = require('express');
const router = express.Router();
const Book = require("./../models/Book");

router.get("/", async (req, res) => {

    let book = new Book({
        title: "Titulo",
        filename: "Filename",
        author: "autor",
        url: "www.google.com",
        keywords: ["test","algo","vamos"],
        sentiment: {
            polarity: 0.35,
            subjectivity: 0.6
        }
    })
    console.log("Espera guardado test");
    try {
        let bookre = await book.save();
        res.status(200).json(bookre);
    }
    catch (err) {
        res.status(500)
    };
})

module.exports = router;
const Book = require('../models/Book');

/**
 * Middleware for save book in MongoDB.
 * @param {Object} req
 * @param {Object} res
 * @param {Function} next
 * @return {*}
 */
exports.saveBook = async (req, res, next) => {

    if (!req.file) {
        return next();
    }

    let book = new Book({
        title: req.file.originalname.replace(/ /g, "_"),
        filename: req.file.cloudStorageObject,
        author: "autor",
        url: req.file.gcsUrl,
        keywords: req.keywords
    })
        console.log("Espera guardado");  
        try {
            req.book = await book.save();
            next();
        }
        catch(err){
            next(err);
        };

}



/**
 * Middleware for delete book in MongoDB.
 * @param {Object} req
 * @param {Object} res
 * @param {Function} next
 * @return {*}
 */
exports.deleteBook = async (req, res, next) => {

    if (!req.params) {
        return next();
    }

    let {id} = req.params;
    try {
        await Book.deleteOne({_id: id});
        req.response.mongo = true;
        next();
    }
    catch (err) {
        next(err);
    };

}
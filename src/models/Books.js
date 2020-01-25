// Cargamos el módulo de mongoose
var mongoose = require('mongoose');
// Usaremos los esquemas
var Schema = mongoose.Schema;
// Creamos el objeto del esquema y sus atributos
var BookSchema = new Schema({

    title: {
        type: String,
        required: [true, 'El titulo del libro es necesario']
    },

    author: {
        type: String,
        required: false
    },

    img: {
        type: String,
        required: false
    },

    keywords: [
        {
            word: String,
        }
    ],

    uploadedDate: {
        type: Date,
        default: Date.now
    },

    hidden: {
        type: Boolean,
        default: false
    },

    category: {
        type: String,
        default: "Otros"
    },

});

// BookSchema.methods.toJSON = function () {

//     let book = this
//     let bookObject = book.toObject();
//     delete bookObject.password;

//     return bookObject;
// }


// Exportamos el modelo para usarlo en otros ficheros
module.exports = mongoose.model('Book', BookSchema);
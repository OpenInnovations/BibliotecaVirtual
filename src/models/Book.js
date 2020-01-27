// Cargamos el módulo de mongoose
const mongoose = require('mongoose');
// Usaremos los esquemas
let Schema = mongoose.Schema;
// Creamos el objeto del esquema y sus atributos
let BookSchema = new Schema({

    title: {
        type: String,
        required: [true, 'El titulo del libro es necesario']
    },

    filename: {
        type: String,
        required: [true]
    },

    author: {
        type: String,
        required: false
    },

    url: {
        type: String,
        required: false
    },

    keywords: [String],

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

    sentiment: {
        polarity: Number,
        subjectivity: Number
    }

}, {collection: "Books"});

// BookSchema.methods.toJSON = function () {

//     let book = this
//     let bookObject = book.toObject();
//     delete bookObject.password;

//     return bookObject;
// }


// Exportamos el modelo para usarlo en otros ficheros
module.exports = mongoose.model('Book', BookSchema);
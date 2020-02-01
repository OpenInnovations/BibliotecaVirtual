const express = require('express');
const app = express();

app.get('/', (req, res) => {
    res.send('Transfer Controller by Jose Quispe');
});


app.use('/file',require('./file'));
app.use('/test',require('./test'));
module.exports = app;
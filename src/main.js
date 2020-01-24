const express = require('express');
const app = express();
var router = require('./routes/index.js');

app.use('/', router);

app.listen(3000, function () {
    console.log('Example app listening on port 3000!');
});
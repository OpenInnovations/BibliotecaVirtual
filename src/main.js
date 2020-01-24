const express = require('express')
const app = express()
 
app.get('/', function (req, res) {
  res.send('Transfer Controller by Jose Quispe')
})
 
app.listen(3000);
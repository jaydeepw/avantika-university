const express = require('express')
const app = express()
const port = 3000

app.get('/', (req, res) => res.send('Index page!'))

app.get('/hello', (req, res) => res.send('Hello World!'))

app.get('/bye', (req, res) => res.send('Good bye'))

app.get('/profile', function(req, res) {
    console.log("API called")
    res.send('{"name_first": "Firstname"}')
})

app.listen(port, () => console.log(`Example app listening on port ${port}!`))
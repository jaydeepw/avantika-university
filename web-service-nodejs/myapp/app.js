const express = require('express')
const app = express()
const port = 3000

app.get('/', (req, res) => res.send('Index page!'))

app.get('/hello', (req, res) => res.send('Hello World!'))

app.get('/bye', (req, res) => res.send('Good bye'))

app.get('/json', (req, res) => res.send({
    "name": "Jay"
}))

app.listen(port, () => console.log(`Example app listening on port ${port}!`))
const express = require('express')
const app = express()
const port = 3000

app.get('/', (req, res) => res.send('Index page!'))

app.get('/hello', (req, res) => res.send('Hello World!'))

app.get('/bye', (req, res) => res.send('Good bye'))

app.get('/profile', function(req, res) {
    console.log("API called")
    res.send('{"name_first": "John", "name_last": "Travolta", "email": "travolta@email.com", "city": "SF", "designation": "Acting Trainer", "contact_number": "+1-9898989898", "age": 35, "dob": "22/05/1988","dp": "https://upload.wikimedia.org/wikipedia/commons/d/d1/John_T_color_01.jpg", "bio": "Android Dev, Blockchain dev, footballer, built million-downloads app. Software consultant helping startups solve technical problems, scale products & grow."}')
})

app.listen(port, () => console.log(`Example app listening on port ${port}!`))
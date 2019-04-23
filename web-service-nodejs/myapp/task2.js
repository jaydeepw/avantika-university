const express = require('express')
const app = express()
const fs = require('fs');

// this will help parse the body of the incoming request.
var bodyParser     =    require("body-parser");
const port = 3000

// configure it to be used with ExpressJS as this is a different library than
// ExpressJS
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.get('/', (req, res) => res.send('Index page!'))

app.get('/profile', (req, res) => res.send({
    "name": "Jay",
    "birthdate": "22/05",
    "city": "Pune"
}))

app.post('/profile',function(request, response) {
    // store data received from the request
    // in the variable.
    var requestBody = request.body;

    // start writing the file now!
    fs.writeFile("profile.txt", requestBody.toString(), function(err) {

        // if error occurs, tell that to client.
        if(err) {
            response.send("Error in saving file");
            return console.log(err);
        }
    
        // if not error, then respond to the client.
        response.send("The file was saved!");
    }); 
});

app.listen(port, () => console.log(`Example app listening on port ${port}!`))
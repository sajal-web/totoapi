const express = require('express');
const app = express();
const mysql = require('mysql2');
const cors = require('cors');
const port = 8001;
require("./db/conn")
const router = require('./Routes/router');

app.get('/', (request, response) => {
    response.send("server running...");
});

// middleware
app.use(express.json());
app.use(cors());

app.use(router);


app.listen(port,() =>{
    console.log("Server start at port no "+port);
});

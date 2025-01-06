const mysql = require('mysql2');

const conn = mysql.createConnection({
    user:"root",
    host:"localhost",
    password:"Sajal@DBMS123",
    database:"todo_app_db"
});

conn.connect((err) =>{
    if(err) throw err;
    console.log("DB Connected");
});

module.exports = conn;
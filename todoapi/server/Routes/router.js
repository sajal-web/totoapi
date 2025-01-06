const express = require('express');

const router = new express.Router();
const conn = require("../db/conn");

// register user data
router.post("/create_task", (req, res) => {
    // console.log(req.body);
    const { title, description, status, due_date } = req.body;

    if (!title || !description || status === undefined || !due_date) {
        return res.status(422).json("Please fill in all the data!");
    }

    try {
        conn.query('SELECT * FROM tasks WHERE title = ?', [title], (err, result) => {
            if (err) {
                console.error("Database error:", err);
                return res.status(500).json("Internal server error.");
            }

            if (result.length > 0) {
                return res.status(422).json("This task already exists!");
            } else {
                conn.query('INSERT INTO tasks SET ?', { title, description, status, due_date }, (err, result) => {
                    if (err) {
                        console.error("Insert error:", err);
                        return res.status(500).json("Failed to create task.");
                    }
                    return res.status(201).json({ message: "Task created successfully", task: req.body });
                });
            }
        });
    } catch (error) {
        res.status(500).json(error);
    }
});


module.exports = router;
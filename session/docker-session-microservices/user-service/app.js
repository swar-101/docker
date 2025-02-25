const express = require('express');
const app = express();
app.use(express.json());

let users = [];

app.get('/users', (req, res) => {
    res.json(users);
});


app.post('/users', (req, res) => {
    const user = req.body;
    user.id = user.length + 1;
    users.push(user);
    res.status(201).json(user);
})


const PORT = process.env.PORT || 8082;

app.listen(PORT, () => {
    console.log(`User Service listening on port ${PORT}`);
});
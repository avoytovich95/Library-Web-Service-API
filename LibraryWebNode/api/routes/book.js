const express = require('express');
const router = express.Router();
var book = require('./../db/bookdb');
var view = require('./../views/bookView');


router.get('/', (req, res, next) => {
    console.log('GET: book');
    // book.getBooks((result) =>{
    //     // res.status(200).json(view.getBooks(result));
    // });
    books = book.getBooks();
    console.log(books);
});

module.exports = router;
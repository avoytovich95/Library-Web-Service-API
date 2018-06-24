const express = require('express');
const router = express.Router();
var book = require('./../db/bookdb');
var view = require('./../views/bookView');


router.get('/', (req, res, next) => {
    console.log('GET: book');
    var books = book.getBooks();
    books.then((result) => {
        res.status(200).json(view.getBooks(result))
    });
});

router.get('/:id', (req, res, next) => {
    var id = req.params.id
    console.log('GET: book id: ${id}')
})

router.post('/', (req, res, next) => {
    console.log('POST: book');
    var bookData = req.body;
    var idData = book.getCurrentId();
    idData.then((result) => {
        id = result[0].id + 1;
        book.addBook(id, bookData.title, bookData.author, bookData.bookYear);
        res.status(200).json(view.addBook(id, bookData));
    });
});

router.delete('/', (req, res, next) => {
    console.log('DELETE: book');
    var id = req.body.id;
    var bookData = book.getBook(id);
    bookData.then((result) => {
        var out = view.deleteBook(id, result[0]);
        book.deleteBook(id);
        res.status(200).json(out);
    });
});

module.exports = router;
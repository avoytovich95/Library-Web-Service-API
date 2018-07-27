const express = require('express');
const router = express.Router();
const { Book, _ } = require('../db/connection');

router.get('/', (req, res, next) => {
    console.log('GET: book');
    Book.findAll().then(books => {
        res.status(200).json(books);
    });
});

router.get('/:id', (req, res, next) => {
    var id = req.params.id;
    console.log(`GET: book ${id}`);
    Book.findById(id).then(book => {
        if (book != null)
            res.status(200).json(book);
        else
            res.status(404).json({message: 'Book id not found'});
    });
});

router.post('/', (req, res, next) => {
    console.log('POST: book');
    var bookData = req.body;
    Book.create(bookData).then(book => {
        res.location(`${req.headers.host}/library/book/${book.id}`).status(201).json(book);
    }).catch(err => {
        console.log(err);
        res.status(400).json({message: 'Missing required info'});
    });
});

router.delete('/:id', (req, res, next) => {
    var id = req.params.id;
    console.log(`DELETE: book ${id}`);
    Book.findById(id).then(book => {
        if (book != null) {
            book.destroy();
            res.status(200).json(book);
        } else   
            res.status(404).json({message: 'Book id not found'});
    });
});
router.delete('/', (req, res, next) => {
    console.log('DELETE: book');
    res.status(400).json({message: 'Missing book id'});
});

module.exports = router;
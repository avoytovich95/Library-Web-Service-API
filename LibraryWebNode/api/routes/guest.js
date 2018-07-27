const express = require('express');
const router = express.Router();
const { _, Guest } = require('../db/connection');

router.get('/', (req, res, next) => {
    console.log('GET: guest');
    Guest.findAll().then(guests => {
        res.status(200).json(guests);
    });
});

router.get('/:id', (req, res, next) => {
    var id = req.params.id;
    console.log(`Get: guest ${id}`)
    Guest.findById(id).then(guest => {
        if (guest != null)
            res.status(200).json(guest);
        else 
            res.status(400).json({message: 'Guest id not found'});
    });
});

router.post('/', (req, res, next) => {
    console.log('POST: guest');
    var guestData = req.body;
    guestData.id = generateId();
    Guest.create(guestData).then(guest => {
        res.location(`${req.headers.host}/library/guest/${guest.id}`).status(201).json(guest);
    }).catch(err => {
        console.log(err);
        res.status(400).json({message: 'Missing required info'});
    });;
});

router.delete('/:id', (req, res, next) => {
    var id = req.params.id;
    console.log(`DELETE: guest ${id}`);
    Guest.findById(id).then(guest => {
        if (guest != null) {
            guest.destroy();
            res.status(200).json(guest);
        } else
            res.status(404).json({message: 'Guest id not found'});
    });
});
router.delete('/', (req, res, next) => {
    console.log('DELETE: guest');
    res.status(400).json({message: 'Missing guest id'});
});

module.exports = router;

const LOWER = 'abcdefghijklmnopqrstuvwxyz';
const UPPER = LOWER.toUpperCase();
const DIGITS = '1234567890';
const ALPH_NUM = LOWER + UPPER + DIGITS;

function generateId() {
    var id = '';
    for (i = 0; i < 8; ++i) {
        id += ALPH_NUM.charAt(
            Math.floor(Math.random() * ALPH_NUM.length)
        );
    }
    return id;
}
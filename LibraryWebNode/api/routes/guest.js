const express = require('express');
const router = express.Router();
var guest = require('./../db/guestdb');
var view = require('./../views/guestView');

router.get('/', (req, res, next) => {
    console.log('GET: guest');
    var guests = guest.getGuests();
    guests.then((result) => {
        res.status(200).json(view.getGuests(result));
    });
});

module.exports = router;
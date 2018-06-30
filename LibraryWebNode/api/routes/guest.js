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

router.get('/:id', (req, res, next) => {
    var id = req.params.id
    console.log('GET: guest id: ' + id)
    var guestid = guest.getGuest(id)
    guest.then((result) => {
        res.status(200).json({})
    })
})

module.exports = router;
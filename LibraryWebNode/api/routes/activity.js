const express = require('express');
const router = express.Router();
const { Book, Guest } = require('../db/connection');

router.get('/latefee/:date', (req, res, next) => {
    console.log('GET: late fee');
    var date = getLateFee(req.params.date);
    res.status(200).json(date);
});
router.get('/latefee', (req, res, next) => {
    res.status(400).json({message: 'Missing date'});
});

router.patch('/checkout', (req, res, next) => {
    console.log('PATCH: check out');
    var data = req.body;
    Book.findById(data.book).then(book => {
        Guest.findById(data.guest).then(guest => {
            if (book != null && guest != null) {
                if (book.status) {
                    book.checkOut(guest.id, getDate());
                    book.save().then(() => {
                        res.status(200).json({book: book, guest: guest});
                    });
                } else
                res.status(400).json({message: 'Book unavailable'})
            } else
                res.status(404).json(bookGuestError(book, guest));
        });
    });
});

// Modify how the guest is dealt with
// Either get the id from the book and not require it
// or check to make sure they're the same
router.patch('/checkin', (req, res, next) => {
    console.log('PATCH: check in');
    var data = req.body;
    Book.findById(data.book).then(book => {
        Guest.findById(data.guest).then(guest => {
            if (book != null && guest != null) {
                if (!book.status) {
                    var date = getLateFee(book.date);
                    guest.addFee(date.fee);
                    book.checkIn();
                    book.save().then(() => {
                        guest.save().then(() => {
                            res.status(200).json({
                                book: book,
                                guest: guest,
                                date: date
                            });
                        });
                    });
                }else
                    res.status(400).json({message: 'Book already in'});
            } else
                res.status(404).json(bookGuestError(book, guest));
        });
    });
});

module.exports = router;

/**
 * Creates and returns an object with the number of days and week between
 * the date passed, and the fee of $0.25 per week
 * @param {String} date Date provided
 * @return {object} Object containing days, weeks, and total fee
 */
function getLateFee(date) {
    total = getDays(date);
    days = total % 7;
    weeks = Math.floor(total / 7);
    fee = weeks * .25;
    return { days: days, weeks: weeks, fee: fee };
}

/**
 * Gets the number of days between the date parameter and current date
 * @param {String} date Date provided
 * @return {number} Total number of days between current date and date provided
 */
function getDays(date) {
    dt1 = new Date(convert(date));
    dt2 = new Date();
    return Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), dt2.getDate()) 
            - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate()) ) 
                /(1000 * 60 * 60 * 24));
}

/**
 * Converts MMddYYYY format to MM/dd/YYYY
 * @param {String} date Date provided
 * @return {String} Converted date format
 */
function convert(date) {
    return date.slice(0,2) +
      '/' + date.slice(2,4) +
        '/' + date.slice(4);
}

/**
 * Returns a date string in the MMddYYYY format
 * @return {String} Current date
 */
function getDate() {
    var date = new Date();
    var day;
    var month;
    if (date.getMonth() + 1 < 10) month = `0${date.getMonth() + 1}`;
    else month = `${date.getMonth() + 1}`;
    if (date.getDate() < 10) day = `0${date.getDate()}`;
    else day = `${date.getDate()}`;
    return `${month}${day}${date.getFullYear()}`;
}

/**
 * Checks if the object are null, and returns a message depending
 * on the existance of the objects
 * @param {object} book  Book object
 * @param {object} guest Guest object
 */
function bookGuestError(book, guest) {
    var msg = '';
    if (book == null && guest == null) msg = 'Book and guest';
    else if (book == null) msg = 'Book';
    else if (guest == null) msg = 'Guest';
    msg += ' id not found';
    return {message: msg};
}
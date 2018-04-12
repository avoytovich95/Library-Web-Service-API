const express = require('express');
const router = express.Router();
var view = require('./../views/activityView');

router.post('/latefee', (req, res, next) => {
    const date = req.body.date;
    total = getDays(date);
    days = total % 7;
    weeks = Math.floor(total / 7);
    fee = weeks * .25;

    res.status(200).json(
        view.lateFee(days, weeks, fee)
    );
});

module.exports = router;

function getDays(date) {
    dt1 = new Date(convert(date));
    dt2 = new Date();
    return Math.floor((Date.UTC(dt2.getFullYear(), dt2.getMonth(), dt2.getDate()) 
            - Date.UTC(dt1.getFullYear(), dt1.getMonth(), dt1.getDate()) ) 
                /(1000 * 60 * 60 * 24));
}

function convert(date) {
    return date.slice(0,2) +
      '/' + date.slice(2,4) +
        '/' + date.slice(4);
}
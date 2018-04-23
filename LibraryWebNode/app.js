const express = require('express');
var bodyParser = require('body-parser');

const app = express();
app.use(bodyParser.json());

const activityRoutes = require('./api/routes/activity');
const bookRoutes = require('./api/routes/book');
const guestRoutes = require('./api/routes/guest');

app.use('/library', activityRoutes);
app.use('/library/book', bookRoutes);
app.use('/library/guest', guestRoutes);

module.exports = app;
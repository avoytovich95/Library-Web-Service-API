const express = require('express');
var bodyParser = require('body-parser');

const app = express();
app.use(bodyParser.json());

const productRoutes = require('./api/routes/products');
const orderRoutes = require('./api/routes/orders');

const activityRoutes = require('./api/routes/activity');
const bookRoutes = require('./api/routes/book');

app.use('/products', productRoutes);
app.use('/orders', orderRoutes);

app.use('/library', activityRoutes);
app.use('/library/book', bookRoutes);

module.exports = app;
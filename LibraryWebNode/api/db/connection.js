const Sequalize = require('sequelize');
const BookModel = require('./bookSQL');
const GuestModel = require('./guestSQL');

const sequelize = new Sequalize('librarywizard', 'libraryweb', '13451460v',
    { dialect: 'mysql' }
);

const Book = BookModel(sequelize, Sequalize);
const Guest = GuestModel(sequelize, Sequalize);

sequelize.sync().then(() => console.log("Database connected"));

module.exports = { Book, Guest };
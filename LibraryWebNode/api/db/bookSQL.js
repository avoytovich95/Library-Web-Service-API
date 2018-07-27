module.exports = (sequelize, type) => {
    var book = sequelize.define('book', {
        id: {
            type: type.INTEGER,
            primaryKey: true,
            autoIncrement: true
        },
        title: type.STRING,
        author: type.STRING,
        year: type.INTEGER,
        status: {type: type.BOOLEAN, defaultValue: true},
        guest: {type: type.STRING, allowNull: true},
        date: {type: type.STRING, allowNull: true}
    },
    {
        tableName: 'book',
        timestamps: false
    });

    book.prototype.checkOut = function(guest, date) {
        this.guest = guest;
        this.date = date;
        this.status = false;
    };

    book.prototype.checkIn = function() {
        this.guest = null;
        this.date = null;
        this.status = true;
    };

    return book;
};
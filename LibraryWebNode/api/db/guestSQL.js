
module.exports = (sequelize, type) => {
    var guest = sequelize.define('guest', {
        id: {
            type: type.INTEGER,
            primaryKey: true
        },
        first: type.STRING,
        last: type.STRING,
        fee: {type: type.DOUBLE, defaultValue: 0.0}
    },
    {
        tableName: 'guest',
        timestamps: false
    })

    guest.prototype.addFee = function(fee) {
        this.fee += fee;
    };

    return guest;
};
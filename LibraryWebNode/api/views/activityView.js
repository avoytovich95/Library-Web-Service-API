module.exports = {

    lateFee:(days, weeks, fee) => {
        return {
            days: days,
            weeks: weeks,
            fee: fee
        };
    }
};
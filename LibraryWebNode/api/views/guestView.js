module.exports = {

    getGuests: (guests) => {
        var array = [];
        for (i = 0; i < guests.length; i++) {
            array.push({
                id: guests[i].id,
                first: guests[i].first,
                last: guests[i].last
            });
        };
        return array;
    }
}
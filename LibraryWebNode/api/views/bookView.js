module.exports = {

    getBooks: (books) =>{
        // console.log(books);
        var array = [];
        var status;
        for (i = 0; i < books.length; i++) {
            if (books[i].checkedOut == 0) status = 'in';
            else status = 'out';
            array.push({
                id: books[i].id,
                title: books[i].title,
                author: books[i].author,
                year: books[i].year,
                status: status
            });
        }
        return array;
    }
}
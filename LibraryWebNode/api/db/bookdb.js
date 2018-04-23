const mysql = require('mysql');
const conn = mysql.createConnection({
    host: 'localhost',
    port: '3306',
    database: 'librarydb',
    user: 'libraryweb',
    password: '13451460v'
});
conn.connect((err) => {
    if (err) throw err;
    console.log('mysql books connected!');
});

module.exports = {

    getBooks: () => {
        return new Promise((resolve, reject) => {
            conn.query('SELECT * FROM books', (err, result) =>{
                if (err) reject(err);
                resolve(result);
            });
        })
        .then(res => res)
        .catch(err => {console.log(err)});
    },

    getBook: (id) => {
        return new Promise((resolve, reject) => {
            conn.query('SELECT * FROM books WHERE id = ' + id, (err, result) => {
                if (err) reject(err);
                resolve(result);
            });
        })
        .then(res => res)
        .catch(err => {console.log(err)});
    },

    addBook: (id, title, author, year) => {
        conn.query("INSERT INTO books VALUES " +
        "(" + id + ", \'" + title + "\', \'" + author + "\', " + year + ", false, \'\', \'\')",
        (err) => {if (err) console.log(err);});
    },

    deleteBook: (id) => {
        conn.query("DELETE FROM books WHERE id = " + id, (err) => {
            if (err) console.log(err);
        });
    },

    getCurrentId: () => {
        return new Promise((resolve, reject) => {
            conn.query('SELECT MAX(id) AS id FROM books', (err, result) => {
                if(err) reject(err);
                resolve(result);
            });
        })
        .then(res => res)
        .catch(err => {console.log(err);});
    }
};
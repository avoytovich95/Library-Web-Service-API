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
    console.log('mysql connected!');
});

// const {Client} = require('pg');
// const client = new Client({
//     user: 'libraryweb',
//     host: 'localhost',
//     database: 'librarydb',
//     password: '13451460v',
//     port: '3306'
// });

// const client = new cl.Client('postgres://librarydb:13451460v@localhost:3306/librarydb');
// client.connect();


module.exports = {

    // getBooks:() => {
    //     console.log('running query');
    //     res = client.query('SELECT * FROM books', (err, res) => {
    //         console.log(res);
    //     });
    // }

    // getBook:(bookId, callback) => {
    //     conn.connect((err) => {
    //         if (err) throw err;
    //         conn.query('SELECT * FROM books WHERE id = ' + bookId, (err, result, fields) => {
    //             if (err) throw err;
    //             return result;
    //         });
    //         console.log('connected');
    //     });
    // },

    // getBooks:(callback) => {
    //     var res = [];
    //     conn.query('SELECT * FROM books', (err, result, fields) => {
    //         if (err) throw err;
    //         for (i = 0; i < result.length; i++) {
    //             res.push(result[i]);
    //         }
    //         callback(res);
    //     });
    // }

    getBooks: () => {
        return new Promise((resolve, reject) =>{
            conn.query('SELECT * FROM books', (err, result) =>{
                if (err) reject(err);
                resolve(result);
            });
        })
        .then(res => res)
        .catch(err => {console.log(err)});
    }
};
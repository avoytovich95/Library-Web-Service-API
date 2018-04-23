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
    console.log('mysql guests connected!');
});

module.exports = {

    getGuests: () => {
        return new Promise((resolve, reject) => {
            conn.query('SELECT * FROM guests', (err, result) => {
                if (err) reject(err);
                resolve(result);
            });
        })
        .then(res => res)
        .catch(err => {console.log(err);});
    },

    getGuest: (id) => {
        return new Promise((resolve, reject) => {
            conn.query('SELECT * FROM guests WHERE id = ' + id, (err, result) => {
                if (err) reject(err);
                resolve(result);
            });
        })
        .then(res => res)
        .catch(err => {console.log(err);});
    }

    
    // return new Promise((resolve, reject) => {
    //     conn.query('', (err, result) => {
    //         if (err) reject(err);
    //         resolve(result);
    //     });
    // })
    // .then(res => res)
    // .catch(err => {console.log(err);});
}
class BookDB:
    def __init__(self, db):
        self.db = db

    def get_books(self):
        books = []
        self.db.query('SELECT * FROM books')
        results = self.db.use_result()
        for row in results.fetch_row(0):
            books.append(_book_object_db(row))
        return books

    def get_book(self, _id):
        self.db.query('SELECT * FROM books WHERE id = %d' % _id)
        results = self.db.use_result()
        row = results.fetch_row(0)
        return _book_object_db(row[0])

    def insert_book(self, title, author, year):
        _id = self.get_id()
        self.db.query("""
            INSERT INTO books VALUES 
            (%d, '%s', '%s', %d, false, '', '')
        """ % (_id, title, author, year))
        return _book_obj(_id, title, author, year)

    def delete_book(self, _id):
        book = self.get_book(_id)
        self.db.query('DELETE FROM books WHERE id = %d' % _id)
        return book

    def get_id(self):
        self.db.query('SELECT MAX(id) FROM books')
        result = self.db.use_result()
        rows = result.fetch_row(0)
        return int(rows[0][0]) + 1

    def get_date(self, _id):
        self.db.query('SELECT outAt FROM books WHERE id = %d' % _id)
        result = self.db.use_result()
        rows = result.fetch_row(0)
        return rows[0][0]

    def check_out(self, _id, guest, date):
        book = self.get_book(_id)
        book['guest'] = guest
        book['date'] = date
        book['status'] = False
        self.db.query("""
            UPDATE books SET 
            checkedOut = true,
            outTo = '%s',
            outAt = '%s'
        """ % (guest, date))
        return book

    def check_in(self, _id):
        book = self.get_book(_id)
        book['status'] = True
        self.db.query("""
            UPDATE books SET
            checkedOut = false,
            outTo = '',
            outAt = ''
        """)
        return book


def _book_object_db(book_row):
    obj = {
        'id': book_row[0],
        'title':  book_row[1].decode("utf-8"),
        'author': book_row[2].decode("utf-8"),
        'year': book_row[3]
    }
    if book_row[4] == 0:
        obj['status'] = True
    else:
        obj['status'] = False
    return obj


def _book_obj(_id, title, author, year):
    return {
        'id': _id,
        'title': title,
        'author': author,
        'year': year,
        'status': True
    }

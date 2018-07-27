from db import db


class BookSQL(db.Model):
    __tablename__ = 'book'
    id = db.Column('id', db.Integer, unique=True, nullable=False, primary_key=True, autoincrement=True)
    title = db.Column('title', db.String(50), nullable=False)
    author = db.Column('author', db.String(50), nullable=False)
    year = db.Column('year', db.Integer, nullable=False)
    status = db.Column('status', db.Boolean)
    guest = db.Column('guest', db.String(50))
    date = db.Column('date', db.String(50))

    def __init__(self, title, author, year):
        self.title = title
        self.author = author
        self.year = year
        self.status = False
        self.guest = ''
        self.date = ''

    def add(self):
        db.session.add(self)
        db.session.commit()

    def delete(self):
        db.session.delete(self)
        db.session.commit()

    def check_out(self, guest, date):
        self.guest = guest
        self.date = date
        self.status = False
        db.session.commit()

    def check_in(self):
        self.guest = ''
        self.date = ''
        self.status = True
        db.session.commit()

    def get_json(self):
        return {
            'id': self.id,
            'title': self.title,
            'author': self.title,
            'year': self.year,
            'status': self.status,
            'guest': self.guest,
            'date': self.guest
        }

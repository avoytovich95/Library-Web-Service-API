from flask import Flask
from flask_restful import Api
from resources.Book import Book
from resources.Guest import Guest
from resources.Task import GetDate, CheckOut, CheckIn
from db import db

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://libraryweb:13451460v@localhost:3306/librarywizard'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db.init_app(app)

api = Api(app)


api.add_resource(Book, '/library/book', '/library/book/<book_id>')
api.add_resource(Guest, '/library/guest', '/library/guest/<guest_id>')
api.add_resource(CheckOut, '/library/checkout')
api.add_resource(CheckIn, '/library/checkin')
api.add_resource(GetDate, '/library/latefee/<date>')


if __name__ == '__main__':
    app.run(port=9999, debug=True)

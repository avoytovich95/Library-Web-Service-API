from flask import Flask
from flask_restful import Api
from resources.Book import Book
from resources.Guest import Guest
from resources.Task import GetDate, CheckOut

app = Flask(__name__)
api = Api(app)


api.add_resource(Book, '/library/book')
api.add_resource(Guest, '/library/guest')
api.add_resource(CheckOut, '/library/checkout')
api.add_resource(GetDate, '/library/latefee')


if __name__ == '__main__':
    app.run(port=9999, debug=True)

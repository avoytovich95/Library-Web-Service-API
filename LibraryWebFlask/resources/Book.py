import _mysql
from flask import request, jsonify, json, Response
from flask_restful import Resource
from db.Book import BookDB

db = _mysql.connect('localhost', 'libraryweb', '', 'librarydb')
book_db = BookDB(db)


class Book(Resource):
    def get(self):
        _id = request.args.get('id')
        if _id is None:
            return jsonify(book_db.get_books())
        else:
            try:
                return jsonify(book_db.get_book(_id))
            except IndexError:
                return Response(json.dumps({'error': 'Book not present'}), 400, mimetype='application/json')

    def post(self):
        book = request.json
        book_obj = book_db.insert_book(book['title'], book['author'], book['year'])
        return Response(json.dumps(book_obj), 201, mimetype='application/json')

    def delete(self):
        _id = int(request.args.get('id'))
        try:
            return jsonify(book_db.delete_book(_id))
        except IndexError:
            return Response(json.dumps({'error': 'Book not present'}), 400, mimetype='application/json')

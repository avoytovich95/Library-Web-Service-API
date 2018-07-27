from flask import request, jsonify, json, Response
from flask_restful import Resource
from db.BookSQL import BookSQL
from resources import TYPE


class Book(Resource):
    def get(self, book_id=None):
        if book_id is None:
            results = BookSQL.query.all()
            return Response(json.dumps(results, default=lambda o: o.get_json()), 200, mimetype=TYPE)
        else:
            book = BookSQL.query.get(book_id)
            if book is not None:
                return jsonify(book.get_json())
            else:
                return Response(json.dumps({'message': 'Book not present'}), 404, mimetype=TYPE)

    def post(self):
        body = request.json
        try:
            book = BookSQL(body['title'], body['author'], body['year'])
            book.add()
            response = Response(json.dumps(book.get_json()), 201, mimetype=TYPE)
            response.headers['location'] = request.base_url + f'/{book.id}'
            return response
        except KeyError:
            return Response(json.dumps({'message': 'Missing required info'}), 422, mimetype=TYPE)

    def delete(self, book_id=None):
        if book_id is not None:
            book = BookSQL.query.get(book_id)
            if book is not None:
                book.delete()
                return jsonify(book.get_json())
            else:
                return Response(json.dumps({'message': 'Book not present'}), 404, mimetype=TYPE)
        else:
            return Response(json.dumps({'message': 'Missing book id'}), 422, mimetype=TYPE)

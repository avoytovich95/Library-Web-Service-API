import _mysql
from flask import request, jsonify, json, Response
from flask_restful import Resource
from db.Guest import GuestDB

db = _mysql.connect('localhost', 'libraryweb', '13451460v', 'librarydb')
guest_db = GuestDB(db)


class Guest(Resource):
    def get(self):
        _id = request.args.get('id')
        if _id is None:
            return jsonify(guest_db.get_guests())
        else:
            try:
                return jsonify(guest_db.get_guest(_id))
            except IndexError:
                return Response(json.dumps({'error': 'Guest not present'}), 400, mimetype='application/json')

    def post(self):
        guest = request.json
        guest_obj = guest_db.insert_guest(guest['first'], guest['last'])
        return Response(json.dumps(guest_obj), 201, mimetype='application/json')

    def delete(self):
        _id = request.args.get('id')
        try:
            return jsonify(guest_db.delete_guest(_id))
        except IndexError:
            return Response(json.dumps({'error': 'Guest not present'}), 400, mimetype='application/json')

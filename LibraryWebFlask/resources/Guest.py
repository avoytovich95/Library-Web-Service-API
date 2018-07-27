from flask import request, json, Response, redirect
from flask_restful import Resource
from db.GuestSQL import GuestSQL
from resources import TYPE


class Guest(Resource):
    def get(self, guest_id=None):
        if guest_id is None:
            results = GuestSQL.query.all()
            return Response(json.dumps(results, default=lambda o: o.get_json()), 200, mimetype=TYPE)
        else:
            guest = GuestSQL.query.get(guest_id)
            if guest is not None:
                return Response(json.dumps(guest.get_json()), 200, mimetype=TYPE)
            else:
                return Response(json.dumps({'message': 'Guest not present'}), 404, mimetype=TYPE)

    def post(self):
        body = request.json
        try:
            guest = GuestSQL(body['first'], body['last'])
            guest.add()
            response = Response(json.dumps(guest.get_json()), 201, mimetype=TYPE)
            response.headers['location'] = request.base_url + f'/{guest.id}'
            return response
        except KeyError:
            return Response(json.dumps({'message': 'Missing required info'}), 400, mimetype=TYPE)

    def delete(self, guest_id=None):
        if guest_id is not None:
            guest = GuestSQL.query.get(guest_id)
            if guest is not None:
                guest.delete()
                return Response(json.dumps(guest.get_json()), 200, mimetype=TYPE)
            else:
                return Response(json.dumps({'error': 'Guest not present'}), 404, mimetype=TYPE)
        else:
            return Response(json.dumps({'message': 'Missing guest id'}), 400, mimetype=TYPE)

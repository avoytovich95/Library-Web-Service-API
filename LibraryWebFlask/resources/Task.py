import datetime
import _mysql
from datetime import date
from flask import request, jsonify, json, Response
from flask_restful import Resource
from db.Book import BookDB
from db.Guest import GuestDB

db = _mysql.connect('localhost', 'libraryweb', '13451460v', 'librarydb')
book_db = BookDB(db)
guest_db = GuestDB(db)


class GetDate(Resource):
    def get(self):
        out_date = request.args.get('date')
        days = _get_days(out_date)
        return jsonify(_date_obj(days))


class CheckOut(Resource):
    def patch(self):
        ids = request.json
        try:
            book = book_db.get_book(ids['book'])
        except IndexError:
            return Response(json.dumps({'error': 'Book not present'}), 400, mimetype='application/json')
        try:
            guest = guest_db.get_guest(ids['guest'])
        except IndexError:
            return Response(json.dumps({'error': 'Guest not present'}), 400, mimetype='application/json')

        if book['status']:
            date = _get_date()
            out_book = book_db.check_out(book['id'], guest['id'], date)
            return jsonify({'guest': guest, 'book': out_book})
        else:
            return Response(304)


class CheckIn(Resource):
    def patch(self):
        ids = request.json
        try:
            book = book_db.get_book(ids['book'])
        except IndexError:
            return Response(json.dumps({'error': 'Book not present'}), 400, mimetype='application/json')
        try:
            guest = guest_db.get_guest(ids['guest'])
        except IndexError:
            return Response(json.dumps({'error': 'Guest not present'}), 400, mimetype='application/json')

        if not book['status']:
            date = book_db.get_date(book['id'])
            date_obj = _date_obj(_get_days(date))
            book_db.check_in(book['id'])
            in_guest = guest_db.update_guest(guest['id'], date['fee'])
            return jsonify({'guest': in_guest, 'book': book, 'date': date})
        else:
            return Response(304)


def _get_days(out_date):
    d0 = date(
        int(out_date[4:]),
        int(out_date[0:2]),
        int(out_date[2:4])
    )
    days = datetime.datetime.now().date() - d0
    return days.days


def _get_date():
    _date = datetime.datetime.now().date()
    if _date.month < 10:
        month = '0%d' % date.month
    else:
        month = '%d' % date.month
    if _date.day < 10:
        day = '0%d' % date.day
    else:
        day = '%d' % date.day
    return '%s%s%d' % (month, day, _date.year)


def _date_obj(days):
    day = days % 7
    week = int(days / 7)
    fee = week * 0.25
    return {"days": day, "weeks": week, "fee": fee}

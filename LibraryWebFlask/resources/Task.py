import datetime
from datetime import date
from flask import request, jsonify, json, Response
from flask_restful import Resource
from db.BookSQL import BookSQL
from db.GuestSQL import GuestSQL
from resources import TYPE


class GetDate(Resource, date):
    def get(self):
        # out_date = request.args.get('date')
        days = _get_days(date)
        return jsonify(_date_obj(days))


class CheckOut(Resource):
    def patch(self):
        data = request.json
        book = BookSQL.query.get(data['book'])
        guest = GuestSQL.query.get(data['guest'])
        if book is None or guest is None:
            return _book_guest_error(book, guest)
        if book.out:
            return Response(json.dumps({'error': 'Book not available'}), 400, mimetype=TYPE)

        _date = _get_date()
        book.check_out(guest.id, _date)
        return jsonify({'book': book.get_json(), 'guest': guest.get_json()})


class CheckIn(Resource):
    def patch(self):
        data = request.json
        book = BookSQL.query.get(data['book'])
        guest = GuestSQL.query.get(data['guest'])
        if book is None or guest is None:
            return _book_guest_error(book, guest)
        if not book.out:
            return Response(json.dumps({'error': 'Book already in'}), 400, mimetype=TYPE)

        _date = _date_obj(_get_days(book.date))
        book.check_in()
        guest.add_fee(_date['fee'])
        return jsonify({'guest': guest.get_json(), 'book': book.get_json(), 'date': _date})


def _get_date():
    _date = datetime.datetime.now().date()
    if _date.month < 10:
        # month = '0%d' % _date.month
        month = f'0{_date.month}'
    else:
        # month = '%d' % _date.month
        month = f'{_date.month}'
    if _date.day < 10:
        # day = '0%d' % _date.day
        day = f'0{_date.day}'
    else:
        # day = '%d' % _date.day
        day = f'{_date.day}'
    # return '%s%s%d' % (month, day, _date.year)
    return f'{month}{day}{_date.year}'


def _get_days(out_date):
    d0 = date(
        int(out_date[4:]),
        int(out_date[0:2]),
        int(out_date[2:4])
    )
    days = datetime.datetime.now().date() - d0
    return days.days


def _date_obj(days):
    day = days % 7
    week = int(days / 7)
    fee = week * 0.25
    return {"days": day, "weeks": week, "fee": fee}


def _book_guest_error(book, guest):
    msg = {}
    if book is None:
        msg['bookError'] = 'Book not present'
    if guest is None:
        msg['guestError'] = 'Guest not present'
    return Response(json.dumps(msg), 404, mimetype=TYPE)

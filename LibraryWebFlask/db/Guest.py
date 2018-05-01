from random import randint


class GuestDB:
    def __init__(self, db):
        self.db = db

    def get_guests(self):
        guests = []
        self.db.query('SELECT * FROM guests')
        results = self.db.use_result()
        for row in results.fetch_row(0):
            guests.append(_guest_obj_db(row))
        return guests

    def get_guest(self, _id):
        self.db.query("SELECT * FROM guests WHERE id = '%s'" % _id)
        results = self.db.use_result()
        row = results.fetch_row(0)
        return _guest_obj_db(row[0])

    def insert_guest(self, first, last):
        _id = _generate_id()
        self.db.query("""
            INSERT INTO guests VALUES
            ('%s', '%s', '%s', 0.0)
        """ % (_id, first, last))
        return _guest_obj(_id, first, last)

    def delete_guest(self, _id):
        guest = self.get_guest(_id)
        self.db.query("DELETE FROM guests WHERE id = '%s'" % _id)
        return guest

    def get_fee(self, _id):
        self.db.query('SELECT fee FROM guests WHERE id = %s' % _id)
        results = self.db.use_result()
        row = results.fetch_row(0)
        return float(row[0][0])

    def update_guest(self, _id, fee):
        guest_fee = self.get_fee(_id)
        guest = self.get_guest(_id)
        guest['fee'] = guest_fee + fee
        self.db.query("UPDATE guests SET fee = %f WHERE id = %s" % (guest['fee'], _id))
        return guest


LOWER = 'abcdefghijklmnopqrstuvwxyz'
UPPER = LOWER.upper()
DIGITS = '1234567890'
ALPHA_NUM = LOWER + UPPER + DIGITS


def _generate_id():
    _str = ''
    for i in range(0, 8):
        _str += ALPHA_NUM[randint(0, ALPHA_NUM.__len__() - 1)]
    return _str


def _guest_obj_db(guest):
    return {
        'id': guest[0].decode("utf-8"),
        'first': guest[1].decode("utf-8"),
        'last': guest[2].decode("utf-8")
    }


def _guest_obj(_id, first, last):
    return {
        'id': _id,
        'first': first,
        'last': last
    }

from db import db, _generate_id


class GuestSQL(db.Model):
    __tablename__ = 'guest'
    id = db.Column('id', db.String(50), unique=True, nullable=False, primary_key=True)
    first = db.Column('first', db.String(50), nullable=False)
    last = db.Column('last', db.String(50), nullable=False)
    fee = db.Column('fee', db.Float)

    def __init__(self, first, last):
        self.id = _generate_id()
        self.first = first
        self.last = last
        self.fee = 0.0

    def add(self):
        db.session.add(self)
        db.session.commit()

    def delete(self):
        db.session.delete(self)
        db.session.commit()

    def add_fee(self, amount):
        self.fee += amount
        db.session.commit()

    def get_json(self):
        return {
            'id': self.id,
            'first': self.first,
            'last': self.last,
            'fee': self.fee
        }

    # def get_json(self):
    #     return {
    #         'id': self.id,
    #         'first': self.first,
    #         'last': self.last
    #     }
    #
    # def get_json_fee(self):
    #     return {
    #         'id': self.id,
    #         'first': self.first,
    #         'last': self.last,
    #         'fee': self.fee
    #     }

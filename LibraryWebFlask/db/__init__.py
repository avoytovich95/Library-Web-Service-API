from random import randint
from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()


LOWER = 'abcdefghijklmnopqrstuvwxyz'
UPPER = LOWER.upper()
DIGITS = '1234567890'
ALPHA_NUM = LOWER + UPPER + DIGITS


def _generate_id():
    _str = ''
    for i in range(0, 8):
        _str += ALPHA_NUM[randint(0, ALPHA_NUM.__len__() - 1)]
    return _str

package librarywebgrails.Views

import librarywebgrails.Book
import librarywebgrails.Guest

/**
 * Created by Alex on 4/5/2018.
 */
class ActivityView {

    def static lateFeeView(long days, long weeks, double fee) {
        def node = [:]
        node['days'] = days
        node['weeks'] = weeks
        node['fee'] = fee
        return node
    }

    def static checkOutView(Book book, Guest guest) {
        def node = [:]
        node['status'] = "out"
        node['book'] = book.title
        node['guest'] = "${guest.firstName} ${guest.lastName}"
        node['date'] = book.outDate
        return node
    }

    def static checkOutFail(boolean book, boolean guest) {
        def node = [:]
        if (!book) node['bookError'] = "Unknown book id"
        if (!guest) node['guestError'] = "Unknown guest id"
        return node
    }

    def static checkOutFail() {
        return ['bookError': "Book unavailable"]
    }

    def static checkInView(Book book, Guest guest, long days, long weeks, String date) {
        def node = [:]
        node['status'] = "in"
        node['book'] = book.title
        node['guest'] = "${guest.firstName} ${guest.lastName}"
        node['date'] = date
        node['days'] = days
        node['weeks'] = weeks
        node['fee'] = guest.fee
        return node
    }

    def static checkInFail(boolean book, boolean  guest) {
        def node = [:]
        if (!book) node['bookError'] = "Unknown book id"
        if (!guest) node['guestError'] = "Unknown guest id"
        return node
    }

    def static checkInFail() {
        return ['bookError': "Book already in"]
    }
}

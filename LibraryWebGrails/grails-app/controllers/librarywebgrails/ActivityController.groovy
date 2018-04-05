package librarywebgrails

import grails.converters.JSON
import librarywebgrails.Views.ActivityView

import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit

class ActivityController {

    def index() { }

    def lateFee() {
        def json = JSON.parse(request.reader.text)
        def date = json['date'].toString()
        def total = getDays(date)
        long weeks = (long) (total / 7)
        long days = total % 7
        double fee = weeks * 0.25

        render ActivityView.lateFeeView(days, weeks, fee) as JSON
    }

    def checkOut() {
        def json = JSON.parse(request.reader.text)
        def guestID = json['guest'].toString()
        def bookID = (long)json['book']

        def guest = Guest.findByGuestId(guestID)
        def chkGuest
        if (guest) chkGuest = true
        else chkGuest = false

        def chkBook = Book.exists(bookID)

        if (chkGuest && chkBook) {
            def book = Book.findById(bookID)

            if (book.status) {
                def date = getDate()
                book.status = false
                book.guest = guest.guestId
                book.outDate = date

                book.save(flush: true, failOnError: true)
                render ActivityView.checkOutView(book, guest) as JSON
            }else
                render ActivityView.checkOutFail() as JSON
        }else
            render ActivityView.checkOutFail(chkBook, chkGuest) as JSON
    }

    def checkIn() {
        def json = JSON.parse(request.reader.text)
        def guestID = json['guest'].toString()
        def bookID = (long)json['book']

        def guest = Guest.findByGuestId(guestID)
        def chkGuest
        if (guest) chkGuest = true
        else chkGuest = false

        def chkBook = Book.exists(bookID)

        if (chkGuest && chkBook) {
            def book = Book.findById(bookID)

            if (!book.status) {
                def date = book.outDate
                def totalDays = getDays(date)
                long days = totalDays % 7
                long weeks = (long) (totalDays / 7)
                guest.fee += weeks * 0.25

                book.status = true
                book.outDate = null
                book.guest = null
                guest.save(flush: true, failOnError: true)
                book.save(flush: true, failOnError: true)

                render ActivityView.checkInView(book, guest, days, weeks, date) as JSON
            } else
                render ActivityView.checkInFail() as JSON
        } else
            render ActivityView.checkInFail(chkBook, chkGuest) as JSON
    }

    def static getDays(String date) {
        def dateFormat = new SimpleDateFormat("MMddyyyy")
        def checkout = dateFormat.parse(date)
        def current = new Date()
        def diff = current.time - checkout.time
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
    }

    def static getDate() {
        def cal  = Calendar.getInstance()
        def mnth = cal.get(Calendar.MONTH) + 1
        def month = ""
        if (mnth < 10) month += "0$mnth"
        else month += mnth

        def dt = cal.get(Calendar.DATE)
        def date = ""
        if (dt < 10) date += "0$dt"
        else date += dt

        return month + date + cal.get(Calendar.YEAR)
    }
}

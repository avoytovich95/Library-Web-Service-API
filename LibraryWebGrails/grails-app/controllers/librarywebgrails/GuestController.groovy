package librarywebgrails

import grails.converters.JSON
import grails.rest.RestfulController
import librarywebgrails.Views.GuestView

class GuestController extends RestfulController{

    GuestController() {
        super(Guest)
    }

    def addGuest() {
        def json = JSON.parse(request.reader.text)

        def first = json['first']
        def last = json['last']
        def id = randString(8)

        def guest = new Guest(guestId: id, firstName: first, lastName: last, fee: 0.0)
        guest.save(flush: true, failOnError: true)

        render GuestView.addGuestView(guest) as JSON
    }

    def deleteGuest() {
        def json = JSON.parse(request.reader.text)

        def id = json['id'].toString()
        def guest = Guest.findByGuestId(id)
        guest.delete(flush: true, failOnError: true)

        render GuestView.deleteGuestView(guest) as JSON
    }

    def getGuests() {
        def guests = Guest.getAll()
        render GuestView.getGuestsView(guests) as JSON
    }

    String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    String lower = upper.toLowerCase()
    String digits = "0123456789"
    String alphaNum = "$upper$lower$digits"

    def randString(int size) {
        def str = ""
        for (def i = 0; i < size; i++)
            str += alphaNum[(Math.random() * alphaNum.length()).toInteger()]
        return str
    }

}

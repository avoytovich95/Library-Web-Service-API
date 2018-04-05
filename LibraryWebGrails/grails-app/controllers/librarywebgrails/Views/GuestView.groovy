package librarywebgrails.Views

import librarywebgrails.Guest

/**
 * Created by Alex on 4/5/2018.
 */
class GuestView {

    def static addGuestView(Guest guest) {
        def node = [:]
        node['id'] = guest.guestId
        node['first'] = guest.firstName
        node['last'] = guest.lastName
        return node
    }

    def static deleteGuestView(Guest guest) {
        def node = [:]
        node['status'] = "deleted"
        node['id'] = guest.guestId
        node['first'] = guest.firstName
        node['last'] = guest.lastName
        return node
    }

    def static getGuestsView(List<Guest> guests) {
        def array = []
        for (g in guests) {
            def node = [:]
            node['id'] = g.guestId
            node['first'] = g.firstName
            node['last'] = g.lastName
            array += node
        }
        return array
    }
}

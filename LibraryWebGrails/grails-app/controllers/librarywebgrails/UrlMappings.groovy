package librarywebgrails

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        "/library/latefee"(controller: 'activity', action: 'lateFee', method: 'POST')
        "/library/checkout"(controller: 'activity', action: 'checkOut', method: 'PUT')
        "/library/checkin"(controller: 'activity', action: 'checkIn', method: 'PUT')

        "/library/guest"(controller: 'guest', action: 'addGuest', method: 'POST')
        "/library/guest"(controller: 'guest', action: 'deleteGuest', method: 'DELETE')
        "/library/guest"(controller: 'guest', action: 'getGuests', method: 'GET')

        "/library/book"(controller: 'book', action: 'addBook', method: 'POST')
        "/library/book"(controller: 'book', action: 'deleteBook', method: 'DELETE')
        "/library/book"(controller: 'book', action: 'getBooks', method: 'GET')
    }
}

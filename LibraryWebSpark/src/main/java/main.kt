import controller.*
import spark.Spark.*

/**
 * Created by Alex on 3/22/2018.
 */
fun main(args: Array<String>) {
    port(9999)

    post("/library/latefee") { req, res -> Api.lateFee(req.body()) }
    put("/library/checkin") { req, res -> Api.checkIn(req.body()) }
    put("/library/checkout") { req, res -> Api.checkOut(req.body()) }

    path("/library/guest") {
        get("") { req, res -> GuestApi.getGuests() }
        post("") { req, res -> GuestApi.addGuest(req.body()) }
        delete("") { req, res -> GuestApi.deleteGuest(req.body()) }
    }
    path("/library/book") {
        get("") { req, res -> BookApi.getBooks() }
        post("") { req, res -> BookApi.addBook(req.body()) }
        delete("") { req, res -> BookApi.deleteBook(req.body()) }
    }

    afterAfter { req, res ->  res.type("application/json") }
}
import controller.*
import spark.Spark.*

/**
 * Created by Alex on 3/22/2018.
 */
fun main(args: Array<String>) {
    port(9999)

    post("/library/latefee") { req, _ -> Api.lateFee(req.body()) }
    put("/library/checkin") { req, _ -> Api.checkIn(req.body()) }
    put("/library/checkout") { req, _ -> Api.checkOut(req.body()) }

    path("/library/guest") {
        get("") { _, _ -> GuestApi.getGuests() }
        post("") { req, _ -> GuestApi.addGuest(req.body()) }
        delete("") { req, _ -> GuestApi.deleteGuest(req.body()) }
    }
    path("/library/book") {
        get("") { _, _ -> BookApi.getBooks() }
        post("") { req, _ -> BookApi.addBook(req.body()) }
        delete("") { req, _ -> BookApi.deleteBook(req.body()) }
    }

    afterAfter { _, res ->  res.type("application/json") }
}
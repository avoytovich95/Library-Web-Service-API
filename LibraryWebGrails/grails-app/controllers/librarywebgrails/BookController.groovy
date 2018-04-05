package librarywebgrails

import grails.converters.JSON
import grails.rest.RestfulController
import librarywebgrails.Views.ActivityView
import librarywebgrails.Views.BookView

class BookController extends RestfulController{

    BookController() {
        super(Book)
    }

    def addBook() {
        def json = JSON.parse(request.reader.text)

        def title = json['title']
        def author = json['author']
        def year = (int)json['bookYear']

        def book = new Book(title: title, author: author, year: year, status: true)
        book.save(flush: true, failOnError: true)

        render BookView.addBookView(book) as JSON
    }

    def deleteBook() {
        def json = JSON.parse(request.reader.text)
        def id = (long)json['id']

        def book = Book.findById(id)
        book.delete(flush: true, failOnError: true)

        render BookView.deleteBookView(book) as JSON
    }

    def getBooks() {
        def books = Book.getAll()
        render BookView.getBooksView(books) as JSON
    }
}

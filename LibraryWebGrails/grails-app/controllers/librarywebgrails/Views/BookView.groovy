package librarywebgrails.Views

import librarywebgrails.Book

/**
 * Created by Alex on 4/5/2018.
 */
class BookView {

    def static addBookView(Book book) {
        def node = [:]
        node['id'] = book.id
        node['title'] = book.title
        node['author'] = book.author
        node['year'] = book.year
        return node
    }

    def static deleteBookView(Book book) {
        def node = [:]
        node['status'] = "deleted"
        node['id'] = book.id
        node['title'] = book.title
        node['author'] = book.author
        node['year'] = book.year
        return node
    }

    def static getBooksView(List<Book> books) {
        def array = []
        for (b in books) {
            def node = [:]
            node['id'] = b.id
            node['title'] = b.title
            node['author'] = b.author
            node['year'] = b.year
            if (b.status) node['status'] = "in"
            else node['status'] = "out"
            array += node
        }
        return array
    }
}

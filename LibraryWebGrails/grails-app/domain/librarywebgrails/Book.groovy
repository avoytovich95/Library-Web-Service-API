package librarywebgrails

class Book {

    String title
    String author
    int year
    boolean status
    String guest
    String outDate

    static constraints = {
        title blank: false
        author blank: false
        year nullable: false
        guest nullable: true
        outDate nullable: true
    }

    static mapping = {
        id generator: "org.hibernate.id.enhanced.SequenceStyleGenerator",
                params: [initial_value:1000, increment_size:1]
    }
}

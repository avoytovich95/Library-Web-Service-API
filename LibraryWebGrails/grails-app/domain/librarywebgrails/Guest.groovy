package librarywebgrails

class Guest {

    String guestId
    String firstName
    String lastName
    double fee

    static constraints = {
        guestId size: 7..9, blank: false, unique: true, primary: true
        firstName blank: false
        lastName blank: false
    }
}

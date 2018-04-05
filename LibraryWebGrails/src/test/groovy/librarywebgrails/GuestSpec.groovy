package librarywebgrails

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class GuestSpec extends Specification implements DomainUnitTest<Guest> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == false
    }
}

package mx.erick

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class CoatServiceSpec extends Specification {

    CoatService coatService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Coat(...).save(flush: true, failOnError: true)
        //new Coat(...).save(flush: true, failOnError: true)
        //Coat coat = new Coat(...).save(flush: true, failOnError: true)
        //new Coat(...).save(flush: true, failOnError: true)
        //new Coat(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //coat.id
    }

    void "test get"() {
        setupData()

        expect:
        coatService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Coat> coatList = coatService.list(max: 2, offset: 2)

        then:
        coatList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        coatService.count() == 5
    }

    void "test delete"() {
        Long coatId = setupData()

        expect:
        coatService.count() == 5

        when:
        coatService.delete(coatId)
        sessionFactory.currentSession.flush()

        then:
        coatService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Coat coat = new Coat()
        coatService.save(coat)

        then:
        coat.id != null
    }
}

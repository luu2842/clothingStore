package mx.erick

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class HatServiceSpec extends Specification {

    HatService hatService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Hat(...).save(flush: true, failOnError: true)
        //new Hat(...).save(flush: true, failOnError: true)
        //Hat hat = new Hat(...).save(flush: true, failOnError: true)
        //new Hat(...).save(flush: true, failOnError: true)
        //new Hat(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //hat.id
    }

    void "test get"() {
        setupData()

        expect:
        hatService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Hat> hatList = hatService.list(max: 2, offset: 2)

        then:
        hatList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        hatService.count() == 5
    }

    void "test delete"() {
        Long hatId = setupData()

        expect:
        hatService.count() == 5

        when:
        hatService.delete(hatId)
        sessionFactory.currentSession.flush()

        then:
        hatService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Hat hat = new Hat()
        hatService.save(hat)

        then:
        hat.id != null
    }
}

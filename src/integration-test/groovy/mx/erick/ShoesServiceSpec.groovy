package mx.erick

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ShoesServiceSpec extends Specification {

    ShoesService shoesService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Shoes(...).save(flush: true, failOnError: true)
        //new Shoes(...).save(flush: true, failOnError: true)
        //Shoes shoes = new Shoes(...).save(flush: true, failOnError: true)
        //new Shoes(...).save(flush: true, failOnError: true)
        //new Shoes(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //shoes.id
    }

    void "test get"() {
        setupData()

        expect:
        shoesService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Shoes> shoesList = shoesService.list(max: 2, offset: 2)

        then:
        shoesList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        shoesService.count() == 5
    }

    void "test delete"() {
        Long shoesId = setupData()

        expect:
        shoesService.count() == 5

        when:
        shoesService.delete(shoesId)
        sessionFactory.currentSession.flush()

        then:
        shoesService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Shoes shoes = new Shoes()
        shoesService.save(shoes)

        then:
        shoes.id != null
    }
}

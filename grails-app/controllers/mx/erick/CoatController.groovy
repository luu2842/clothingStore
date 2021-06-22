package mx.erick

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')

class CoatController {

    CoatService coatService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond coatService.list(params), model:[coatCount: coatService.count()]
    }

    def show(Long id) {
        respond coatService.get(id)
    }

    def create() {
        respond new Coat(params)
    }

    def save(Coat coat) {
        if (coat == null) {
            notFound()
            return
        }
        if (!coat.validate()) {
          respond coat.errors, view:'create'
          return
        }

        try {
            coatService.save(coat)
        } catch (ValidationException e) {
            respond coat.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'coat.label', default: 'Coat'), coat.id])
                redirect coat
            }
            '*' { respond coat, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond coatService.get(id)
    }

    def update(Coat coat) {
        if (coat == null) {
            notFound()
            return
        }
        if (!coat.validate()) {
          respond coat.errors, view:'edit'
          return
        }

        try {
            coatService.save(coat)
        } catch (ValidationException e) {
            respond coat.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'coat.label', default: 'Coat'), coat.id])
                redirect coat
            }
            '*'{ respond coat, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        coatService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'coat.label', default: 'Coat'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'coat.label', default: 'Coat'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

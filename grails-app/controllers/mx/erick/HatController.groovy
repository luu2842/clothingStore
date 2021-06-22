package mx.erick

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')

class HatController {

    HatService hatService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond hatService.list(params), model:[hatCount: hatService.count()]
    }

    def show(Long id) {
        respond hatService.get(id)
    }

    def create() {
        respond new Hat(params)
    }

    def save(Hat hat) {
        if (hat == null) {
            notFound()
            return
        }
        if (!hat.validate()) {
          respond hat.errors, view:'create'
          return
        }

        try {
            hatService.save(hat)
        } catch (ValidationException e) {
            respond hat.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'hat.label', default: 'Hat'), hat.id])
                redirect hat
            }
            '*' { respond hat, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond hatService.get(id)
    }

    def update(Hat hat) {
        if (hat == null) {
            notFound()
            return
        }
        if (!hat.validate()) {
          respond hat.errors, view:'edit'
          return
        }

        try {
            hatService.save(hat)
        } catch (ValidationException e) {
            respond hat.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'hat.label', default: 'Hat'), hat.id])
                redirect hat
            }
            '*'{ respond hat, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        hatService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'hat.label', default: 'Hat'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'hat.label', default: 'Hat'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

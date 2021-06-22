package mx.erick

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_USER')

class ShoesController {

    ShoesService shoesService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond shoesService.list(params), model:[shoesCount: shoesService.count()]
    }

    def show(Long id) {
        respond shoesService.get(id)
    }

    def create() {
        respond new Shoes(params)
    }

    def save(Shoes shoes) {
        if (shoes == null) {
            notFound()
            return
        }

        if (!shoes.validate()) {
          respond shoes.errors, view:'create'
          return
        }

        try {
            shoesService.save(shoes)
        } catch (ValidationException e) {
            respond shoes.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shoes.label', default: 'Shoes'), shoes.id])
                redirect shoes
            }
            '*' { respond shoes, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond shoesService.get(id)
    }

    def update(Shoes shoes) {
        if (shoes == null) {
            notFound()
            return
        }
        if (!shoes.validate()) {
          respond shoes.errors, view:'edit'
          return
        }
        try {
            shoesService.save(shoes)
        } catch (ValidationException e) {
            respond shoes.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'shoes.label', default: 'Shoes'), shoes.id])
                redirect shoes
            }
            '*'{ respond shoes, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        shoesService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'shoes.label', default: 'Shoes'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoes.label', default: 'Shoes'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

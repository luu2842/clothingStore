package clothingstore

import mx.erick.*
import grails.gorm.transactions.Transactional

class BootStrap {

    def init = { servletContext ->
    addTestUser()
  }

@Transactional
void addTestUser(){
      new Stock(name: "almacen 1").save()
      5.times{ number->
        new Color(name: "color${number}").save()
        new Size(name: "size${number}").save()
      }
      Role role = new Role( authority: "ROLE_USER").save()
      Role roleAdmin = new Role (authority: "ROLE_ADMIN").save()
      User user = new User(username: "eve", password:"11111111").save()
      User admin = new User(username: "admin", password:"11111111").save()
      UserRole.create user, role
      UserRole.create admin, roleAdmin 

      UserRole.withSession {
         it.flush()
         it.clear()
      }

    }
    def destroy = {
    }
}

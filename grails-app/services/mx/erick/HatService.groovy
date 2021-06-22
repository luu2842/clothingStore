package mx.erick

import grails.gorm.transactions.Transactional

@Transactional
class HatService {

    Hat get(Serializable id){
      Hat.get(id)
    }

    List<Hat> list(Map args){
      Hat.findAll(args)
    }

    Long count(){
      Hat.count()
    }

    void delete(Serializable id){
      Hat.delete(id)
    }

    Hat save(Hat hat){
      hat.save()
    }

}

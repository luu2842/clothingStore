package mx.erick

import grails.gorm.transactions.Transactional

@Transactional
class CoatService {

    Coat get(Serializable id){
      Coat.get(id)
    }

    List<Coat> list(Map args){
      Coat.findAll(args)
    }

    Long count(){
      Coat.count()
    }

    void delete(Serializable id){
      Coat.delete(id)
    }

    Coat save(Coat coat){
      coat.save()
    }

}

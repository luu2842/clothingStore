package mx.erick

import grails.gorm.transactions.Transactional

@Transactional
class ShortService {

    Short get(Serializable id){
      Short.get(id)
    }

    List<Short> list(Map args){
      Short.findAll(args)
    }

    Long count(){
      Short.count()
    }

    void delete(Serializable id){
      Short.delete(id)
    }

    Short save(Short shortt){
      shortt.save()
    }

}

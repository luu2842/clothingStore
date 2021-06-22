package mx.erick

import grails.gorm.transactions.Transactional

@Transactional
class ShoesService {


    Shoes get(Serializable id){
      Shoes.get(id)
    }

    List<Shoes> list(Map args){
      Shoes.findAll(args)
    }

    Long count(){ 
      Shoes.count() 
    }

    void delete(Serializable id){ 
      Shoes.delete(id) 
    }

    Shoes save(Shoes shoes){ 
      shoes.save() 
    }


}

package mx.erick

import groovy.transform.ToString

@ToString(includeNames=true, includeFields=true, includes="name")
class Size {

  String name

  static constraints = {
    name  unique: true 
  }

}

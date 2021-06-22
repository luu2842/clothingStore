package mx.erick
import groovy.transform.ToString

@ToString(includeNames=true, includeFields=true, include="name")

class Stock {
 String name

    static constraints = {
    name unique: true
    }
}

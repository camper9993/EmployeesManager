package data

import javafx.collections.ObservableMap
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import tornadofx.getProperty
import tornadofx.property

class Month(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Month>(Calendar)

    var date by Calendar.date
    var employee_id by Employee referencedOn Calendar.employees_id
    var encoding_id by Encoder referencedOn Calendar.encoding_id
}

class Department(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Department>(Departments)

    var name by Departments.name
}

class Employee(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Employee>(Employees)

    var name by Employees.name
    var position by Employees.position
    var identifier by Employees.identifier
    var department_id by Department referencedOn Employees.department_id
}

class Encoder(id: EntityID<Int>): IntEntity(id) {
    companion object : IntEntityClass<Encoder>(Encoding)

    var name by Encoding.type
    var description by Encoding.description
}

class Days(days: ObservableMap<Int,String>) {
    var days by property(days)
    fun daysProperty() = getProperty(Days::days)
}



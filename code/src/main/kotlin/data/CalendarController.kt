package data

import javafx.collections.ObservableList
import javafx.scene.control.Alert
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import tornadofx.Controller
import tornadofx.alert
import tornadofx.observable
import ui.DepartmentModel
import ui.EmployeeModel
import java.lang.Exception
import java.sql.Connection

class CalendarController : Controller(){
    val employee : ObservableList<EmployeeModel> by lazy {
        transaction {
            Employee.all().map {
                EmployeeModel().apply {
                    item = it
                }
            }.observable()
        }
    }

    fun getEmployees(employeeId : String) : ObservableList<EmployeeModel> {
        val res: ObservableList<EmployeeModel> by lazy {
            transaction {
                val query = Employees.innerJoin(Departments).slice(Employees.columns)
                    .select { Departments.name eq employeeId }
                val res = Employee.wrapRows(query)
                res.map {
                    EmployeeModel().apply {
                        item = it
                    }
                }.observable()
            }
        }
        return res
    }

    fun getWhatYouNeed(employeeId : Int, monthName : String) : ObservableList<Days>{
        var res : ObservableList<Days> = listOf<Days>().observable()
        var dateStart = DateTime()
        var dateEnd = DateTime()
        when (monthName) {
            "Январь" -> {
                dateStart = dateStart.withDate(2020,1,1)
                dateEnd = dateEnd.withDate(2020, 1, 31)
            }
            "Февраль" -> {
                dateStart = dateStart.withDate(2020,2,1)
                dateEnd = dateEnd.withDate(2020, 2, 29)
            }
            "Март" -> {
                dateStart = dateStart.withDate(2020,3,1)
                dateEnd = dateEnd.withDate(2020, 3, 31)
            }
            "Апрель" -> {
                dateStart = dateStart.withDate(2020,4,1)
                dateEnd = dateEnd.withDate(2020, 4, 30)
            }
            "Май" -> {
                dateStart = dateStart.withDate(2020,5,1)
                dateEnd = dateEnd.withDate(2020, 5, 31)
            }
            "Июнь" -> {
                dateStart = dateStart.withDate(2020,6,1)
                dateEnd = dateEnd.withDate(2020, 6, 30)
            }
            "Июль" -> {
                dateStart = dateStart.withDate(2020,7,1)
                dateEnd = dateEnd.withDate(2020, 7, 31)
            }
            "Август" -> {
                dateStart = dateStart.withDate(2020,8,1)
                dateEnd = dateEnd.withDate(2020, 8, 31)
            }
            "Сентябрь" -> {
                dateStart = dateStart.withDate(2020,9,1)
                dateEnd = dateEnd.withDate(2020, 9, 30)
            }
            "Октябрь" -> {
                dateStart = dateStart.withDate(2020,10,1)
                dateEnd = dateEnd.withDate(2020, 10, 31)
            }
            "Ноябрь" -> {
                dateStart = dateStart.withDate(2020,11,1)
                dateEnd = dateEnd.withDate(2020, 11, 30)
            }
            "Декабрь" -> {
                dateStart = dateStart.withDate(2020,12,1)
                dateEnd = dateEnd.withDate(2020, 12, 31)
            }
        }
        transaction {
            val query = Calendar.select { (Calendar.date greaterEq dateStart) and (Calendar.date lessEq dateEnd) and (Calendar.employees_id eq employeeId) }
            val result = Month.wrapRows(query)
            val list: MutableMap<Int, String> = mutableMapOf()
            result.forEach {
                list[it.date.dayOfMonth] = it.encoding_id.name
            }
            res = listOf(Days(list.observable())).observable()
        }
        return res
    }

    fun getDepartment() : ObservableList<DepartmentModel> {
        return departments
    }

    val departments : ObservableList<DepartmentModel> by lazy {
        transaction {
            Department.all().map {
                DepartmentModel().apply {
                    item = it
                }
            }.observable()
        }
    }

    fun addCategory(name: String) {
        transaction {
            val department = Department.new {
                this.name = name
            }
            departments.add(
                DepartmentModel().apply {
                    item = department
                })
        }
    }

    fun updateCalendar(employeeId : Int, newValue : String, day : Int, month : Int) {
        var res : Encoder? = null
        transaction {
            val getCode = Encoding.select{Encoding.type eq newValue}
            try {
                res = Encoder.wrapRows(getCode).find {
                    it.name == newValue
                }
            }
            catch (e : Exception) {
                alert(Alert.AlertType.WARNING,"Ошибка", "Неверный тип данных")
            }
            try {
                val query = Calendar.select {
                    Calendar.date eq DateTime().withDate(2020, month, day) and
                            (Calendar.employees_id eq employeeId)
                }
                Month.wrapRows(query).forEach {
                    it.encoding_id = res!!
                }
            }
            catch (e : Exception) {
                alert(Alert.AlertType.WARNING,"Ошибка", "Неверный тип данных")
            }
        }
    }


    init {
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
    }
}



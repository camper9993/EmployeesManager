package data

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import java.util.Random

class DataGeneration {
    private val random = Random()

    private fun rand(from: Int, to: Int) : Int {
        return random.nextInt(to - from) + from
    }

    fun createCalendar() {
        val dateT = DateTime().withDate(2020,1,1)
        val list = mutableListOf<Int>()
        transaction {
            addLogger(StdOutSqlLogger)
            for (i in 1 .. 366) {
                val random = rand(0, Encoder.count().toInt())
                Month.new {
                    date = dateT.plusDays(i - 1)
                    employee_id = Employee.findById(6)!!
                    encoding_id = Encoder[random]
                }
            }
        }
    }

    fun addEmployees() {
        transaction {
            Employee.new {
                name = "Пупкин Иван Васильевич"
                position = "Руководитель отдела"
                identifier = 3678
                department_id = Department.findById(1)!!
            }
            Employee.new {
                name = "Петрушин Алексей Иванович"
                position = "Стажер"
                identifier = 1522
                department_id = Department.findById(1)!!
            }
            Employee.new {
                name = "Нечипорук Алла Витальевна"
                position = "Бухгалтер"
                identifier = 9870
                department_id = Department.findById(2)!!
            }
            Employee.new {
                name = "Странина Ирина Генадьевна"
                position = "Старший экономист"
                identifier = 1289
                department_id = Department.findById(2)!!
            }
            Employee.new {
                name = "Странина Ирина Генадьевна"
                position = "Инспектор"
                identifier = 4786
                department_id = Department.findById(3)!!
            }
            Employee.new {
                name = "Странина Ирина Генадьевна"
                position = "Секретарь"
                identifier = 1488
                department_id = Department.findById(3)!!
            }
        }
    }

    fun addDepartments() {
        transaction {
            addLogger(StdOutSqlLogger)
            Department.new {
                name = "IT отдел"
            }
            Department.new {
                name = "Бухгалтерия"
            }
            Department.new {
                name = "Отдел кадров"
            }
        }
    }
}
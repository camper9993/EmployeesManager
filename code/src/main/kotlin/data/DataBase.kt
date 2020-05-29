package data

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.jodatime.date


object Departments : IntIdTable() {
        val name = varchar("name", length = 255)
    }

    object Employees : IntIdTable() {
        val name = varchar("name", length = 255)
        val position = varchar("position", length = 255)
        val identifier = integer("identifier")
        val department_id = reference("department_id", Departments)
    }

    object Calendar : IntIdTable() {
        val date = date("date")
        val employees_id = reference("employees_id", Employees)
        val encoding_id = reference("encoding_id", Encoding)

    }

    object Encoding : IntIdTable() {
        val type = varchar("type", length = 16)
        val description = varchar("description", length = 255)
    }


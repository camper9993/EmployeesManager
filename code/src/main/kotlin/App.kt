import data.Calendar
import org.jetbrains.exposed.sql.Database.Companion.connect

import tornadofx.launch
import ui.CalendarView
import ui.MyApp

fun main(args : Array<String>) {
    connect(
        "jdbc:postgresql://localhost:5432/TimeControl", driver = "org.postgresql.Driver",
        user = "Alex", password = "danilov1999"
    )
    launch<MyApp>(args)
}

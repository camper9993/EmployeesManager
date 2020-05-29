package ui

import data.*
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.scene.control.TextField
import tornadofx.*

class DepartmentModel : ItemViewModel<Department>() {
    val name = bind(Department::name)
}

class EmployeeModel : ItemViewModel<Employee>() {
    val name = bind(Employee::name)
    val identifier = bind(Employee::identifier)
    val department_id = bind(Employee::department_id)
    val position = bind(Employee::position)
}

class MonthModel : ItemViewModel<Month>() {
    val date = bind(Month::date)
    val encoding = bind(Month::encoding_id)
    val employee = bind(Month::employee_id)
}

class EncoderModel : ItemViewModel<Encoder>() {
    val name = bind(Encoder::name)
    val description = bind(Encoder::description)
}


class CalendarView : View() {
    val controller: CalendarController by inject()
    lateinit var employee: ObservableList<EmployeeModel>
    lateinit var departments : ObservableList<DepartmentModel>
    var days: ObservableList<Days> = listOf<Days>().observable()
    override val root = borderpane {
        center = tabpane {
            employee = controller.employee
            tab("Январь") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Январь")
                            }
                            for (i in 1..31) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 1)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Февраль") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Февраль")
                            }
                            for (i in 1..29) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 2)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Март") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Март")
                            }
                            for (i in 1..31) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 3)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Апрель") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Апрель")
                            }
                            for (i in 1..30) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 4)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Май") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Май")
                            }
                            for (i in 1..31) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 5)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Июнь") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Июнь")
                            }
                            for (i in 1..30) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 6)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Июль") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Июль")
                            }
                            for (i in 1..31) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 7)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Август") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Август")
                            }
                            for (i in 1..31) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 8)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Сентябрь") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Сентябрь")
                            }
                            for (i in 1..30) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 9)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Октябрь") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Октябрь")
                            }
                            for (i in 1..31) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 10)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Ноябрь") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Ноябрь")
                            }
                            for (i in 1..30) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 11)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
            tab("Декабрь") {
                tableview(employee) {
                    column("Имя", EmployeeModel::name)
                    column("Должность", EmployeeModel::position)
                    column("Табельный №", EmployeeModel::identifier)
                    rowExpander(expandOnDoubleClick = false) { employeeModel ->
                        val employeeId = employeeModel.item.id.value
                        paddingLeft = expanderColumn.width
                        tableview<Days> {
                            runAsync {
                                items = controller.getWhatYouNeed(employeeId, "Декабрь")
                            }
                            for (i in 1..31) {
                                column<Days, String>("$i") {
                                    SimpleStringProperty(itemsProperty().value[0].days.getValue(i))
                                }.makeEditable().setOnEditCommit {
                                    controller.updateCalendar(employeeId, it.newValue, i, 12)
                                }
                            }
                        }
                    }
                    smartResize()
                }
            }.isClosable = false
        }
        left = borderpane {
            top = listview<String> {
                departments = controller.departments
                departments.all {
                    items.add(it.name.value)
                }
                selectionModel.selectedItems.onChange {
                    employee.setAll(controller.getEmployees(selectedItem!!))
                }
            }
            bottom = buttonbar {
                button("Изменить список подразделений") {
                    action {
                        find(DepartmentsChange::class).openWindow()
                    }
                }
            }
        }
    }
}

class DepartmentsChange : View() {
    val calendarView : CalendarView by inject()
    val controller: DepartmentController by inject()
    var departments: ObservableList<DepartmentModel> = listOf<DepartmentModel>().observable()
    var departmentTable: TableViewEditModel<DepartmentModel> by singleAssign()
    var nameField: TextField by singleAssign()
    override val root = borderpane {
        departments = controller.departments

        center = vbox {
            buttonbar {
                button("Подтвердить изменения") {
                    action {
                        controller.commitDirty(departmentTable.items.asSequence())
                    }
                }
                button("Отменить изменения") {
                    action {
                        departmentTable.rollback()
                    }
                }
                button("Удалить") {
                    action {
                        when (val model = departmentTable.tableView.selectedItem) {
                            null -> return@action
                            else -> controller.deleteCategory(model)
                        }
                    }
                }
            }
            tableview(departments) {
                departmentTable = editModel
                enableCellEditing()
                enableDirtyTracking()

                column("Name", DepartmentModel::name).makeEditable()
            }
        }
        right = form {
            fieldset {
                field("Название") {
                    textfield {
                        nameField = this
                    }
                }
            }
            button("Создать") {
                action {
                    calendarView.controller.addCategory(nameField.text)
                    controller.addCategory(nameField.text)
                    nameField.text = ""
                }
            }
        }
    }
}

class MyApp : App(CalendarView::class)

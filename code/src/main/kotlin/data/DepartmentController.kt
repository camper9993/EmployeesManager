package data

import javafx.collections.ObservableList
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import tornadofx.Controller
import tornadofx.TableColumnDirtyState
import tornadofx.observable
import ui.DepartmentModel
import java.sql.Connection


typealias ModelToDirtyState = Map.Entry<DepartmentModel, TableColumnDirtyState<DepartmentModel>>

class DepartmentController : Controller(){

    val departments : ObservableList<DepartmentModel> by lazy {
        transaction {
            Department.all().map {
                DepartmentModel().apply {
                    item = it
                }
            }.observable()
        }
    }


    fun deleteCategory(model: DepartmentModel) {
        transaction {
            model.item.delete()
        }
        departments.remove(model)
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

    fun commitDirty(modelDirtyMappings: Sequence<ModelToDirtyState>) {
        transaction {
            modelDirtyMappings.filter { it.value.isDirty }.forEach {
                it.key.commit()
                it.value.commit()
            }
        }
    }
    
    
    
    init {
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
    }
}
package tech.danielwaiguru.todoapp

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TaskActivity : AppCompatActivity() {
    private lateinit var TaskListRecyclerView: RecyclerView
    private val listDataManager: ListDataManager = ListDataManager(this)
    companion object{
        const val INTENT_LIST_KEY = "list"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        /**
         * Reference recyclerview
         */
        val lists = listDataManager.readList()
        TaskListRecyclerView = findViewById(R.id.todos_rv)
        TaskListRecyclerView.layoutManager = LinearLayoutManager(this)
        TaskListRecyclerView.adapter = TaskListAdapter(lists)
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            showCreateTODODialog()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun showCreateTODODialog(){
        val dialogTitle = getString(R.string.dialogTitle)
        val dialogPositiveButton = getString(R.string.dialogPositiveButton)
        val dialogNegativeButton = getString(R.string.dialogNegativeButton)
        val todoEditText = EditText(this)
        todoEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_AUTO_CORRECT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
        val builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
        builder.setView(todoEditText)
        builder.setPositiveButton(dialogPositiveButton){ dialog, _ ->
            val adapter = TaskListRecyclerView.adapter as TaskListAdapter
            val data = TaskList(todoEditText.text.toString())
            listDataManager.saveList(data)
            adapter.addList(data)
            dialog.dismiss()
        }
        builder.setNegativeButton(dialogNegativeButton){_, _ ->

        }
        builder.create().show()
    }
    private fun showTaskItems(list: TaskList){
        val itemsIntent = Intent(this, AddTaskActivity::class.java)
        itemsIntent.putExtra(INTENT_LIST_KEY, list)
        startActivity(itemsIntent)
    } 
}
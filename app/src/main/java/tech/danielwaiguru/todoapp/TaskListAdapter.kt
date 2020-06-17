package tech.danielwaiguru.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TaskListAdapter(private val lists: ArrayList<TaskList>) : RecyclerView.Adapter<TaskListViewHolder>() {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_view_holder, parent, false)
        return TaskListViewHolder(itemView)
    }

    override fun getItemCount(): Int = lists.size

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        holder.todoPositionTextView.text = (position + 1).toString()
        holder.todoTextView.text = lists[position].name
    }

    fun addList(data: TaskList) {
        lists.add(data)
        notifyItemChanged(lists.lastIndex)
    }


}
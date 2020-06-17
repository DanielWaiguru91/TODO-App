package tech.danielwaiguru.todoapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val todoPositionTextView = itemView.findViewById<TextView>(R.id.itemNumber)
    val todoTextView = itemView.findViewById<TextView>(R.id.itemString)
}
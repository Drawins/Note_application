package com.example.myapplication


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.TodoItemBinding

class TodoAdapter(private val todoInterface: TodoItemInterface) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var todos = emptyList<Todo>()

    inner class TodoViewHolder(private val binding: TodoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val todoPosition = adapterPosition
                if (todoPosition != RecyclerView.NO_POSITION) {
                    val todo = todos[todoPosition]
                    val action = ListFragmentDirections.actionListFragmentToUpdateFragment(todo)
                    itemView.findNavController().navigate(action)
                }
            }
        }

        fun bindTodo(todo: Todo) {
            binding.titleTextView.text = todo.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding =
            TodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindTodo(todos[position])
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    fun setData(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    interface TodoItemInterface {
        fun onTodoClicked(todo: Todo)
    }
}
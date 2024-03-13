package com.example.myapplication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class UpdateFragment : Fragment() {

    private lateinit var todoViewModel: TodoViewModel
    private lateinit var titleEditText: EditText
    private lateinit var updateButton: Button
    private lateinit var deleteButton: Button


    private val args: UpdateFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_update, container, false)


        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]


        titleEditText = view.findViewById(R.id.title_edit_text)
        updateButton = view.findViewById(R.id.add_button)
        deleteButton = view.findViewById(R.id.delete_button)


        val todo: Todo = args.todo
        titleEditText.setText(todo.title)

        val action = UpdateFragmentDirections.actionUpdateFragmentToListFragment()

        updateButton.setOnClickListener {
            val updatedTitle = titleEditText.text.toString()
            val updatedTodo = todo.copy(title = updatedTitle)
            todoViewModel.updateTodo(updatedTodo)
            findNavController().navigate(action)
        }

        // Set click listener for the delete button
        deleteButton.setOnClickListener {
            todoViewModel.deleteTodo(todo)
            findNavController().navigate(action)
        }

        return view
    }
}
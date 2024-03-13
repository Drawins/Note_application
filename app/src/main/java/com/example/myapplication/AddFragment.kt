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

class AddFragment : Fragment() {
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var title_edit_text:EditText
    private lateinit var add_button:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        //set up add button
        add_button = view.findViewById(R.id.add_button)
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        add_button.setOnClickListener {
            title_edit_text = view.findViewById(R.id.title_edit_text)
            val title = title_edit_text.text.toString()
            val todo = Todo(0, title)
            if (title.isNotEmpty()) {
                todoViewModel.addTodo(todo)
            }
            val action = AddFragmentDirections.actionAddFragmentToListFragment()
            findNavController().navigate(action)
        }
        return  view
    }
}
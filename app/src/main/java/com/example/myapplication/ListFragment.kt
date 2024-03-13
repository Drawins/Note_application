package com.example.myapplication


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.FragmentListBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment(), TodoAdapter.TodoItemInterface {

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var floatingActionButton: FloatingActionButton
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)


        todoAdapter = TodoAdapter(this)
        recyclerView = binding.todoRecyclerView
        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // set up view-model
        todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]
        todoViewModel.readAllTodo.observe(viewLifecycleOwner) { todos ->
            todoAdapter.setData(todos)
        }

        // set up floating action button
        floatingActionButton = binding.floatingActionButton
        floatingActionButton.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onTodoClicked(todo: Todo) {
    }
}
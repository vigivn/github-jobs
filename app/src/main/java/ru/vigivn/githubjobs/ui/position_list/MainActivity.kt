package ru.vigivn.githubjobs.ui.position_list

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.vigivn.githubjobs.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = PositionListAdapter()
    private lateinit var viewModel: PositionListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PositionListViewModel::class.java)

        viewModel.positionList.observe(this, Observer{
            adapter.setData(it)
        })

        with(binding) {
            positionList.adapter = adapter
            positionList.layoutManager = LinearLayoutManager(applicationContext)
            btnSearch.setOnClickListener {
                viewModel.fetch(description.text.toString(), location.text.toString())

                // hide keyboard
                val imm = binding.root.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
            }
        }
    }
}
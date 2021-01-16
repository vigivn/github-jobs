package ru.vigivn.githubjobs.ui.position_list

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ru.vigivn.githubjobs.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = PositionListAdapter()
    private val viewModel: PositionListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.positionList.observe(this) {
            adapter.setData(it)
        }

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
package ru.vigivn.githubjobs.ui.position_details

import android.os.Bundle
import android.text.Html
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import ru.vigivn.githubjobs.databinding.ActivityPositionDetailsBinding
import ru.vigivn.githubjobs.model.Position


@AndroidEntryPoint
class PositionDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPositionDetailsBinding
    private lateinit var viewModel: PositionDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PositionDetailsViewModel::class.java)

        val id = intent.getStringExtra("id") ?: ""
        viewModel.fetch(id)

        observe()
    }

    private fun observe() {
        viewModel.positionDetails.observe(this, Observer{
            bindUI(it)
        })
    }

    private fun bindUI(position: Position) {
        with(binding) {
            title.text = position.title
            company.text = position.company
            location.text = position.location
            description.text = Html.fromHtml(
                position.description,
                Html.FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE
            ).toString()
            howToApply.text = Html.fromHtml(
                position.howToApply,
                Html.FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE
            ).toString()
            btnApply.setOnClickListener {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        }

        Picasso.get().load(position.companyLogo).into(binding.logo)
    }
}
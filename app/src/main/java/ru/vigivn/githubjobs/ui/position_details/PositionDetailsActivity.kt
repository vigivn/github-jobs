package ru.vigivn.githubjobs.ui.position_details

import android.os.Bundle
import android.text.Html
import android.widget.ScrollView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.vigivn.githubjobs.api.JobsApiClient
import ru.vigivn.githubjobs.databinding.ActivityPositionDetailsBinding


class PositionDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityPositionDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra("id") ?: ""
        val api = JobsApiClient.getClient()
        val repository = PositionDetailsRepository(api)

        val viewModel: PositionDetailsViewModel by viewModels {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return PositionDetailsViewModel(repository, id) as T
                }
            }
        }

        viewModel.positionDetails.observe(this) {
            with(binding) {
                title.text = it.title
                company.text = it.company
                location.text = it.location
                description.text = Html.fromHtml(
                    it.description,
                    Html.FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE
                ).toString()
                howToApply.text = Html.fromHtml(
                    it.howToApply,
                    Html.FROM_HTML_SEPARATOR_LINE_BREAK_BLOCKQUOTE
                ).toString()
                btnApply.setOnClickListener {
                    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                }
            }

            Picasso.get().load(it.companyLogo).into(binding.logo)
        }
    }
}
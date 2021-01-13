package ru.vigivn.githubjobs

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vigivn.githubjobs.api.JobsApiClient
import ru.vigivn.githubjobs.databinding.ActivityMainBinding
import ru.vigivn.githubjobs.ui.position_details.PositionDetailsActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(applicationContext, PositionDetailsActivity::class.java)
        intent.putExtra("id", "cb287f27-8e08-4677-88e5-4d025a1fee08")
        applicationContext.startActivity(intent)
    }
}
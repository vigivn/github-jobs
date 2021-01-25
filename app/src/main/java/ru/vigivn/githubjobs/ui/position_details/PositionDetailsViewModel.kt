package ru.vigivn.githubjobs.ui.position_details

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vigivn.githubjobs.model.Position
import ru.vigivn.githubjobs.repository.Repository

class PositionDetailsViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {
    val positionDetails = MutableLiveData<Position>()
    private val compositeDisposable = CompositeDisposable()

    fun fetch(positionId: String) {
        compositeDisposable.add(
            repository.fetchPositionDetails(positionId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    positionDetails.postValue(it)
                }, {
                    Log.e(PositionDetailsViewModel::class.java.simpleName, it.message, it)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
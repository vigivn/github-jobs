package ru.vigivn.githubjobs.ui.position_list

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vigivn.githubjobs.model.Position
import ru.vigivn.githubjobs.repository.Repository

class PositionListViewModel @ViewModelInject constructor(
    private val repository: Repository
) : ViewModel() {
    val positionList = MutableLiveData<List<Position>>()
    private val compositeDisposable = CompositeDisposable()

    fun fetch(description: String = "", location: String = "") {
        compositeDisposable.add(
            repository.fetchPositionList(description, location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    positionList.postValue(it)
                }, {
                    Log.e(PositionListViewModel::class.java.simpleName, it.message, it)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
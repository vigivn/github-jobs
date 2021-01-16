package ru.vigivn.githubjobs.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vigivn.githubjobs.api.JobsApiInterface
import ru.vigivn.githubjobs.model.Position

class PositionListData(
    private val api: JobsApiInterface,
    private val compositeDisposable: CompositeDisposable
) {
    val positionList = MutableLiveData<List<Position>>()

    fun fetch(description: String, location: String) {
        compositeDisposable.add(
            api.getPositions(description, location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    positionList.postValue(it)
                }, {
                    Log.e(PositionListData::class.java.simpleName, it.message, it)
                })
        )
    }
}
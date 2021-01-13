package ru.vigivn.githubjobs.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.vigivn.githubjobs.api.JobsApiInterface
import ru.vigivn.githubjobs.model.Position

class PositionDetailsData(
    private val api: JobsApiInterface,
    private val compositeDisposable: CompositeDisposable
) {
    val positionDetails = MutableLiveData<Position>()

    fun fetch(id: String) {
        compositeDisposable.add(
            api.getPosition(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    positionDetails.postValue(it)
                }, {
                    Log.e(PositionDetailsData::class.java.simpleName, it.message, it)
                })
        )
    }
}
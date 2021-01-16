package ru.vigivn.githubjobs.ui.position_list


import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class PositionListViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val positionListRepository = PositionListRepository(compositeDisposable)

    val positionList by lazy {
        fetch()
        positionListRepository.positionList
    }

    fun fetch(description: String = "", location: String = "") {
        positionListRepository.fetch(description, location)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
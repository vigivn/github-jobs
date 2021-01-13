package ru.vigivn.githubjobs.ui.position_details

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class PositionDetailsViewModel(
    private val positionDetailsRepository: PositionDetailsRepository,
    private val id: String
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val positionDetails by lazy {
        positionDetailsRepository.fetchPositionDetails(compositeDisposable, id)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
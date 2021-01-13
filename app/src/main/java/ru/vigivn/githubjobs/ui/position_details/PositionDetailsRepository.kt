package ru.vigivn.githubjobs.ui.position_details

import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import ru.vigivn.githubjobs.api.JobsApiInterface
import ru.vigivn.githubjobs.model.Position
import ru.vigivn.githubjobs.repository.PositionDetailsData

class PositionDetailsRepository(
    private val api: JobsApiInterface
) {
    lateinit var positionDetailsData: PositionDetailsData

    fun fetchPositionDetails(compositeDisposable: CompositeDisposable, id: String): LiveData<Position> {
        positionDetailsData = PositionDetailsData(api, compositeDisposable)
        positionDetailsData.fetch(id)
        return positionDetailsData.positionDetails
    }
}
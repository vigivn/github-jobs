package ru.vigivn.githubjobs.ui.position_list

import androidx.lifecycle.LiveData
import io.reactivex.disposables.CompositeDisposable
import ru.vigivn.githubjobs.api.JobsApiClient
import ru.vigivn.githubjobs.api.JobsApiInterface
import ru.vigivn.githubjobs.model.Position
import ru.vigivn.githubjobs.repository.PositionListData

class PositionListRepository (
    compositeDisposable: CompositeDisposable
) {
    private val api = JobsApiClient.getClient()
    private val positionListData = PositionListData(api, compositeDisposable)

    val positionList: LiveData<List<Position>> = positionListData.positionList

    fun fetch(description: String, location: String) {
        positionListData.fetch(description, location)
    }
}
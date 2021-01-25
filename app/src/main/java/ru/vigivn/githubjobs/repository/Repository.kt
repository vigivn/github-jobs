package ru.vigivn.githubjobs.repository

import io.reactivex.Single
import ru.vigivn.githubjobs.api.JobsApiService
import ru.vigivn.githubjobs.model.Position
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: JobsApiService
) {
    fun fetchPositionList(description: String, location: String): Single<List<Position>> {
        return api.getPositions(description, location)
    }

    fun fetchPositionDetails(positionId: String): Single<Position> {
        return api.getPosition(positionId)
    }
}
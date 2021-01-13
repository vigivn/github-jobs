package ru.vigivn.githubjobs.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.vigivn.githubjobs.model.Position

interface JobsApiInterface {
    @GET("positions.json")
    fun getPositions(@Query("description") description: String): Single<List<Position>>

    @GET("positions/{position_id}.json")
    fun getPosition(@Path("position_id") id: String): Single<Position>
}
package com.coruptiaucide.vavedem.api.webservice

import com.coruptiaucide.vavedem.api.meta.MetaDate
import com.coruptiaucide.vavedem.api.meta.ResponseWithMeta
import com.coruptiaucide.vavedem.api.model.body.BodyUserRegisterLogin
import com.coruptiaucide.vavedem.api.model.data.DataCreateSession
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import rx.Observable

/**
 * Created by Tiberiu on 6/19/2016.
 */


internal interface SessionService {

    @POST("loginEmail")
    fun login( @Body user: BodyUserRegisterLogin): Observable<ResponseWithMeta<DataCreateSession, MetaDate>>

    @POST("registerEmail")
    fun register( @Body user: BodyUserRegisterLogin): Observable<ResponseWithMeta<DataCreateSession, MetaDate>>

}
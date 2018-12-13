package com.asykurkhamid.kickball.retrofit

import com.asykurkhamid.kickball.BuildConfig
import retrofit2.Retrofit
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


open class ServiceFactory{

    private fun retrofit(): Retrofit{
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL + BuildConfig.TSDB_API_KEY)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun getInitInstance() : Services {
        return retrofit().create(Services::class.java)
    }


}
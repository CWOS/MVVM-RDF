package com.cqrd.mrt.mcf.di


import android.content.Context
import com.cw.rdf.core.ext.converterFactory
import com.cw.rdf.core.ext.getConverterFactory
import com.cw.rdf.core.ext.getInterceptor
import com.cw.rdf.core.ext.interceptor
import com.cw.rdf.core.http.DefaultHttpExceptionHandler
import com.cw.rdf.core.http.cookie.HttpCookieJar
import com.cw.rdf.core.http.RetrofitConfig
import com.google.gson.Gson
import okhttp3.CookieJar
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named

/**
 *
 * @Description:
 * @Author: chengminghui
 * @CreateDate：2020/8/25 7:13 AM
 *
 */
val retrofitModule = module {
    single { provideGson() }
    single { provideCookieJar() }
//    single(named(NAME_BASE_URL)) {
//        "baseUrl"
//    }
//    single(named(NAME_DEBUG)) {
//        false
//    }


    single { getHttpLogInterceptor(get(named(NAME_DEBUG))) }
    single { provideRetrofitBuilder(get(named(NAME_BASE_URL)), get(), getConverterFactory()) }
    single { provideRetrofit(get()) }

    single { provideRetrofitConfig(get(),get(),get()) }
    single { provideOkhttpClientBuild(get(), get(), getInterceptor()) }
    single { provideOkhttpClient(get()) }


    //inject okHttp interceptor
    interceptor(1){
        Interceptor { chain -> chain.proceed(chain.request()) }
    }

    //inject retrofit Converter.Factory
    converterFactory(1){
        GsonConverterFactory.create(get())
    }

}


const val NAME_BASE_URL = "baseUrl"
const val NAME_DEBUG = "httpDebug"


fun provideOkhttpClientBuild(
    interceptor: HttpLoggingInterceptor, config: RetrofitConfig,
    interceptors: Map<Int, Interceptor>
): OkHttpClient.Builder {
    val builder = OkHttpClient.Builder()
        .addNetworkInterceptor(interceptor)
        .cookieJar(config.cookieJar!!)
    interceptors.keys.sortedByDescending { it }
        .forEach {
            interceptors[it]?.let { inter ->
                builder.addInterceptor(inter)
            }
        }
    config.callTimeout?.let { builder.callTimeout(it, TimeUnit.SECONDS) }
    config.connectTimeout?.let { builder.connectTimeout(it, TimeUnit.SECONDS) }
    config.readTimeout?.let { builder.readTimeout(it, TimeUnit.SECONDS) }
    config.writeTimeout?.let { builder.writeTimeout(it, TimeUnit.SECONDS) }
    return builder
}


fun provideOkhttpClient(builder: OkHttpClient.Builder): OkHttpClient {
    return builder.build()
}


fun getHttpLogInterceptor(@Named(NAME_DEBUG) isDebug: Boolean): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    val level = if (isDebug)
        HttpLoggingInterceptor.Level.BODY
    else
        HttpLoggingInterceptor.Level.NONE
    interceptor.level = level
    return interceptor
}

fun provideRetrofitBuilder(
    @Named(NAME_BASE_URL) baseUrl: String,
    client: OkHttpClient,
    converterFactories: Map<Int, Converter.Factory>
): Retrofit.Builder {

    val builder = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)

    converterFactories.keys.sortedByDescending { it }
        .forEach {
            converterFactories[it]?.let { conv ->
                builder.addConverterFactory(conv)
            }
        }
    return builder
}

fun provideRetrofit(builder: Retrofit.Builder): Retrofit {
    return builder.build()
}


fun provideCookieJar(): CookieJar {
    return HttpCookieJar()
}

fun provideGson(): Gson {
    return Gson()
}

fun provideRetrofitConfig(context: Context,cookieJar: CookieJar,gson: Gson):RetrofitConfig{
    RetrofitConfig.cookieJar = RetrofitConfig.cookieJar ?: cookieJar
    RetrofitConfig.gson = RetrofitConfig.gson ?: gson
    RetrofitConfig.httpExceptionHandler = RetrofitConfig.httpExceptionHandler ?: DefaultHttpExceptionHandler(context)
    return RetrofitConfig
}
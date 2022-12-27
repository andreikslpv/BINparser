package com.andreikslpv.binparser.data.binrepo

import com.andreikslpv.binparser.data.BuildConfig
import com.andreikslpv.binparser.data.binrepo.SslUtils.getTrustAllHostsSSLSocketFactory
import com.andreikslpv.binparser.data.binrepo.dto.BinDataDto
import com.andreikslpv.binparser.domain.ApiCallback
import com.andreikslpv.binparser.domain.BinRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class BinRepositoryImpl : BinRepository {
    private var retrofitServiceGetBinData: BinInterfaceGetBinData

    init {
        //Создаём кастомный клиент
        val okHttpClient = OkHttpClient.Builder()
        //Настраиваем таймауты для медленного интернета
            .callTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            //Добавляем логгер
            .addInterceptor(HttpLoggingInterceptor().apply {
                if (BuildConfig.DEBUG) {
                    level = HttpLoggingInterceptor.Level.BASIC
                }
            })
        // Для исправления ошибки:
        // "HTTP FAILED: javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException:
        // Trust anchor for certification path not found."
        // делаю так, как нельзя делать:
        // доверяю всем хостам (способ с созданием Network Security Configuration file не помог)
        getTrustAllHostsSSLSocketFactory()?.let {
            okHttpClient.sslSocketFactory(it)
        }

        //Создаем Ретрофит
        val retrofit = Retrofit.Builder()
            //Указываем базовый URL из констант
            .baseUrl(BinConstants.BASE_URL)
            //Добавляем конвертер
            .addConverterFactory(GsonConverterFactory.create())
            //Добавляем кастомный клиент
            .client(okHttpClient.build())
            .build()
        //Создаем сам сервис с методами для запросов
        retrofitServiceGetBinData = retrofit.create(BinInterfaceGetBinData::class.java)
    }

    override fun getBinData(binNumber: String, callback: ApiCallback) {
        retrofitServiceGetBinData.getBinData(binNumber).enqueue(object : Callback<BinDataDto> {
            override fun onResponse(
                call: Call<BinDataDto>,
                response: Response<BinDataDto>
            ) {
                when(response.code()) {
                    //При успехе мы вызываем метод onSuccess и передаем в этот коллбэк список фильмов
                    BinConstants.SUCCESS_RESPONSE -> callback.onSuccess(
                        BinDataDtoToModel.map(
                            response.body()
                        )
                    )
                    else -> callback.onFailure(response.code().toString())
                }
            }

            override fun onFailure(call: Call<BinDataDto>, t: Throwable) {
                //В случае провала вызываем другой метод коллбека
                callback.onFailure(t.message?:"")
            }
        })
    }
}
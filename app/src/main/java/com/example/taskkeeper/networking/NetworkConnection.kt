package com.example.taskkeeper.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.taskkeeper.exceptions.NoInternetException
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class NetworkConnection @Inject constructor(
    @ApplicationContext context: Context
) : Interceptor {

    //needed for getting the system services functions
    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isConnectionAvailable())
            throw NoInternetException("The internet is not available!")

        return chain.proceed(chain.request())

    }

    private fun isConnectionAvailable(): Boolean {

        //used for storing a boolean regarding the availability of internet
        var result = false

        val connectivityManager =
                applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when{
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }
}
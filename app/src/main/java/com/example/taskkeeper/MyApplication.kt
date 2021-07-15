package com.example.taskkeeper

import android.app.Application
import com.example.taskkeeper.utils.Constants
import dagger.hilt.android.HiltAndroidApp
import one.space.networking.core.SpoClient
import one.space.networking.core.SpoClientConfig
import one.space.networking.core.model.SpoAppCredentials

@HiltAndroidApp
class MyApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        SpoClient.createDefault(
            spoClientConfig = SpoClientConfig(
                context = this,
                baseUrl = Constants.BASE_URL_SPO,
                scope = Constants.SCOPE_KEY,
                appName = Constants.APP_NAME, /* optional */
                appVersion = Constants.APP_VERSION, /* optional */
                appCredentials = SpoAppCredentials.fromOAuthClientSecret(
                    clientId = Constants.CLIENT_ID,
                    clientSecret = Constants.CLIENT_SECRET
                )
            )
        )
    }
}
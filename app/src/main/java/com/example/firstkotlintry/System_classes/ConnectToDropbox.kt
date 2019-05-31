package com.example.firstkotlintry.System_classes

import android.os.AsyncTask
import com.dropbox.core.DbxException
import com.dropbox.core.DbxRequestConfig
import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.users.FullAccount
import com.example.firstkotlintry.System_classes.Global.GlobalVariables

class ConnectToDropbox : AsyncTask<String, Void, FullAccount>() {
    companion object {
        private val ACCESS_TOKEN = "sVAYu9CmrfAAAAAAAAAAO2G6RNfKWZgAHU9JviPLWL1rbqC7X8c0b01GphWOslKs"
    }

    @Throws(DbxException::class)
    fun connectToDropbox() {
        val config = DbxRequestConfig.newBuilder("dropbox/FirstKotlinTry").build()
        val client = DbxClientV2(config, ACCESS_TOKEN)
        try {
            val account = client.users().currentAccount
            println(account.name.displayName)

            val globalVariables = GlobalVariables()
            globalVariables.setgDropboxClient(client)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    override fun doInBackground(vararg strings: String): FullAccount? {
        return null
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }
}
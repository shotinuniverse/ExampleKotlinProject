package com.example.firstkotlintry.Working_with_JSON

import android.content.Context
import android.os.AsyncTask
import com.dropbox.core.DbxException
import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.files.FileMetadata
import com.dropbox.core.v2.files.Metadata
import com.dropbox.core.v2.users.FullAccount
import java.io.FileOutputStream
import java.io.IOException

class LoadJSONDatafile: AsyncTask<String, Void, FullAccount>() {
    var client: DbxClientV2? = null

    @Throws(DbxException::class, IOException::class)
    fun loadJSONFileFromDropbox(context: Context) {
        val uploadUnloadJSONCommon = UploadUnloadJSONCommon()
        val filenameforupload = uploadUnloadJSONCommon.getFilenameForUpload()

        val result = client!!.files().listFolder("")
        var gotMetadataofDropbox: Metadata? = null
        for (i in 0 until result.entries.size) {
            for (metadata in result.entries) {
                //ADDING PATH FROM SETTING'S USER
                //ADDING PREFIXS FROM SETTING'S USER
                if (metadata.name == filenameforupload) {
                    gotMetadataofDropbox = metadata
                }
            }
        }
        //THERE MAY GET DATE OF CHANGE FILE
        val metadata = gotMetadataofDropbox as FileMetadata?
        val path = context.getDir("assets", 0).path + "/JsonReadFile.json"

        val out = FileOutputStream(path)

        client!!.files().download(metadata!!.pathLower).download(out)
    }

    override fun doInBackground(vararg strings: String): FullAccount? {
        return null
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }
}

package com.example.firstkotlintry.Working_with_JSON

import android.content.Context
import android.os.AsyncTask
import com.dropbox.core.DbxException
import com.dropbox.core.v2.DbxClientV2
import com.dropbox.core.v2.files.FileMetadata
import com.dropbox.core.v2.files.Metadata
import com.dropbox.core.v2.files.WriteMode
import com.dropbox.core.v2.users.FullAccount
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

class UnloadJSONDatafile : AsyncTask<String, Void, FullAccount>() {
    var ReportName: String? = null
    var ReportParams: String? = null

    var DirectoryName: String? = null
    var DirectoryParams: String? = null

    var client: DbxClientV2? = null

    @Throws(IOException::class, DbxException::class)
    fun mainUnloadJSON(context: Context, actionName: String) {
        if (actionName === "Report") {
            unloadReportData(context)
        }
        else if (actionName == "Directory"){
            unloadDirectoryData(context)
        }
    }

    @Throws(DbxException::class, IOException::class)
    fun unloadReportData(context: Context) {
        val path = context.getDir("assets", 0).path + "/JsonWriteFile.json"
        val uploadUnloadJSONCommon = UploadUnloadJSONCommon()
        val filenameforunload = uploadUnloadJSONCommon.getFilenameForUnload()

        try {
            val myFile = File(context.getDir("assets", 0).path + "/JsonWriteFile.json")
            myFile.createNewFile()
            val text = "{\n" +
                    "\t\"report_name\": \"" + ReportName + "\",\n" +
                    "\t\"report_params\": \"" + ReportParams + "\",\n" +
                    "}"
            val outputStream = FileOutputStream(myFile)
            outputStream.write(text.toByteArray())
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val fileInputStream = FileInputStream(path)

        client!!.files().uploadBuilder("/$filenameforunload").withMode(WriteMode.OVERWRITE)
            .uploadAndFinish(fileInputStream)
    }

    @Throws(DbxException::class, IOException::class)
    fun unloadDirectoryData(context: Context) {
        val path = context.getDir("assets", 0).path + "/JsonWriteFile.json"
        val uploadUnloadJSONCommon = UploadUnloadJSONCommon()
        val filenameforunload = uploadUnloadJSONCommon.getFilenameForUnload()

        try {
            val myFile = File(context.getDir("assets", 0).path + "/JsonWriteFile.json")
            myFile.createNewFile()
            val text = "{\n" +
                    "\t\"directory_name\": \"" + DirectoryName + "\",\n" +
                    "\t\"directory_params\": \"" + DirectoryParams + "\",\n" +
                    "}"
            val outputStream = FileOutputStream(myFile)
            outputStream.write(text.toByteArray())
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val fileInputStream = FileInputStream(path)

        client!!.files().uploadBuilder("/$filenameforunload").withMode(WriteMode.OVERWRITE)
            .uploadAndFinish(fileInputStream)
    }

    @Throws(DbxException::class)
    fun checkFileUpdate() {
        val uploadUnloadJSONCommon = UploadUnloadJSONCommon()
        val filenameforupload = uploadUnloadJSONCommon.getFilenameForUpload()

        var g = 0

        var lastTimeUpdateFile: Date? = null
        var newTimeUpdateFile: Date? = null

        while (g == 0) {
            val result = client!!.files().listFolder("")
            var gotMetadataofDropbox: Metadata? = null
            for (i in 0 until result.entries.size) {
                for (metadata in result.entries) {
                    if (metadata.name == filenameforupload) {
                        gotMetadataofDropbox = metadata
                        if (lastTimeUpdateFile == null) {
                            lastTimeUpdateFile = (gotMetadataofDropbox as FileMetadata).clientModified
                        } else {
                            newTimeUpdateFile = (gotMetadataofDropbox as FileMetadata).clientModified
                        }
                    }
                }
            }

            if (lastTimeUpdateFile != newTimeUpdateFile) {
                g = 1
            }
        }
    }

    override fun doInBackground(vararg strings: String): FullAccount? {
        return null
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }
}

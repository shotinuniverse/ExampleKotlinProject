package com.example.firstkotlintry.System_classes.Global

import android.database.sqlite.SQLiteDatabase
import com.dropbox.core.v2.DbxClientV2

class GlobalVariables{

    companion object {
        lateinit var gDBPath: String

        lateinit var gDBName:               String
        lateinit var gDatabase:             SQLiteDatabase
        lateinit var gDropboxClient:        DbxClientV2
        lateinit var gPrefThisHost:         String
        lateinit var gPrefCorHost:          String
        lateinit var gCurrentFormareReport: String
        var gDBVersion:                     Int = 0
    }

    fun setgDBPath(temp: String){
        gDBPath = temp
    }

    fun getgDBPath(): String?{
        return gDBPath
    }

    fun setgDBName(tempdbname: String){
        gDBName = tempdbname
    }

    fun getgDBName(): String{
        return gDBName
    }

    fun setgDBVersion(temp: Int){
        gDBVersion = temp
    }

    fun getgDBVersion(): Int{
        return gDBVersion
    }

    fun setgDatabase(tempdatabase: SQLiteDatabase){
        gDatabase = tempdatabase
    }

    fun getgDatabase(): SQLiteDatabase {
        return gDatabase
    }

    fun setgDropboxClient(tempDropboxClient: DbxClientV2){
        gDropboxClient = tempDropboxClient
    }

    fun getgDropboxClient(): DbxClientV2?{
        return gDropboxClient
    }

    fun setgPrefThisHost(tempprefthishost: String){
        gPrefThisHost = tempprefthishost
    }

    fun getgPrefThisHost():String? {
        return gPrefThisHost
    }

    fun setgPrefCorHost(tempprefcorhost: String){
        gPrefCorHost = tempprefcorhost
    }

    fun getgPrefCorHost():String? {
        return gPrefCorHost
    }

    fun setgCurrentFormareReport(tempcurrentFormareReport: String){
        gCurrentFormareReport = tempcurrentFormareReport
    }

    fun getgCurrentFormareReport(): String?{
        return gCurrentFormareReport
    }
}
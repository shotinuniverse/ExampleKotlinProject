package com.example.firstkotlintry.System_classes

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.firstkotlintry.System_classes.Global.GlobalStaticInterfaces_forSQL
import java.io.FileOutputStream
import java.io.IOException
import android.database.sqlite.SQLiteException as SQLiteException1

class ConnectDatabase(context: Context, name: String?,
    factory: SQLiteDatabase.CursorFactory?, version: Int) :
SQLiteOpenHelper(context, DATABASE_NAME,
        factory, DATABASE_VERSION), GlobalStaticInterfaces_forSQL {

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME: String = "AndroidDatabase.db"
        lateinit var DATABASE_PATH: String
    }

    override fun setterForVariables(context: Context) {
        super.setterForVariables(context)
    }

    override fun openDataBase() {
        super.openDataBase()
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    override fun onCreate(db: SQLiteDatabase) {

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

    }

    @Throws(IOException::class)
    fun createDataBase(context: Context) {
        var dbExist = checkDataBase()

        if (dbExist) {

        } else {
            this.readableDatabase

            try {
                copyDataBase(context)
            } catch (e: IOException) {
                throw Error("Error copying database")
            }

        }
    }

    fun checkDataBase(): Boolean {

        var checkDB: SQLiteDatabase? = null

        try {
            val myPath = DATABASE_PATH + DATABASE_NAME
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY)

        } catch (e: SQLiteException1) {

        }

        checkDB?.close()
        return if (checkDB != null) true else false
    }

    @Throws(IOException::class)
    private fun copyDataBase(context: Context) {
        val myInput = context.assets.open("databases/$DATABASE_NAME")

        val outFileName = DATABASE_PATH + DATABASE_NAME

        val myOutput = FileOutputStream(outFileName)

        val buffer = ByteArray(1024)
        var length: Int = myInput.read(buffer)
        while (length > 0){
            myOutput.write(buffer, 0, length)
        }

        myOutput.flush()
        myOutput.close()
        myInput.close()
    }

//    @Throws(SQLException::class)
//    fun openDataBase() {
//
//        val myPath = DATABASE_PATH + DATABASE_NAME
//        val db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE)
//
//        val globalVariables = GlobalVariables()
//        globalVariables.setgDatabase(db)
//    }
}


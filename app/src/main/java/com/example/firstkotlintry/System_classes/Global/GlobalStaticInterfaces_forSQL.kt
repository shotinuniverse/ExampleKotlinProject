package com.example.firstkotlintry.System_classes.Global

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.firstkotlintry.System_classes.ConnectDatabase
import java.sql.SQLException

interface GlobalStaticInterfaces_forSQL{
    fun setterForVariables(context: Context){
        val globalVariables = GlobalVariables()
        if (android.os.Build.VERSION.SDK_INT >= 4.2) {
            ConnectDatabase.DATABASE_PATH = context.getApplicationInfo().dataDir + "/databases/"
        } else {
            ConnectDatabase.DATABASE_PATH = "/data/data/" + context.getPackageName() + "/databases/"
        }
        globalVariables.setgDBPath(ConnectDatabase.DATABASE_PATH)
        globalVariables.setgDBName(ConnectDatabase.DATABASE_NAME)
        globalVariables.setgDBVersion(ConnectDatabase.DATABASE_VERSION)
    }

    @Throws(SQLException::class)
    fun openDataBase() {

        val myPath = ConnectDatabase.DATABASE_PATH + ConnectDatabase.DATABASE_NAME
        val db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE)

        val globalVariables = GlobalVariables()
        globalVariables.setgDatabase(db)
    }

    fun dropTable(db: SQLiteDatabase, table_name: String){
        db.execSQL("DROP TABLE IF EXISTS $table_name")
    }
}
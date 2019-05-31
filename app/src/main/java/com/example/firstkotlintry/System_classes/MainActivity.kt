package com.example.firstkotlintry.System_classes

import android.database.SQLException
import android.os.Bundle
import android.os.StrictMode
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.dropbox.core.DbxException
import com.example.firstkotlintry.R
import com.example.firstkotlintry.System_classes.Global.GlobalVariables
import com.example.firstkotlintry.User_interface.CommonForms.UserInterface_FormareListOfDirectories
import com.example.firstkotlintry.User_interface.CommonForms.UserInterface_FormareListOfReports
import com.example.firstkotlintry.User_interface.CommonForms.UserInterface_FormareListOfSettings
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val checkingSettings = CheckingSettings()
        checkingSettings.checkingSystemSettings(applicationContext)

        val globalVariables = GlobalVariables()

        val myDbHelper = ConnectDatabase(this, null, null, 1)
        myDbHelper.setterForVariables(this)

        myDbHelper.createDataBase(this)

        try {
            myDbHelper.openDataBase()
        } catch (sqle: SQLException) {
            throw sqle
        }

        try {
            myDbHelper.onOpen(globalVariables.getgDatabase())
        } catch (sqle: SQLException) {
            throw sqle
        }

        val dropboxconnect = ConnectToDropbox()

        try {
            dropboxconnect.connectToDropbox()
        } catch (dbp: DbxException){
            throw dbp
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        var returnValue: Boolean
        when (item.itemId) {
            R.id.action_settings -> {
                clickedOnSettings()
                returnValue = true
        }else -> {
            returnValue = super.onOptionsItemSelected(item)
        }
        }
        return returnValue
    }

    fun clickedOnSettings(){
        var userInterface_FormareListOfSettings =
            UserInterface_FormareListOfSettings()
        intent = userInterface_FormareListOfSettings.newIntent(this)
        startActivity(intent)
    }

    fun listOfDirectoryOnClick(v: View){
        var intanseUserInterface_FormareListOfDirectories =
            UserInterface_FormareListOfDirectories()
        intent = intanseUserInterface_FormareListOfDirectories.newIntent(this)
        startActivity(intent)
    }

    fun listOfDocumentsOnClick(v: View){

    }

    fun listOfReportsOnClick(v: View){
        var intanseUserInterface_FormareListOfReports =
            UserInterface_FormareListOfReports()
        intent = intanseUserInterface_FormareListOfReports.newIntent(this)
        startActivity(intent)
    }
}

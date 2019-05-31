package com.example.firstkotlintry.User_interface.CommonForms

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.firstkotlintry.R
import com.example.firstkotlintry.System_classes.Global.GlobalStaticInterfaces_functions
import com.example.firstkotlintry.User_interface.ListFormsDirectories.UserInterface_FormareListOfNomenclatures
import com.example.firstkotlintry.User_interface.ListFormsDirectories.UserInterface_FormareListOfUnits
import kotlinx.android.synthetic.main.activity_main.*

class UserInterface_FormareListOfDirectories: AppCompatActivity(),
    GlobalStaticInterfaces_functions {
    override fun newIntent(context: Context): Intent {
        val intent = Intent(context, UserInterface_FormareListOfDirectories::class.java)

        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chosesdirectory)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_directories, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        var returnValue: Boolean
        when (item.itemId) {
            R.id.action_refresh -> {
                clickedOnRefresh()
                returnValue = true
            }else -> {
            returnValue = super.onOptionsItemSelected(item)
        }
        }
        return returnValue
    }

    fun clickedOnRefresh(){

    }

    fun listOfNomenclaturesOnClick(v: View){
        var userInterface_FormareListOfNomenclatures =
            UserInterface_FormareListOfNomenclatures()

        intent = userInterface_FormareListOfNomenclatures.newIntent(this)
        startActivity(intent)
    }

    fun listOfUnitsOnClick(v: View){
        var userInterface_FormareListOfUnits =
            UserInterface_FormareListOfUnits()

        intent = userInterface_FormareListOfUnits.newIntent(this)
        startActivity(intent)
    }
}
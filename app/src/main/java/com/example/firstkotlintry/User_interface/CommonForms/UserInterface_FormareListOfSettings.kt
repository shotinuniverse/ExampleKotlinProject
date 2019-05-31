package com.example.firstkotlintry.User_interface.CommonForms

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.firstkotlintry.R
import com.example.firstkotlintry.System_classes.Global.GlobalStaticInterfaces_functions
import com.example.firstkotlintry.System_classes.Global.GlobalVariables

class UserInterface_FormareListOfSettings: AppCompatActivity(),
    GlobalStaticInterfaces_functions {

    var PrefThisHost:   String? = null
    var PrefCorHost:    String? = null
    var DBName:         String? = null


    override fun newIntent(context: Context): Intent {
        val intent = Intent(context, UserInterface_FormareListOfSettings::class.java)

        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val globalVariables = GlobalVariables()
        PrefCorHost     = globalVariables.getgPrefCorHost()
        PrefThisHost    = globalVariables.getgPrefThisHost()
        DBName          = globalVariables.getgDBName()

        val textView1: TextView = findViewById(R.id.databasename) as TextView
        val textView2: TextView = findViewById(R.id.prefthis) as TextView
        val textView3: TextView = findViewById(R.id.prefcor) as TextView

        textView1.text = DBName
        textView2.text = PrefThisHost
        textView3.text = PrefCorHost
    }

    fun changeSettings(v: View){

    }
}
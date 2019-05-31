package com.example.firstkotlintry.User_interface.CommonForms

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.firstkotlintry.R
import com.example.firstkotlintry.System_classes.Global.GlobalStaticInterfaces_functions
import com.example.firstkotlintry.System_classes.Global.GlobalVariables
import com.example.firstkotlintry.User_interface.ReportsAndOther.UserInterface_InputParamsForReports

class UserInterface_FormareListOfReports: AppCompatActivity(),
    GlobalStaticInterfaces_functions {
        override fun newIntent(context: Context): Intent {
        val intent = Intent(context, UserInterface_FormareListOfReports::class.java)

        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chosesreports)
    }

    fun formareStatementOfNomenclatureRests(v: View){
        var intanseserInterface_InputParamsForReports =
            UserInterface_InputParamsForReports()

        val globalVariables = GlobalVariables()
        globalVariables.setgCurrentFormareReport("StatementOfNomenclatureRests")
        intent = intanseserInterface_InputParamsForReports.newIntent(this)
        startActivity(intent)
    }
}
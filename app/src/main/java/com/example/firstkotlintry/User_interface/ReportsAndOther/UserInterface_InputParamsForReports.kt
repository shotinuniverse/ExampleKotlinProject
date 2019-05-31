package com.example.firstkotlintry.User_interface.ReportsAndOther

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import com.example.firstkotlintry.R
import com.example.firstkotlintry.System_classes.Global.GlobalStaticInterfaces_functions
import com.example.firstkotlintry.System_classes.Global.GlobalVariables


class UserInterface_InputParamsForReports: AppCompatActivity(),
    GlobalStaticInterfaces_functions {
    override fun newIntent(context: Context): Intent {
        val intent = Intent(context, UserInterface_InputParamsForReports::class.java)

        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inputparametersforreport)

        val vbegTime: TextView = findViewById(R.id.begTime) as TextView

        vbegTime.addTextChangedListener(object : TextWatcher {

            internal var length_before = 0

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                length_before = s.length
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (length_before < s.length) {
                    if (s.length == 2 || s.length == 5)
                        s.append("/")
                    if (s.length > 2) {
                        if (Character.isDigit(s[2]))
                            s.insert(2, "/")
                    }
                    if (s.length > 5) {
                        if (Character.isDigit(s[5]))
                            s.insert(5, "/")
                    }
                }
            }
        })

        val vendTime: TextView = findViewById(R.id.endTime) as TextView

        vendTime.addTextChangedListener(object : TextWatcher {

            internal var length_before = 0

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                length_before = s.length
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable) {
                if (length_before < s.length) {
                    if (s.length == 2 || s.length == 5)
                        s.append("/")
                    if (s.length > 2) {
                        if (Character.isDigit(s[2]))
                            s.insert(2, "/")
                    }
                    if (s.length > 5) {
                        if (Character.isDigit(s[5]))
                            s.insert(5, "/")
                    }
                }
            }
        })
    }

    fun formareReport(v: View){
        val globalVariables = GlobalVariables()

        val textView1: TextView = findViewById(R.id.begTime) as TextView
        val textView2: TextView = findViewById(R.id.endTime) as TextView
        val textView3: TextView = findViewById(R.id.Nomenclature) as TextView
        if (globalVariables.getgCurrentFormareReport() == "StatementOfNomenclatureRests") {
            var intanseUserInterface_FormareListOfNomenclature =
                UserInterface_FormareStatementOfNomenclatureRests()
            intent = intanseUserInterface_FormareListOfNomenclature.newIntent(this, "ДатаНачала=" + textView1.text.toString() + ";ДатаОкончания=" + textView2.text.toString() + ";Номенклатура=" + textView3.text.toString())
            startActivity(intent)
        }
    }


}
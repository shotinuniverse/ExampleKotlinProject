package com.example.firstkotlintry.User_interface.ListFormsDirectories

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import com.example.firstkotlintry.R
import com.example.firstkotlintry.System_classes.Global.GlobalStaticInterfaces_functions
import kotlinx.android.synthetic.main.activity_statementofnomeclaturerests.*

class UserInterface_FormareListOfNomenclatures: AppCompatActivity(), GlobalStaticInterfaces_functions {
    override fun newIntent(context: Context): Intent {
        val intent = Intent(context, UserInterface_FormareListOfNomenclatures::class.java)

        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listofnomenclatures)

        val display = windowManager.defaultDisplay
        val width = display.width  // deprecated
        val height = display.height  // deprecated

        val widthOneColumn = (width - 2) / 4
        val heightOneColumn = (height - 2) / 8

        val row = TableRow(this)
        row.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        fillHeaderTable(row, widthOneColumn,  heightOneColumn)

        row.setBackgroundColor(Color.parseColor("#0452d1"))

        row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        //elementTableLayout.add

        elementTableLayout.addView(row)

    }

    fun fillHeaderTable(row: TableRow, widthOneColumn: Int, heightOneColumn: Int) {
        val labelColumnName_IDRref = TextView(this)
        labelColumnName_IDRref.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_IDRref.apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "_IDRRef"
        }
        row.addView(labelColumnName_IDRref)


        val labelColumnName_Name = TextView(this)
        labelColumnName_Name.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_Name.apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Наименование"
        }
        row.addView(labelColumnName_Name)

        val labelColumnName_FullName = TextView(this)
        labelColumnName_FullName.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_FullName.apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Полное наименование"
        }
        row.addView(labelColumnName_FullName)

        val labelColumnName_Unit = TextView(this)
        labelColumnName_Unit.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_Unit.apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Ед. Изм."
        }
        row.addView(labelColumnName_Unit)
    }

}
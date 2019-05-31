package com.example.firstkotlintry.User_interface.ListFormsDirectories

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import com.example.firstkotlintry.R
import com.example.firstkotlintry.System_classes.Global.GlobalStaticInterfaces_functions
import com.example.firstkotlintry.System_classes.Global.GlobalVariables
import com.example.firstkotlintry.User_interface.ElementsFormsDirectories.UserInterface_FormElementOfUnit
import com.example.firstkotlintry.Working_with_JSON.JSONReadDirectories
import com.example.firstkotlintry.Working_with_JSON.UnloadJSONDatafile
import com.example.firstkotlintry.Working_with_SQLite.SetDataAboutNomenclaturesUnit
import kotlinx.android.synthetic.main.activity_listofunits.*
import java.util.*

class UserInterface_FormareListOfUnits : AppCompatActivity(), GlobalStaticInterfaces_functions {

    companion object {
        var DirectoryBuilder: ArrayList<HashMap<String, String>>? = null
        var DirectoryParams: String? = null
    }

    override fun newIntent(context: Context): Intent {
        val intent = Intent(context, UserInterface_FormareListOfUnits::class.java)

        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listofunits)
        setSupportActionBar(formlistunits_toolbar)

        val display = windowManager.defaultDisplay
        val width = display.width  // deprecated
        val height = display.height  // deprecated

        val widthOneColumn = (width - 2) / 4
        val heightOneRow = (height - 2) / 12

        val row = TableRow(this)
        row.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        fillHeaderTable(row, widthOneColumn, heightOneRow)

        //#0452d1
        row.setBackgroundColor(resources.getColor(R.color.colorPrimary))

        row.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        elementTableLayout_Units.addView(row)

        val globalVariables = GlobalVariables()

        val setDataAboutNomenclaturesUnit = SetDataAboutNomenclaturesUnit(this, null, null, 1)
        setDataAboutNomenclaturesUnit.onOpen(globalVariables.getgDatabase())
        //setDataAboutNomenclaturesUnit.deleteFromDatabase()
//        setDataAboutNomenclaturesUnit.onUpgrade(globalVariables.getgDatabase(), 1, 1)
//        setDataAboutNomenclaturesUnit.addDataNomenclaturesUnit()
        setDataAboutNomenclaturesUnit.getListOfUnit(elementTableLayout_Units, this, widthOneColumn, heightOneRow)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_nomenclaturesunit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        var returnValue: Boolean
        when (item.itemId) {
            R.id.formlistunits_create -> {
                clickedOnCreate()
                returnValue = true
            }
            R.id.formlistunits_refreshfromFIRSTC -> {
                clickedOnRefreshFromFIRSTC()
                returnValue = true
            }
            else -> {
                returnValue = super.onOptionsItemSelected(item)
            }
        }
        return returnValue
    }

    fun fillHeaderTable(row: TableRow, widthOneColumn: Int, heightOneColumn: Int) {
        val labelColumnName_id = TextView(this)
        labelColumnName_id.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_id.apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "_id"
        }
        row.addView(labelColumnName_id)

        val labelColumnName_Code = TextView(this)
        labelColumnName_Code.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_Code.apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Код"
        }
        row.addView(labelColumnName_Code)

        val labelColumnName_Name = TextView(this)
        labelColumnName_Name.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_Name.apply {
            layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
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
                TableRow.LayoutParams.WRAP_CONTENT
            )
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Полное наименование"
        }
        row.addView(labelColumnName_FullName)
    }

    fun clickedOnCreate() {
        var userInterface_FormElementOfUnit =
            UserInterface_FormElementOfUnit()
        intent = userInterface_FormElementOfUnit.newIntent(this)
        startActivity(intent)
    }

    fun clickedOnRefreshFromFIRSTC(){
        val GlobalVariables = GlobalVariables()
        val client = GlobalVariables.getgDropboxClient()

        val instanseJSONWrite = UnloadJSONDatafile()
        instanseJSONWrite.DirectoryName = "УпаковкиЕдиницыИзмерения"
        DirectoryParams = ""
        instanseJSONWrite.DirectoryParams = DirectoryParams

        instanseJSONWrite.client = client
        instanseJSONWrite.mainUnloadJSON(this, "Directory")

        instanseJSONWrite.checkFileUpdate()

        val instanseJSONRead = JSONReadDirectories()
        instanseJSONRead.nameDirectory = "NOMENCLATURES_UNIT"
        instanseJSONRead.nameDirectoryOnRu = "УпаковкиЕдиницыИзмерения"
        instanseJSONRead.client = client

        DirectoryBuilder = instanseJSONRead.mainJSON(this)
        val setDataAboutNomenclaturesUnit = SetDataAboutNomenclaturesUnit(this, null, null, 1)
        setDataAboutNomenclaturesUnit.onOpen(GlobalVariables.getgDatabase())
        setDataAboutNomenclaturesUnit.addDataNomenclaturesUnitToDataFromJSON(DirectoryBuilder)

        this.closeContextMenu()
    }
}
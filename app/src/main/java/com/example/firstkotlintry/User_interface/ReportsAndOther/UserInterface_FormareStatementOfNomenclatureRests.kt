package com.example.firstkotlintry.User_interface.ReportsAndOther

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.TableRow
import android.widget.TextView
import com.example.firstkotlintry.R
import com.example.firstkotlintry.System_classes.Global.GlobalVariables
import com.example.firstkotlintry.Working_with_JSON.JSONReadReports
import com.example.firstkotlintry.Working_with_JSON.UnloadJSONDatafile
import kotlinx.android.synthetic.main.activity_statementofnomeclaturerests.*
import java.util.*

class UserInterface_FormareStatementOfNomenclatureRests: AppCompatActivity()  {

    companion object {
        var ReportBuilder: java.util.ArrayList<HashMap<String, String>>? = null
        var ReportParams: String? = null
    }

    fun newIntent(context: Context, tempParams: String): Intent {
        ReportParams = tempParams

        val intent = Intent(context, UserInterface_FormareStatementOfNomenclatureRests::class.java)

        val GlobalVariables = GlobalVariables()
        val client = GlobalVariables.getgDropboxClient()

        val instanseJSONWrite = UnloadJSONDatafile()
        instanseJSONWrite.ReportName = "ВедомостьПоТоварамНаСкладах"
        instanseJSONWrite.ReportParams =
            ReportParams
        instanseJSONWrite.client = client
        instanseJSONWrite.mainUnloadJSON(context, "Report")

        instanseJSONWrite.checkFileUpdate()

        val instanseJSONRead = JSONReadReports()
        instanseJSONRead.nameReport = "StatementOfNomenclatureRests"
        instanseJSONRead.nameReportOnRu = "ВедомостьПоТоварамНаСкладах"
        instanseJSONRead.client = client

        ReportBuilder = instanseJSONRead.mainJSON(context)

        return intent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statementofnomeclaturerests)

        if (ReportBuilder != null) {
            createTable()
        }
    }

    fun createTable() {
        val tempBuilder =
            ReportBuilder

        val display = windowManager.defaultDisplay
        val width = display.width  // deprecated
        val height = display.height  // deprecated

        val widthOneColumn = (width - 2) / 9
        val heightOneColumn = (height - 2) / 8

        val row = TableRow(this)
        row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
        fillHeaderTable(row, widthOneColumn, heightOneColumn)

//        val labelColumnName_Number = TextView(this)
//        labelColumnName_Number.apply {
//            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
//                TableRow.LayoutParams.WRAP_CONTENT)
//            layoutParams.width = widthOneColumn
////            layoutParams.height = heightOneColumn
//            text = "#Number"
//        }
//        row.addView(labelColumnName_Number)

//        val labelColumnName_Code = TextView(this)
//        labelColumnName_Code.setTextColor(Color.parseColor("#ffffff"))
//        labelColumnName_Code.apply {
//            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
//                 TableRow.LayoutParams.WRAP_CONTENT)
//            layoutParams.width = widthOneColumn
//            layoutParams.height = heightOneColumn
//            text = "Code"
//         }
//         row.addView(labelColumnName_Code)
//
//
//        val labelColumnName_Name = TextView(this)
//        labelColumnName_Name.setTextColor(Color.parseColor("#ffffff"))
//        labelColumnName_Name.apply {
//            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
//                TableRow.LayoutParams.WRAP_CONTENT)
//            layoutParams.width = widthOneColumn
//            layoutParams.height = heightOneColumn
//            text = "Name"
//        }
//        row.addView(labelColumnName_Name)
//
//        val labelColumnName_FullName = TextView(this)
//        labelColumnName_FullName.setTextColor(Color.parseColor("#ffffff"))
//        labelColumnName_FullName.apply {
//            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
//                TableRow.LayoutParams.WRAP_CONTENT)
//            layoutParams.width = widthOneColumn
//            layoutParams.height = heightOneColumn
//            text = "Full name"
//        }
//        row.addView(labelColumnName_FullName)
//
//        val labelColumnName_UploadDate = TextView(this)
//        labelColumnName_UploadDate.setTextColor(Color.parseColor("#ffffff"))
//        labelColumnName_UploadDate.apply {
//            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
//                TableRow.LayoutParams.WRAP_CONTENT)
//            layoutParams.width = widthOneColumn
//            layoutParams.height = heightOneColumn
//            text = "Upload date"
//        }
//        row.addView(labelColumnName_UploadDate)



        row.setBackgroundColor(Color.parseColor("#0452d1"))

        row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        //elementTableLayout.add

        elementTableLayout.addView(row)

        //var rows = listofreadedfile.size
        var stockfromitem = ""
        var nomeclaturefromitem = ""
        var specificationfromitem = ""
        var seriesfromitem = ""
        var unitfromitem = ""
        var begrestsfromitem = ""
        var plusrestsfromitem = ""
        var minusrestsfromitem = ""
        var finrestsfromitem = ""
//        var uploaddatefromitem = ""
        var countAddingColumn = 0

        var row_main = TableRow(this)
        row_main.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        var colorofrow = 0

        if (tempBuilder != null) {
            for (item in tempBuilder){
                if (countAddingColumn == 0){
    //                val labelRowName_Number = TextView(this)
    //                labelRowName_Number.apply {
    //                    layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
    //                        TableRow.LayoutParams.WRAP_CONTENT)
    //                    layoutParams.width = 13
    //                   // layoutParams.height = heightOneColumn
    //                    text = numberofstring.toString()
    //                }
    //                //labelColumnName_Number.textSize =
    //                row_main.addView(labelRowName_Number)

    //                codefromitem = item.getValue("Code")
    //
    //                val labelRowName_Code = TextView(this)
    //                labelRowName_Code.apply {
    //                    layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
    //                        TableRow.LayoutParams.WRAP_CONTENT)
    //                    layoutParams.width = widthOneColumn
    //                    //layoutParams.height = heightOneColumn
    //                    text = codefromitem
    //                }
    //                row_main.addView(labelRowName_Code)
    //
    //                namefromitem = item.getValue("Name")

    //                val labelRowName_Name = TextView(this)
    //                labelRowName_Name.apply {
    //                    layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
    //                        TableRow.LayoutParams.WRAP_CONTENT)
    //                    layoutParams.width = widthOneColumn
    //                    //layoutParams.height = heightOneColumn
    //                    text = namefromitem
    //                }
    //                row_main.addView(labelRowName_Name)
    //
    //            }
    //            else if (countAddingColumn == 1){
    //                fullnamefromitem = item.getValue("FullName")
    //
    //                val labelRowName_FullName = TextView(this)
    //                labelRowName_FullName.apply {
    //                    layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
    //                        TableRow.LayoutParams.WRAP_CONTENT)
    //                    layoutParams.width = widthOneColumn
    //                    //layoutParams.height = heightOneColumn
    //                    text = fullnamefromitem
    //                }
    //                row_main.addView(labelRowName_FullName)
    //
    //            }
    //            else if (countAddingColumn == 2){
    //                uploaddatefromitem = item.getValue("UnloadDate")
    //
    //                val labelRowName_UploadDate = TextView(this)
    //                labelRowName_UploadDate.apply {
    //                    layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
    //                        TableRow.LayoutParams.WRAP_CONTENT)
    //                    layoutParams.width = widthOneColumn
    //                    //layoutParams.height = heightOneColumn
    //                    text = uploaddatefromitem
    //                }
    //                row_main.addView(labelRowName_UploadDate)
    //
    //                if(colorofrow == 0){
    //                    row_main.setBackgroundColor(Color.parseColor("#ffffff"))
    //                    colorofrow = 1
    //                }
    //                else{
    //                    row_main.setBackgroundColor(Color.parseColor("#80b3f2"))
    //                    colorofrow = 0
    //                }
    //
    //                //Last column of row. Add row on form
    //                elementTableLayout.addView(row_main)
    //
    //                row_main = TableRow(this)
    //                row_main.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
    //                    ViewGroup.LayoutParams.WRAP_CONTENT)
    //
    //                countAddingColumn = 0
    //                continue
    //            }
    //            countAddingColumn++
                    stockfromitem = item.getValue("Склад")
                    val labelRowName_Stock = TextView(this)
                    labelRowName_Stock.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = stockfromitem
                    }
                    row_main.addView(labelRowName_Stock)

                }
                else if (countAddingColumn == 1){
                    nomeclaturefromitem = item.getValue("Номенклатура")

                    val labelRowName_Nomeclature = TextView(this)
                    labelRowName_Nomeclature.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = nomeclaturefromitem
                    }
                    row_main.addView(labelRowName_Nomeclature)

                }
                else if (countAddingColumn == 2){
                    specificationfromitem = item.getValue("Характеристика")

                    val labelRowName_Specification = TextView(this)
                    labelRowName_Specification.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = specificationfromitem
                    }
                    row_main.addView(labelRowName_Specification)

                }
                else if (countAddingColumn == 3){
                    seriesfromitem = item.getValue("Серия")

                    val labelRowName_Series = TextView(this)
                    labelRowName_Series.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = seriesfromitem
                    }
                    row_main.addView(labelRowName_Series)

                }
                else if (countAddingColumn == 4){
                    unitfromitem = item.getValue("ЕдиницаИзмерения")

                    val labelRowName_Unit = TextView(this)
                    labelRowName_Unit.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = unitfromitem
                    }
                    row_main.addView(labelRowName_Unit)

                }
                else if (countAddingColumn == 5){
                    begrestsfromitem = item.getValue("НачальныйОстаток")

                    val labelRowName_begrests = TextView(this)
                    labelRowName_begrests.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = begrestsfromitem
                    }
                    row_main.addView(labelRowName_begrests)

                }
                else if (countAddingColumn == 6){
                    plusrestsfromitem = item.getValue("Приход")

                    val labelRowName_plusrests = TextView(this)
                    labelRowName_plusrests.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = plusrestsfromitem
                    }
                    row_main.addView(labelRowName_plusrests)

                }
                else if (countAddingColumn == 7){
                    minusrestsfromitem = item.getValue("Расход")

                    val labelRowName_minusrests = TextView(this)
                    labelRowName_minusrests.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = minusrestsfromitem
                    }
                    row_main.addView(labelRowName_minusrests)

                }
                else if (countAddingColumn == 8){
                    finrestsfromitem = item.getValue("КонечныйОстаток")

                    val labelRowName_finrests = TextView(this)
                    labelRowName_finrests.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = finrestsfromitem
                    }
                    row_main.addView(labelRowName_finrests)

                }
                else if (countAddingColumn == 9){
    //                uploaddatefromitem = item.getValue("UnloadDate")
    //
    //                val labelRowName_UploadDate = TextView(this)
    //                labelRowName_UploadDate.apply {
    //                    layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
    //                        TableRow.LayoutParams.WRAP_CONTENT)
    //                    layoutParams.width = widthOneColumn
    //                    //layoutParams.height = heightOneColumn
    //                    text = uploaddatefromitem
    //                }
    //                row_main.addView(labelRowName_UploadDate)

                    if(colorofrow == 0){
                        row_main.setBackgroundColor(Color.parseColor("#ffffff"))
                        colorofrow = 1
                    }
                    else{
                        row_main.setBackgroundColor(Color.parseColor("#80b3f2"))
                        colorofrow = 0
                    }

                    //Last column of row. Add row on form
                    elementTableLayout.addView(row_main)

                    row_main = TableRow(this)
                    row_main.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)

                    countAddingColumn = 0
                    continue
                }
                countAddingColumn++
            }
        }
    }

    fun fillHeaderTable(row: TableRow , widthOneColumn: Int, heightOneColumn: Int) {
        val labelColumnName_Stock = TextView(this)
        labelColumnName_Stock.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_Stock.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Склад"
        }
        row.addView(labelColumnName_Stock)


        val labelColumnName_Nomenclature = TextView(this)
        labelColumnName_Nomenclature.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_Nomenclature.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Номенклатура"
        }
        row.addView(labelColumnName_Nomenclature)

        val labelColumnName_Specification = TextView(this)
        labelColumnName_Specification.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_Specification.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Характеристика"
        }
        row.addView(labelColumnName_Specification)

        val labelColumnName_Series = TextView(this)
        labelColumnName_Series.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_Series.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Серия"
        }
        row.addView(labelColumnName_Series)

        val labelColumnName_Unit = TextView(this)
        labelColumnName_Unit.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_Unit.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Ед. Изм."
        }
        row.addView(labelColumnName_Unit)

        val labelColumnName_begRests = TextView(this)
        labelColumnName_begRests.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_begRests.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Нач. ост."
        }
        row.addView(labelColumnName_begRests)

        val labelColumnName_plusRests = TextView(this)
        labelColumnName_plusRests.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_plusRests.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Приход"
        }
        row.addView(labelColumnName_plusRests)

        val labelColumnName_minusRests = TextView(this)
        labelColumnName_minusRests.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_minusRests.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Расход"
        }
        row.addView(labelColumnName_minusRests)

        val labelColumnName_finRests_value = TextView(this)
        labelColumnName_finRests_value.setTextColor(Color.parseColor("#ffffff"))
        labelColumnName_finRests_value.apply {
            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT)
            layoutParams.width = widthOneColumn
            layoutParams.height = heightOneColumn
            text = "Кон. ост."
        }
        row.addView(labelColumnName_finRests_value)

//        val labelColumnName_UploadDate = TextView(this)
//        labelColumnName_UploadDate.setTextColor(Color.parseColor("#ffffff"))
//        labelColumnName_UploadDate.apply {
//            layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
//                TableRow.LayoutParams.WRAP_CONTENT)
//            layoutParams.width = widthOneColumn
//            layoutParams.height = heightOneColumn
//            text = "Upload date"
//        }
//        row.addView(labelColumnName_UploadDate)
    }
}
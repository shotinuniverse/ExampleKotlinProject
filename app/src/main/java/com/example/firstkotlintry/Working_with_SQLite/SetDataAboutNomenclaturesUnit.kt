package com.example.firstkotlintry.Working_with_SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.firstkotlintry.R
import com.example.firstkotlintry.System_classes.Global.GlobalStaticInterfaces_forSQL
import com.example.firstkotlintry.System_classes.Global.GlobalVariables
import com.example.firstkotlintry.User_interface.ElementsFormsDirectories.UserInterface_FormElementOfUnit
import java.util.*


class SetDataAboutNomenclaturesUnit(context: Context, name: String?,
                                    factory: SQLiteDatabase.CursorFactory?, version: Int) :
    SQLiteOpenHelper(context, GlobalVariables.gDBName,
        factory, GlobalVariables.gDBVersion), GlobalStaticInterfaces_forSQL{

    companion object {
        const val TABLE_NAME = "NOMENCLATURES_UNIT"
        const val C_IDRREF = "_IDRRef"
        const val C_CODE = "code"
        const val C_NAME = "name"
        const val C_FULLNAME = "full_name"
    }

    override fun dropTable(db: SQLiteDatabase, table_name: String) {
        super.dropTable(db, "Nomenclatures_Unit")
    }

    fun getListOfUnit(tableLayout: TableLayout, context: Context,
                      widthOneColumn: Int, heightOneRow: Int){
        val globalVariables = GlobalVariables()
        val db = globalVariables.getgDatabase()

        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                var colorofrow = 0
                do {
                    val row_main = TableRow(context)
                    row_main.layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)

                    val id = cursor.getString(cursor.getColumnIndex("_id"))
                    val code = cursor.getString(cursor.getColumnIndex(C_CODE))
                    val name = cursor.getString(cursor.getColumnIndex(C_NAME))
                    val full_name = cursor.getString(cursor.getColumnIndex(C_FULLNAME))

                    val labelRowName_ID = TextView(context)
                    labelRowName_ID.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = id
                    }
                    row_main.addView(labelRowName_ID)

                    val labelRowName_Code = TextView(context)
                    labelRowName_Code.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = code
                    }
                    row_main.addView(labelRowName_Code)

                    val labelRowName_Name = TextView(context)
                    labelRowName_Name.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = name
                    }
                    row_main.addView(labelRowName_Name)

                    val labelRowName_FullName = TextView(context)
                    labelRowName_FullName.apply {
                        layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT)
                        layoutParams.width = widthOneColumn
                        //layoutParams.height = heightOneColumn
                        text = full_name
                    }
                    row_main.addView(labelRowName_FullName)

                    row_main.setClickable(true)
                    row_main.setOnClickListener(object : View.OnClickListener {
                        override fun onClick(v: View) {
                            val t = v as TableRow
                            val idTextView = t.getChildAt(0) as TextView
                            val idText = idTextView.text

                            val userInterface_FormElementOfUnit = UserInterface_FormElementOfUnit()
                            val intent = userInterface_FormElementOfUnit.newIntent(context)
                            AppCompatActivity().startActivity(intent)
                        }
                    })
//                    row_main.setOnClickListener{
//                        v:View -> processClickedOnRow()
//                    }
//                    row_main.setOnClickListener {
////                        println("kek")
//                        v: View? ->
//                    }

                    if(colorofrow == 0){
                        row_main.setBackgroundColor(context.getColor(R.color.colorTextTableHeader))
                        colorofrow = 1
                    }
                    else{
                        row_main.setBackgroundColor(context.getColor(R.color.colorTableRow))
                        colorofrow = 0
                    }

                    tableLayout.addView(row_main)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
    }

    fun addDataNomenclaturesUnit(values: ContentValues) {
        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun addDataNomenclaturesUnitToDataFromJSON(DataBuilder: ArrayList<HashMap<String, String>>?){
        var tempBuilder = DataBuilder
        val db = this.writableDatabase

        var values = ContentValues()

        var countAddingColumn = 0

        if (tempBuilder != null) {
            for (item in tempBuilder) {
                if (countAddingColumn == 0) {
                    values.put(C_IDRREF, item.getValue("_IDRRef"))
                }else if (countAddingColumn == 1){
                    values.put(C_NAME, item.getValue("Наименование"))
                }else if (countAddingColumn == 2){
                    values.put(C_FULLNAME, item.getValue("НаименованиеПолное"))
                }else if (countAddingColumn == 3){
                    values.put(C_CODE, item.getValue("Код"))
                }
                else if (countAddingColumn == 4){
                    countAddingColumn = 0
                    db.insert(TABLE_NAME, null, values)
                    continue
                }
                countAddingColumn++
            }
        }
        db.close()
    }

    fun deleteFromDatabase(){
        val query =
            "SELECT * FROM $TABLE_NAME WHERE _id = 4"

        val db = this.writableDatabase

        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val id = Integer.parseInt(cursor.getString(0))
            db.delete(TABLE_NAME, "_id = ?",
                arrayOf(id.toString()))
            cursor.close()
        }
    }
    fun processClickedOnRow(){

    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_UNITS_TABLE = ("CREATE TABLE $TABLE_NAME (\n" +
                "  _id        integer PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE,\n" +
                "  $C_IDRREF    varchar(50) NOT NULL UNIQUE,\n" +
                "  $C_CODE       varchar(5),\n" +
                "  $C_NAME       varchar(10),\n" +
                "  $C_FULLNAME  varchar(50)\n" +
                ");\n" +
                "\n" +
                "CREATE INDEX k_id\n" +
                "  ON $TABLE_NAME\n" +
                "  (_id);")
        db.execSQL(CREATE_UNITS_TABLE)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        dropTable(db, TABLE_NAME)
        this.onCreate(db)
    }
}
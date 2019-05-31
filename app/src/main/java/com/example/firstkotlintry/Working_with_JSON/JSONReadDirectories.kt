package com.example.firstkotlintry.Working_with_JSON

import android.content.Context
import com.dropbox.core.DbxException
import com.dropbox.core.v2.DbxClientV2
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.FileInputStream
import java.io.IOException
import java.util.*

class JSONReadDirectories {
    var nameDirectory: String? = null
    var nameDirectoryOnRu: String? = null
    var client: DbxClientV2? = null

    @Throws(IOException::class, DbxException::class)
    fun mainJSON(context: Context): ArrayList<HashMap<String, String>> {
        val instanse = LoadJSONDatafile()
        instanse.client = client
        instanse.loadJSONFileFromDropbox(context)
        return begginingReadFile(context)
    }

    //// Начинаем чтение файла формата .json
    ////////////////////////////////////////
    @Throws(IOException::class)
    internal fun begginingReadFile(context: Context): ArrayList<HashMap<String, String>> {

        val jsonString = loadJSON(context)
        var formList = ArrayList<HashMap<String, String>>()
        try {
            val obj = JSONObject(jsonString)
            val m_jArry = obj.getJSONArray(nameDirectoryOnRu)

            //Ведомость по товарам на складах
            if (nameDirectory === "NOMENCLATURES_UNIT") {
                formList = buildNomenclaturesUnit(context, formList, m_jArry)
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return formList
    }

    //// Звгружаем файл JSON
    ////////////////////////
    @Throws(IOException::class)
    internal fun loadJSON(context: Context): String? {
        var json: String? = null
        try {
            val `is` = FileInputStream(context.getDir("assets", 0).path + "/JsonReadFile.json")

            val size = `is`.available()
            //int size = 999999;
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }

    //// ЗДЕСЬ ФОРМИРУЮТСЯ СТРУКТУРЫ ДАННЫХ ОТЧЕТОВ
    ////////////////////////////////////////////////////////////////////////////////

    @Throws(IOException::class)
    internal fun buildNomenclaturesUnit(
        context: Context,
        formList: ArrayList<HashMap<String, String>>,
        m_jArry: JSONArray
    ): ArrayList<HashMap<String, String>> {
        var m_li: HashMap<String, String>
        try {
            for (i in 0 until m_jArry.length()) {
                val jo_inside = m_jArry.getJSONObject(i)

                val g = i + 1
                val code_value = g.toString()

                val IDRREF_value        = jo_inside.getString("_IDRRef")
                val Name_value          = jo_inside.getString("Наименование")
                val FullName_value      = jo_inside.getString("НаименованиеПолное")
                val unitcode_value      = jo_inside.getString("Код")
                val unloadDate_value    = jo_inside.getString("UnloadDate")

                //Add your values in your `ArrayList` as below:
                for (j in 0..4) {
                    m_li = HashMap()
                    m_li["Code"] = code_value
                    if (j == 0) {
                        m_li["_IDRRef"] = IDRREF_value
                    } else if (j == 1) {
                        m_li["Наименование"] = Name_value
                    } else if (j == 2) {
                        m_li["НаименованиеПолное"] = FullName_value
                    } else if (j == 3) {
                        m_li["Код"] = unitcode_value
                    } else {
                        m_li["UnloadDate"] = unloadDate_value
                    }

                    formList.add(m_li)
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return formList
    }
}

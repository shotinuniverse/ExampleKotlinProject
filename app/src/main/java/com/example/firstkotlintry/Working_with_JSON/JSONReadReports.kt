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

class JSONReadReports {
    var nameReport: String? = null
    var nameReportOnRu: String? = null
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
            val m_jArry = obj.getJSONArray(nameReportOnRu)

            //Ведомость по товарам на складах
            if (nameReport === "StatementOfNomenclatureRests") {
                formList = buildStatementOfNomenclatureRests(context, formList, m_jArry)
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
    internal fun buildStatementOfNomenclatureRests(
        context: Context,
        formList: ArrayList<HashMap<String, String>>,
        m_jArry: JSONArray
    ): ArrayList<HashMap<String, String>> {
        var m_li: HashMap<String, String>
        try {
            for (i in 0 until m_jArry.length()) {
                val jo_inside = m_jArry.getJSONObject(i)
                //Log.d("Details-->", jo_inside.getString("Code"));
                //String code_value = jo_inside.getString("Code");
                val g = i + 1
                val code_value = g.toString()
                //              String name_value = jo_inside.getString("Name");
                //              String fullName_value = jo_inside.getString("FullName");
                //              String unloadDate_value = jo_inside.getString("UnloadDate");
                val stock_value = jo_inside.getString("Склад")
                val nomenclature_value = jo_inside.getString("Номенклатура")
                val specification_value = jo_inside.getString("Характеристика")
                val series_value = jo_inside.getString("Серия")
                val unit_value = jo_inside.getString("ЕдиницаИзмерения")
                val begRests_value = jo_inside.getString("НачальныйОстаток")
                val plusRests_value = jo_inside.getString("Приход")
                val minusRests_value = jo_inside.getString("Расход")
                val finRests_value = jo_inside.getString("КонечныйОстаток")
                val unloadDate_value = jo_inside.getString("UnloadDate")


                //Add your values in your `ArrayList` as below:
                for (j in 0..9) {
                    m_li = HashMap()
                    m_li["Code"] = code_value
                    if (j == 0) {
                        m_li["Склад"] = stock_value
                    } else if (j == 1) {
                        m_li["Номенклатура"] = nomenclature_value
                    } else if (j == 2) {
                        m_li["Характеристика"] = specification_value
                    } else if (j == 3) {
                        m_li["Серия"] = series_value
                    } else if (j == 4) {
                        m_li["ЕдиницаИзмерения"] = unit_value
                    } else if (j == 5) {
                        m_li["НачальныйОстаток"] = begRests_value
                    } else if (j == 6) {
                        m_li["Приход"] = plusRests_value
                    } else if (j == 7) {
                        m_li["Расход"] = minusRests_value
                    } else if (j == 8) {
                        m_li["КонечныйОстаток"] = finRests_value
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

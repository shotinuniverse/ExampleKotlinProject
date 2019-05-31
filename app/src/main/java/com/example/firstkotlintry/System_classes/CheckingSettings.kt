package com.example.firstkotlintry.System_classes

import android.content.Context
import com.example.firstkotlintry.System_classes.Global.GlobalVariables

class CheckingSettings {
    fun checkingSystemSettings(application_context: Context) {
        var list_settings_database = spreadOutTextFile(application_context)
        var structure_params: java.util.HashMap<String, String> = getConformityNameValue(list_settings_database)

        val globalVariables = GlobalVariables()
        globalVariables.setgDBName(structure_params.get("NameDatabase").toString())
        globalVariables.setgPrefThisHost(structure_params.get("NameHost").toString())
        globalVariables.setgPrefCorHost(structure_params.get("NameCorHost").toString())
    }

    fun spreadOutTextFile(application_context: Context): String {
        var file_name = "systemFiles/conf.txt"
        val string_settings = application_context.assets.open(file_name).bufferedReader().use {
            it.readText()
        }

        return string_settings
    }

    fun getConformityNameValue(string_settings: String): java.util.HashMap<String, String> {
        var array_to_string_settings = string_settings.toCharArray()
        var counter = 0
        var temp_name_param = ""
        var temp_value_param = ""
        var going_name_param = false

        val conformity_nameparam_valueparam = java.util.HashMap<String, String>()

        while (array_to_string_settings[counter] != ';') {
            if (array_to_string_settings[counter] == '\r') {
                conformity_nameparam_valueparam.set(temp_name_param, temp_value_param)
                temp_value_param = ""
                temp_name_param = ""
                counter++
            }
            if (array_to_string_settings[counter] == ']'){
                going_name_param = false
                counter++
            }
            if (going_name_param) {
                temp_name_param += array_to_string_settings[counter]
            }
            if (array_to_string_settings[counter] == '[') {
                going_name_param = true
            }
            if (!going_name_param) {
                if (array_to_string_settings[counter] == '='
                    && array_to_string_settings[counter + 1] == '\r'
                    && array_to_string_settings[counter + 2] == '\n'
                ) {
                    conformity_nameparam_valueparam.set(temp_name_param, "")

                }
                else if (array_to_string_settings[counter] == '='){

                }
                else {
                    if (array_to_string_settings[counter] != '\n') {
                        temp_value_param += array_to_string_settings[counter]
                    }
                }
            }

            counter++
        }
        for (item in conformity_nameparam_valueparam) {
            println(item.value)
        }
        return conformity_nameparam_valueparam
    }
}
package com.example.firstkotlintry.Working_with_JSON

import com.example.firstkotlintry.System_classes.Global.GlobalVariables

class UploadUnloadJSONCommon{
    var PrefThisHost: String? = null
    var PrefCorHost: String? = null

    constructor(){
        val globalVariables = GlobalVariables()
        PrefCorHost     = globalVariables.getgPrefCorHost()
        PrefThisHost    = globalVariables.getgPrefThisHost()
    }

    fun getFilenameForUpload(): String{
        return "Message_" + PrefCorHost + "_" + PrefThisHost + ".json"
    }

    fun getFilenameForUnload(): String{
        return "Message_" + PrefThisHost + "_" + PrefCorHost + ".json"
    }
}
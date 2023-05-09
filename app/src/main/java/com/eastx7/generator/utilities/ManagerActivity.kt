package com.eastx7.generator.utilities

interface ManagerActivity {

    fun checkNumString(numString: String): String {
        if (numString.isEmpty()) {
            return "0"
        }
        val correctChars = "0123456789"
        var resString = ""
        for (subNum in numString) {
            if (correctChars.contains(subNum, ignoreCase = true)) {
                resString = resString + subNum
            }
        }
        return resString
    }

    fun intToStrZeroBlank(value: Int) = if (value == 0) {
        ""
    } else {
        value.toString()
    }

    data class ScreenAttr(
        var id: Int = 0,
        var name: String,
        var desc: Int = 0,
        var type: ScreenAttrType = ScreenAttrType.TEXT,
        var required: Boolean = true,
        var readOnly: Boolean = false,
        var valueText: String = "",
        var showSuggestion: Boolean = false,
        var error: Boolean = false,
        var errorDesc: Int = 0,
    )

    enum class ScreenAttrType { EDIT_NUM, TEXT, LABEL }
}
class ManagerActivityImpl: ManagerActivity
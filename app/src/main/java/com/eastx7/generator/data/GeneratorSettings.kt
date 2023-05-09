package com.eastx7.generator.data

import com.eastx7.generator.MainActivity.TypeActivity
import com.eastx7.generator.MainActivity.ExhibitionType

data class GeneratorSettings(
    var id: Int = 0,
    var startNum: Int = 0,
    var endNum: Int = 0,
    var exhibitionType: ExhibitionType = ExhibitionType.Dogs,
    var digitDisplay: String = "",
    var typeActivity: TypeActivity = TypeActivity.Settings
) {
    fun settingsIsOk(): Boolean {
        this.digitDisplay = "0".repeat(endNum.toString().length)
        return if (this.startNum != 0 && this.endNum > this.startNum) {
            true
        } else {
            false
        }
    }
}

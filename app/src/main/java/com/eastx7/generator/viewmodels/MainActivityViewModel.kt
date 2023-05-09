package com.eastx7.generator.viewmodels

import android.content.Context
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import com.eastx7.generator.R
import com.eastx7.generator.data.*
import com.eastx7.generator.MainActivity.TypeActivity
import com.eastx7.generator.utilities.ManagerActivity
import kotlinx.coroutines.flow.*
import java.lang.Exception
import javax.inject.Inject

@ExperimentalMaterial3Api
@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel(), ManagerActivity {

    var generatorSettings = GeneratorSettings()

    private val _generatorSettingsId = MutableStateFlow(0)
    internal val generatorSettingsId: StateFlow<Int> = _generatorSettingsId.asStateFlow()

    private val _settingsIsOk = MutableStateFlow(generatorSettings.settingsIsOk())
    internal val settingsIsOk: StateFlow<Boolean> = _settingsIsOk.asStateFlow()

    private var prevTypeActivity = TypeActivity.Settings

    internal fun setTypeActivity(type: TypeActivity) {
        prevTypeActivity = generatorSettings.typeActivity
        generatorSettings.typeActivity = type
        emitGeneratorSettingsId()
    }

    internal fun onAttrEdit(name: String, value: String) {
        var valueInt = 0
        try {
            valueInt = checkNumString(value).toInt()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        when (name) {
            "startNum" -> generatorSettings.startNum = valueInt
            "endNum" -> generatorSettings.endNum = valueInt
            else -> throw java.lang.IllegalStateException("Unexpected attr name: ${name}")
        }
        _settingsIsOk.value = generatorSettings.settingsIsOk()
    }

    internal fun onExhibitionTypeRadioBtn(type: Int) {
        generatorSettings.exhibitionType =
            generatorSettings.exhibitionType.exhibitionTypeByStringId(type)
    }

    internal fun onBack() {
        setTypeActivity(prevTypeActivity)
    }

    internal fun onStartClick() {
        generatorSettings.digitDisplay =
            (generatorSettings.startNum..generatorSettings.endNum)
                .random()
                .toString()
                .padStart(generatorSettings.endNum.toString().length, '0')
        emitGeneratorSettingsId()
    }

    internal fun getTitle(context: Context): String =
        when (generatorSettings.typeActivity) {
            TypeActivity.Settings -> {
                context.getString(R.string.settings)
            }
            TypeActivity.Generator -> {
                context.getString(R.string.generator)
            }
        }

    internal fun emitGeneratorSettingsId() {
        generatorSettings.id++
        _generatorSettingsId.value = generatorSettings.id
    }
}

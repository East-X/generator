package com.eastx7.generator.uiutilities

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.eastx7.generator.MainActivity.TypeActivity
import com.eastx7.generator.data.GeneratorSettings
import com.eastx7.generator.ui.RandomBody
import com.eastx7.generator.ui.SettingsBody

@Composable
internal fun MainActivityScreen(
    generatorSettings: GeneratorSettings,
    title: String,
    settingsIsOk: Boolean,
    onAttrEdit: (name: String, value: String) -> Unit,
    onExhibitionTypeRadioBtn: (Int) -> Unit,
    onSetSettings: () -> Unit,
    onBack: () -> Unit,
    onStartClick: () -> Unit
) {
    BackPressHandler(onBackPressed = onBack)
    Scaffold(
        topBar = {
            SimpleTopBar(
                title = title,
                onBack = onBack
            )
        },
        content = { innerPadding ->
            when (generatorSettings.typeActivity) {
                TypeActivity.Settings ->
                    SettingsBody(
                        innerPadding = innerPadding,
                        generatorSettings = generatorSettings,
                        settingsIsOk = settingsIsOk,
                        onAttrEdit = onAttrEdit,
                        onExhibitionTypeRadioBtn = onExhibitionTypeRadioBtn,
                        onSetSettings = onSetSettings
                    )
                TypeActivity.Generator ->
                    RandomBody(
                        innerPadding = innerPadding,
                        generatorSettings = generatorSettings,
                        onStartClick = onStartClick
                    )
            }
        }
    )
}
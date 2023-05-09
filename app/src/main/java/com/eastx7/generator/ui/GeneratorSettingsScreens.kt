package com.eastx7.generator.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.res.stringResource
import com.eastx7.generator.R
import com.eastx7.generator.data.GeneratorSettings
import com.eastx7.generator.theme.HorizontalFieldSpacer
import com.eastx7.generator.theme.VertFieldSpacer
import com.eastx7.generator.theme.startEndPaddings
import com.eastx7.generator.uiutilities.UtilitiesBodyEditText
import com.eastx7.generator.uiutilities.UtilitiesBodyLabel
import com.eastx7.generator.uiutilities.UtilitiesBodyRadioBtn
import com.eastx7.generator.utilities.ManagerActivity.ScreenAttr
import com.eastx7.generator.utilities.ManagerActivity.ScreenAttrType
import com.eastx7.generator.utilities.ManagerActivityImpl

@Composable
fun SettingsBody(
    innerPadding: PaddingValues,
    generatorSettings: GeneratorSettings,
    settingsIsOk: Boolean,
    onAttrEdit: (name: String, value: String) -> Unit,
    onExhibitionTypeRadioBtn: (Int) -> Unit,
    onSetSettings: () -> Unit
) {
    val sizeHeightDp = LocalViewConfiguration.current.minimumTouchTargetSize.height
    val ma = ManagerActivityImpl()
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(
                top = innerPadding.calculateTopPadding(),
                start = startEndPaddings,
                end = startEndPaddings
            )
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top

    ) {
        UtilitiesBodyLabel(
            ScreenAttr(
                1,
                "label_participants",
                R.string.participants,
                ScreenAttrType.LABEL
            )
        )
        VertFieldSpacer()
        Column {
            UtilitiesBodyEditText(
                attr = ScreenAttr(
                    2,
                    "startNum",
                    R.string.first_participant,
                    ScreenAttrType.EDIT_NUM,
                    true,
                    false,
                    ma.intToStrZeroBlank(generatorSettings.startNum)
                ),
                onAttrEdit = onAttrEdit
            )
            HorizontalFieldSpacer()
            UtilitiesBodyEditText(
                attr = ScreenAttr(
                    3,
                    "endNum",
                    R.string.last_participant,
                    ScreenAttrType.EDIT_NUM,
                    true,
                    false,
                    ma.intToStrZeroBlank(generatorSettings.endNum)
                ),
                onAttrEdit = onAttrEdit
            )
        }

        UtilitiesBodyLabel(
            ScreenAttr(
                4,
                "label_exhibition_type",
                R.string.exhibition_type,
                ScreenAttrType.LABEL
            )
        )
        val exhibitionTypeOptions = listOf(
            R.string.dogs,
            R.string.cats
        )
        VertFieldSpacer()
        UtilitiesBodyRadioBtn(
            exhibitionTypeOptions,
            generatorSettings.exhibitionType.exhibitionTypeStringId(),
            sizeHeightDp,
            onExhibitionTypeRadioBtn
        )
        VertFieldSpacer()
        Button(
            onClick = onSetSettings,
            modifier = Modifier.padding(start = startEndPaddings, end = startEndPaddings),
            enabled = settingsIsOk
        ) {
            Text(stringResource(R.string.next))
        }
    }
}
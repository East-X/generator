package com.eastx7.generator.uiutilities

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import com.eastx7.generator.theme.startEndPaddings
import com.eastx7.generator.utilities.ManagerActivity.ScreenAttr
import com.eastx7.generator.utilities.ManagerActivity.ScreenAttrType

@Composable
fun UtilitiesBodyLabel(attr: ScreenAttr) {
    Text(
        text = stringResource(attr.desc),
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun UtilitiesBodyEditText(
    attr: ScreenAttr,
    onAttrEdit: (name: String, value: String) -> Unit,
) {
    val kbOptions = KeyboardOptions(
        keyboardType = when (attr.type) {
            ScreenAttrType.EDIT_NUM -> KeyboardType.Number
            else -> KeyboardType.Text

        }
    )
    var value by rememberSaveable { mutableStateOf(attr.valueText) }

    OutlinedTextField(
        value = value,
        onValueChange = {
            value = it
            onAttrEdit(attr.name, it)
        },
        label = { Text(stringResource(attr.desc)) },
        trailingIcon = {
            IconButton(onClick = {
                value = ""
                onAttrEdit(attr.name, "")
            }
            )
            {
                Icon(
                    imageVector = Icons.Outlined.Cancel,
                    contentDescription = "Cancel"
                )
            }

        },
        keyboardOptions = kbOptions,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun UtilitiesBodyRadioBtn(
    listOptions: List<Int>,
    selectedId: Int,
    height: Dp,
    onClickRadioBtn: (Int) -> Unit,
) {
    var selectedOption by remember {
        mutableStateOf(listOptions.indexOf(selectedId))
    }
    Column(Modifier.selectableGroup()) {
        listOptions.forEach { id ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(height)
                    .selectable(
                        selected = (selectedOption == listOptions.indexOf(id)),
                        onClick = {
                            selectedOption = listOptions.indexOf(id)
                            onClickRadioBtn(id)
                        },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = startEndPaddings),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (selectedOption == listOptions.indexOf(id)),
                    onClick = null
                )
                Text(
                    text = stringResource(id),
                    modifier = Modifier.padding(start = startEndPaddings)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopBar(
    title: String,
    onBack: () -> Unit,
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ChevronLeft,
                    contentDescription = "back"
                )
            }
        }
    )
}

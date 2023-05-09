package com.eastx7.generator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.eastx7.generator.data.GeneratorSettings
import dagger.hilt.android.AndroidEntryPoint
import com.eastx7.generator.theme.GeneratorTheme
import com.eastx7.generator.uiutilities.MainActivityScreen
import com.eastx7.generator.viewmodels.MainActivityViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: MainActivityViewModel = hiltViewModel()
            val context = LocalContext.current
            val generatorSettingsId by viewModel.generatorSettingsId.collectAsState()
            val settingsIsOk by viewModel.settingsIsOk.collectAsState()
            var title by remember { mutableStateOf("") }
            var generatorSettings by remember { mutableStateOf(GeneratorSettings()) }

            LaunchedEffect(
                key1 = generatorSettingsId,
                block = {
                    title = viewModel.getTitle(context)
                    generatorSettings = viewModel.generatorSettings
                }
            )

            GeneratorTheme {
                MainActivityScreen(
                    generatorSettings = generatorSettings,
                    title = title,
                    settingsIsOk = settingsIsOk,
                    onAttrEdit = { name, value -> viewModel.onAttrEdit(name, value) },
                    onExhibitionTypeRadioBtn = { viewModel.onExhibitionTypeRadioBtn(it) },
                    onSetSettings = { viewModel.setTypeActivity(TypeActivity.Generator) },
                    onBack = { viewModel.onBack() },
                    onStartClick = { viewModel.onStartClick() },
                )
            }
        }
    }

    enum class TypeActivity {
        Settings, Generator
    }

    enum class ExhibitionType {
        Dogs, Cats;

        fun exhibitionTypeStringId() =
            when (this) {
                Dogs -> R.string.dogs
                Cats -> R.string.cats
            }

        fun exhibitionTypeByStringId(stringId: Int) =
            when (stringId) {
                R.string.dogs -> Dogs
                else -> Cats
            }
    }

    companion object {
        internal const val DURATION_MILLIS_PER_DIGIT = 300
    }
}

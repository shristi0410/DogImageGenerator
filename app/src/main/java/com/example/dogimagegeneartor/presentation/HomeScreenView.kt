package com.example.dogimagegeneartor.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.navigation.NavHostController
import com.example.dogimagegeneartor.Destination
import com.example.dogimagegeneartor.R
import com.example.dogimagegeneartor.presentation.common.RoundedButton

/**
 * Displays the home screen with options to generate dogs or view recently generated dogs.
 *
 * @param appNavigationController The navigation controller to handle navigation between screens.
 */
@Composable
fun HomeScreenView(appNavigationController: NavHostController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(dimensionResource(id = R.dimen.dp_16)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(R.string.random_dog_generator),
            style = TextStyle(fontSize = TextUnit(24f, TextUnitType.Sp)),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.dp_96))
        )

        RoundedButton(text = stringResource(R.string.generate_dogs)) {
            appNavigationController.navigate(Destination.GENERATE_DOGS_SCREEN)
        }

        RoundedButton(text = stringResource(R.string.my_recently_genrated_dogs)) {
            appNavigationController.navigate(Destination.RECENTLY_GENERATED_DOGS_SCREEN)
        }


    }
}
package com.example.dogimagegeneartor.presentation

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.example.dogimagegeneartor.R
import com.example.dogimagegeneartor.imagehelper.ImageDownloader.loadImageWithGlide
import com.example.dogimagegeneartor.model.HomeScreenViewmodel
import com.example.dogimagegeneartor.presentation.common.RoundedButton

/**
 * Displays the screen to generate dog images.
 *
 * @param appNavigationController The navigation controller to handle navigation between screens.
 * @param viewmodel The ViewModel that provides the data and business logic for the screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateDogsScreen(appNavigationController: NavHostController, viewmodel: HomeScreenViewmodel) {
    Scaffold (topBar = {
        TopAppBar(
            title = { Text(text = stringResource(R.string.generate_dogs)) },
            navigationIcon = {
                IconButton(
                    onClick = {
                        appNavigationController.navigateUp()
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }, content = {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.dp_16)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            val imageUrl by viewmodel.apiResponse.collectAsState(initial = null)
            var bitmap by remember { mutableStateOf<Bitmap?>(null) }
            val context = LocalContext.current
            imageUrl?.let { url ->
                LaunchedEffect(url.message) {
                    bitmap = loadImageWithGlide(context, url.message)
                }

                bitmap?.let {
                    viewmodel.saveImage(url = url.message, bitmap = it)
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Loaded Image",
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.dp_300))
                            .padding(dimensionResource(R.dimen.dp_16))
                    )
                }
            }

            RoundedButton(text = stringResource(R.string.generate)) {
                viewmodel.generateDogs()
            }


        }
    })
}
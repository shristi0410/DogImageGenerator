package com.example.dogimagegeneartor.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.dogimagegeneartor.R
import com.example.dogimagegeneartor.imagehelper.byteArrayToBitmap
import com.example.dogimagegeneartor.model.HomeScreenViewmodel
import com.example.dogimagegeneartor.model.database.DogImage
import com.example.dogimagegeneartor.presentation.common.RoundedButton

/**
 * Displays the screen with recently generated dog images.
 *
 * @param appNavigationController The navigation controller to handle navigation between screens.
 * @param viewmodel The ViewModel that provides the data and business logic for the screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecentlyGenerated(appNavigationController: NavHostController, viewmodel: HomeScreenViewmodel) {
    Scaffold (topBar = {
        TopAppBar(
            title = { Text(text = stringResource(R.string.my_recently_genrated_dogs)) },
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
        viewmodel.fetchAllImages()
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .padding(dimensionResource(id = R.dimen.dp_16)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            val images by viewmodel.allImages.collectAsState(emptyList())
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(images) { image ->
                    ImageItem(image)
                }
            }

            RoundedButton(text = stringResource(R.string.clear_dogs)) {
                viewmodel.clearData()
            }


        }
    })
}

@Composable
fun ImageItem(imageEntity: DogImage) {
    val bitmap = remember {
        imageEntity.imageData.byteArrayToBitmap()
    }
    Image(
        bitmap = bitmap.asImageBitmap(),
        contentDescription = "Saved Image",
        modifier = Modifier
            .size(300.dp)
    )
}
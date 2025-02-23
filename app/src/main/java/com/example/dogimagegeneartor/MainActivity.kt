package com.example.dogimagegeneartor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dogimagegeneartor.model.HomeScreenViewmodel
import com.example.dogimagegeneartor.model.database.ImageDatabase
import com.example.dogimagegeneartor.model.factory.GenericViewModelFactory
import com.example.dogimagegeneartor.model.network.NetworkApiHelper
import com.example.dogimagegeneartor.model.repo.GenerateRepositoryImpl
import com.example.dogimagegeneartor.model.repo.ImageRepository
import com.example.dogimagegeneartor.presentation.GenerateDogsScreen
import com.example.dogimagegeneartor.presentation.HomeScreenView
import com.example.dogimagegeneartor.presentation.RecentlyGenerated
import com.example.dogimagegeneartor.ui.theme.DogImageGeneartorTheme

/**
 * MainActivity is the entry point of the application.
 * It sets up the navigation and initializes the ViewModel.
 */
class MainActivity : ComponentActivity() {

    private val networkApiService by lazy { NetworkApiHelper.getApiService() }
    private val database by lazy { ImageDatabase.getDatabase(application) }
    private val viewModelFactory = GenericViewModelFactory(
        HomeScreenViewmodel::class.java
    ) {
        val repo = GenerateRepositoryImpl(networkApiService)
        val imageRepo = ImageRepository(database.imageCacheDao())
        HomeScreenViewmodel(repo,imageRepo)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogImageGeneartorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
                    val viewModel  : HomeScreenViewmodel =  viewModel(factory = viewModelFactory)
                    val appNavigationController = rememberNavController()
                    NavHost(
                        navController = appNavigationController, startDestination = Destination.HOME_SCREEN
                    ) {
                        composable(Destination.HOME_SCREEN) {
                            HomeScreenView(appNavigationController)
                        }
                        composable(Destination.GENERATE_DOGS_SCREEN) {
                            GenerateDogsScreen(appNavigationController,viewModel)
                        }
                        composable(Destination.RECENTLY_GENERATED_DOGS_SCREEN) {
                            RecentlyGenerated(appNavigationController,viewModel)
                        }

                    }

                }
            }
        }
    }
}


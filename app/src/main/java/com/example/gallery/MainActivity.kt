package com.example.gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gallery.ui.theme.GalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GalleryTheme {
                Main(Modifier)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val entree = rememberSaveable{ mutableStateOf(entrees[0]) }
    val sideDish = rememberSaveable { mutableStateOf(sideDishes[0]) }
    val accompaniment = rememberSaveable { mutableStateOf(accompaniments[0]) }
    val currentRoute = rememberSaveable { mutableStateOf<String?>(null) }
    Scaffold(modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = if (currentRoute.value != NavRoutes.Start.route) {
                    {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                        }
                    }
                } else {
                    { }
                },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = when (currentRoute.value) {
                                NavRoutes.Start.route -> NavRoutes.Start.header
                                NavRoutes.EntreeMenu.route -> NavRoutes.EntreeMenu.header
                                NavRoutes.SideDishMenu.route -> NavRoutes.SideDishMenu.header
                                NavRoutes.AccompanimentMenu.route -> NavRoutes.AccompanimentMenu.header
                                NavRoutes.OrderCheckout.route -> NavRoutes.OrderCheckout.header
                                else -> NavRoutes.Start.header
                            }
                        )
                    }
                }
            )
        }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = NavRoutes.Start.route,
            modifier = modifier.padding(innerPadding)

        ) {
            composable(NavRoutes.Start.route) {

                Start(onButtonAction = {
                    navController.navigate(NavRoutes.EntreeMenu.route)
                })
            }
            composable(NavRoutes.EntreeMenu.route) {
                EntreeMenu(onPrevButtonAction = {
                    navController.navigateUp()
                }, onNextButtonAction = {
                    navController.navigate(NavRoutes.SideDishMenu.route)
                }, choose = entree)
            }
            composable(NavRoutes.SideDishMenu.route) {
                SideDishMenu(onPrevButtonAction = {
                    navController.navigateUp()
                }, onNextButtonAction = {
                    navController.navigate(NavRoutes.AccompanimentMenu.route)
                }, choose = sideDish)
            }
            composable(NavRoutes.AccompanimentMenu.route) {
                AccompanimentMenu(
                    onPrevButtonAction = {
                        navController.navigateUp()
                    }, onNextButtonAction = {
                        navController.navigate(NavRoutes.OrderCheckout.route)
                    }, choose = accompaniment
                )
            }
            composable(NavRoutes.OrderCheckout.route) { OrderCheckout(
                entree.value,
                sideDish.value,
                accompaniment.value,
                onButtonPressed = {
                    navController.navigate(NavRoutes.Start.route)
                }
            ) }
        }
    }
    DisposableEffect(key1 = navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            currentRoute.value = destination.route
        }
        onDispose {
        }
    }
}

@Composable
fun Start(onButtonAction: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { onButtonAction() }) {
            Text("Start order")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GalleryTheme {
        Start({})
    }
}

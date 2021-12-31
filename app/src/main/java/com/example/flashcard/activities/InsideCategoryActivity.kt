package com.example.flashcard.activities

import android.app.Application
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.flashcard.ScreenRoute
import com.example.flashcard.components.CategoryTopBar
import com.example.flashcard.components.WordCardListBox
import com.example.flashcard.localDatabase.WordViewModel


@ExperimentalFoundationApi
@Composable
fun InsideCategoryActivity(navController: NavController, category_name: String?) {
    Log.d("CHECK_NAV", "InsideCategoryActivity: ${category_name}")
    val category_name = if (category_name != null) category_name else "all"
    var wordCardList =
        WordViewModel(LocalContext.current.applicationContext as Application).getRelatedWords(
            category_name
        )
    Scaffold(topBar = { CategoryTopBar(navController= navController, name= category_name) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(ScreenRoute.AddWordScreenRoute.route + "/$category_name") }
            ) {
                Icon(Icons.Filled.Add, "")
            }
        },
        bottomBar = { }) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            WordCardListBox(live_cards_list = wordCardList)
        }
    }

}
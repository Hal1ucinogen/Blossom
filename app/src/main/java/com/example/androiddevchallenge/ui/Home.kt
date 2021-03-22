/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Checkbox
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.component.BlossomBottomNav
import com.example.androiddevchallenge.ui.component.BlossomCard
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun Home() {
    val navController = rememberNavController()
    val items = HomeScreen.values().toList()
    Scaffold(
        bottomBar = {
            BlossomBottomNav(
                // FIXME It's not an elegant way to process WindowInsets in BottomNavigation #1
                //  56.dp is BottomNavigationHeight
                modifier = Modifier.navigationBarsHeight(56.dp)
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.arguments?.getString("route")
                items.forEach { homeScreen ->
                    BottomNavigationItem(
                        icon = { Icon(homeScreen.icon, homeScreen.title) },
                        label = {
                            Text(
                                homeScreen.title,
                                style = MaterialTheme.typography.caption
                            )
                        },
                        selected = currentRoute == homeScreen.route,
                        onClick = {
                            navController.navigate(homeScreen.route) {
                                popUpTo = navController.graph.startDestination
                                launchSingleTop = true
                            }
                        },
                        // FIXME It's not an elegant way to process WindowInsets in BottomNavigation #2
                        modifier = Modifier.navigationBarsPadding(left = false, right = false)
                    )
                }
            }
        }
    ) {
        NavHost(navController, startDestination = HomeScreen.Home.route) {
            composable(HomeScreen.Home.route) { HomePage() }
            composable(HomeScreen.Favorites.route) { }
            composable(HomeScreen.Profile.route) { }
            composable(HomeScreen.Cart.route) { }
        }
    }
}

@Composable
fun HomePage() {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            var search by remember { mutableStateOf("") }
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "search",
                        modifier = Modifier.size(18.dp)
                    )
                },
                label = {
                    Text(
                        "Search",
                        style = MaterialTheme.typography.body1,
                    )
                },
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 0.dp)
                    .statusBarsPadding(),
                colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colors.onBackground)
            )
            Text(
                text = "Browse themes",
                modifier = Modifier
                    .paddingFromBaseline(32.dp, 8.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground,
            )
            val rowPlants = listOf(
                Plant("Desert chic", R.drawable.desert_chic),
                Plant("Tiny Terrariums", R.drawable.tiny_terrariums),
                Plant("Jungle Vibes", R.drawable.jungle_vibes),
                Plant("Easy care", R.drawable.easy_care),
                Plant("Statements", R.drawable.statements)
            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(rowPlants) { plant ->
                    BlossomCard(
                        modifier = Modifier.size(136.dp)
                    ) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Image(
                                painter = painterResource(id = plant.image),
                                contentDescription = plant.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                            Text(
                                text = plant.name,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = MaterialTheme.typography.h2,
                                modifier = Modifier
                                    .paddingFromBaseline(24.dp, 16.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.padding(start = 16.dp, end = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Design your home garden",
                    modifier = Modifier
                        .paddingFromBaseline(32.dp, 16.dp)
                        .weight(1f),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.onBackground,
                )
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Default.FilterList, contentDescription = "filter")
                }
            }
            val columnPlants = listOf(
                Plant("Monstera", R.drawable.monstera),
                Plant("Aglaonema", R.drawable.aglaonema),
                Plant("Peace lily", R.drawable.peace_lily),
                Plant("Fiddle leaf tree", R.drawable.fiddle_leaf),
                Plant("Snake plant", R.drawable.snake_plant),
                Plant("Pothos", R.drawable.pothos),
                Plant("Fiddle leaf tree", R.drawable.fiddle_leaf),
                Plant("Snake plant", R.drawable.snake_plant),
                Plant("Pothos", R.drawable.pothos)
            )
            LazyColumn(
                contentPadding = PaddingValues(16.dp, 0.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                itemsIndexed(columnPlants) { index, plant ->
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        val (image, title, desc, divider, checkbox) = createRefs()
                        Image(
                            painter = painterResource(id = plant.image),
                            contentDescription = plant.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .aspectRatio(1f, true)
                                .constrainAs(image) {
                                    start.linkTo(parent.start)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                    height = Dimension.fillToConstraints
                                }
                                .clip(MaterialTheme.shapes.small)
                        )
                        Text(
                            text = plant.name,
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier
                                .constrainAs(title) {
                                    start.linkTo(image.end, 16.dp)
                                    top.linkTo(parent.top)
                                }
                                .paddingFromBaseline(top = 24.dp)
                        )
                        Text(
                            text = "This is a description",
                            style = MaterialTheme.typography.body1,
                            modifier = Modifier
                                .constrainAs(desc) {
                                    start.linkTo(title.start)
                                    top.linkTo(title.bottom)
                                    bottom.linkTo(parent.bottom)
                                }
                                .paddingFromBaseline(bottom = 24.dp)
                        )
                        var checked by remember { mutableStateOf(index == 0) }
                        Checkbox(
                            checked = checked,
                            onCheckedChange = { checked = it },
                            modifier = Modifier.constrainAs(checkbox) {
                                end.linkTo(parent.end)
                                bottom.linkTo(desc.bottom, 24.dp)
                            }
                        )
                        Divider(
                            modifier = Modifier
                                .constrainAs(divider) {
                                    start.linkTo(image.end, 8.dp)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                    height = Dimension.value(1.dp)
                                    width = Dimension.fillToConstraints
                                },
                            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
                        )
                    }
                }
            }
        }
    }
}

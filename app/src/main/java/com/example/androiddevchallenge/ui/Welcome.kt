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
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R

@Composable
fun Welcome() {
    Surface(color = MaterialTheme.colors.primary) {
        Box(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.ic_welcome_bg),
                contentDescription = "background",
                modifier = Modifier.fillMaxSize()
            )
            Column(Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.height(72.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_welcome_illos),
                    contentDescription = "leaf",
                    modifier = Modifier
                        .padding(start = 88.dp)
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    alignment = Alignment.TopStart,
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.height(48.dp))
                Image(
                    painter = painterResource(id = R.drawable.ic_logo),
                    contentDescription = "logo",
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "Beautiful home garden solutions",
                    modifier = Modifier
                        .paddingFromBaseline(32.dp, 40.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.onBackground,
                )
                Button(
                    onClick = {},
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary)
                ) {
                    Text(text = "Create account", style = MaterialTheme.typography.button)
                }
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(
                    onClick = {},
                    modifier = Modifier
                        .height(48.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = if (isSystemInDarkTheme()) {
                            MaterialTheme.colors.onBackground
                        } else {
                            MaterialTheme.colors.secondary
                        }
                    )
                ) {
                    Text(
                        text = "Log in",
                        style = MaterialTheme.typography.button
                    )
                }
            }
        }
    }
}

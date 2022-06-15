package com.co.gino.disneycodechallenge_ginoamaurygongora.ui.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.co.gino.disneycodechallenge_ginoamaurygongora.R


@Composable
fun HomeAppBar(
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Box {
        TopAppBar(
            backgroundColor = backgroundColor,
            modifier = modifier.fillMaxWidth()
        ) {
            Box(modifier = Modifier.height(32.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(72.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = stringResource(id = R.string.accessibility_back))
                    }
                }
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.semantics { heading() },
                        text = stringResource(id = R.string.top_bar_title),
                        style = MaterialTheme.typography.body1,
                        color = MaterialTheme.colors.primaryVariant,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
package com.co.gino.disneycodechallenge_ginoamaurygongora.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.co.gino.disneycodechallenge_ginoamaurygongora.R
import com.co.gino.domain.models.Guest

@Composable
fun BottomBarMain(
    guests: SnapshotStateList<Guest>,
    snackbarVisibleState: Boolean,
    changeStatusSnackbar: (status: Boolean) -> Unit
) {
    Box {
        ExtendedFloatingActionButton(
            backgroundColor = if (guests.isNotEmpty()) MaterialTheme.colors.primary else MaterialTheme.colors.secondaryVariant,
            text = {
                Text(
                    text = stringResource(id = R.string.fab_continue),
                    color = Color.White,
                    style = MaterialTheme.typography.body2
                )
            },
            onClick = {
                //TODO Navigation
            },
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
                .fillMaxWidth(),
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp
            )
        )
        SnackbarReservationNeeded(snackbarVisibleState, changeStatusSnackbar = changeStatusSnackbar)
    }
}

@Composable
fun SnackbarReservationNeeded(snackbarVisibleState: Boolean, changeStatusSnackbar: (status:Boolean) -> Unit) {
    if (snackbarVisibleState) {
        Box(
            Modifier
                .height(92.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant),
            contentAlignment = Alignment.TopEnd
        ) {
            Box(Modifier.padding(end = 16.dp, top = 16.dp), contentAlignment = Alignment.TopEnd){
                Row(modifier = Modifier.size(21.dp)) {
                    IconButton(onClick = { changeStatusSnackbar(false) }) {
                        Icon(Icons.Default.Close, contentDescription = stringResource(id = R.string.accessibility_icon_close),tint = Color.White)
                    }
                }
            }
            Box(Modifier.padding(start = 16.dp, top = 16.dp, end = 63.dp)) {
                Row(horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {
                    Column {
                        Text(text = stringResource(id = R.string.snackbar_title), style = MaterialTheme.typography.body1 , color = Color.White)
                        Text(text = stringResource(id = R.string.snackbar_body),  style = MaterialTheme.typography.body2 , color = Color.White)
                    }
                }
            }
        }
    }
}
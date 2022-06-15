package com.co.gino.disneycodechallenge_ginoamaurygongora.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.co.gino.disneycodechallenge_ginoamaurygongora.R
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.widget.BottomBarMain
import com.co.gino.disneycodechallenge_ginoamaurygongora.ui.widget.HomeAppBar
import com.co.gino.disneycodechallenge_ginoamaurygongora.viewmodel.GuestsViewModel
import com.co.gino.domain.models.Guest

@Composable
fun MainScreen(
    viewModel: GuestsViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val appBarColor = MaterialTheme.colors.surface.copy(alpha = 0.87f)
    var guestsChecked = viewModel.guestsWithReservationChecked
    var showReservationNeeded = viewModel.showReservationNeeded
    Scaffold(
        topBar = { HomeAppBar(backgroundColor = appBarColor, Modifier.fillMaxWidth()) },
        floatingActionButton = {
            BottomBarMain(guests = guestsChecked,showReservationNeeded.value,changeStatusSnackbar = {viewModel.setReservationNeededStatus(it)})
        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        HomeContent(
            loading = uiState.loading,
            guests = uiState.success,
            error = uiState.error,
            checkGuest = { guest, checked ->
                viewModel.checkGuest(guest = guest, isChecked = checked)
            },
        )
    }
}

@Composable
fun HomeContent(
    guests: List<Guest>,
    checkGuest: (guest: Guest, checked: Boolean) -> Unit,
    loading: Boolean,
    error: Boolean
) {
    if (loading) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
                .testTag(stringResource(id = R.string.test_tag_loading)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        if (error) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp).testTag(stringResource(id = R.string.test_tag_error)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(Icons.Default.Warning,
                    contentDescription = stringResource(id = R.string.accessibility_icon_error),
                    modifier = Modifier.size(15.dp),
                    tint = MaterialTheme.colors.primaryVariant )
            }
        } else {
            LazyColumn(modifier = Modifier.padding(bottom = 70.dp).testTag(stringResource(id = R.string.test_tag_loaded))) {
                item {
                    Text(
                        text = stringResource(id = R.string.heading_have_reservations),
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                            .semantics { heading() }
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                }
                val guestsWithReservation = guests.filter { it.hasReservation }
                itemsIndexed(guestsWithReservation) { index, item ->
                    GuestItem(guest = item, checkGuest = checkGuest,sizeList = guestsWithReservation.size ,currentItem = index)
                }
                item {
                    Text(
                        text = stringResource(id = R.string.heading_need_reservations),
                        color = MaterialTheme.colors.primaryVariant,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                            .semantics { heading() }
                    )
                    Spacer(modifier = Modifier.padding(top = 20.dp))
                }
                val guestsWithoutReservation =guests.filter { !it.hasReservation }
                itemsIndexed(guestsWithoutReservation) { index, item ->
                    GuestItem(guest = item, checkGuest = checkGuest,sizeList = guestsWithoutReservation.size ,currentItem = index)
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 16.dp, end = 16.dp, top = 20.dp, bottom = 20.dp)
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = stringResource(id = R.string.accessibility_icon_info),
                            modifier = Modifier.size(15.dp),
                            tint = MaterialTheme.colors.primaryVariant
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = stringResource(id = R.string.advice_guest),
                            color = MaterialTheme.colors.primaryVariant,
                            letterSpacing = (-0.28).sp,
                            lineHeight = 21.sp,
                            style = MaterialTheme.typography.subtitle2,
                            modifier = Modifier.padding(start = 6.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun GuestItem(
    guest: Guest,
    checkGuest: (guest: Guest, checked: Boolean) -> Unit,
    sizeList: Int,
    currentItem: Int
) {
    val checked = remember { mutableStateOf(false) }
    val accessibilityCheckedMessage = "${stringResource(id = R.string.accessibility_check)}, $currentItem of $sizeList"
    val accessibilityUncheckedMessage = " ${stringResource(id = R.string.accessibility_uncheck)}, $currentItem of $sizeList"
    Row(
        Modifier
            .padding(start = 16.dp, top = 4.dp, bottom = 4.dp)
            .toggleable(value = checked.value, role = Role.Checkbox, onValueChange = {
                checkGuest.invoke(guest, it)
                checked.value = it
            })
            .semantics {
                stateDescription =
                    if (checked.value) accessibilityCheckedMessage else accessibilityUncheckedMessage
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = null
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = guest.name,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.primaryVariant
        )
    }
}
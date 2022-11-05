package com.technorapper.myfriendsbday.ui.component.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.technorapper.myfriendsbday.data.model.UserInfoModel
import com.technorapper.myfriendsbday.ui.component.list.HorizontalDottedProgressBar
import com.technorapper.myfriendsbday.ui.component.list.NothingHere
import com.technorapper.myfriendsbday.ui.component.list.RecipeCard
import kotlinx.coroutines.ExperimentalCoroutinesApi


@Composable
fun LabsList(
    loading: Boolean,
    Labs: List<UserInfoModel>,
    onClick: (UserInfoModel) -> Unit,
) {
    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
    ) {
        if (loading && Labs.isEmpty()) {
            HorizontalDottedProgressBar()
        } else if (Labs.isEmpty()) {
            NothingHere()
        } else {
            LazyColumn {
                itemsIndexed(
                    items = Labs
                ) { _, labItem ->
                    if(!labItem.name.isEmpty())
                    RecipeCard(
                        lab = labItem,
                        onClick = { onClick.invoke(labItem)


                        }
                    )
                }
            }
        }
    }
}








package com.example.gallery

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gallery.ui.theme.GalleryTheme

@Parcelize
data class ElementDesc(
    val nameId: Int = 0,
    val descriptionId: Int = 0,
    val priceId: Int = 0
) : Parcelable

@Composable
fun Element(
    name: String,
    description: String,
    price: String,
    choose: () -> Unit,
    selected: Boolean
) {
    Row(modifier = Modifier.padding(vertical = 10.dp)) {
        RadioButton(
            selected = selected,
            onClick = choose,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
        Column {
            Text(text = name, fontSize = 20.sp)
            Text(text = description, fontSize = 13.sp)
            Text(text = "$$price", fontSize = 10.sp, fontWeight = FontWeight.Light)
        }
    }
}

@Composable
fun ElementsMenu(
    onNextButtonAction: () -> Unit,
    onPrevButtonAction: () -> Unit,
    choose: MutableState<ElementDesc>,
    elements: Array<ElementDesc>,
    modifier: Modifier = Modifier
) {
    val (selectedOption, onOptionSelected) = remember { mutableIntStateOf(choose.value.nameId) }
    Column {
        LazyColumn(
            modifier = modifier.selectableGroup()
        ) {
            items(elements) { element ->
                Column {
                    Element(
                        name = stringResource(id = element.nameId),
                        description = stringResource(id = element.descriptionId),
                        price = stringResource(id = element.priceId),
                        selected = (element.nameId == selectedOption),
                        choose = {
                            onOptionSelected(element.nameId)
                            choose.value = element
                        }
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        modifier = Modifier.padding(start = 50.dp, end = 20.dp)
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedButton(onClick = onPrevButtonAction, modifier = Modifier.width(150.dp)) {
                Text(text = "Previous")
            }

            Button(onClick = onNextButtonAction, modifier = Modifier.width(150.dp)) {
                Text(text = "Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ElementPreview() {
    GalleryTheme {
        Element(
            name = "Name",
            description = "Something about this element, I don`t know",
            price = "110.23",
            {},
            false
        )
    }
}

package com.example.gallery

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("DefaultLocale")
@Composable
fun OrderCheckout(entree: ElementDesc,
                  sideDish: ElementDesc,
                  accompaniment: ElementDesc,
                  onButtonPressed: () -> Unit){
    val total = stringResource(id = entree.priceId).toFloat() +
            stringResource(id = sideDish.priceId).toFloat() +
            stringResource(id = accompaniment.priceId).toFloat()
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Text("Order Summary", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(id = entree.nameId))
            Text("$"+ stringResource(id = entree.priceId))
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(id = sideDish.nameId))
            Text("$"+ stringResource(id = sideDish.priceId))
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(id = accompaniment.nameId))
            Text("$"+ stringResource(id = accompaniment.priceId))
        }
        val tax = total * 0.1
        val overall = total + tax
        HorizontalDivider(thickness = 1.dp)
        Text("Full total: $${String.format("%.2f", total)}", modifier = Modifier.align(Alignment.End))
        Text("Tax: $${String.format("%.2f", tax)}", modifier = Modifier.align(Alignment.End))
        Text("Total: $${String.format("%.2f", overall)}", modifier = Modifier.align(Alignment.End))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            OutlinedButton(onClick = onButtonPressed, modifier = Modifier.width(150.dp)) {
                Text(text = "Previous")
            }

            Button(onClick = onButtonPressed, modifier = Modifier.width(150.dp)) {
                Text(text = "Next")
            }
        }
    }

}

/*
@Preview(showBackground = true)
@Composable
fun OrderCheckoutPreview() {
    GalleryTheme {
        OrderCheckout()
    }
}*/

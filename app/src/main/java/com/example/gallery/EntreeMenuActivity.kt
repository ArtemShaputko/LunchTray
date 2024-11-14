package com.example.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gallery.ui.theme.GalleryTheme

val entrees = arrayOf(
    ElementDesc(R.string.entree1Name, R.string.entree1Desc, R.string.entree1Price),
    ElementDesc(R.string.entree2Name, R.string.entree2Desc, R.string.entree2Price),
    ElementDesc(R.string.entree3Name, R.string.entree3Desc, R.string.entree3Price),
    ElementDesc(R.string.entree4Name, R.string.entree4Desc, R.string.entree4Price),
    ElementDesc(R.string.entree5Name, R.string.entree5Desc, R.string.entree5Price),
)

@Composable
fun EntreeMenu(
    onNextButtonAction: () -> Unit,
    onPrevButtonAction: () -> Unit,
    choose: MutableState<ElementDesc>,
    modifier: Modifier = Modifier
) {
    ElementsMenu(
        onNextButtonAction = onNextButtonAction,
        onPrevButtonAction = onPrevButtonAction,
        choose = choose,
        elements = entrees,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun EntreeMenuPreview() {
    GalleryTheme {
        val myObject = remember { mutableStateOf(ElementDesc(0,0,0)) }
        EntreeMenu({}, {}, myObject)
    }
}
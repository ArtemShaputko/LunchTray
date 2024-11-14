package com.example.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gallery.ui.theme.GalleryTheme

val sideDishes = arrayOf(
    ElementDesc(R.string.sideDish1Name, R.string.sideDish1Desc, R.string.sideDish1Price),
    ElementDesc(R.string.sideDish2Name, R.string.sideDish2Desc, R.string.sideDish2Price),
    ElementDesc(R.string.sideDish3Name, R.string.sideDish3Desc, R.string.sideDish3Price),
    ElementDesc(R.string.sideDish4Name, R.string.sideDish4Desc, R.string.sideDish4Price),
    ElementDesc(R.string.sideDish5Name, R.string.sideDish5Desc, R.string.sideDish5Price),
)

@Composable
fun SideDishMenu(
    onNextButtonAction: () -> Unit,
    onPrevButtonAction: () -> Unit,
    choose: MutableState<ElementDesc>,
    modifier: Modifier = Modifier
) {
    ElementsMenu(
        onNextButtonAction = onNextButtonAction,
        onPrevButtonAction = onPrevButtonAction,
        choose = choose,
        elements = sideDishes,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun SideDishMenuPreview() {
    GalleryTheme {
        val myObject = remember { mutableStateOf(ElementDesc(0,0,0)) }
        SideDishMenu({}, {}, myObject)
    }
}
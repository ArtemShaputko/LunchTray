package com.example.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gallery.ui.theme.GalleryTheme


val accompaniments = arrayOf(
    ElementDesc(R.string.accompaniment1Name, R.string.accompaniment1Desc, R.string.accompaniment1Price),
    ElementDesc(R.string.accompaniment2Name, R.string.accompaniment2Desc, R.string.accompaniment2Price),
    ElementDesc(R.string.accompaniment3Name, R.string.accompaniment3Desc, R.string.accompaniment3Price),
    ElementDesc(R.string.accompaniment4Name, R.string.accompaniment4Desc, R.string.accompaniment4Price),
    ElementDesc(R.string.accompaniment5Name, R.string.accompaniment5Desc, R.string.accompaniment5Price),
)

@Composable
fun AccompanimentMenu(
    onNextButtonAction: () -> Unit,
    onPrevButtonAction: () -> Unit,
    choose: MutableState<ElementDesc>,
    modifier: Modifier = Modifier
) {
    ElementsMenu(
        onNextButtonAction = onNextButtonAction,
        onPrevButtonAction = onPrevButtonAction,
        choose = choose,
        elements = accompaniments,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun AccompanimentMenuPreview() {
    GalleryTheme {
        val myObject = remember { mutableStateOf(ElementDesc(0,0,0)) }
        AccompanimentMenu(
            onNextButtonAction = {},
            onPrevButtonAction = {},
            choose = myObject
        )
    }
}


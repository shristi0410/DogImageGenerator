package com.example.dogimagegeneartor.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.example.dogimagegeneartor.R
import com.example.dogimagegeneartor.ui.theme.PrimaryColor

@Composable
fun RoundedButton(text : String, onClick: () -> Unit){

    TextButton(
        onClick = { onClick() },
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(dimensionResource(id = R.dimen.dp_16))
            .clip(
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp_24))
            )
            .border(
                width = dimensionResource(R.dimen.dp_1),
                color = Color.Black,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.dp_24))
            )
            .background(PrimaryColor),
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.dp_12))
    ) {
        Text(
            text = text,
            color = Color.White ,
            fontSize = TextUnit(16F, TextUnitType.Sp)
        )

    }
}
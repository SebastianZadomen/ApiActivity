package com.example.apiappactividad.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel


class ViewDesign : ViewModel() {

    var gridCells by mutableStateOf(0)
    var imageSizeScreen1 by mutableStateOf(50.dp)
    var imageSizeDetailScreen by mutableStateOf(250.dp)
    val paddingTop by mutableStateOf(60.dp)
    var paddingStart by mutableStateOf(10.dp)
    var paddingEnd by mutableStateOf(10.dp)

    var fontSizeName by mutableStateOf(22)
    var fontSizeDetail by mutableStateOf(15)
}
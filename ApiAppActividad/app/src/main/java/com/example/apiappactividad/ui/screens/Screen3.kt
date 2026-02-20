package com.example.apiappactividad.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.apiappactividad.R
import com.example.apiappactividad.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen3(navController: NavController, viewModel : MainViewModel) {

            ConstraintLayout(Modifier.fillMaxSize()) {

                val ( OpMode , switch, OpShow, list,dropMenu) = createRefs()

                Text("Dark Mode", fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(OpMode){
                        top.linkTo(parent.top, margin = 200.dp)
                        start.linkTo(parent.start, margin = 85.dp)
                    }
                )
                Switch(checked = viewModel.state, onCheckedChange = { viewModel.state = !viewModel.state},
                    modifier = Modifier.constrainAs(switch){
                        start.linkTo(OpMode.end, margin = 20.dp)
                        top.linkTo(parent.top, margin = 193.dp)
                    })

                Text("Show Mode", fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(OpShow){
                        top.linkTo(OpMode.bottom, margin = 90.dp)
                        start.linkTo(parent.start, margin = 40.dp)
                    })

                Box(modifier = Modifier.constrainAs(list){
                    top.linkTo(OpMode.bottom, margin = 78.dp)
                    start.linkTo(OpShow.end, margin = 20.dp)
                }){
                    OutlinedTextField(
                        value = viewModel.selectedText,
                        onValueChange = { viewModel.selectedText = it },
                        enabled = false,
                        readOnly = true,
                        modifier = Modifier
                            .clickable { viewModel.expanded = true }
                            .height(50.dp).width(100.dp)
                    )

                    DropdownMenu(
                        expanded = viewModel.expanded,
                        onDismissRequest = { viewModel.expanded = false },
                        modifier = Modifier.height(100.dp).width(100.dp)
                    ) {
                        viewModel.showMode.forEach { showMode ->
                            DropdownMenuItem(text = { Text(text = showMode) },
                                onClick = {
                                    viewModel.expanded = false
                                    viewModel.selectedText = showMode
                                    if(showMode == "Grid"){
                                        viewModel.showGrid = false
                                    }
                                    else{
                                        viewModel.showGrid = true
                                    }
                                })
                        }
                    }
                }


            }
    }





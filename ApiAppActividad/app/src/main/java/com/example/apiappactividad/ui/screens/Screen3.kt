package com.example.apiappactividad.ui.screens

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.apiappactividad.ui.viewmodel.CharacterViewModel
import com.example.apiappactividad.ui.viewmodel.MainViewModel
import com.example.apiappactividad.ui.viewmodel.SettingsViewModel
import com.example.apiappactividad.ui.viewmodel.ViewDesign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen3(
    navController: NavController,
    viewModel: MainViewModel,
    vDesign: ViewDesign,
    reciView1: SettingsViewModel,
    bdViewModel: CharacterViewModel
) {

            ConstraintLayout(Modifier.fillMaxSize()) {

                val ( OpMode , switch, OpShow, list, btnDelete) = createRefs()

                Text("Dark Mode", fontSize = 30.sp,
                    color = vDesign.colorFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(OpMode){
                        top.linkTo(parent.top, margin = 200.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        horizontalBias = 0.3f
                    }
                )
                Switch(checked = reciView1.modeDark, onCheckedChange = { reciView1.cambiarMode()},
                    modifier = Modifier.constrainAs(switch){
                        start.linkTo(OpMode.end, margin = 20.dp)
                        top.linkTo(parent.top, margin = 193.dp)
                    })

                Text("Show Mode", fontSize = 30.sp,
                    color = vDesign.colorFont,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(OpShow){
                        top.linkTo(OpMode.bottom, margin = 90.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        horizontalBias = 0.3f
                    })

                Box(modifier = Modifier.constrainAs(list){
                    top.linkTo(OpMode.bottom, margin = 78.dp)
                    start.linkTo(OpShow.end, margin = 20.dp)
                }){
                    OutlinedTextField(
                        value = reciView1.showMode,
                        onValueChange = { reciView1.actualizarShowMode(it) },
                        enabled = false,
                        readOnly = true,
                        colors = TextFieldDefaults.colors(Color.Gray),
                        modifier = Modifier
                            .clickable { reciView1.expanded = true }
                            .height(50.dp)
                            .width(100.dp)
                    )

                    DropdownMenu(
                        expanded = reciView1.expanded,
                        onDismissRequest = { reciView1.expanded = false },
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp)
                    ) {
                        reciView1.showlistMode.forEach { showMode ->
                            DropdownMenuItem(text = { Text(text = showMode,color = Color.Black) },
                                onClick = {
                                    reciView1.expanded = false
                                    reciView1.actualizarShowMode(showMode)
                                    if(showMode == "Grid"){
                                        reciView1.actualizarShowGrid(false)
                                    }
                                    else{
                                        reciView1.actualizarShowGrid(true)
                                    }
                                })
                        }
                    }
                }
                Button(onClick = { bdViewModel.deleteAllFavorites()},
                    modifier = Modifier.padding(8.dp).constrainAs(btnDelete){
                        top.linkTo(list.bottom, margin = 90.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        horizontalBias = 0.5f
                    },
                    colors = ButtonDefaults.buttonColors(Color.Red),
                    )
                {
                    Text("Delete Favs")
                }




            }
    }





package com.example.unitconverter

import android.graphics.Paint.Style
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverter()
        }
    }
}

@Composable
fun UnitConverter(){

    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember{ mutableStateOf("") }
    var inputunit by remember{ mutableStateOf("Centimeters") }
    var outputunit  by remember{ mutableStateOf("Meters") }
    var iexpand by  remember{ mutableStateOf(false) }
    var oexpand by remember{ mutableStateOf(false) }
    val conversion = remember { mutableStateOf(1.00) }
    val oconversion = remember { mutableStateOf(1.00) }

    fun unitconvert(){

     val inputvalueDouble = inputvalue.toDoubleOrNull() ?: 0.0
     val Result = (inputvalueDouble * conversion.value * 100.0 / oconversion.value).roundToInt() / 100.0
          outputvalue = Result.toString()
 }

  Column(modifier = Modifier.fillMaxSize(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
      )

  {
      Text("Unit Converter",style = MaterialTheme.typography.headlineLarge)
      Spacer(modifier = Modifier.height(16.dp))
      OutlinedTextField(value = inputvalue, onValueChange = {
          inputvalue = it},
          label = {Text("Enter Value")})
     unitconvert()

      Spacer(modifier = Modifier.height(16.dp))
         Row {
        Box{
           Button(onClick = { iexpand = true }) {
               Text(inputunit)
               Icon(Icons.Default.ArrowDropDown,
                   contentDescription = "Arrow Down")
               DropdownMenu(expanded = iexpand,
                   onDismissRequest = { iexpand = false })
               {
                   DropdownMenuItem(text = { Text("Centimeters") },
                       onClick = { iexpand = false
                           inputunit = "Centimeters"
                           conversion.value = 0.01
                           unitconvert()
                       })
                   DropdownMenuItem(text = { Text("Meters") },
                       onClick = { iexpand = false
                           inputunit = "Meters"
                           conversion.value = 1.00
                           unitconvert() })
                   DropdownMenuItem(text = { Text("Feet") },
                       onClick = {  iexpand = false
                           inputunit = "Feet"
                           conversion.value = 0.3048
                           unitconvert() })
                   DropdownMenuItem(text = { Text("Millimeters") },
                       onClick = { iexpand = false
                           inputunit = "Millimeters"
                           conversion.value = 0.001
                           unitconvert() })
               }
           }
        }
             Spacer(modifier = Modifier.width(32.dp))
             Box{
                 Button(onClick = {oexpand = true }) {
                     Text(outputunit)
                     Icon(Icons.Default.ArrowDropDown,
                         contentDescription = "Arrow Down")
                     DropdownMenu(expanded = oexpand,
                         onDismissRequest = { oexpand = false })
                     {
                         DropdownMenuItem(text = { Text("Centimeters") },
                             onClick = { oexpand = false
                                 outputunit = "Centimeters"
                                 oconversion.value = 0.01
                                 unitconvert() })
                         DropdownMenuItem(text = { Text("Meters") },
                             onClick = {oexpand = false
                                 outputunit = "Meters"
                                 oconversion.value = 1.00
                                 unitconvert() })
                         DropdownMenuItem(text = { Text("Feet") },
                             onClick = { oexpand = false
                                 outputunit = "Feet"
                                 oconversion.value = 0.3048
                                 unitconvert()  })
                         DropdownMenuItem(text = { Text("Millimeters") },
                             onClick = { oexpand = false
                                 outputunit = "Millimeters"
                                 oconversion.value = 0.001
                                 unitconvert() })
                     }
                 }
             }

         }
      Spacer(modifier = Modifier.height(32.dp))
      Text("Result: $outputvalue $outputunit",
       style = MaterialTheme.typography.headlineMedium )
  }
}

@Preview(showBackground = true, device = "id:pixel_8_pro", showSystemUi = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}
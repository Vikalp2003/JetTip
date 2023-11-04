package com.example.jettip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jettip.components.InputField
import com.example.jettip.ui.theme.JetTipTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetTipTheme {
                MyApp {
//                    TopHeader()
                    MainContent()
                }
            }
        }
    }
}

@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 134.0){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height((150.dp))
        .clip(CircleShape.copy(all = CornerSize(12.dp))),
        color = Color(0xFFED7F7)
//        .clip(RoundedCornerShape(corner = CornerSize(12.dp)))
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val  total = "%.2f".format(totalPerPerson)
            Text(text = "Total Per Person",
                style = MaterialTheme.typography.headlineMedium)
            Text(text = "Rs $total", style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.ExtraBold)
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    Surface(color = MaterialTheme.colorScheme.background) {
        content()
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetTipTheme {

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Preview
@Composable
fun MainContent(){

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier: Modifier=Modifier,
             onValChange: (String)->Unit={},

             ){
    val totalBillsState = remember {
        mutableStateOf("")
    }
    val validState = remember(totalBillsState.value) {
        totalBillsState.value.trim().isNotEmpty()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column {
            InputField(valueState = totalBillsState,
                labelId = "Enter Bill",
                enabled =true ,
                isSingleLine =true,
                onAction = KeyboardActions({
                    if(!validState) return@KeyboardActions
                    //Todo- onvaluedchanged

                    keyboardController?.hide()
                })
            )
        }

    }

}
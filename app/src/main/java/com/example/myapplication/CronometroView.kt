//import android.text.Layout.Alignment
import android.text.format.DateUtils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cronometro.ui.theme.CronometroTheme
import kotlinx.coroutines.delay

@Composable
fun CronometroView(modifier: Modifier = Modifier) {
    var prendido by remember {
        mutableStateOf(false)
    }

    var tiempo by remember {
        mutableStateOf<Long>(0)
    }

    LaunchedEffect(prendido){
        while (prendido){
            delay(1)
            tiempo++
        }
    }


    var ultimaVuelta by remember {
        mutableStateOf<Long>(0)
    }


    var vueltas = remember {
        mutableStateListOf<Long>()
    }

    /*var vueltas by remember {
        mutableStateOf<List<Long>>(listOf())
    }*/


     Column(modifier = modifier.background(color = MaterialTheme.colorScheme.surface)) {
         Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.SpaceAround) {
             Text(
                 text = DateUtils.formatElapsedTime(tiempo),
                 modifier = Modifier
                     .align(Alignment.CenterHorizontally),
                 style = MaterialTheme.typography.titleLarge
             )
             if (prendido){
                 Row(modifier = Modifier.fillMaxWidth() ,
                     horizontalArrangement = Arrangement.SpaceAround
                 ) {
                     Button(
                         onClick = {
                             /*TODO*/
                             var vuelta = tiempo - ultimaVuelta
                             ultimaVuelta = tiempo
                             vueltas.add(vuelta)
                         },
                         modifier = Modifier
                             .height(100.dp)
                             .aspectRatio(1f),
                         colors = ButtonDefaults.buttonColors((MaterialTheme.colorScheme.tertiary))
                     ) {
                         Text(text = "Lap")
                     }

                     //Spacer(modifier = Modifier.width(190.dp))

                     Button(onClick = { /*Esto deberia Pasar a false*/
                                    prendido = false
                                      }, modifier = Modifier
                         .height(100.dp)
                         .aspectRatio(1f),
                         colors = ButtonDefaults.buttonColors((MaterialTheme.colorScheme.primary))) {
                         Text(text = "Stop")
                     }
                 }
             }else{
                 Row(modifier = Modifier.fillMaxWidth() ,
                     horizontalArrangement = Arrangement.SpaceAround
                 ) {
                     Button(onClick = { /*TODO*/
                                      tiempo = 0
                                      vueltas.clear()
                                      //  vueltas = listOf()
                                      ultimaVuelta = 0
                                      },
                         modifier = Modifier
                             .height(100.dp)
                             .aspectRatio(1f),
                         colors = ButtonDefaults.buttonColors((MaterialTheme.colorScheme.tertiary))
                     ) {
                         Text(text = "Restart")
                     }

                     //Spacer(modifier = Modifier.width(190.dp))

                     Button(onClick = { /*Esto deberia Pasar a true*/
                                        prendido = true

                                      }, modifier = Modifier
                         .height(100.dp)
                         .aspectRatio(1f),
                         colors = ButtonDefaults.buttonColors((MaterialTheme.colorScheme.secondary))) {
                         Text(text = "Start")
                     }
                 }
             }

         }

        /*Column(modifier = Modifier
            .weight(1f)
            .align(Alignment.CenterHorizontally)) {

            vueltas.forEach(){

                Text(DateUtils.formatElapsedTime(it))
            }
        }*/

         LazyColumn(modifier = Modifier
             .weight(1f)
             .align(Alignment.CenterHorizontally)) {

             var contador = 1

             items(items = vueltas){
                 Divider(
                     modifier = modifier
                         .padding(vertical = 30.dp)
                         .fillMaxWidth(),
                     thickness = 2.dp,
                     color = Color.Black
                 )
                 Row(modifier = Modifier.fillMaxWidth() ,
                     horizontalArrangement = Arrangement.SpaceAround) {

                     Text(text = "Vuelta " + contador++ + ": ")
                     Text(DateUtils.formatElapsedTime(it))
                 }

             }

         }

    }

}

@Preview(showBackground = true)
@Composable
fun CronometroPreview() {
    CronometroTheme(true) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ){
        CronometroView()
        }
    }
}
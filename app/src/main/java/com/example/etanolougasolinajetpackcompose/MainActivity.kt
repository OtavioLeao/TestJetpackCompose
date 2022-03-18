package com.example.etanolougasolinajetpackcompose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.etanolougasolinajetpackcompose.ui.theme.EtanolOuGasolinaJetpackComposeTheme

class MainActivity : ComponentActivity() {

    var resultado = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EtanolOuGasolinaJetpackComposeTheme {
                Scaffold(
                    topBar = {
                        Toolbar()
                    }
                ) {
                    Column {
                        Body()
                    }
                }
            }
        }
    }


    @Composable
    private fun Toolbar() {
        TopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            backgroundColor = colorResource(id = R.color.purple_500)
        )
    }


    @Composable
    fun Body() {

        var valueEtanol by remember {
            mutableStateOf(value = "")
        }
        var valueGasolina by remember {
            mutableStateOf(value = "")
        }


        Column(
            modifier = Modifier
                .padding(30.dp)
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Text(text = "Etanol")
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = valueEtanol,
                onValueChange = {
                    valueEtanol = it
                },
                placeholder = { Text(text = "R$0,00") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        Log.e("Etanol", valueEtanol)
                    })
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Text(text = "Gasolina")
            Spacer(modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                value = valueGasolina,
                onValueChange = {
                    valueGasolina = it
                },
                placeholder = { Text(text = "R$0,00") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        // Log.e("Resultado", fazerCalculo(etanol = valueEtanol, gasolina = valueGasolina))
                    })
            )
            Spacer(modifier = Modifier.padding(12.dp))
            Button(
                onClick = {
                    resultado = fazerCalculo(valueEtanol, valueGasolina)
                    ProximaTela(resultado)
                },
                contentPadding = PaddingValues(
                    start = 20.dp,
                    top = 12.dp,
                    end = 20.dp,
                    bottom = 12.dp
                ),

                ) {
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Calcular")
            }
        }
    }

    private fun fazerCalculo(etanol: String?, gasolina: String?): String {
        val result = (etanol?.toDouble() ?: 0.0) / (gasolina?.toDouble() ?: 0.0)
        return if (result <= 0.7) "Alcool" else "gasolina"
    }

    private fun ProximaTela(resultado:String){
        val intent = Intent(this, TelaResultado::class.java)
        intent.putExtra("result", resultado)
        startActivity(intent)
    }

    //@Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {

        Scaffold(
            topBar = { Toolbar() }

        ) {

            Column {
                Body()
            }
        }
    }
}
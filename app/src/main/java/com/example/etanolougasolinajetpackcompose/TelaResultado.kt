package com.example.etanolougasolinajetpackcompose

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource

class TelaResultado : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var resultado = intent.getStringExtra("result")
        setContent {
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    backgroundColor = colorResource(id = R.color.purple_500),
                    navigationIcon = {
                        IconButton(onClick = { onBackPressed()}) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                        
                    }}
                )
            }
            ) {
                Text(text = resultado!!)
            }
        }
    }
}
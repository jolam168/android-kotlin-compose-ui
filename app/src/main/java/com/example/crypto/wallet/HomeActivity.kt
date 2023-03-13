package com.example.crypto.wallet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crypto.wallet.model.SolanaWallet

class HomeActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "screen1") {
                composable(route = "screen1") {
                    TokenBalancesScreen(navController)
                }
                composable(route = "screen2") {
                    Screen2(navController)
                }
            }
        }
    }
}

@Composable
fun SearchTextField() {
    val textState = remember { mutableStateOf(TextFieldValue()) }
    TextField(
        modifier = Modifier,
        value = textState.value,
        onValueChange = { textState.value = it },
        leadingIcon = {
            Icon(Icons.Filled.Search, contentDescription = "Search")
        },
        placeholder = { Text("Search") }
    )
}

@Preview
@Composable
fun PreviewSearchTextField() {
    SearchTextField()
}

@Preview("Screen2")
@Composable
fun PreviewScreen2() {
    val navController = rememberNavController()
    Screen2(navController)
}

@Composable
fun Screen2(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Screen 2")
        Row() {
            Button(onClick = { }) {
                Text(text = "Buy")
            }
            Button(onClick = { }) {
                Text(text = "Sell")
            }
        }
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Back to Screen 1")
        }
    }
}

@Composable
fun TokenBalanceItem(
    tokenBalance: SolanaWallet.Balance,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .clickable(onClick = { navController.navigate("screen2") })
            .padding(4.dp)
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        TokenCard(tokenName = tokenBalance.tokenName, tokenAmount = tokenBalance.tokenAmount)
    }
}

@Preview("card")
@Composable
fun PreviewTokenCard() {
    TokenCard(tokenName = "", tokenAmount = "")
}

@Composable
fun TokenCard(tokenName: String, tokenAmount: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Column(Modifier.padding(8.dp)) {
            Text(
                text = tokenName,
                style = MaterialTheme.typography.h4,
                color = MaterialTheme.colors.onSurface,
            )
            Text(
                text = tokenAmount,
                style = MaterialTheme.typography.body2,
            )
        }

    }
}

@Preview
@Composable
fun PreviewTokenBalancesScreen() {
    val navController = rememberNavController()
    TokenBalancesScreen(navController)
}

@Composable
fun TokenBalancesScreen(navController: NavController) {
    val solanaWallet = SolanaWallet()

    val tokenBalances = solanaWallet.getTokenBalances()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = "Token Balances",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(tokenBalances) { balance ->
                TokenBalanceItem(
                    tokenBalance = balance,
                    navController = navController
                )
            }
        }
    }
}


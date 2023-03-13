package com.example.crypto.wallet.model

class SolanaWallet {
    fun getTokenBalances(): List<Balance> {
        return listOf(
            Balance("Solana", "3,000"),
            Balance("Bitcoin", "3,000"),
            Balance("Ethereum", "3,000"),
            Balance("Litecoin", "3,000"),
            Balance("Ripple", "3,000"),
            Balance("Dash", "3,000"),
        )
    }

    data class Balance(
        val tokenName: String,
        val tokenAmount: String
    )
}

package com.example.wallet.dto;

public class WalletResponse {
    private long balance;

    public WalletResponse(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }
}

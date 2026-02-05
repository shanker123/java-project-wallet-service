
package com.example.wallet.service;

import java.util.UUID;

public interface WalletService {
    long process(UUID walletId, String operationType, long amount);
    long getBalance(UUID walletId);
}

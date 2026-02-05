
package com.example.wallet.service;

import com.example.wallet.entity.Wallet;
import com.example.wallet.exception.*;
import com.example.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;

    public WalletServiceImpl(WalletRepository repository) {
        this.repository = repository;
    }

    @Override
    public long process(UUID walletId, String operationType, long amount) {
        Wallet wallet = repository.findByIdForUpdate(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found"));

        if ("WITHDRAW".equalsIgnoreCase(operationType)) {
            if (wallet.getBalance() < amount) {
                throw new InsufficientFundsException("Not enough funds");
            }
            wallet.setBalance(wallet.getBalance() - amount);
        } else if ("DEPOSIT".equalsIgnoreCase(operationType)) {
            wallet.setBalance(wallet.getBalance() + amount);
        } else {
            throw new IllegalArgumentException("Invalid operation type");
        }

        return wallet.getBalance();
    }

    @Override
    public long getBalance(UUID walletId) {
        return repository.findById(walletId)
                .orElseThrow(() -> new NotFoundException("Wallet not found"))
                .getBalance();
    }
}

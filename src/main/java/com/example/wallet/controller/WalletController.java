
package com.example.wallet.controller;

import com.example.wallet.dto.*;
import com.example.wallet.service.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class WalletController {

    private final WalletService service;

    public WalletController(WalletService service) {
        this.service = service;
    }

    @PostMapping("/wallet")
    public WalletResponse operate(@RequestBody WalletRequest request) {
        long balance = service.process(
                request.getWalletId(),
                request.getOperationType(),
                request.getAmount()
        );
        return new WalletResponse(balance);
    }

    @GetMapping("/wallets/{id}")
    public WalletResponse getBalance(@PathVariable UUID id) {
        return new WalletResponse(service.getBalance(id));
    }
}

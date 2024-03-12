package com.Task01_CashCardFullStack.CashCardBackend.Controllers;

import com.Task01_CashCardFullStack.CashCardBackend.Entities.CashCard;
import com.Task01_CashCardFullStack.CashCardBackend.Service.CashCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final CashCardService cashCardService;

    @Autowired
    public UserController(CashCardService cashCardService){
        this.cashCardService = cashCardService;
    }

    @GetMapping("/cashCards")
    public ResponseEntity<List<CashCard>> getCashCards(Principal principal){
        List<CashCard> cashCards = cashCardService.getAllCashCards(principal.getName());
        return ResponseEntity.ok(cashCards);
    }

    @PostMapping("/cashCards")
    public ResponseEntity<Void> postCashCard(@RequestBody CashCard cashCard){
        cashCardService.createCashCard(cashCard);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/cashCards")
    public ResponseEntity<Void> updateCashCard(@RequestBody CashCard cashCard){
        CashCard card = cashCardService.getById(cashCard.getId());
        if(card == null){
            return ResponseEntity.notFound().build();
        }
        cashCardService.updateCashCard(cashCard);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cashCards/{id}")
    public ResponseEntity<Void> deleteCashCard(@PathVariable int id){
        cashCardService.deleteCashCard(id);
        return null;
    }
}

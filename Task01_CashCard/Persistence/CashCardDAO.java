package Persistence;

import Entities.CashCard;

import java.util.List;

public interface CashCardDAO {
    void createCard(CashCard cashCard);
    CashCard updateCard(CashCard cashCard);
    void deleteCard(String name);

    List<CashCard> getAllCards();
}

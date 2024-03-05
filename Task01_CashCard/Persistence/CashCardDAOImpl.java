package Persistence;

import Entities.CashCard;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class CashCardDAOImpl implements CashCardDAO{

    @Override
    public void createCard(CashCard cashCard) {
        try {
            String file = "Persistence//Data.csv";
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = "";

//            Check if CashCard already exist
            while((line = br.readLine()) != null){
                String[] data = line.split(",");

                if(data[0].compareTo(cashCard.getName()) == 0){
                    System.out.println("Name already exist");
                    return;
                }
            }

//            Create new
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(cashCard.getName()+","+cashCard.getAmount()+"\n");

            bw.flush();
            bw.close();
            fw.close();

            System.out.println("Append successfully");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public CashCard updateCard(CashCard cashCard) {
        return null;
    }

    @Override
    public void deleteCard(String name) {

    }

    @Override
    public List<CashCard> getAllCards() {
        return null;
    }
}

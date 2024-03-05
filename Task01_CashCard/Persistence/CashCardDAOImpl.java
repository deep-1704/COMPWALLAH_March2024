package Persistence;

import Entities.CashCard;

import java.io.*;
import java.util.ArrayList;
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
    public CashCard updateCard(String name, CashCard cashCard) {
//        Create a new CSV file with updated record and then rename it with the original one

        String newCsv = "Persistence//temp.csv";
        String oldCsv = "Persistence//Data.csv";
        try{
            File temp = new File(newCsv);
            File Data = new File(oldCsv);
            if(!temp.createNewFile()){
                throw new RuntimeException("Error creating file");
            }
            BufferedReader br = new BufferedReader(new FileReader(oldCsv));
            BufferedWriter bw = new BufferedWriter(new FileWriter(newCsv));
            String line = "";

            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                if(row[0].compareTo(name) == 0){
                    bw.write(cashCard.getName()+","+cashCard.getAmount()+"\n");
                }
                else{
                    bw.write(line+"\n");
                }
            }

            if(!Data.delete()){
                throw new RuntimeException("Error deleting Data");
            }
            if(!temp.renameTo(Data)){
                throw new RuntimeException("Error renaming temp");
            }

            br.close();
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return cashCard;
    }

    @Override
    public void deleteCard(String name) {
//        Create a new CSV file with deleted record and then rename it with the original one

        String newCsv = "Persistence//temp.csv";
        String oldCsv = "Persistence//Data.csv";
        try{
            File temp = new File(newCsv);
            File Data = new File(oldCsv);
            if(!temp.createNewFile()){
                throw new RuntimeException("Error creating file");
            }
            BufferedReader br = new BufferedReader(new FileReader(oldCsv));
            BufferedWriter bw = new BufferedWriter(new FileWriter(newCsv));
            String line = "";

            while((line = br.readLine()) != null){
                String[] row = line.split(",");
                if(row[0].compareTo(name) != 0){
                    bw.write(line+"\n");
                }
            }

            if(!Data.delete()){
                throw new RuntimeException("Error deleting Data");
            }
            if(!temp.renameTo(Data)){
                throw new RuntimeException("Error renaming temp");
            }

            br.close();
            bw.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<CashCard> getAllCards() {
        String file = "Persistence//Data.csv";
        String line = "";

        List<CashCard> cashCards = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            while((line = br.readLine()) != null){
                String[] cashCard = line.split(",");
                cashCards.add(new CashCard(cashCard[0], Integer.valueOf(cashCard[1])));
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return cashCards;
    }
}

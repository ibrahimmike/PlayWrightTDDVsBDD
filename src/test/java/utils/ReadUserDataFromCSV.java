package utils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReadUserDataFromCSV {



    public static List<String[]> getTestData(){
        List<String[]> data = new ArrayList<>() ;
        try{
            CSVReader reader = new CSVReader(new FileReader("./userLoginData.csv"));



              //  System.out.println("I am inside the while loop : "+Arrays.toString(reader.readNext()));
             //  data.add(reader.readNext());
                data = reader.readAll();


            for (String[] line : data){
                for (int i = 0; i < line.length; i++){
                    System.out.print(line[i] + "  ");
                }
                System.out.println("\n");

            }
           // System.out.println(data);



        }catch (IOException e){

        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}

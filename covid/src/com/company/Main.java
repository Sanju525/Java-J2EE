package com.company;

import com.company.database.DataBase;
import com.company.database.Person;
import com.company.database.VaccineData;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws ParseException {
//        write your code here
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.print(
                    "==============menu==============\n" +
                            "1.Add the vaccine stock\n" +
                            "2.show the count of vaccines\n" +
                            "3.Adding the details of person\n" +
                            "4.view the person details\n" +
                            "5.exit\n" +
                            "Enter your choice:\t"
            );
            int choice = sc.nextInt();
            VaccineData vd = new VaccineData();
            if (choice == 1) {
                vd.setVaccineAvailable(sc.nextInt());
            } else if (choice == 2) {
                System.out.println("Available Vaccines: " + vd.getVaccineAvailable());
            } else if (choice == 3) {
                Person person = new Person();
                if(DataBase.VaccineAvailable>0) {
                    person.getDetails();
                    DataBase.array.add(person);
                }
                else{
                    System.out.println("No vaccine available!");
                }
            } else if (choice == 4) {
                if(DataBase.array.size()>0){
                    for(int i=0;i<DataBase.array.size();i++){
                        Person details = DataBase.array.get(i);
                        System.out.println("------------------------------------------" + "\n" +
                                            "| Aadhar card number | "+details.getAadharNumber() +"\n"+
                                            "| Person Name        | "+details.getName() +"\n"+
                                            "| Phone Number       | "+details.getPhoneNumber() +"\n"+
                                            "| Vaccinated         | "+details.getVaccinated() +"\n"+
                                            "------------------------------------------");
                    }
                }
                else {
                    System.out.println("No data available!\n");
                }
            } else if (choice == 5) {
                System.exit(0);
            } else {
                throw new IllegalStateException("Unexpected value: " + choice);
            }
        }
    }
}
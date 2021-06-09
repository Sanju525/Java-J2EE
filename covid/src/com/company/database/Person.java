package com.company.database;

import com.company.Main;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Scanner;

public class Person {
    private String name;
    private String aadharNumber;
    private String phoneNumber;
    private String vaccinationDate;
    private int vaccinated;

    public void getDetails() throws ParseException {
        System.out.print("Enter Aadhar number: \t");
        setAadharNumber(new Scanner(System.in).next());
        System.out.print("Enter name: \t");
        setName(new Scanner(System.in).nextLine());
        System.out.print("Enter Phone number: \t");
        setPhoneNumber(new Scanner(System.in).nextLine());
        System.out.print("Enter Vaccination Date inFormat(yyyy-mm-dd): \t");
        setVaccinationDate(new Scanner(System.in).nextLine());
        setVaccinated();
        DataBase.VaccineAvailable--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAadharNumber() {
        return aadharNumber;
    }

    public void setAadharNumber(String aadharNumber) throws ParseException {
        if(isValidAadhar(aadharNumber)){
            if(isInDatabase(aadharNumber)){
                if(isVaccinatedTwice(aadharNumber)){
                    System.out.println("You are vaccinated twice.\nStay Home! Stay Safe!\n");
                }
                else{
                    if(isReady(aadharNumber)) {
                        System.out.println("You are ready for the second dose.\n");
                        for (int i = 0; i < DataBase.array.size(); i++) {
                            Person details = DataBase.array.get(i);
                            if (aadharNumber.equals(details.getAadharNumber())) {
                                DataBase.array.get(i).setVaccinated();
                                System.out.println("---------------Your Details---------------" + "\n" +
                                        "| Aadhar card number | " + details.getAadharNumber() + "\n" +
                                        "| Person Name        | " + details.getName() + "\n" +
                                        "| Phone Number       | " + details.getPhoneNumber() + "\n" +
                                        "| Vaccinated         | " + details.getVaccinated() + "\n" +
                                        "------------------------------------------");
                                DataBase.VaccineAvailable--;
                                break;
                            }
                        }
                    }
                    else {
                        String input = null;
                        for(int i=0;i<DataBase.array.size();i++){
                            Person person = DataBase.array.get(i);
                            if(aadharNumber.equals(person.getAadharNumber())){
                                input=DataBase.array.get(i).vaccinationDate;
                                break;
                            }
                        }
                        LocalDate inputDate = LocalDate.parse(input);
                        LocalDate waitUntil = inputDate.plusDays(30);
                        System.out.println("You are not yet ready to take the second dose!\nPlease wait until: " +
                                            waitUntil.toString());
                    }
                }
                Main.main(null);
            }
            else {
                this.aadharNumber = aadharNumber;
            }
        }
        else{
            System.out.println("Not a valid Aadhar Number! \nPlease re-enter: \t");
            setAadharNumber(new Scanner(System.in).next());
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(isValidPhone(phoneNumber)){
            this.phoneNumber = phoneNumber;
        }
        else {
            System.out.println("Not a valid Phone Number! \nPlease re-enter: \t");
            setPhoneNumber(new Scanner(System.in).nextLine());
        }
    }

    public String getVaccinationDate() {
        return vaccinationDate;
    }

    public void setVaccinationDate(String vaccinationDate) {
        this.vaccinationDate = vaccinationDate;
    }

    public int getVaccinated() {
        return vaccinated;
    }

    public void setVaccinated() {
        this.vaccinated+=1;
    }



    boolean isValidAadhar(String checkValid){
        return checkValid.length() == 12;
    }
    boolean isValidPhone(String checkValid){
        return checkValid.length() == 10;
    }
    boolean isVaccinatedTwice(String aadharNumber){
        for(int i=0;i<DataBase.array.size();i++){
            Person person = DataBase.array.get(i);
            if(aadharNumber.equals(person.getAadharNumber())){
                if(person.vaccinated >=2){
                    return true;
                }
            }
        }
        return false;
    }
    boolean isInDatabase(String aadharNumber){
        for(int i=0;i<DataBase.array.size();i++){
            Person person = DataBase.array.get(i);
            if(aadharNumber.equals(person.getAadharNumber())){
                return true;
            }
        }
        return false;
    }
    boolean isReady(String aadharNumber) throws ParseException {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
        for(int i=0;i<DataBase.array.size();i++){
            Person person = DataBase.array.get(i);
            if(aadharNumber.equals(person.getAadharNumber())){
                LocalDateTime t = LocalDateTime.now();
                Date input = myFormat.parse(person.getVaccinationDate());
                Date today = myFormat.parse(t.toString());
                try {
                    long difference =  today.getTime() - input.getTime();
                    float daysBetween = (difference / (1000*60*60*24));
                    if(daysBetween>30){
                        return true;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}

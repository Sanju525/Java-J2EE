package com.company.database;

public class VaccineData {
    private int vaccineAvailable;

    public int getVaccineAvailable() {
        return DataBase.VaccineAvailable;
    }

    public void setVaccineAvailable(int vaccineAvailable) {
        this.vaccineAvailable = vaccineAvailable;
        addToDataBase(vaccineAvailable);
    }

    void addToDataBase(int availabe){
        DataBase.VaccineAvailable += availabe;
    }
}

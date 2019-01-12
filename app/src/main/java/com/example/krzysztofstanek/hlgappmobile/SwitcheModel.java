package com.example.krzysztofstanek.hlgappmobile;

public class SwitcheModel {

    private String Name;
    private String ID;

    // Constructor that is used to create an instance of the Movie object
    public SwitcheModel(String Name, String ID) {
        this.Name = Name;
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}

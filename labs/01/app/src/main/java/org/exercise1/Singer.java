package org.exercise1;

public class Singer {
    private int id;
    private String name;
    private String address;
    private String dateOfBirth;
    private int numberOfAlbums;

    public Singer() {
        this.id = 0;
        this.name = "";
        this.address = "";
        this.dateOfBirth = "";
        this.numberOfAlbums = 0;
    }

    public Singer(int id) {
        this.id = id;
        this.name = "";
        this.address = "";
        this.dateOfBirth = "";
        this.numberOfAlbums = 0;
    }

    public Singer(int id, String name) {
        this.id = id;
        this.name = name;
        this.address = "";
        this.dateOfBirth = "";
        this.numberOfAlbums = 0;
    }

    public Singer(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = "";
        this.numberOfAlbums = 0;
    }

    public Singer(int id, String name, String address, String dateOfBirth) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbums = 0;
    }

    public Singer(int id, String name, String address, String dateOfBirth, int numberOfAlbums) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbums = numberOfAlbums;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setNumberOfAlbums(int numberOfAlbums) {
        this.numberOfAlbums = numberOfAlbums;
    }

    public void setSinger(int id, String name, String address, String dateOfBirth, int numberOfAlbums) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.numberOfAlbums = numberOfAlbums;
    }

    // Getters
    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public int getNumberOfAlbums() {
        return this.numberOfAlbums;
    }

    public String toString() {
        return "The singer's id is " + this.id + ", name is " + this.name + ", address is " + this.address
                + ", date of birth is " + this.dateOfBirth + ", number of albums is " + this.numberOfAlbums;
    }
}

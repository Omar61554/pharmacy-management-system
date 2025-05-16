package model;
public abstract class Person {
    protected int id;
    protected String name;
    protected String email;
    protected String phoneNumber;

    public Person(int id, String name, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getDetails() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phoneNumber;
    }
}


package com.example.customerdemowithoutdb.entity;



public class Customer {

  
    private Long id;
    private String name;
    private String email;
    private String city;
    private String state;
    private String country;  // ✅ Added country

    public Customer() {}

    public Customer(Long id, String name, String email, String city, String state, String country) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }
}
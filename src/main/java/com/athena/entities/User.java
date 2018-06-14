package com.athena.entities;

public class User
{
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String city;
    private String state;
    private String zip;
    private String address;
    private int floor;

    public User()
    {
        firstName = "";
        lastName = "";
        email = "";
        phoneNumber = "";
        city = "";
        state = "";
        zip = "";
        address = "";
        floor = 0;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public int getFloor()
    {
        return floor;
    }

    public void setFloor(int floor)
    {
        this.floor = floor;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getZip()
    {
        return zip;
    }

    public void setZip(String zip)
    {
        this.zip = zip;
    }

}

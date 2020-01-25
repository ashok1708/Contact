package com.example.contact;

public class Contact {
    private String Fname,Mname,Lname,Mobile,Email,Image;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public Contact(String fname, String mname, String lname, String mobile, String email, String image) {
        Fname = fname;
        Mname = mname;
        Lname = lname;
        Mobile = mobile;
        Email = email;
        Image=image;
    }

    public Contact() {
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getMname() {
        return Mname;
    }

    public void setMname(String mname) {
        Mname = mname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

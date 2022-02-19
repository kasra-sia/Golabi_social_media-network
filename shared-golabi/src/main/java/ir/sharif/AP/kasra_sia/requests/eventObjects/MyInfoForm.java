package ir.sharif.AP.kasra_sia.requests.eventObjects;

import java.util.Date;
import java.util.EventObject;

public class MyInfoForm extends EventObject {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password1;
    private String password2;
    private String phoneNumber;
    private Date birthday;
    private String bio;
    private String encodedImage;

    public MyInfoForm(Object source, String firstname, String lastname, String username, String email, String phoneNumber, Date birthday, String bio, String encodedImage) {
        super(source);
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.bio = bio;
        this.encodedImage = encodedImage;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setName(String name) {
        this.lastname = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getBio() {
        return bio;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }
}
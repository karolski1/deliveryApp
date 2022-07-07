package users;


import engine.Order;

import java.util.ArrayList;
import java.util.List;

public class Company extends User {
    private String address,email,creditCardNumber,name,logo;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setCompanyName(String name) {
        this.name = name;
    }

    @Override
    public void setPassword(String password) {
    super.setPassword(password);
    }
    @Override
    public String toString() {
        return "Company{" +
                "address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", name='" + name + '\'' +
                ", logo='" + logo + '\'' +
                ", order=" + order +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    List<Order> order = new ArrayList<>();


    public Company(String username, String password, String address, String email, String creditCardNumber, String name) {
        super(username, password);
        this.address = address;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
        this.name = name;
    }

    public String getLogo() {
        return this.logo;
    }

    public String getAdress() {
        return this.address;
    }

    public String getCreditCardNumber() {
        return this.creditCardNumber;
    }

    public String getCompanyName() {
        return this.name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

}

package Sumerge.JavaLearning.Quarter1.Entities;

public class UserBuilder {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String country;
    private String passwordHash;
    private String userName;

    public UserBuilder userName(String userName) {
        this.userName = userName;
        return this;

    }

    public UserBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this;
    }

    public UserBuilder address(String address) {
        this.address = address;
        return this;
    }

    public UserBuilder city(String city) {
        this.city = city;
        return this;
    }

    public UserBuilder country(String country) {
        this.country = country;
        return this;
    }

    public UserBuilder passwordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    public User build() {
        return new User(firstName, lastName, email, phone, address, city, country, passwordHash,userName);
    }
}
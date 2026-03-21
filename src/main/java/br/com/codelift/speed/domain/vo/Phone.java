package br.com.codelift.speed.domain.vo;

public class Phone {
    private final String phoneNumber;

    private Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static Phone create(String phoneNumber) {
        return new Phone(phoneNumber);
    }
}

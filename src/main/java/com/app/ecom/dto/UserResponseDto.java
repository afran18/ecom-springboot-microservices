package com.app.ecom.dto;
import com.app.ecom.model.UserRole;

public class UserResponseDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressResponseDto addressResponse;

    public UserResponseDto() {
    }

    public UserResponseDto(String id, String firstName, String lastName, String email, String phone, UserRole role, AddressResponseDto addressResponse) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.addressResponse = addressResponse;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public AddressResponseDto getAddressResponse() {
        return addressResponse;
    }

    public void setAddressResponse(AddressResponseDto addressResponse) {
        this.addressResponse = addressResponse;
    }
}

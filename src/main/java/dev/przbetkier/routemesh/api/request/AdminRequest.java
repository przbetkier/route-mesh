package dev.przbetkier.routemesh.api.request;

public class AdminRequest {

    private final String name;
    private final String address;
    private final String postalCode;
    private final String telephone;
    private final String email;
    private final String fax;

    public AdminRequest(String name, String address, String postalCode, String telephone, String email, String fax) {
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.telephone = telephone;
        this.email = email;
        this.fax = fax;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public String getFax() {
        return fax;
    }
}

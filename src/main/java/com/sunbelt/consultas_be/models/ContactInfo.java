package com.sunbelt.consultas_be.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactInfo {
    @NotBlank(message = "El primer nombre es requerido")
    private String firstName;
    private String middleName;
    @NotBlank(message = "El primer apellido es requerido")
    private String firstLastName;
    private String secondLastName;
    private String telephoneNumber;
    private String address;
    @NotBlank(message = "La ciudad de residencia es requerida")
    private String cityOfResidence;
    @NotBlank(message = "El tipo de documento es requerido")
    private String documentType;
    @NotBlank(message = "El número de identificación es requerido")
    @Size(min = 7, max = 15, message = "El número de identificación debe tener entre 7 y 15 caracteres")
    private String identificationNumber;

    public ContactInfo() {
    }

    public ContactInfo(String firstName, String middleName, String firstLastName, String secondLastName, String telephoneNumber, String address, String cityOfResidence, String documentType, String identificationNumber){
        this.firstName = firstName;
        this.middleName = middleName;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.telephoneNumber = telephoneNumber;
        this.address = address;
        this.cityOfResidence = cityOfResidence;
        this.documentType = documentType;
        this.identificationNumber = identificationNumber;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityOfResidence() {
        return cityOfResidence;
    }

    public void setCityOfResidence(String cityOfResidence) {
        this.cityOfResidence = cityOfResidence;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    @Override
    public String toString(){
        return "Información_del_contacto = {" +
                "\nPrimer nombre: " + firstName + ',' +
                "\nSegundo nombre: " + middleName + ',' +
                "\nPrimer apellido: " + firstLastName + ',' +
                "\nSegundo apellido: " + secondLastName + ',' +
                "\nTeléfono: " + telephoneNumber + ',' +
                "\nDirección: " + address + ',' +
                "\nCiudad de residencia: " + cityOfResidence + '.' +
                "\n}";
    }

}

package com.sunbelt.consultas_be.controllers;

import com.sunbelt.consultas_be.models.ContactInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personas")
class ContactInfoController {

    private List<ContactInfo> contactsList = new ArrayList<>();

    public ContactInfoController() {
        contactsList.add(new ContactInfo("Iván", "Mauricio", "Martínez", "Guzmán", "3186574742", "Calle 67 No. 6-27", "Ibagué", "Cédula", "1110544233"));
        contactsList.add(new ContactInfo("Myriam", null, "Guzmán", "Soler", "3102494767", "Calle 67 #25-38", "Ibagué", "Pasaporte", "AX438289"));
    }

    @GetMapping
    public ResponseEntity<List<ContactInfo>> getAllContactsInfo() {
        return new ResponseEntity<>(getContactsList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable int id) {
        if (id >= 0 && id < getContactsList().size()) {
            return new ResponseEntity<>(getContactsList().get(id), HttpStatus.OK);
        } else {
            String errorMessage = "No se encontró ningún contacto con el ID especificado: " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/identification/{docType}/{idNumber}")
    public ResponseEntity<?> getContactByIdentification(@PathVariable String idNumber, @PathVariable String docType) {
        if (docType == null || docType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo de documento es obligatorio.");
        }

        if (!isValidIdNumber(idNumber, docType)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El número de identificación proporcionado es inválido: " + idNumber);
        }

        try {
            for (ContactInfo userInfo : getContactsList()) {
                if (userInfo.getIdentificationNumber().equals(idNumber)) {
                    return new ResponseEntity<>(userInfo, HttpStatus.OK);
                }
            }
            String errorMessage = "No se encontró ningún contacto con la identificación proporcionada: " + idNumber;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        } catch (NullPointerException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error interno al procesar la solicitud.");
        }
    }

    public List<ContactInfo> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<ContactInfo> contactsList) {
        this.contactsList = contactsList;
    }

    private boolean isValidIdNumber(String idNumber, String docType) {
        if (idNumber == null || idNumber.isEmpty()) {
            return false;
        }

        switch (docType) {
            case "P":
                if (idNumber.length() > 14) {
                    return false;
                }
                for (char c : idNumber.toCharArray()) {
                    if (!Character.isLetterOrDigit(c)) {
                        return false;
                    }
                }
                break;
            case "C":
                if (idNumber.length() != 10) {
                    return false;
                }
                for (char c : idNumber.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        return false;
                    }
                }
                break;
            default:
                return false;
        }

        return true;
    }
}


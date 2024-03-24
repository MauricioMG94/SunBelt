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
    public ResponseEntity<ContactInfo> getContactById(@PathVariable int id) {
        if (id >= 0 && id < getContactsList().size()) {
            return new ResponseEntity<>(getContactsList().get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/identification/{idNumber}")
    public ResponseEntity<ContactInfo> getContactByIdentification(@PathVariable String idNumber) {
        for (ContactInfo userInfo : getContactsList()) {
            if (userInfo.getIdentificationNumber().equals(idNumber)) {
                return new ResponseEntity<>(userInfo, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public List<ContactInfo> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<ContactInfo> contactsList) {
        this.contactsList = contactsList;
    }
}

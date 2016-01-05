package ua.com.knitka.model;

import java.util.List;

public class ContactsDTO {
    private List<String> phoneNumbers;
    private String eMail;

    public List<String> getPhoneNumbers () {
        return phoneNumbers;
    }

    public void setPhoneNumbers (List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getEMail () {
        return eMail;
    }

    public void setEMail (String eMail) {
        this.eMail = eMail;
    }
}

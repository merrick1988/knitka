package ua.com.knitka.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ua.com.knitka.model.ContactsDTO;
import ua.com.knitka.resource.ContactsResource;

import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.io.IOException;
import java.util.ArrayList;

public class ContactsResourceService implements ContactsResource{
    public void getContacts(final @Suspended AsyncResponse asyncResponse) {
        ContactsDTO contacts = collectContacts();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String response = objectMapper.writeValueAsString(contacts);
            asyncResponse.resume(response);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ContactsDTO collectContacts() {
        ContactsDTO contacts = new ContactsDTO();

        ArrayList<String> phoneNumbersFromDB = new ArrayList<String>();
        phoneNumbersFromDB.add("+380997610249");

        final String emailFromDB = "marrick1988@gmail.com";
        contacts.setEMail(emailFromDB);
        contacts.setPhoneNumbers(phoneNumbersFromDB);

        return contacts;
    }
}

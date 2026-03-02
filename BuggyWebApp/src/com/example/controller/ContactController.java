package com.example.controller;

import com.example.model.ContactForm;
import com.example.service.ContactService;
import com.example.util.Logger;

public class ContactController {

    private ContactService service;

    public ContactController() {
        service = new ContactService();
    }

    public void submitContactForm(String name, String email) {
        Logger.log("Recibiendo formulario de contacto...");
        if (name.trim().isEmpty() || email.trim().isEmpty()) {
            Logger.log("ERROR: El nombre y el email no pueden estar vacíos.");
            return;
        }
        ContactForm form = new ContactForm(name, email);
        service.processForm(form);
        Logger.log("Fin de submitContactForm");
    }
}
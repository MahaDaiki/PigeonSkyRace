package com.example.pigeon.entity;


import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "organisateurs")
public class Organisateur extends Utilisateur {

    public Organisateur() {
        super();
    }

    public Organisateur(String username, String password) {
        super(username, password);
    }


}

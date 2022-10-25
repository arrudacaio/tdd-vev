package org.example.Controller;

import org.example.models.Client;

import java.util.HashMap;
import java.util.Map;

public class ClientController {

    private Map<String, Client> clients;

    public ClientController(){
        this.clients = new HashMap<>();
    }

    public String insert(Client client){
        this.clients.put(client.getId(), client);
        return client.getId();
    }

    public Client findById(String id){
        return this.clients.get(id);
    }
}
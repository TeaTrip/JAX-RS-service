package com.wishmaster.ifmo.ws.jaxrs.client;
import  java.util.Base64;

public class Credential {
    private String login;
    private String password;
    private String encryptedCredential;

    public Credential(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getAuthString(){
        if(this.encryptedCredential != null){
            return  this.encryptedCredential;
        }
        String result = "Basic ";
        String credential = this.login + ":" + this.password;
        result += new String(Base64.getEncoder().encode(credential.getBytes()));
        this.encryptedCredential = result;
        return result;
    }
}
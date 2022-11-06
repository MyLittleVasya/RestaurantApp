package com.example.chatrebuild.RequestBodies;

public class CreateOrderRequest {
    private String idList;
    private String login;

    public CreateOrderRequest(String idList, String login) {
        this.idList = idList;
        this.login = login;
    }

    public CreateOrderRequest() {
    }

    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}

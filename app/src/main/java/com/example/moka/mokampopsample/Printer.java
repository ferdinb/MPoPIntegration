package com.example.moka.mokampopsample;



/**
 * Created by ferdi on 01/12/2015.
 */
public class Printer {
    private String mac;
    private String name;
    private String status;
    private String nickname;
    private String ip;
    private boolean orderTicket;
    private boolean receipt;
    private String type;
    private boolean success;
    private String defaultName;

    public void setDefaultName(String defaultName) { this.defaultName = defaultName; }

    public String getDefaultName() { return defaultName; }

    public boolean isVisible() {
        return isVisible;
    }

    public void setIsVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    private boolean isVisible;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isOrderTicket() {
        return orderTicket;
    }

    public void setOrderTicket(boolean orderTicket) {
        this.orderTicket = orderTicket;
    }

    public boolean isReceipt() {
        return receipt;
    }

    public void setReceipt(boolean receipt) {
        this.receipt = receipt;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

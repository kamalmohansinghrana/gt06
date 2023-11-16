package com.onelap.gt06onelap.model;

import java.util.Calendar;

public class LoginInformation {
    private int startBit;
    private int packetLength;
    private int dataLength;
    private int protocolNumber;
    private LoginInformationContent informationContent;
    private String serialNumber;
    private int errorChecking;
    private int endBit;
    public static Builder getInstance(){
        return  new Builder();
    }

    public static class Builder{
        private int startBit;
        private int packetLength;
        private int dataLength;
        private int protocolNumber;
        private LoginInformationContent loginInformationContent;
        private String serialNumber;
        private int errorChecking;
        private int endBit;



        public Builder setStartBit(int bit){
            Calendar.getInstance();
            this.startBit = startBit;
            return this;
        }

        public Builder packetLength(int packetLength){
            this.packetLength = packetLength;
            return this;
        }

        public Builder dataLength(int dataLength){
            this.dataLength = dataLength;
            return this;
        }

        public Builder protocolNumber(int protocolNumber){
            this.protocolNumber = protocolNumber;
            return this;
        }

        public Builder informationContent(LoginInformationContent loginInformationContent){
            this.loginInformationContent = loginInformationContent;
            return this;
        }

        public Builder setSerialNumber(String serialNumber){
            this.serialNumber = serialNumber;
            return this;
        }
        public Builder setErrorChecking(int errorChecking){
            this.errorChecking = errorChecking;
            return this;
        }

        public Builder setEndBit(int endBit){
            this.endBit = endBit;
            return this;
        }

        public LoginInformation build(){
            LoginInformation loginInformation = new LoginInformation();
            loginInformation.startBit = startBit;
            loginInformation.packetLength = packetLength;
            loginInformation.dataLength = dataLength;
            loginInformation.protocolNumber = protocolNumber;
            loginInformation.informationContent = loginInformationContent;
            loginInformation.serialNumber = serialNumber;
            loginInformation.errorChecking = errorChecking;
            loginInformation.endBit = endBit;
            return loginInformation;
        }
    }

    public int getStartBit() {
        return startBit;
    }

    public int getPacketLength() {
        return packetLength;
    }

    public int getDataLength() {
        return dataLength;
    }

    public int getProtocolNumber() {
        return protocolNumber;
    }



    public LoginInformationContent getTerminal() {
        return informationContent;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public int getErrorChecking() {
        return errorChecking;
    }

    public int getEndBit() {
        return endBit;
    }


}

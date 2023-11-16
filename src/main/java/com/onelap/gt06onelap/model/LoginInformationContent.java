package com.onelap.gt06onelap.model;

import java.util.TimeZone;

public class LoginInformationContent {
    private String terminalId;
    private int identificationCode;
    private Integer timeZoneLanguage;


    public static Builder getInstance(){
        return new Builder();
    }

    public static class Builder{
        private String terminalId;
        private int identicationCode;
        private Integer timeZoneLanguage;

        public Builder terminalId(String terminalId) {
            this.terminalId = terminalId;
            return this;
        }

        public Builder identicationCode(int identicationCode) {
            this.identicationCode = identicationCode;
            return this;
        }

        public Builder timeZoneLanguage(Integer timeZoneLanguage) {
            this.timeZoneLanguage = timeZoneLanguage;
            return this;
        }

        public LoginInformationContent build(){
            LoginInformationContent content = new LoginInformationContent();
            content.identificationCode = identicationCode;
            content.timeZoneLanguage = timeZoneLanguage;
            content.terminalId = terminalId;
            return content;
        }
    }

    public String getTerminalId() {
        return terminalId;
    }

    public int getIdentificationCode() {
        return identificationCode;
    }

    public int getTimeZoneLanguage() {
        return timeZoneLanguage;
    }

    public TimeZone getTimeZone(){
        int hours = (timeZoneLanguage >> 4)/100;
        int minutes = (timeZoneLanguage >>4 ) %100;
        int offset =(hours * 60 + minutes) *60;
        if((timeZoneLanguage & 0x8) != 0){
            offset = -offset;
        }
        TimeZone tz = TimeZone.getDefault();
        tz.setRawOffset(offset * 1000);
        return tz;
    }


}

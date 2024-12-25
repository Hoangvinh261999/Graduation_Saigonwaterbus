package com.sgwb.saigonwaterbus.Model.DTO.AuthenDTO;
import java.util.Map;
public class LocalStorageDTO {
    private Map<String, String> localStorageData;

    // Getters và Setters
    public Map<String, String> getLocalStorageData() {
        return localStorageData;
    }

    public void setLocalStorageData(Map<String, String> localStorageData) {
        this.localStorageData = localStorageData;
    }
}

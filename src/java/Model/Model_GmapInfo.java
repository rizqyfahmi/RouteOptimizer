/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.json.simple.JSONObject;

/**
 *
 * @author RIZQY FAHMI
 */
public class Model_GmapInfo {

    private String lng;
    private String lat;
    private String keterangan;
    private String alamat;
    private boolean status;

    public Model_GmapInfo(String lng, String lat, String keterangan, String alamat, boolean status) {
        this.lng = lng;
        this.lat = lat;
        this.keterangan = keterangan;
        this.alamat = alamat;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }        

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public JSONObject getJSONObject(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("keterangan", String.valueOf(getKeterangan()));
        jsonObject.put("alamat", String.valueOf(getAlamat()));        
        jsonObject.put("lat", String.valueOf(getLat()));        
        jsonObject.put("lng", String.valueOf(getLng()));        
        jsonObject.put("status", String.valueOf(isStatus()));        
        return jsonObject;
    }
}

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
public class Model_Tiket {
    private String nomorTiket;
    private String slg;
    private String alert;
    private String regId;
    private String area;
    private String cid;
    private String headline;
    private String layanan;
    private String lamaGangguan;
    private String alamat;
    private String lat;
    private String lng;
    

    public Model_Tiket(String nomorTiket, String slg, String alert, String regId, String area, String cid, String headline, String layanan, String lamaGangguan, String alamat, String lat, String lng) {
        this.nomorTiket = nomorTiket;
        this.slg = slg;
        this.alert = alert;
        this.regId = regId;
        this.area = area;
        this.cid = cid;
        this.headline = headline;
        this.layanan = layanan;
        this.lamaGangguan = lamaGangguan;
        this.alamat = alamat;
        this.lat = lat;
        this.lng = lng;
    }        

    public Model_Tiket() {
        
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }        
    
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public String getNomorTiket() {
        return nomorTiket;
    }

    public void setNomorTiket(String nomorTiket) {
        this.nomorTiket = nomorTiket;
    }

    public String getSlg() {
        return slg;
    }

    public void setSlg(String slg) {
        this.slg = slg;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getLayanan() {
        return layanan;
    }

    public void setLayanan(String layanan) {
        this.layanan = layanan;
    }

    public String getLamaGangguan() {
        return lamaGangguan;
    }

    public void setLamaGangguan(String lamaGangguan) {
        this.lamaGangguan = lamaGangguan;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }        
    
    public JSONObject getJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nomorTiket", String.valueOf(getNomorTiket()));
        jsonObject.put("slg", String.valueOf(getSlg()));
        jsonObject.put("alert", String.valueOf(getAlert()));
        jsonObject.put("regional", String.valueOf(getRegId()));
        jsonObject.put("headline", String.valueOf(getHeadline()));
        jsonObject.put("cid", String.valueOf(getCid()));
        jsonObject.put("layanan", String.valueOf(getLayanan()));
        jsonObject.put("lamaGangguan", String.valueOf(getLamaGangguan()));
        jsonObject.put("alamat", String.valueOf(getAlamat()));
        jsonObject.put("lat", String.valueOf(getLat()));
        jsonObject.put("lng", String.valueOf(getLng()));        
        return jsonObject;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author RIZQY FAHMI
 */
public class Model_Kantor {    
    private String id;
    private String nama;
    private String regional;
    private String area;
    private String teknisi;
    private String alamat;
    private String telepon;   
    private String fax;   
    private String lat;
    private String lng;

    public Model_Kantor(String id, String nama, String regional, String area, String teknisi, String alamat, String telepon, String fax, String lat, String lng) {
        this.id = id;
        this.nama = nama;
        this.regional = regional;
        this.area = area;
        this.teknisi = teknisi;
        this.alamat = alamat;
        this.telepon = telepon;
        this.fax = fax;
        this.lat = lat;
        this.lng = lng;
    }   
    
    public String getTeknisi() {
        return teknisi;
    }

    public void setTeknisi(String teknisi) {
        this.teknisi = teknisi;
    }   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public Model_Kantor() {

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getRegional() {
        return regional;
    }

    public void setRegional(String regional) {
        this.regional = regional;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
    
    
}

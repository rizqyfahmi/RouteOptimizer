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
public class Model_Saw {

    private Model_Tiket tiket;
    private double total;
    private JSONObject jsonObject;

    public Model_Saw(Model_Tiket tiket, double total) {
        this.tiket = tiket;
        this.total = total;        
    }

    public Model_Tiket getTiket() {
        return tiket;
    }

    public void setTiket(Model_Tiket tiket) {
        this.tiket = tiket;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public JSONObject getJSONObject() {
        jsonObject = new JSONObject();
        jsonObject.put("nomorTiket", String.valueOf(tiket.getNomorTiket()));
        jsonObject.put("slg", String.valueOf(tiket.getSlg()));
        jsonObject.put("alert", String.valueOf(tiket.getAlert()));
        jsonObject.put("regional", String.valueOf(tiket.getRegId()));
        jsonObject.put("headline", String.valueOf(tiket.getHeadline()));
        jsonObject.put("cid", String.valueOf(tiket.getCid()));
        jsonObject.put("layanan", String.valueOf(tiket.getLayanan()));
        jsonObject.put("lamaGangguan", String.valueOf(tiket.getLamaGangguan()));
        jsonObject.put("alamat", String.valueOf(tiket.getAlamat()));
        jsonObject.put("lat", String.valueOf(tiket.getLat()));
        jsonObject.put("lng", String.valueOf(tiket.getLng()));
        jsonObject.put("saw", String.valueOf(total));
        return jsonObject;
    }

}

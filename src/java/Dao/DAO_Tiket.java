/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;


import Model.Model_Tiket;
import Other.GoogleService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author RIZQY FAHMI
 */
public class DAO_Tiket {

    private ArrayList<Model_Tiket> tiketList = new ArrayList<Model_Tiket>();

    public void insert(Row row) throws ParseException, IOException, Exception {
        tiketList.add(convertToTiket(row));
    }

    public int getIndex(String id) {
        int index = -1;
        for (int j = 0; j < tiketList.size(); j++) {
            if (tiketList.get(j).getNomorTiket().trim().toUpperCase().equals(id.trim().toUpperCase())) {
                index = j;
                break;
            }
        }
        return index;
    }
   
    public Model_Tiket searchById(String id) {
        Model_Tiket tiket = new Model_Tiket();
        int index = -1;
        for (int j = 0; j < view().size(); j++) {
            if (view().get(j).getNomorTiket().trim().toUpperCase().equals(id.trim().toUpperCase())) {
                tiket = view().get(j);
                break;
            }
        }
        return tiket;
    }

    public ArrayList<Model_Tiket> view() {
        return tiketList;
    }

    public void display() {
        for (Model_Tiket tiket : view()) {
            System.out.println("NOMOR TIKET \t: " + tiket.getNomorTiket());
            System.out.println("SLG \t: " + tiket.getSlg());
            System.out.println("ALERT \t: " + tiket.getAlert());
            System.out.println("REGIONAL \t: " + tiket.getRegId());
            System.out.println("AREA \t: " + tiket.getArea());
            System.out.println("HEADLINE \t: " + tiket.getHeadline());
            System.out.println("CID \t: " + tiket.getCid());
            System.out.println("LAYANAN \t: " + tiket.getLayanan());
            System.out.println("LAMA GANGGUAN \t: " + tiket.getLamaGangguan());
            System.out.println("LAT \t: " + tiket.getLat());
            System.out.println("LANG \t: " + tiket.getLng());
            System.out.println("");
        }

    }

    public String convertToJson() {
        JSONObject object = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Model_Tiket t : view()) {
            JSONObject object2 = new JSONObject();
            object2.put("nomorTiket", String.valueOf(t.getNomorTiket()));
            object2.put("slg", String.valueOf(t.getSlg()));
            object2.put("alert", String.valueOf(t.getAlert()));
            object2.put("regional", String.valueOf(t.getRegId()));
            object2.put("area", String.valueOf(t.getArea()));
            object2.put("headline", String.valueOf(t.getHeadline()));
            object2.put("cid", String.valueOf(t.getCid()));
            object2.put("layanan", String.valueOf(t.getLayanan()));
            object2.put("lamaGangguan", String.valueOf(t.getLamaGangguan()));
            object2.put("lat", String.valueOf(t.getLat()));
            object2.put("lng", String.valueOf(t.getLng()));
            arr.add(object2);
        }
        object.put("data", arr);
        return object.toJSONString();
    }

    public Model_Tiket convertToTiket(Row row) throws ParseException, MalformedURLException, IOException, Exception, Exception {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Model_Tiket tiket = new Model_Tiket();
        tiket.setNomorTiket(row.getCell(1).toString());
        tiket.setSlg(row.getCell(2).toString());
        tiket.setRegId(row.getCell(3).toString());
        tiket.setArea(row.getCell(4).toString());
        tiket.setHeadline(row.getCell(5).toString());
        tiket.setAlamat(row.getCell(6).toString().trim());
        tiket.setCid(row.getCell(7).toString());
        tiket.setLayanan(row.getCell(8).toString());
        tiket.setAlert(format.format(row.getCell(9).getDateCellValue()));
        tiket.setLamaGangguan(row.getCell(11).toString());
//        String address = row.getCell(6).toString().trim().replaceAll(" ", "+");
        String address = row.getCell(6).toString().trim();
        String latLng[] = new GoogleService().getLatLongByPlace(address);
//        GoogleResponse res = getLatLong(WordUtils.capitalizeFully(address));
//        if (res.getStatus().equals("OK")) {
//            String lat = "";
//            String lng = "";
//            for (Result result : res.getResults()) {
//                lat = result.getGeometry().getLocation().getLat();
//                lng = result.getGeometry().getLocation().getLng();
//            }
            tiket.setLat(latLng[0]);
            tiket.setLng(latLng[1]);
//        } else {
//            System.out.println(res.getStatus());
//        }
        return tiket;
    }

    
    
}

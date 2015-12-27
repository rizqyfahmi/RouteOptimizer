/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Model_GmapInfo;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author RIZQY FAHMI
 */
public class DAO_GmapInfo {
    private ArrayList<Model_GmapInfo> infoList = new ArrayList<Model_GmapInfo>();
    
    public void insert(Model_GmapInfo gmapInfo) {
        infoList.add(gmapInfo);
    }
    
    public ArrayList<Model_GmapInfo> view() {
        return infoList;
    }
    
    public String convertToJson() {        
        JSONObject object = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Model_GmapInfo g : view()) {
            JSONObject object2 = new JSONObject();
            object2.put("alamat", String.valueOf(g.getAlamat()));
            object2.put("keterangan", String.valueOf(g.getKeterangan()));
            object2.put("lat", String.valueOf(g.getLat()));
            object2.put("lng", String.valueOf(g.getLng()));
            arr.add(object2);
        }
        object.put("data", arr);
        return object.toJSONString();
    }
    
//    public GoogleResponse getLatLong(String address) throws UnsupportedEncodingException, MalformedURLException, IOException {
//        String URL = "http://maps.googleapis.com/maps/api/geocode/json";
//        URL url = new URL(URL + "?address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=false");
//        URLConnection conn = url.openConnection();
//
//        InputStream in = conn.getInputStream();
//        ObjectMapper mapper = new ObjectMapper();
//        GoogleResponse res = (GoogleResponse) mapper.readValue(in, GoogleResponse.class);
//        in.close();
//        return res;
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Model_Slg;
import Model.Model_Tiket;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.Row;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 *
 * @author RIZQY FAHMI
 */
public class DAO_Slg {

    private ArrayList<Model_Slg> slgList = new ArrayList<Model_Slg>();

    public DAO_Slg() {
        slgList.add(new Model_Slg("SLG 95%", 3.0));
        slgList.add(new Model_Slg("SLG 97%", 2.45));
        slgList.add(new Model_Slg("SLG 98%", 2.3));
        slgList.add(new Model_Slg("SLG 98.5%", 2.3));        
        slgList.add(new Model_Slg("SLG 99%", 2.0));
        slgList.add(new Model_Slg("SLG 99.5%", 1.3));
        slgList.add(new Model_Slg("SLG 99.95%", 1.0));
    }
    
    public int getIndex(String id) {
        int index = -1;
        for (int j = 0; j < slgList.size(); j++) {
            if (slgList.get(j).getNamaSlg().trim().toUpperCase().equals(id.trim().toUpperCase())) {
                index = j;
                break;
            }
        }
        return index;
    }
    
    public double getMaksimalPerbaikan(String id) {
        double maksimalPerbaikan = 0.0;
        for (int j = 0; j < slgList.size(); j++) {
            if (slgList.get(j).getNamaSlg().trim().toUpperCase().equals(id.trim().toUpperCase())) {
                maksimalPerbaikan = slgList.get(j).getMaksimalPerbaikan();
                break;
            }
        }
        return maksimalPerbaikan;
    }

    public void display() {
        for (Model_Slg slg : view()) {            
            System.out.println("SLG \t: " + slg.getNamaSlg());
            System.out.println("Maksimal Perbaikan \t: " + slg.getMaksimalPerbaikan());
            System.out.println("");
        }

    }
    
    public ArrayList<Model_Slg> view() {
        return slgList;
    }
    
    public String convertToJson() {        
        JSONObject object = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Model_Slg slg : view()) {
            JSONObject object2 = new JSONObject();
            object2.put("namaSlg", String.valueOf(slg.getNamaSlg()));
            object2.put("maksimalPerbaikan", String.valueOf(slg.getMaksimalPerbaikan()));            
            arr.add(object2);
        }
        object.put("data", arr);
        return object.toJSONString();
    }
}

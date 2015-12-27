/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Model_Kantor;
import Other.IoFile;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author RIZQY FAHMI
 */
public class DAO_Kantor {

    private String pathFile = "D:\\PROJECTS\\Spring 4\\TugasAkhirFinalV2\\web\\WEB-INF\\json\\kantor.json";
    private IoFile ioFile = new IoFile(pathFile);

    public void init(ArrayList<Model_Kantor> kantor) throws ParseException {
        ioFile.writeFile(convert(kantor));
    }

    public void insert(Model_Kantor kantor) throws ParseException {
        ArrayList<Model_Kantor> temp = view();
        temp.add(kantor);
        ioFile.writeFile(convert(temp));
    }

    public ArrayList<Model_Kantor> view() throws ParseException {
        ArrayList<Model_Kantor> kantor = new ArrayList<Model_Kantor>();
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(ioFile.readFile());

        JSONObject object = (JSONObject) obj;
        JSONArray arr = (JSONArray) object.get("data");
        for (int i = 0; i < arr.size(); i++) {
            JSONObject object2 = (JSONObject) arr.get(i);
            Model_Kantor temp = new Model_Kantor();
            temp.setId(object2.get("id").toString());
            temp.setNama(object2.get("nama").toString());
            temp.setRegional(object2.get("regional").toString());
            temp.setArea(object2.get("area").toString());
            temp.setAlamat(object2.get("alamat").toString());
            temp.setFax(object2.get("fax").toString());
            temp.setTelepon(object2.get("telepon").toString());
            temp.setLat(object2.get("lat").toString());
            temp.setLng(object2.get("lng").toString());
            temp.setTeknisi(object2.get("teknisi").toString());
            kantor.add(temp);
        }
        return kantor;
    }

    public String convert(ArrayList<Model_Kantor> kantor) {
        JSONObject object = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Model_Kantor k : kantor) {
            JSONObject object2 = new JSONObject();
            object2.put("id", k.getId());
            object2.put("nama", k.getNama());
            object2.put("regional", k.getRegional());
            object2.put("area", k.getArea());
            object2.put("alamat", k.getAlamat());
            object2.put("telepon", k.getTelepon());
            object2.put("fax", k.getFax());
            object2.put("lat", k.getLat());
            object2.put("lng", k.getLng());
            object2.put("teknisi", k.getTeknisi());
            arr.add(object2);
        }
        object.put("data", arr);
        return object.toJSONString();
    }

    public String singleConvert(Model_Kantor k) {
        JSONObject object = new JSONObject();
        object.put("id", k.getId());
        object.put("nama", k.getNama());
        object.put("regional", k.getRegional());
        object.put("area", k.getArea());
        object.put("alamat", k.getAlamat());
        object.put("telepon", k.getTelepon());
        object.put("fax", k.getFax());
        object.put("lat", k.getLat());
        object.put("lng", k.getLng());
        object.put("teknisi", k.getTeknisi());
        return object.toJSONString();
    }

    public Model_Kantor searchById(String id) throws ParseException {
        Model_Kantor kantor = null;
        for (Model_Kantor temp : view()) {
            if (temp.getId().equals(id.trim())) {
                kantor = temp;
                break;
            }
        }
        return kantor;
    }

    public int getIndex(String id) throws ParseException {
        int i = -1;
        Model_Kantor Kantor = null;
        for (int j = 0; j < view().size(); j++) {
            if (view().get(j).getId().equals(id.trim())) {
                i = j;
                break;
            }
        }
        return i;
    }
    
    public ArrayList<Model_Kantor> searchByRegionalArea(String regional, String area) throws ParseException{
        ArrayList<Model_Kantor> kantor = new ArrayList<Model_Kantor>();
        for (Model_Kantor k : view()) {            
            if ((k.getRegional().toUpperCase().equals(regional.toUpperCase())) && (area.contains(k.getArea().toUpperCase()))) {
                kantor.add(k);
            }
        }        
        return kantor;
    }
    
    public void delete(String id) throws ParseException {
        ArrayList<Model_Kantor> temp = view();
        temp.remove(getIndex(id));
        ioFile.writeFile(convert(temp));
    }

    public void update(Model_Kantor kantor) throws ParseException {
        ArrayList<Model_Kantor> temp = view();
        temp.set(getIndex(kantor.getId()), kantor);
        ioFile.writeFile(convert(temp));
    }
}

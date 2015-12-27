/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Dao.DAO_Kantor;
import Model.Model_Kantor;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author RIZQY FAHMI
 */
@Controller
public class Controller_Kantor {

    DAO_Kantor kantorDAO = new DAO_Kantor();

    @RequestMapping(value = "/kantor", method = RequestMethod.GET)
    public String getKantor() {
        return "kantor";
    }

    @RequestMapping(value = "/kantor", method = RequestMethod.POST)
    public String post(@ModelAttribute("kantor") Model_Kantor kantor) throws ParseException, MalformedURLException, IOException {
        kantor.setId(randomID(""));
        System.out.println(kantor.getRegional());
        kantorDAO.insert(kantor);
        return "kantor";
    }
    
    @RequestMapping(value = "/update-kantor.json", method = RequestMethod.GET)
    public @ResponseBody
    String getUpdate(@RequestParam("id") String id) throws ParseException {
        return kantorDAO.singleConvert(kantorDAO.searchById(id));
    }
    
    @RequestMapping(value = "/update-kantor", method = RequestMethod.POST)
    public String update(@ModelAttribute("kantor") Model_Kantor kantor) throws ParseException {        
        kantorDAO.update(kantor);
        return "redirect:kantor";
    }

    @RequestMapping(value = "/delete-kantor", method = RequestMethod.POST)
    public String delete(@RequestParam("id") String id) throws ParseException {
        kantorDAO.delete(id);
        return "redirect:kantor";
    }
    
    
    @RequestMapping(value = "/kantor.json", method = RequestMethod.GET)
    public @ResponseBody String getJson() throws ParseException {        
        return kantorDAO.convert(kantorDAO.view());
    }

    public String randomID(String x) throws ParseException {
        int id = 0;
        do {
            id = ((int) (Math.random() * (999999 - 100000))) + 100000;
        } while (kantorDAO.searchById(x + String.valueOf(id)) != null);        
        return x + String.valueOf(id);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Rizqy Fahmi
 */
public class GoogleService {
//    private final String key  = "AIzaSyBWmdlMODUxD8NU62MNSoYZ1KpQQOEgUm0";
//    private final String key  = "AIzaSyDfew5JM3ZYqHJ5dZja_GfXKaVBGC4zXtw";
//    private final String key  = "AIzaSyDYZlDrgVHKyb-dH8zWsEJ9tbCYBrTTFD0";
    private final String key  = "AIzaSyBAUCYZySYJ-jGt8xJc-C1oQ_DZSECW0VE";
//    private final String key  = "AIzaSyAxemHrYHVLtgCrCFlkR9MkdWje2tgJ-R4";
//    private final String key  = "AIzaSyAC9VxTj-fkTQcSckjsSw9lj2ETlqSrScg";
//    private final String key  = "AIzaSyBJiJe8cPF4XJkZZgRT_3fAvP2gLyC13PM";
//    private final String key  = "AIzaSyBhK9KLkwzR-gIjn3IkzldMOk3afi9n2Rg";

    public String getKey() {
        return key;
    }        
    
    public String[] getLatLongByAddress(String address) throws Exception {
        URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?key="+getKey()+"&address=" + URLEncoder.encode(address, "UTF-8") + "&sensor=true");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");        
        String line, outputString = "";
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {
            outputString += line;
        }
        String latLng[] = new String[2];
        JSONArray jArray;
        JSONParser parser = new JSONParser();
        JSONObject jObject = (JSONObject) parser.parse(outputString);
        if (jObject.get("status").equals("OK")) {
            jArray = (JSONArray) jObject.get("results");
            jObject = (JSONObject) jArray.get(0);
            jObject = (JSONObject) jObject.get("geometry");
            jObject = (JSONObject) jObject.get("location");
            latLng[0] = jObject.get("lat").toString();
            latLng[1] = jObject.get("lng").toString();
//            System.out.println("Test \n"+jObject);
        }                
        return latLng;
    }
    
    public String[] getLatLongByPlace(String address) throws Exception {
        URL url = new URL("https://maps.googleapis.com/maps/api/place/textsearch/json?key="+getKey()+"&query=" + URLEncoder.encode(address, "UTF-8"));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");        
        String line, outputString = "";
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {
            outputString += line;
        }
        String latLng[] = new String[2];
        JSONArray jArray;
        JSONParser parser = new JSONParser();
        JSONObject jObject = (JSONObject) parser.parse(outputString);
        if (jObject.get("status").equals("OK")) {
            jArray = (JSONArray) jObject.get("results");
            jObject = (JSONObject) jArray.get(0);            
            jObject = (JSONObject) jObject.get("geometry");            
            jObject = (JSONObject) jObject.get("location");
            latLng[0] = jObject.get("lat").toString();
            latLng[1] = jObject.get("lng").toString();
//            System.out.println("Test \n"+jObject);
        }                
        return latLng;
    }
    
    public int getDuration(String origins, String destinations) throws MalformedURLException, IOException, ParseException {
        URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?key="+getKey()+"&origins=" + origins.replaceAll(" ", "+") + "&destinations=" + destinations.replaceAll(" ", "+"));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        String line, outputString = "";
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {
            outputString += line;
        }
        JSONArray jArray;
        JSONParser parser = new JSONParser();
        JSONObject jObject = (JSONObject) parser.parse(outputString);

        jArray = (JSONArray) jObject.get("rows");
        jObject = (JSONObject) jArray.get(0);
        jArray = (JSONArray) jObject.get("elements");
        jObject = (JSONObject) jArray.get(0);
        jObject = (JSONObject) jObject.get("duration");
        System.out.println(jObject);
        return Integer.parseInt(jObject.get("value").toString());
    }
    public int getDuration(String lat1, String lng1, String lat2, String lng2) throws MalformedURLException, IOException, ParseException {
        URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?key="+getKey()+"&origins="+lat1+","+lng1+"&destinations="+lat2+","+lng2+"&mode=driving&language=en-EN&sensor=false");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        String line, outputString = "";
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {
            outputString += line;
        }
        JSONArray jArray;
        JSONParser parser = new JSONParser();
        JSONObject jObject = (JSONObject) parser.parse(outputString);

        jArray = (JSONArray) jObject.get("rows");
        jObject = (JSONObject) jArray.get(0);
        System.out.println(jObject);
        jArray = (JSONArray) jObject.get("elements");
        jObject = (JSONObject) jArray.get(0);
        jObject = (JSONObject) jObject.get("duration");
        
        return Integer.parseInt(jObject.get("value").toString());
    }
    
    public int getDurationOffline(double distance){
        double speed = 30;
        double dist = distance * 1000;
        double s = ((speed * 1000)/3600);
//        System.out.println("speed : "+s);
        int second = (int) (dist/s);       
//        System.out.println(toHour(second));
        
        
//        double dist = distance * 1000;
//        double speed = (30 * 1000);        
//        int hour = (int) (dist/speed);
//        int minutes = (int) ((int) ((dist%speed)*60)/speed);
////        int second = (int) ((int) ((minutes%speed)*60)/speed);
//        int second = (hour*3600)+(minutes*60);
//        
//        System.out.println(hour+"\t"+minutes+"\t"+second+"\t"+toHour(second));
        
        return second;
    }
    
    public double getDistance(double lat1, double lng1, double lat2, double lng2) {
//        double dlat = lat2-lat1; 
//        double dlng = lng2-lng1; 
//        double dist = Math.sqrt(Math.pow(dlat, 2) + Math.pow(dlng, 2));
//        return dist;
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = (earthRadius * c);

        return doubleFormater(toKilometers(doubleFormater(dist)));
    }
    
    public double doubleFormater(double x) {
        NumberFormat formatter = new DecimalFormat("#.##");
        double temp = Double.parseDouble(String.valueOf(formatter.format(x)).replaceAll(",", "."));
        return temp;
    }

    public double toKilometers(double meters) {
        double kilometers = meters * 0.001;
        return kilometers;
    }
    
    public double toHour(int second){
        int hour = second/3600;
        String minute = String.valueOf(((second%3600)/60));        
        if (minute.split("").length==1) {
            minute = "0"+minute;
        }
        return Double.parseDouble(hour+"."+minute);
    }
}

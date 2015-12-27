/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Model.Model_Slg;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.parser.ParseException;

/**
 *
 * @author RIZQY FAHMI
 */
public class DAO_Index {
    private DAO_Tiket tiketDAO;    
    public void upload(InputStream fis, int numSheet) throws IOException, ParseException, Exception {        
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        tiketDAO = new DAO_Tiket();
//        tiketWilayahDAO = new DAO_TiketWilayah();
        
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(numSheet);
        Iterator<org.apache.poi.ss.usermodel.Row> rowIterator = sheet.iterator();        
        while (rowIterator.hasNext()) {            
            Row row = rowIterator.next();
//            System.out.println(row.getCell(5).toString());
//            tiketWilayahDAO.insert(row);            
            tiketDAO.insert(row);            
        }
//        tiketWilayahDAO.display();            
        tiketDAO.display();
    }
    
    public DAO_Tiket getTiketDAO(){
        return tiketDAO;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Algorithm.Runner;
import Dao.DAO_Kantor;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author RIZQY FAHMI
 */
@Controller
public class Controller_Index {

    Runner runner;

    @RequestMapping(value = "/beranda", method = RequestMethod.GET)
    public String get() {
        runner = new Runner();
        return "beranda";
    }

    @RequestMapping(value = "/beranda", method = RequestMethod.POST)
    public String post(@RequestParam("file") MultipartFile file, /*@RequestParam("id") String id,*/ @RequestParam("weight") double weight[], @RequestParam("c") double c, @RequestParam("alpha") double alpha, @RequestParam("betha") double betha, @RequestParam("q") double q, @RequestParam("evaporation") double evaporation, @RequestParam("maxIterations") int maxIterations) throws IOException, ParseException, Exception {
        String r = "result";
        try {
            DAO_Kantor kantorDAO = new DAO_Kantor();
            runner.setAcoParams(c, alpha, betha, q, evaporation, maxIterations);
            runner.setSawWeight(weight);
            runner.setFile(file);
        } catch (Exception e) {
            System.out.println(e);
            r = "redirect:error";
        }        
        return r;
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String getError() {
        runner = new Runner();
        return "error";
    }

    @RequestMapping(value = "/tiket.json", method = RequestMethod.GET)
    public @ResponseBody
    String getTiket() throws ParseException, IOException {
        return runner.getjTiket();
    }

    @RequestMapping(value = "/aco.json", method = RequestMethod.GET)
    public @ResponseBody
    String getAco() throws ParseException, IOException {
        return runner.getjAco();
    }

    @RequestMapping(value = "/saw.json", method = RequestMethod.GET)
    public @ResponseBody
    String getSaw() throws ParseException, IOException {
        return runner.getjSaw();
    }

    @RequestMapping(value = "/iterDistance.json", method = RequestMethod.GET)
    public @ResponseBody
    String getIterDistance() throws ParseException, IOException {
        return runner.getjIterDistance();
    }
    
    @RequestMapping(value = "/iterDuration.json", method = RequestMethod.GET)
    public @ResponseBody
    String getIterDuration() throws ParseException, IOException {
        return runner.getjIterDuration();
    }

    @RequestMapping(value = "/info.json", method = RequestMethod.GET)
    public @ResponseBody
    String getInfo() throws ParseException, IOException {
        return runner.getjInfo();
    }
    
    @RequestMapping(value = "/fifoTour.json", method = RequestMethod.GET)
    public @ResponseBody
    String getFifoTour() throws ParseException, IOException {
        return runner.getjFifoTour();
    }
    
    @RequestMapping(value = "/acoTour.json", method = RequestMethod.GET)
    public @ResponseBody
    String getAcoTour() throws ParseException, IOException {
        return runner.getjAcoTour();
    }
    
    @RequestMapping(value = "/kantorMap.json", method = RequestMethod.GET)
    public @ResponseBody
    String getKantorMap() throws ParseException, IOException {
        return runner.getjKantor();
    }
    
    @RequestMapping(value = "/fifo.json", method = RequestMethod.GET)
    public @ResponseBody
    String getFifo() throws ParseException, IOException {
        return runner.getjFifo();
    }
    
    @RequestMapping(value = "/sawFifo.json", method = RequestMethod.GET)
    public @ResponseBody
    String getSawFifo() throws ParseException, IOException {
        return runner.getjSawFifo();
    }
    
    @RequestMapping(value = "/tiketFifo.json", method = RequestMethod.GET)
    public @ResponseBody
    String getTiketFifo() throws ParseException, IOException {
        return runner.getjTiketFifo();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import Dao.DAO_Index;
import Dao.DAO_Kantor;
import Dao.DAO_Slg;
import Model.Model_Aco;
import Model.Model_GmapInfo;
import Model.Model_Kantor;
import Model.Model_Tiket;
import Other.GoogleService;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author RIZQY FAHMI
 */
public class Runner {

    private DAO_Index indexDAO = new DAO_Index();
    private DAO_Slg slgDAO = new DAO_Slg();
    private DAO_Kantor kantorDAO = new DAO_Kantor();
    private ArrayList<Model_Aco> acoList;
    private ArrayList<FIFO> fifoList;

    private JSONArray jAco = new JSONArray();
    private JSONArray jSaw = new JSONArray();
    private JSONArray jIterDistance = new JSONArray();
    private JSONArray jIterDuration = new JSONArray();
    private JSONArray jTiket = new JSONArray();
    private JSONArray jInfo = new JSONArray();
    private JSONArray jFifoTour = new JSONArray();
    private JSONArray jAcoTour = new JSONArray();
    private JSONArray jKantor = new JSONArray();
    private JSONArray jFifo = new JSONArray();
    private JSONArray jSawFifo = new JSONArray();

    private GoogleService gs = new GoogleService();

    private double sawWeight[];
    private double acoC;
    private double acoAlpha;
    private double acoBetha;
    private double acoQ;
    private double acoEvaporation;
    private int acoMaxIterations;

    public Runner() {
    }

    public void setFile(MultipartFile file) throws IOException, Exception {
        indexDAO.upload(file.getInputStream(), 0);
        runThis();
//        setSAW();
//        setACS();
    }

    public String getTiketDAO() {
        return indexDAO.getTiketDAO().convertToJson();
    }

    public void runThis() throws ParseException, IOException {
        Model_Tiket tiketList = indexDAO.getTiketDAO().view().get(0);
        String regional = tiketList.getRegId();
        String area = tiketList.getArea();
        acoList = new ArrayList<Model_Aco>();
        fifoList = new ArrayList<FIFO>();
        ArrayList<Model_Kantor> kantorList = kantorDAO.searchByRegionalArea(regional, area);
        for (Model_Kantor k : kantorList) {
            System.out.println("Kantor : " + k.getNama());
            ArrayList<Model_GmapInfo> infoList = new ArrayList<Model_GmapInfo>();
            infoList.add(new Model_GmapInfo(k.getLng(), k.getLat(), k.getNama(), k.getAlamat(), false));
            for (Model_Tiket t : indexDAO.getTiketDAO().view()) {
                infoList.add(new Model_GmapInfo(t.getLng(), t.getLat(), "Tiket " + t.getNomorTiket() + " " + t.getHeadline() + " " + t.getLamaGangguan(), t.getAlamat(), true));
            }
            double[][] data = new double[infoList.size()][infoList.size()];
            int[][] duration = new int[infoList.size()][infoList.size()];
            for (int i = 0; i < infoList.size(); i++) {
                for (int j = 0; j < infoList.size(); j++) {
                    Model_GmapInfo point1 = infoList.get(i);
                    Model_GmapInfo point2 = infoList.get(j);
                    data[i][j] = getGs().getDistance(Double.parseDouble(point1.getLat()), Double.parseDouble(point1.getLng()), Double.parseDouble(point2.getLat()), Double.parseDouble(point2.getLng()));
//                    duration[i][j] = getGs().getDuration(point1.getLat(), point1.getLng(), point2.getLat(), point2.getLng());
                    duration[i][j] = getGs().getDurationOffline(data[i][j]);
                }
            }
            ACO acs = new ACO(acoC, acoAlpha, acoBetha, acoEvaporation, acoQ, acoMaxIterations);
            acs.setGraph(data);
            acs.setDuration(duration);
            acs.runThis();
            System.out.println("Duration Length " + acs.getBestDurationLength());
            Model_Aco mAco = new Model_Aco();
            mAco.setKantor(k);
            mAco.setInfoList(infoList);
            mAco.setBestTour(acs.getBestTour());
            mAco.setBestTourIter(acs.getBestTourIter());
            mAco.setBestTourLength(acs.getBestTourLength());
            mAco.setBestDurationIter(acs.getBestDurationIter());
            mAco.setBestDurationLength(acs.getBestDurationLength());
            
            acoList.add(mAco);
            fifoList.add(new FIFO(data, duration, k));
        }
        System.out.println(">>Clear ACO<<");

        double data[][] = new double[acoList.size()][3];
        int index[] = new int[data.length];
        for (int i = 0; i < acoList.size(); i++) {
            Model_Aco aco = acoList.get(i);
            index[i] = i;
            data[i][0] = aco.getBestTourLength();
            data[i][1] = gs.toHour(aco.getBestDurationLength());
            data[i][2] = Double.parseDouble(aco.getKantor().getTeknisi());
            setjAco(aco.getKantor().getNama(), String.valueOf(aco.getBestTourLength()), String.valueOf(data[i][1]), String.valueOf(data[i][2]));
            System.out.println(aco.getKantor().getNama() + "\t" + aco.getBestDurationLength() + "\t" + gs.toHour(aco.getBestDurationLength()) + "\t" + aco.getBestTourLength() + "\t" + aco.getKantor().getTeknisi());
        }

        int benefit[] = {0, 0, 1};
        double weight[] = {5, 4, 4};
        SAW sw = new SAW(data, sawWeight, benefit, index);
        sw.normalization();
        sw.rankThis();
        double total[] = sw.getTotal();
        int j = 0;
        index = sw.getIndexData();
        for (int i : index) {
            setjIterDistance(i);
            setjIterDuration(i);
            setjSaw(String.valueOf(j + 1), acoList.get(i).getKantor().getNama(), String.valueOf(data[j][0]), String.valueOf(data[j][1]), String.valueOf(data[j][2]), String.valueOf(total[j]));
            j++;
        }
        setjTiket(index[0]);
        setjInfo(index[0]);
        setjKantor(acoList.get(index[0]).getKantor());
        calculateFifo(benefit, weight);
        setjFifoTour(index[0]);
        setjAcoTour(acoList);
    }

    public void calculateFifo(int benefit[], double weight[]) {
        double data[][] = new double[fifoList.size()][3];
        int index[] = new int[fifoList.size()];
        for (int i = 0; i < fifoList.size(); i++) {
            FIFO fifo = fifoList.get(i);
            index[i] = i;
            data[i][0] = gs.doubleFormater(fifo.getTotalDistance());
            data[i][1] = gs.doubleFormater(gs.toHour(fifo.getTotalDuration()));
            data[i][2] = Double.parseDouble(fifo.getKantor().getTeknisi());
            setjFifo(fifo.getKantor().getNama(), String.valueOf(data[i][0]), String.valueOf(data[i][1]), String.valueOf(data[i][2]));
        }

        SAW sw = new SAW(data, sawWeight, benefit, index);
        sw.normalization();
        sw.rankThis();
        double total[] = sw.getTotal();
        int j = 0;
        index = sw.getIndexData();
        for (int i : index) {
            setjSawFifo(String.valueOf(j + 1), fifoList.get(i).getKantor().getNama(), String.valueOf(data[j][0]), String.valueOf(data[j][1]), String.valueOf(data[j][2]), String.valueOf(total[j]));
            j++;
        }

    }
    
    
    
    public void setAcoParams(double c, double alpha, double betha, double q, double evaporation, int maxIterations) {
        acoC = c;
        acoAlpha = alpha;
        acoBetha = betha;
        acoQ = q;
        acoEvaporation = evaporation;
        acoMaxIterations = maxIterations;
    }

    public void setSawWeight(double[] sawWeight) {
        this.sawWeight = sawWeight;
    }

    public double[] getSawWeight() {
        return sawWeight;
    }

    public void setjKantor(Model_Kantor kantor) throws ParseException {
        for (Model_Kantor k : kantorDAO.view()) {
            if (!kantor.getId().equals(k.getId())) {
                JSONObject object2 = new JSONObject();
                object2.put("id", k.getId());
                object2.put("nama", k.getNama());
                object2.put("alamat", k.getAlamat());
                object2.put("telepon", k.getTelepon());
                object2.put("fax", k.getFax());
                object2.put("lat", k.getLat());
                object2.put("lng", k.getLng());
                this.jKantor.add(object2);
            }
        }
    }

    public String getjKantor() {
        JSONObject object = new JSONObject();
        object.put("data", this.jKantor);
        return object.toJSONString();
    }

    public String getjAco() {
        JSONObject object = new JSONObject();
        object.put("data", this.jAco);
        return object.toJSONString();
    }

    public void setjAco(String kantor, String jarak, String durasi, String teknisi) {
        JSONObject object2 = new JSONObject();
        object2.put("kantor", kantor);
        object2.put("jarak", jarak);
        object2.put("durasi", durasi);
        object2.put("teknisi", teknisi);
        this.jAco.add(object2);
    }

    public String getjFifo() {
        JSONObject object = new JSONObject();
        object.put("data", this.jFifo);
        return object.toJSONString();
    }

    public void setjFifo(String kantor, String jarak, String durasi, String teknisi) {
        JSONObject object2 = new JSONObject();
        object2.put("kantor", kantor);
        object2.put("jarak", jarak);
        object2.put("durasi", durasi);
        object2.put("teknisi", teknisi);
        this.jFifo.add(object2);
    }

    public String getjSaw() {
        JSONObject object = new JSONObject();
        object.put("data", this.jSaw);
        return object.toJSONString();
    }

    public void setjSaw(String rank, String kantor, String jarak, String durasi, String teknisi, String total) {
        JSONObject object2 = new JSONObject();
        object2.put("rank", rank);
        object2.put("kantor", kantor);
        object2.put("jarak", jarak);
        object2.put("durasi", durasi);
        object2.put("teknisi", teknisi);
        object2.put("saw", total);
        this.jSaw.add(object2);
    }

    public String getjSawFifo() {
        JSONObject object = new JSONObject();
        object.put("data", this.jSawFifo);
        return object.toJSONString();
    }

    public void setjSawFifo(String rank, String kantor, String jarak, String durasi, String teknisi, String total) {
        JSONObject object2 = new JSONObject();
        object2.put("rank", rank);
        object2.put("kantor", kantor);
        object2.put("jarak", jarak);
        object2.put("durasi", durasi);
        object2.put("teknisi", teknisi);
        object2.put("saw", total);
        this.jSawFifo.add(object2);
    }

    public String getjIterDistance() {
        JSONObject object = new JSONObject();
        object.put("dataset", this.jIterDistance);
        return object.toJSONString();
    }

    public void setjIterDistance(int i) {
        JSONObject object2 = new JSONObject();
        JSONArray arr2 = new JSONArray();
        for (int j = 0; j < acoList.get(i).getBestTourIter().length; j++) {
            JSONArray arr3 = new JSONArray();
            arr3.add(j + 1);
            arr3.add(acoList.get(i).getBestTourIter()[j]);
            arr2.add(arr3);
        }
        object2.put("label", acoList.get(i).getKantor().getNama());
        object2.put("data", arr2);
        this.jIterDistance.add(object2);
    }

    public String getjIterDuration() {
        JSONObject object = new JSONObject();
        object.put("dataset", this.jIterDuration);
        return object.toJSONString();
    }

    public void setjIterDuration(int i) {
        JSONObject object2 = new JSONObject();
        JSONArray arr2 = new JSONArray();
        for (int j = 0; j < acoList.get(i).getBestDurationIter().length; j++) {
            JSONArray arr3 = new JSONArray();
            arr3.add(j + 1);
            arr3.add(gs.toHour(acoList.get(i).getBestDurationIter()[j]));
            arr2.add(arr3);
        }
        object2.put("label", acoList.get(i).getKantor().getNama());
        object2.put("data", arr2);
        this.jIterDuration.add(object2);
    }

    public String getjTiket() {
        JSONObject object = new JSONObject();
        object.put("data", this.jTiket);
        return object.toJSONString();
    }
    
    public void setjFifoTour(int index) {
        FIFO fifo = fifoList.get(index);
        Model_GmapInfo info = new Model_GmapInfo(fifo.getKantor().getLng(), fifo.getKantor().getLat(), fifo.getKantor().getNama(), fifo.getKantor().getAlamat(), false);
        this.jFifoTour.add(info.getJSONObject());
        for (Model_Tiket t : indexDAO.getTiketDAO().view()) {
            Model_GmapInfo info2 = new Model_GmapInfo(t.getLng(), t.getLat(), "Tiket " + t.getNomorTiket() + " " + t.getHeadline() + " " + t.getLamaGangguan(), t.getAlamat(), true);
            this.jFifoTour.add(info2.getJSONObject());
        }
    }
    
    public String getjFifoTour() {
        JSONObject object = new JSONObject();
        object.put("data", this.jFifoTour);
        return object.toJSONString();
    }
    
    public void setjAcoTour(ArrayList<Model_Aco> acoList) {
        double minDistance = Double.MAX_VALUE;
        int minDuration = Integer.MAX_VALUE;
        int min = 0;
        for (int i = 0; i < acoList.size(); i++) {
            if ((minDistance > acoList.get(i).getBestTourLength()) && (minDuration > acoList.get(i).getBestDurationLength())) {
                minDistance = acoList.get(i).getBestTourLength();
                minDuration = acoList.get(i).getBestDurationLength();
                min = i;
            }
        }
        Model_Aco aco = acoList.get(min);
        for (int i = 0; i < aco.getBestTour().length; i++) {
            Model_GmapInfo g = aco.getInfoList().get(aco.getBestTour()[i]);
            this.jAcoTour.add(g.getJSONObject());
        }        
    }
    
    public String getjAcoTour() {
        JSONObject object = new JSONObject();
        object.put("data", this.jAcoTour);
        return object.toJSONString();
    }
    
    public void setjInfo(int index) {
        Model_Aco aco = acoList.get(index);
        for (int i = 0; i < aco.getBestTour().length; i++) {
            Model_GmapInfo g = aco.getInfoList().get(aco.getBestTour()[i]);
            this.jInfo.add(g.getJSONObject());
        }
    }

    public String getjInfo() {
        JSONObject object = new JSONObject();
        object.put("data", this.jInfo);
        return object.toJSONString();
    }

    public void setjTiket(int index) {
        for (int i = 1; i < acoList.get(index).getBestTour().length; i++) {
            Model_Tiket t = indexDAO.getTiketDAO().view().get(acoList.get(index).getBestTour()[i] - 1);
            JSONObject j = t.getJSONObject();
            j.put("nomorKunjungan", i);
            jTiket.add(j);
        }
    }

    public String getjTiketFifo() {
        JSONObject object = new JSONObject();
        JSONArray arr = new JSONArray();
        int i = 1;
        for (Model_Tiket t : indexDAO.getTiketDAO().view()) {
            JSONObject j = t.getJSONObject();
            j.put("nomorKunjungan", i);
            arr.add(j);
            i++;
        }
        object.put("data", arr);
        return object.toJSONString();
    }

    public GoogleService getGs() {
        return gs;
    }

}

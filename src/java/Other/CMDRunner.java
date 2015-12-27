/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import Algorithm.ACO;
import Algorithm.SAW;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import org.json.simple.parser.ParseException;

/**
 *
 * @author RIZQY FAHMI
 */
public class CMDRunner {

    public void distance(double kantor[][], double tiket[][]) throws IOException, MalformedURLException, ParseException {
        GoogleService gs = new GoogleService();
        for (int i = 0; i < kantor.length; i++) {
            System.out.println("Kantor ke " + (i + 1));

            double dist = gs.getDistance(kantor[i][0], kantor[i][1], tiket[0][0], tiket[0][1]);
            int d = gs.getDuration(String.valueOf(kantor[i][0]), String.valueOf(kantor[i][1]), String.valueOf(tiket[0][0]), String.valueOf(tiket[0][1]));
            int dur = d;
            System.out.println(gs.getDistance(kantor[i][0], kantor[i][1], tiket[0][0], tiket[0][1]) + "\t" + gs.toHour(d));
            for (int j = 0; j < tiket.length - 1; j++) {
                dist += gs.getDistance(tiket[j][0], tiket[j][1], tiket[j + 1][0], tiket[j + 1][1]);
                d = gs.getDuration(String.valueOf(tiket[j][0]), String.valueOf(tiket[j][1]), String.valueOf(tiket[j + 1][0]), String.valueOf(tiket[j + 1][1]));
                dur += d;
                System.out.println(gs.getDistance(tiket[j][0], tiket[j][1], tiket[j + 1][0], tiket[j + 1][1]) + "\t" + gs.toHour(d));
            }
            dist += gs.getDistance(tiket[tiket.length - 1][0], tiket[tiket.length - 1][1], kantor[i][0], kantor[i][1]);
            d = gs.getDuration(String.valueOf(tiket[tiket.length - 1][0]), String.valueOf(tiket[tiket.length - 1][1]), String.valueOf(kantor[i][0]), String.valueOf(kantor[i][1]));
            dur += d;
            System.out.println(gs.getDistance(tiket[tiket.length - 1][0], tiket[tiket.length - 1][1], kantor[i][0], kantor[i][1]) + "\t" + gs.toHour(d));
            System.out.println("Jarak : " + gs.doubleFormater(dist));
            System.out.println("Durasi : " + gs.doubleFormater(gs.toHour(dur)));
        }
    }

    public double[][] getDistance(double coordinate[][]) {
        GoogleService gs = new GoogleService();
        double distance[][] = new double[coordinate.length][coordinate.length];
        for (int i = 0; i < coordinate.length; i++) {
            for (int j = 0; j < coordinate.length; j++) {
                distance[i][j] = gs.getDistance(coordinate[i][0], coordinate[i][1], coordinate[j][0], coordinate[j][1]);
                System.out.print(distance[i][j] + "\t");
            }
            System.out.println("");
        }
        return distance;
    }

    public int[][] getDuration(double coordinate[][]) throws IOException, MalformedURLException, ParseException {
        GoogleService gs = new GoogleService();
        int duration[][] = new int[coordinate.length][coordinate.length];
        for (int i = 0; i < coordinate.length; i++) {
            for (int j = 0; j < coordinate[i].length; j++) {
                duration[i][j] = gs.getDurationOffline(coordinate[i][j]);
                System.out.print(duration[i][j] + "\t");
            }
            System.out.println("");
        }
        return duration;
    }

    public void display(double x[][]) {
        for (int j = 0; j < x.length; j++) {
            for (int k = 0; k < x[j].length; k++) {
                System.out.print(x[j][k] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void displayCoor(double x[][]){
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                System.out.print(x[i][j]+"\t");
            }
            System.out.println("");
        }
    }
    
    public void test() throws IOException, MalformedURLException, ParseException {
        GoogleService gs = new GoogleService();
        int a = gs.getDurationOffline(13.68);
        System.out.println(gs.toHour(a));

        double kantor[][] = {
            {-6.960573, 107.66437100000007},//BANDUNG CIJAURA
            {-6.9364953, 107.59533060000001},//BANDUNG KOPO
            {-6.9173734, 107.61184760000003},//BANDUNG LEMBONG
            {-6.9126113, 107.57441070000004},//BANDUNG RAJAWALI
            {-6.854885899999999, 107.59587390000002},//BANDUNG SETIABUDI
            {-6.9023692, 107.62708120000002},//BANDUNG SUPRATMAN
            {-6.9352756, 107.71779839999999}//BANDUNG UJUNGBERUNG
        };

//        double tiket01052013[][] = {
//            {-6.885783, 107.578918},
//            {-6.899246, 107.640928},
//            {-6.928277, 107.609483},
//            {-6.886107, 107.601519},
//            {-6.931486, 107.625803},
//            {-6.921304, 107.610325},
//            {-6.9047, 107.607},
//            {-6.918357, 107.610599},
//            {-6.884738, 107.580636},
//            {-6.92059, 107.617671},
//            {-6.858584, 107.596455},
//            {-6.931379, 107.615799}
//        };
//        distance(kantor, tiket01052013);
//        
        double tiket13032015[][] = {
            {-6.949212, 107.619524},
            {-6.937264, 107.579333},
            {-6.976767, 107.619121},
            {-6.943073, 107.66406},
            {-6.944825, 107.596503},
            {-6.906928, 107.621216},
            {-6.946432, 107.592255},
            {-6.93459, 107.650161},
            {-6.914631, 107.604426},
            {-6.896443, 107.610556}
        };
        distance(kantor, tiket13032015);
//        double tiket13052013[][] = {
//            {-6.913848, 107.639031},
//            {-6.922372, 107.629478},
//            {-6.940672, 107.6267},
//            {-6.907319, 107.630349},
//            {-6.873049, 107.595348},
//            {-6.931885, 107.542353},
//            {-6.914985, 107.634926},
//            {-6.898863, 107.631805},
//            {-6.946322, 107.608141},
//            {-6.877675, 107.591041},
//            {-6.927274, 107.636485},
//            {-6.884896, 107.581612},
//            {-6.937539, 107.580482},
//            {-6.912453, 107.635696},
//            {-6.91584, 107.629},
//        };
//        distance(kantor, tiket13052013);
//        double tiket29042013[][] = {
//            {-6.895764, 107.592324},
//            {-6.909271, 107.626863},
//            {-6.904288, 107.599989},
//            {-6.885895, 107.596846},
//            {-6.940815, 107.667485},
//            {-6.972686, 107.572892},
//            {-6.917904, 107.609458}
//        };
//        distance(kantor, tiket29042013);
//        double tiket31032015[][] = {
//            {-6.927834, 107.615298},
//            {-6.915055, 107.600303},
//            {-6.962717, 107.576366},
//            {-6.932268, 107.57128},
//            {-6.931222, 107.624467},
//            {-6.951837299999999, 107.6318903},
//            {-6.965727, 107.576513},
//            {-6.938238, 107.67125},
//            {-6.909535, 107.585528},
//            {-6.908044, 107.649837},
//            {-6.953718, 107.612626}
//        };
//        distance(kantor, tiket31032015);

//        for (int i = 0; i < 1; i++) {
//            double coordinate[][] = new double[tiket31032015.length + 1][2];
//            coordinate[0][0] = kantor[i][0];
//            coordinate[0][1] = kantor[i][1];
//            for (int j = 0; j < tiket31032015.length; j++) {
//                coordinate[j + 1][0] = tiket31032015[j][0];
//                coordinate[j + 1][1] = tiket31032015[j][1];
//            }
//            System.out.println("Jarak");
//            display(getDistance(coordinate));
//            System.out.println("Durasi");
//            display(getDuration(coordinate));
//        }
    }

    public void durCal() {
        Scanner nilai = new Scanner(System.in);
        int jarak, kecepatan, meter;
        System.out.println("Menghitung waktu tempuh\n");
        System.out.print("masukkan jarak yang ditempuh(km) : ");
        jarak = nilai.nextInt();
        System.out.print("masukkan nilai kecepatan(Km/jam) : ");
        kecepatan = nilai.nextInt();
        // penyederhanaan nilai
        meter = jarak * 1000;
        int kecep = kecepatan * 1000;
        double kec2 = ((double) (kecep) / 3600);
        System.out.println("ke " + kec2 + "\t" + kecep);

        double waktu = meter / kec2;
        int waktu1 = (int) waktu;
        //proses pengubahan detik menjadi jam:menit:detik
        int Jam, Menit, Detik, sisa;
        sisa = waktu1 % 3600;
        Jam = (waktu1 - sisa) / 3600;
        waktu1 = sisa;
        sisa = waktu1 % 60;
        Menit = (waktu1 - sisa) / 60;
        waktu1 = sisa;
        sisa = waktu1 % 1;
        Detik = (waktu1 - sisa) / 1;
        System.out.println("waktu yang diperlukan untuk menempuh jarak sejauh " + jarak + " km ");
        System.out.print("Dengan kecepatan " + kecepatan + " Km/jam adalah...");
        if (waktu < 60) {
            System.out.println(+Detik + " detik");
        } else if (waktu < 3600) {
            System.out.println(+Menit + " menit " + Detik + " detik");
        } else if (waktu >= 3600) {
            System.out.println(+Jam + " jam " + Menit + " menit " + Detik + " detik");
        }
    }

    public static void main(String[] args) throws Exception {

        String alamat[] = {
            "Jl. Margahayu, Bandung",
            "Jl. Karapitan, Bandung",
            "Jl. Cibaduyut, Bandung",
            "Jl. Asia Afrika, Bandung",
            "Jl. Braga, Bandung",
            "Jl. Mohamad Toha, Bandung",
            "Jl. Banceuy, Bandung",
            "Jl. Bangka, Bandung",
            "Jl. Sultan Agung, Bandung",
            "Jl. Buah Batu, Bandung"
        };
//
////        String kantor[] = {
////            "Jl. Raya Ciwastra Bandung Jawa Barat",
////            "Jl. Kopo Sayati No.182 Bandung Jawa Barat",
////            "Jl. Lembong 11 Bandung Jawa Barat",
////            "Jl. Rajawali No.101 Bandung Jawa Barat",
////            "Jl. Setiabudhi No.87 Bandung Jawa Barat",
////            "Jl. WR Supratman No.62 Bandung Jawa Barat",
////            "Jl. Raya Ujung Berung Km.12 Bandung Jawa Barat"
////        };
        String kantor[] = {
            "Jl. Raya Ciwastra Bandung Jawa Barat",
            "Jl. Kopo Sayati Bandung Jawa Barat",
            "Jl. Lembong Bandung Jawa Barat",
            "Jl. Rajawali Bandung Jawa Barat",
            "Jl. Setiabudhi Bandung Jawa Barat",
            "Jl. WR Supratman Bandung Jawa Barat",
            "Jl. Raya Ujung Berung Bandung Jawa Barat"
        };

////
        double teknisi[] = {10, 16, 20, 20, 20, 20, 16};
        GoogleService gs = new GoogleService();
//        System.out.println(gs.getDurationOffline(1.52));
//        System.out.println(gs.toHour(gs.getDurationOffline(1.52)));
        CMDRunner runner = new CMDRunner();
//        

//        String temp[] = gs.getLatLongByPlace(kantor[0]);
//        
//        String latLng[][] = new String[alamat.length+1][2];
//        latLng[0][0] = temp[0];
//        latLng[0][1] = temp[1];
//        
//        
        String ticketCoor[][] = new String[alamat.length][2];
        for (int i = 0; i < alamat.length; i++) {
            String temp2[] = gs.getLatLongByPlace(alamat[i]);
            ticketCoor[i][0] = temp2[0];
            ticketCoor[i][1] = temp2[1];
        }
//        
//        for (int i = 0; i < latLng.length; i++) {
//            for (int j = 0; j < latLng[i].length; j++) {
//                System.out.print(latLng[i][j]+"\t");                
//            }
//            System.out.println("");
//        }
//        
//        double dataDistance[][] = new double[latLng.length][latLng.length];
//        System.out.println("Distance");
//        for (int j = 0; j < latLng.length; j++) {
//            for (int k = 0; k < latLng.length; k++) {
//                dataDistance[j][k] = gs.getDistance(Double.parseDouble(latLng[j][0]), Double.parseDouble(latLng[j][1]), Double.parseDouble(latLng[k][0]), Double.parseDouble(latLng[k][1]));
//                System.out.print(dataDistance[j][k]+"\t");
//            }
////            System.out.println(latLng[j][0]+"\t"+latLng[j][1]);
//            System.out.println("");
//        }
////        
//        double dataDuration[][] = new double[latLng.length][latLng.length];
//        System.out.println("Duration");
//        for (int j = 0; j < latLng.length; j++) {
//            for (int k = 0; k < latLng.length; k++) {
////                dataDuration[j][k] = gs.getDistance(Double.parseDouble(latLng[j][0]), Double.parseDouble(latLng[j][1]), Double.parseDouble(latLng[k][0]), Double.parseDouble(latLng[k][1]));
//                dataDuration[j][k] = gs.toHour(gs.getDurationOffline(dataDistance[j][k]));
//                System.out.print(dataDuration[j][k]+"\t");
//            }
////            System.out.println(latLng[j][0]+"\t"+latLng[j][1]);
//            System.out.println("");
//        }
        
        double data[][] = new double[kantor.length][3];
        int index[] = new int[data.length];
        for (int i = 0; i < kantor.length; i++) {
            double latLng[][] = new double[alamat.length + 1][2];
            String coor[] = gs.getLatLongByPlace(kantor[i]);
            latLng[0][0] = Double.parseDouble(coor[0]);
            latLng[0][1] = Double.parseDouble(coor[1]);
            for (int j = 0; j < ticketCoor.length; j++) {
                latLng[j + 1][0] = Double.parseDouble(ticketCoor[j][0]);
                latLng[j + 1][1] = Double.parseDouble(ticketCoor[j][1]);
            }
            System.out.println("");
            runner.displayCoor(latLng);
            System.out.println("");
//            System.out.println("Distance");
//            double distance[][] = runner.getDistance(latLng);
//            System.out.println("Duration");
//            int duration[][] = runner.getDuration(distance);
//            System.out.println("\n\n");
//
//            ACO acs = new ACO(1.0, 1.0, 5, 0.5, 1, 100);
//            acs.setGraph(distance);
//            acs.setDuration(duration);
//            acs.runThis();
//            data[i][0] = acs.getBestTourLength();
//            data[i][1] = gs.toHour(acs.getBestDurationLength());
//            data[i][2] = teknisi[i];
//            index[i] = i;
        }

        
//        int benefit[] = {0, 0, 1};
//        double weight[] = {4, 4, 5};
//        
//        SAW sw = new SAW(data, weight, benefit, index);
//        sw.normalization();
//        sw.rankThis();
//        double total[] = sw.getTotal();        
//        index = sw.getIndexData();
//        for (int i : index) {
//            System.out.println(i);            
//        }
        
    }

}

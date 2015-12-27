/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Other;

import java.util.Scanner;

/**
 *
 * @author Rizqy Fahmi
 */
public class HitungWaktu {

    public static void main(String[] argh) {
        Scanner nilai = new Scanner(System.in);
        double jarak, kecepatan, meter;
        jarak = 27.95;
        kecepatan = 30;
        double j = jarak * 1000;
        double k = ((kecepatan * 1000)/3600);
        double w = j/k;
        System.out.println(w);
        double sisa = w % 3600;
        int jam = (int) (w-sisa)/3600; 
        w = sisa;
        sisa = w % 60;
        int menit = (int) (w-sisa)/60;         
        w = sisa;
        sisa = w % 1;
        int detik = (int) (w-sisa)/1;
        System.out.println(j+"\t"+k+"\t"+w+"\t"+sisa+"\t"+jam+"\t"+menit+"\t"+detik);
        

//        // penyederhanaan nilai
//        meter = jarak * 1000;
//        double kecep = kecepatan * 1000;
//        double kec2 = ((double) (kecep) / 3600);
//        System.out.println(kec2);
//        double waktu = meter / kec2;
//        int waktu1 = (int) waktu;
//        System.out.println(waktu+"\t"+waktu1);
////        //proses pengubahan detik menjadi jam:menit:detik
//        int Jam, Menit, Detik;
//        sisa = waktu1 % 3600;
//        System.out.println(sisa);
//        Jam = (int) ((waktu1 - sisa) / 3600);
//        waktu1 = (int) sisa;
//        sisa = waktu1 % 60;
//        Menit = (int) ((waktu1 - sisa) / 60);
//        waktu1 = (int) sisa;
//        sisa = waktu1 % 1;
//        Detik = (int) ((waktu1 - sisa) / 1);
//        System.out.println("waktu yang diperlukan untuk menempuh jarak sejauh " + jarak + " km ");
//        System.out.print("Dengan kecepatan " + kecepatan + " Km/jam adalah...");
//        if (waktu < 60) {
//            System.out.println(+Detik + " detik");
//        } else if (waktu < 3600) {
//            System.out.println(+Menit + " menit " + Detik + " detik");
//        } else if (waktu >= 3600) {
//            System.out.println(+Jam + " jam " + Menit + " menit " + Detik + " detik");
//        }
    }
}

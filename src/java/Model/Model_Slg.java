/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author RIZQY FAHMI
 */
public class Model_Slg {
    private String namaSlg;
    private double maksimalPerbaikan;

    public Model_Slg(String namaSlg, double maksimalPerbaikan) {
        this.namaSlg = namaSlg;
        this.maksimalPerbaikan = maksimalPerbaikan;
    }

    public String getNamaSlg() {
        return namaSlg;
    }

    public void setNamaSlg(String namaSlg) {
        this.namaSlg = namaSlg;
    }

    public double getMaksimalPerbaikan() {
        return maksimalPerbaikan;
    }

    public void setMaksimalPerbaikan(double maksimalPerbaikan) {
        this.maksimalPerbaikan = maksimalPerbaikan;
    }
    
    
}

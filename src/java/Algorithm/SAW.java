/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author X450JN
 */
public class SAW {

    private double data[][];
    private double normalizedData[][];
    private double weight[];
    private double min[], max[];
    private int benefit[];
    private int indexData[];
//    private String indexDataString[];
    private int dataLength;
    private int variableLength;
    private double total[];

    public SAW(double[][] data, double[] weight, int[] benefit, int indexData[]) {
        this.data = data;
        this.weight = weight;
        this.benefit = benefit;
        this.dataLength = data.length;
        this.variableLength = data[0].length;
        this.indexData = indexData;
        this.min = new double[this.variableLength];
        this.max = new double[this.variableLength];
        this.normalizedData = new double[this.dataLength][this.variableLength];
        this.total = new double[this.dataLength];
        setMinMax();
//        displayData();
    }
    
    public SAW(double[][] data, double[] weight, int[] benefit, String indexDataString[]) {
        this.data = data;
        this.weight = weight;
        this.benefit = benefit;
        this.dataLength = data.length;
        this.variableLength = data[0].length;
//        this.indexDataString = indexDataString;
        this.min = new double[this.variableLength];
        this.max = new double[this.variableLength];
        this.normalizedData = new double[this.dataLength][this.variableLength];
        this.total = new double[this.dataLength];
        setMinMax();
//        displayData();
    }

    public void setMinMax() {
        for (int i = 0; i < this.variableLength; i++) {
            double tMin = Double.MAX_VALUE;
            double tMax = 0;
            for (int j = 0; j < this.dataLength; j++) {
                System.out.print(this.data[j][i]+"\t");
                if (tMin > this.data[j][i]) {
                    tMin = this.data[j][i];
                }
                if (tMax < this.data[j][i]) {
                    tMax = this.data[j][i];
                }
            }
            
            System.out.println("MAX: "+tMax+"\tMIN: "+tMin);
            this.min[i] = tMin;
            this.max[i] = tMax;
        }
        
//        for (int i = 0; i < this.variableLength; i++) {
//            System.out.println(max[i]+" "+min[i]);
//        }
    }

    public void normalization() {
        for (int i = 0; i < this.dataLength; i++) {
            for (int j = 0; j < this.variableLength; j++) {
                if (this.benefit[j] == 0) { //cost
                    this.normalizedData[i][j] = this.min[j] / this.data[i][j];
                } else if (this.benefit[j] == 1) { //benefit
                    this.normalizedData[i][j] = this.data[i][j] / this.max[j];
                }
            }

        }
    }

    public double calTotal(double temp[]) {
        double t = 0;
        for (int i = 0; i < temp.length; i++) {            
            t += this.weight[i] * temp[i];
        }        
        return t;
    }
    
    public void displayCalTotal(double temp[]) {
        double t = 0;
        for (int i = 0; i < temp.length; i++) {
            System.out.print(this.weight[i]+" * "+doubleFormater(temp[i])+"\t");
            t += this.weight[i] * temp[i];
        }
        System.out.println("Total : "+doubleFormater(t));        
    }

    public void rankThis() {
        for (int i = 0; i < this.dataLength; i++) {
            displayCalTotal(this.normalizedData[i]);            
        }
        
        for (int i = 0; i < this.dataLength; i++) {
            for (int j = 0; j < this.dataLength; j++) {
                if (calTotal(this.normalizedData[i]) > calTotal(this.normalizedData[j])) {
//                    System.out.println(true);
                    double temp[] = this.data[i];
                    this.data[i] = this.data[j];
                    this.data[j] = temp;
                    
                    int tempIndex = this.indexData[i];
                    this.indexData[i] = this.indexData[j];
                    this.indexData[j] = tempIndex;
                }
                normalization();
            }
        }
        for (int i = 0; i < this.dataLength; i++) {            
            this.total[i] = doubleFormater(calTotal(this.normalizedData[i]));
        }
//        System.out.println("");
//        displayData();
    }   

    public void displayData() {
//        System.out.println("");
        for (int i = 0; i < this.dataLength; i++) {
            System.out.print((this.indexData[i]+1)+" : \t");
            for (int j = 0; j < this.variableLength; j++) {
                System.out.print(doubleFormater(this.data[i][j]) + "\t");
            }
//            this.total[i] = doubleFormater(calTotal(this.normalizedData[i]));
            System.out.println(" : " + doubleFormater(this.total[i]));
        }
    }
    
    public double doubleFormater(double x) {
        NumberFormat formatter = new DecimalFormat("#.###");
        double temp = Double.parseDouble(String.valueOf(formatter.format(x)).replaceAll(",", "."));
        return temp;
    }
    
    
//    public static void main(String args[]) {
//        double data[][] = {{54, 120, 300}, {80, 150, 340}, {60, 120, 350}};
//        double weight[] = {5, 4, 4};
//        int benefit[] = {1, 1, 0};
//        SAW saw = new SAW(data, weight, benefit);
//        saw.normalization();
//        saw.rankThis();
//    }

    public double[][] getData() {
        return data;
    }

    public double[] getTotal() {
        return total;
    }

    public int[] getIndexData() {
        return indexData;
    }

//    public String[] getIndexDataString() {
//        return indexDataString;
//    }
    
    
}

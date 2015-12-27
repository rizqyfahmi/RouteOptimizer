/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import Model.Model_Kantor;

/**
 *
 * @author Rizqy Fahmi
 */
public class FIFO {
    private double distance[][];
    private double totalDistance;
    private int duration[][];
    private int totalDuration;
    private Model_Kantor kantor;
    
    public FIFO(double[][] distance, int[][] duration, Model_Kantor kantor) {
        this.distance = distance;
        this.duration = duration;
        this.kantor = kantor;
//        System.out.println(this.distance.length+"\t"+this.distance.length);
        setTotalDistance(calculateDistance(this.distance));
        setTotalDuration(calculateDuration(this.duration));
    }
    
    public double calculateDistance(double x[][]){
        double length = x[x.length-1][0];
        for (int i = 0; i < x.length-1; i++) {
            length += x[i][i+1];
        }
        return length;
    }
    
    public int calculateDuration(int x[][]){
        int length = x[x.length-1][0];
        for (int i = 0; i < x.length-1; i++) {
            length += x[i][i+1];
        }
        return length;
    }

    public double[][] getDistance() {
        return distance;
    }

    public void setDistance(double[][] distance) {
        this.distance = distance;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(double totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int[][] getDuration() {
        return duration;
    }

    public void setDuration(int[][] duration) {
        this.duration = duration;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(int totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Model_Kantor getKantor() {
        return kantor;
    }

    public void setKantor(Model_Kantor kantor) {
        this.kantor = kantor;
    }
    
    
}

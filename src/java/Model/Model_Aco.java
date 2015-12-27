/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author RIZQY FAHMI
 */
public class Model_Aco {
    public int bestTour[];
    public double bestTourLength;
    public double bestTourIter[];
    public int bestDurationLength;
    public int bestDurationIter [];
    public Model_Kantor kantor;
    public ArrayList<Model_GmapInfo> infoList;   

    public Model_Aco() {
    }

    public int[] getBestTour() {
        return bestTour;
    }

    public void setBestTour(int[] bestTour) {
        this.bestTour = bestTour;
    }

    public double getBestTourLength() {
        return bestTourLength;
    }

    public void setBestTourLength(double bestTourLength) {
        this.bestTourLength = bestTourLength;
    }

    public double[] getBestTourIter() {
        return bestTourIter;
    }

    public void setBestTourIter(double[] bestTourIter) {
        this.bestTourIter = bestTourIter;
    }

    public int getBestDurationLength() {
        return bestDurationLength;
    }

    public void setBestDurationLength(int bestDurationLength) {
        this.bestDurationLength = bestDurationLength;
    }

    public int[] getBestDurationIter() {
        return bestDurationIter;
    }

    public void setBestDurationIter(int[] bestDurationIter) {
        this.bestDurationIter = bestDurationIter;
    }

    public Model_Kantor getKantor() {
        return kantor;
    }

    public void setKantor(Model_Kantor kantor) {
        this.kantor = kantor;
    }

    public ArrayList<Model_GmapInfo> getInfoList() {
        return infoList;
    }

    public void setInfoList(ArrayList<Model_GmapInfo> infoList) {
        this.infoList = infoList;
    }

    
    
    
    
}

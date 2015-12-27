/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beckup;

import Algorithm.*;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author RIZQY FAHMI
 */
public class ACS {

    private double c;
    private double alpha;
    private double beta;
    private double evaporation;
    private double Q;
    private int maxIterations; //NC MAX
    public int n = 0; // # towns
    public int m = 0; // # ants
    private double graph[][];
    private double trails[][];
    private Ant ants[];
    private Random rand = new Random();
    private double probs[];
    private int currentIndex;//S

    public int[] bestTour;
    public double bestTourLength;

    private class Ant {

        public int tour[] = new int[graph.length];
        // Maintain visited list for towns, much faster
        // than checking if in tour so far.
        public boolean visited[] = new boolean[graph.length];

        public void visitTown(int town) {
            tour[currentIndex + 1] = town;
            visited[town] = true;
        }

        public boolean visited(int i) {
            return visited[i];
        }

        public double tourLength() {
            double length = graph[tour[n - 1]][tour[0]];
            for (int i = 0; i < n - 1; i++) {
                length += graph[tour[i]][tour[i + 1]];
            }
            return length;
        }

        public void clear() {
            for (int i = 0; i < n; i++) {
                visited[i] = false;
            }
        }
    }

    public ACS(double c, double alpha, double beta, double evaporation, double Q, int maxIterations) {
        this.c = c;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporation = evaporation;
        this.Q = Q;
        this.maxIterations = maxIterations;
    }

    public void setGraph(double[][] graph) {
        this.graph = graph;
        n = this.graph.length;
        m = n;
//        System.out.println("NAF : " + m);
//        // all memory allocations done here
        trails = new double[n][n];
        probs = new double[n];
        ants = new Ant[m];
        for (int j = 0; j < m; j++) {
            ants[j] = new Ant();
        }

    }

    private void probTo(Ant ant) {
        int i = ant.tour[currentIndex];

        double denom = 0.0;
        for (int l = 0; l < n; l++) {
//            System.out.println(ant.visited(l));
            if (!ant.visited(l)) {
                denom += Math.pow(trails[i][l], alpha) * Math.pow(1.0 / graph[i][l], beta);
            }
        }

        for (int j = 0; j < n; j++) {
            if (ant.visited(j)) {
                probs[j] = 0.0;
            } else {
                double numerator = Math.pow(trails[i][j], alpha) * Math.pow(1.0 / graph[i][j], beta);
                probs[j] = numerator / denom;
            }
        }

    }

    private int selectNextTown(Ant ant) {
        // sometimes just randomly select
//        if (rand.nextDouble() < pr) {
//            int t = rand.nextInt(n - currentIndex); // random town
//            int j = -1;
//            for (int i = 0; i < n; i++) {
//                if (!ant.visited(i)) {
//                    j++;
//                }
//                if (j == t) {
//                    return i;
//                }
//            }
//
//        }
        // calculate probabilities for each town (stored in probs)
        probTo(ant);
        // randomly select according to probs
        double r = rand.nextDouble();
        double tot = 0;
        for (int i = 0; i < n; i++) {
            tot += probs[i];
            if (tot >= r) {
                return i;
            }
        }

        throw new RuntimeException("Not supposed to get here.");
    }

    private void setupAnts() {
        currentIndex = -1;
        ArrayList<Integer> townList = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            if (townList.isEmpty()) {
                townList.add(rand.nextInt(n));
            } else {
                boolean status = true;
                int temp = -1;
                while (status == true) {
                    status = false;
                    temp = rand.nextInt(n);
                    for (int j = 0; j < townList.size(); j++) {
                        if (townList.get(j) == temp) {
                            status = true;
                            break;
                        }
                    }
                }
                townList.add(temp);
            }
            ants[i].clear(); // faster than fresh allocations.
            ants[i].visitTown(townList.get(townList.size() - 1));
            System.out.println("Semut ke " + (i + 1) + " ada di " + ants[i].tour[0]);
        }
        currentIndex++;

    }

    private void moveAnts() {
        // each ant follows trails...        
        while (currentIndex < n - 1) {
            for (Ant a : ants) {
                a.visitTown(selectNextTown(a));
            }
            currentIndex++;
        }
    }

    private void updateTrails() {
//        // evaporation
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                trails[i][j] *= evaporation;
//            }
//        }
//
//        // each ants contribution
//        for (Ant a : ants) {
//            double contribution = Q / a.tourLength();
//            for (int i = 0; i < n - 1; i++) {
//                trails[a.tour[i]][a.tour[i + 1]] += contribution;
//            }
//            trails[a.tour[n - 1]][a.tour[0]] += contribution;
//        }
        double deltaTrails = 0;
        for (Ant a : ants) {
            deltaTrails += Q / a.tourLength();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trails[i][j] = (evaporation * trails[i][j]) + deltaTrails;
            }
        }
    }

    private void updateBest() {
        if (bestTour == null) {
            bestTour = ants[0].tour;
            bestTourLength = ants[0].tourLength();
        }
        for (Ant a : ants) {
            if (a.tourLength() < bestTourLength) {
                bestTourLength = a.tourLength();
                bestTour = a.tour.clone();
            }
        }
    }

    public static String tourToString(int tour[]) {
        String t = new String();
        for (int i : tour) {
            t = t + " " + i;
        }
        return t;
    }

    public void runThis() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trails[i][j] = c;
            }
        }
        int iteration = 0;
        while (iteration < maxIterations) {
            System.out.println("Iterasi ke " + (iteration + 1));
            setupAnts();
            moveAnts();
            updateTrails();
            updateBest();
            System.out.println("");
            for (int i = 0; i < m; i++) {
                System.out.print("Semut ke " + (i + 1) + " :\t");
                for (int j = 0; j < n; j++) {
                    System.out.print(ants[i].tour[j] + "\t");
                }
                System.out.println("");
            }
            System.out.println("Best tour length: " + (bestTourLength - n));
            System.out.println("Best tour:" + tourToString(bestTour));
            iteration++;

        }
        
    }

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello World");
        double graph[][] = {
            {0.0, 3.0, 5.0, 7.0, 10.0, 13.0, 8.0},
            {3.0, 0.0, 13.0, 10.0, 7.0, 5.0, 3.0},
            {5.0, 3.0, 0.0, 5.0, 13.0, 4.0, 5.0},
            {7.0, 6.0, 1.0, 0.0, 4.0, 20.0, 8.0},
            {10.0, 9.0, 2.0, 5.0, 0.0, 5.0, 7.0},
            {13.0, 1.0, 4.0, 2.0, 7.0, 0.0, 1.0}
        };

        ACS acs = new ACS(1.0, 1, 5, 0.5, 500, 200);
        acs.setGraph(graph);
        acs.runThis();
        
//        int tour[] = new int[5];
        
//        for (int i = 0; i < tour.length; i++) {
//            System.out.println(tour[i]);
//        }
    }

    public int[] getBestTour() {
        return bestTour;
    }
}

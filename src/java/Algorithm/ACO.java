/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithm;

import Other.GoogleService;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author RIZQY FAHMI
 */
public class ACO {

    private double c;
    private double alpha;
    private double beta;
    private double evaporation;
    private double Q;
    private int maxIterations; //NC MAX
    public int n = 0; // # towns
    public int m = 0; // # ants
    private double graph[][];
    private int duration[][];
    private double trails[][];
    private Ant ants[];
    private Random rand = new Random();
    private double probs[];
    private int currentIndex;//S

    public int[] bestTour;
    public double bestTourLength;
    public double bestTourIter[];

    public int bestDurationLength;
    public int bestDurationIter[];

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
//            System.out.println("Best Distance : ");            
            double length = graph[tour[n - 1]][tour[0]];
            for (int i = 0; i < n - 1; i++) {
                length += graph[tour[i]][tour[i + 1]];
//                System.out.print(graph[tour[i]][tour[i + 1]]+"\t");
            }
//            System.out.println(graph[tour[n - 1]][tour[0]]+"\t");            
            return length;
        }

        public int durationLength() {
//            System.out.println("Best Duration : ");            
            int length = duration[tour[n - 1]][tour[0]];
            for (int i = 0; i < n - 1; i++) {
                length += duration[tour[i]][tour[i + 1]];
//                System.out.print(duration[tour[i]][tour[i + 1]]+"\t");
            }
//            System.out.println(duration[tour[n - 1]][tour[0]]+"\t");            
            return length;
        }

        public void clear() {
            for (int i = 0; i < n; i++) {
                visited[i] = false;
            }
        }
    }

    public ACO(double c, double alpha, double beta, double evaporation, double Q, int maxIterations) {
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
        bestTourIter = new double[maxIterations];
        bestDurationIter = new int[maxIterations];
        for (int j = 0; j < m; j++) {
            ants[j] = new Ant();
        }

    }

    public void setDuration(int[][] duration) {
        this.duration = duration;
    }

    private void probTo(Ant ant) {
        int i = ant.tour[currentIndex];

        double denom = 0.0;
        for (int l = 0; l < n; l++) {
//            System.out.println(ant.tour[l]+" "+ant.visited(l));
            if (!ant.visited(l)) {
                System.out.print(trails[i][l] + " ^ " + alpha + " * " + "(1.0 / " + graph[i][l] + ") ^ " + beta);
                double t = Math.pow(trails[i][l], alpha) * Math.pow(1.0 / graph[i][l], beta);
                if (!Double.isInfinite(t) && !Double.isNaN(t)) {
                    denom += t;
                }
                System.out.println(" = " + denom);
            }
        }

        for (int j = 0; j < n; j++) {
            if (ant.visited(j)) {
                probs[j] = 0.0;
            } else {
                double numerator = Math.pow(trails[i][j], alpha) * Math.pow(1.0 / graph[i][j], beta);
                probs[j] = numerator / denom;
                System.out.println(numerator + " / " + denom + " = " + probs[j]);
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
        System.out.println("Random : " + r);
        double tot2 = 0;
        boolean pilih = false;
        for (int i = 0; i < n; i++) {
            tot2 += probs[i];
            System.out.print(i + "\t" + probs[i] + "\t");
            if (tot2 >= r && pilih == false) {
                System.out.print("(Terpilih)");
                pilih = true;
            }
            System.out.println("");
        }

        double tot = 0;
        for (int i = 0; i < n; i++) {
            tot += probs[i];
            if (tot >= r) {
                return i;

            }
        }
        throw new RuntimeException("Not supposed to get here.");
//        double tot = 0;
//        int top = 0;
//        for (int i = 0; i < n; i++) {            
//            if (probs[i] > tot) {                
//                tot = probs[i];
//                top = i;
//            }
//        }
//        return top;
    }

    private void setupAnts() {
        currentIndex = 0;
        ArrayList<Integer> node = new ArrayList<Integer>();
        node.add(1);
        for (int i = 1; i < n; i++) {
            node.add(i);
        }
        Collections.shuffle(node);
        for (int i = 0; i < node.size(); i++) {
            ants[i].visitTown(node.get(i));
            System.out.println("Semut ke " + (i + 1) + " ada di " + ants[i].tour[1]);
        }
//        for (int i = 0; i < m; i++) {
//            int r = 0;
//            do {
//                r = rand.nextInt(n);
//            } while (r == 0);
////            ants[i].clear(); // faster than fresh allocations.
//            ants[i].visitTown(r);
////            System.out.println("Semut ke " + (i + 1) + " ada di " + ants[i].tour[1]);
//        }        
        currentIndex++;
        System.out.println(">>clear setup<<");

    }

    private void intAnts() {
        currentIndex = -1;
        for (int i = 0; i < m; i++) {
            ants[i].clear(); // faster than fresh allocations.
            ants[i].visitTown(0);//set kantor operasional telkom
//            System.out.println("Semut ke " + (i + 1) + " ada di " + ants[i].tour[0]);
        }
        currentIndex++;

    }

    private void moveAnts() {
        // each ant follows trails...        
        while (currentIndex < n - 1) {
//            for (Ant a : ants) {
//                System.out.println("Semut");
//                a.visitTown(selectNextTown(a));
//            }
            for (int i = 0; i < ants.length; i++) {
                System.out.print("Semut " + (i + 1) + " ");
                int s = selectNextTown(ants[i]);
                System.out.println("pindah ke " + s + "\t");
                ants[i].visitTown(s);
            }
            System.out.println("");
            currentIndex++;
        }
    }

    private void updateTrails() {

        // evaporation
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                trails[i][j] *= evaporation;
            }
        }
        System.out.println("Contribution");
        double contribution = 0;
        for (Ant a : ants) {
            contribution += Q / a.tourLength();
            System.out.println(Q+"/"+a.tourLength()+"\t = "+(Q / a.tourLength()));
        }
        System.out.println("Total : " + contribution);

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                trails[i][j] += contribution;
//            }
//        }
//        
        for (Ant a : ants) {
//            double contribution = Q / a.tourLength();
            for (int i = 0; i < n - 1; i++) {
                System.out.print("Titik "+a.tour[i]+" "+a.tour[i + 1]+" : ");
                System.out.print("Before "+trails[a.tour[i]][a.tour[i + 1]]+"\t");
                trails[a.tour[i]][a.tour[i + 1]] += contribution;
                System.out.print("After "+trails[a.tour[i]][a.tour[i + 1]]+"\t");
            }
            System.out.println("Titik "+a.tour[n - 1]+" "+a.tour[0]);
            trails[a.tour[n - 1]][a.tour[0]] += contribution;
        }

//        for (Ant a : ants) {              
////            double contribution = Q / a.tourLength();
//            for (int i = 0; i < n - 1; i++) {
//                System.out.println("Titik "+a.tour[i]+" "+a.tour[i + 1]);
//                System.out.println("Before "+trails[a.tour[i]][a.tour[i + 1]]);
//                trails[a.tour[i]][a.tour[i + 1]] += (trails[a.tour[i]][a.tour[i + 1]]*evaporation)+contribution;
//                System.out.println("After "+trails[a.tour[i]][a.tour[i + 1]]);
//            }
//            System.out.println("Titik "+a.tour[n - 1]+" "+a.tour[0]);
//            trails[a.tour[n - 1]][a.tour[0]] += (trails[a.tour[n - 1]][a.tour[0]]*evaporation)+contribution;
//        }
//         each ants contribution
//        for (Ant a : ants) {            
//            double contribution = Q / a.tourLength();
//            for (int i = 0; i < n - 1; i++) {
//                System.out.println("Before "+trails[a.tour[i]][a.tour[i + 1]]);
//                trails[a.tour[i]][a.tour[i + 1]] += contribution;
//                System.out.println("After "+trails[a.tour[i]][a.tour[i + 1]]);
//            }
//            trails[a.tour[n - 1]][a.tour[0]] += contribution;
//        }
    }

    private void updateBest() {
        if (bestTour == null) {
            bestTour = ants[0].tour;
            bestTourLength = ants[0].tourLength();
            bestDurationLength = ants[0].durationLength();
        }
        for (Ant a : ants) {
            if (a.tourLength() < bestTourLength) {
//                System.out.println("Begin Best");                
                bestTourLength = doubleFormater(a.tourLength());
                bestDurationLength = a.durationLength();
                bestTour = a.tour.clone();
//                System.out.println("End of Best");
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
        System.out.println("Distance");
        displayInit(graph);
        System.out.println("Duration");
        displayDuration(duration);
        displayInit(trails);
        int iteration = 0;
        while (iteration < maxIterations) {
            System.out.println("-Iterasi ke " + (iteration + 1) + "-");
            intAnts();
            System.out.println("");
            setupAnts();
            System.out.println("");
            moveAnts();
            System.out.println("");
            updateTrails();
            System.out.println("");
            displayInit(trails);
            System.out.println("");
            updateBest();
            System.out.println("");
            bestTourIter[iteration] = doubleFormater(bestTourLength);
            bestDurationIter[iteration] = bestDurationLength;
//            System.out.println("Best tour length: " + doubleFormater(bestTourLength));
//            System.out.println("Best tour length: " + doubleFormater(bestTourLength - n));
//            System.out.println("Best tour :" + tourToString(bestTour));
            GoogleService gs = new GoogleService();
            System.out.println("Best tour :" + tourToString(bestTour));
            System.out.println("Best Distance :" + displayBestDistance(graph, bestTour));
            int sekon = displayBestDuration(duration, bestTour);
            System.out.println("Best Duration :" + sekon +" -> "+gs.toHour(sekon));
            System.out.println("-End Iterasi ke :" + (iteration + 1) + "-");
            iteration++;

        }
        
    }

    public void displayInit(double x[][]) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(x[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public void displayDuration(int x[][]) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(x[i][j] + "\t");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public double displayBestDistance(double graph[][], int tour[]) {
        System.out.println("Best Distance : ");
        double length = graph[tour[n - 1]][tour[0]];
        for (int i = 0; i < n - 1; i++) {
            length += graph[tour[i]][tour[i + 1]];
            System.out.print(graph[tour[i]][tour[i + 1]] + "\t");
        }
        System.out.println(graph[tour[n - 1]][tour[0]] + "\t");
        return length;
    }
    
    public int displayBestDuration(int graph[][], int tour[]) {
        System.out.println("Best Duration : ");
        int length = graph[tour[n - 1]][tour[0]];
        for (int i = 0; i < n - 1; i++) {
            length += graph[tour[i]][tour[i + 1]];
            System.out.print(graph[tour[i]][tour[i + 1]] + "\t");
        }
        System.out.println(graph[tour[n - 1]][tour[0]] + "\t");
        return length;
    }

    public int[] getBestTour() {
        return bestTour;
    }

    public double[] getBestTourIter() {
        return bestTourIter;
    }

    public double getBestTourLength() {
        return bestTourLength;
    }

    public int getBestDurationLength() {
        return bestDurationLength;
    }

    public int[] getBestDurationIter() {
        return bestDurationIter;
    }

    public double doubleFormater(double x) {
        NumberFormat formatter = new DecimalFormat("#.##");
        double temp = Double.parseDouble(String.valueOf(formatter.format(x)).replaceAll(",", "."));
        return temp;
    }
}

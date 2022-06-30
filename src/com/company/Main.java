package com.company;

import java.io.File;
import java.util.*;

public final class Main {
    double printingDistance;
    double lastDistance;
    int numberOfIterations;
    int iter = 1;

    public static void main(String[] args) {
        new Main().begin();
    }

    public void begin() {
        Reader reader = new Reader(new File("iris.data.txt")); //read data from file
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter k");
        int k = sc.nextInt();
        System.out.println("Enter iterations");
        Map<String, List<Double>> centroids = new HashMap<>();
        List<Double> xs;
        System.out.println("Starting centroids are");
        for (int i = 0; i < k; i++) {
            System.out.print(1 + i + ")");
            xs = reader.listOfCoordinates.get(new Random().nextInt(105));   //creating k first centroids (just take k first vectors)
            System.out.println(xs);
            centroids.put(String.valueOf(i), xs);
        }

        Map<List<Double>, Integer> clusters = calculateClusters(reader.listOfCoordinates, centroids);//calculate clusters

        List<Double> newCentroidCoords;
        while (lastDistance != printingDistance && printingDistance != 0) {
            numberOfIterations++;
            for (int j = 0; j < centroids.size(); j++) {
                List<List<Double>> list = new ArrayList<>();
                for (var key : clusters.keySet()) {
                    if (clusters.get(key) == j) {
                        list.add(key);
                    }
                }
                if (list.size() != 0) {
                    newCentroidCoords = calculateCentroids(list);   //calculate new centroids basing on the old ones

                    centroids.put(String.valueOf(j), newCentroidCoords);
                }
            }

            clusters.clear();
            clusters = calculateClusters(reader.listOfCoordinates, centroids);
        }
        System.out.println("Enter \n1 to repeat\n2 to exit");
        int fromUser = sc.nextInt();
        switch (fromUser) {
            case 1 -> begin();
            case 2 -> System.exit(0);
        }
    }

    public List<Double> calculateCentroids(List<List<Double>> a) {
        int count;
        double sum;
        if (a.size() == 0) System.out.println("LIST IS EMPTY IN ITER " + iter++);

        List<Double> centroids = new ArrayList<>(Reader.cap);
        for (int i = 0; i < Reader.cap; i++) {
            sum = 0.0;
            count = 0;
            for (var x : a) {  //add every vector from a list and divide it by quantity, then fill new list of centroids
                count++;
                sum += x.get(i);
            }

            centroids.add(sum / count);
            //System.out.println(centroids);
        }

        return centroids;
    }

    public Map<List<Double>, Integer> calculateClusters(List<List<Double>> irisCoords, Map<String, List<Double>> centroids) {
        Map<List<Double>, Integer> clusters = new HashMap<>();
        lastDistance = printingDistance;
        printingDistance = 0;
        int arrIn = 0;
        double distance;
        for (List<Double> irisCoord : irisCoords) {
            double minimum = Distance.euclideanDistance(centroids.get("0"), irisCoord);
            for (int j = 0; j < centroids.size(); j++) {
                distance = Distance.euclideanDistance(centroids.get(String.valueOf(j)), irisCoord); // find distance from every vector to every centroid
                if (distance <= minimum) {
                    minimum = distance;
                    arrIn = j;
                }
            }
            printingDistance += minimum;
            clusters.put(irisCoord, arrIn);
        }

        System.out.println("Sum of distance is " + printingDistance);
        return clusters;
    }
}

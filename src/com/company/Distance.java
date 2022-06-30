package com.company;

import java.util.List;

public interface Distance {
    int[] i = {0};
    static double euclideanDistance(List<Double> coords, List<Double> centroidCoords) {
        double sum = 0.0;
        for(int i = 0; i < coords.size(); i++)
            sum += ((coords.get(i) - centroidCoords.get(i)) * (coords.get(i) - centroidCoords.get(i)));
        //if(i[0]==0) System.out.println(Math.sqrt(sum));
        i[0]++;
        return Math.sqrt(sum);
    }
}

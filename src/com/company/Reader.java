package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public final class Reader {
    List<List<Double>> listOfCoordinates = new ArrayList<>();
    List<String> names = new ArrayList<>();
    public static int cap;

    public Reader(File file) {
        readFromFile(file);
    }

    public void readFromFile(File file){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream((file))));
            String line;
            while ((line = br.readLine()) != null)
                readFromLine(line);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFromLine(String line) {
        String[] ln = line.replaceAll("\"", "").split(",");
        List<Double> coords = new ArrayList<>(ln.length-1);
        for (int i = 0; i < ln.length - 1; i++)
            coords.add(Double.parseDouble(ln[i]));
        listOfCoordinates.add(coords);
        names.add(String.valueOf(ln[ln.length-1]));
        cap = ln.length-1;
    }
}

package com.anleu.dsptoolkit.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EquidistantDoubleSignal {

    private String name;
    private String unit;
    private String XUnit;
    private double samplingRate; // samples per second

    // it would be better to use a double array instead of an ArrayList but this is easier to use for now
    private List<Double> yValues = new ArrayList<>(10000);

    public EquidistantDoubleSignal(String name, String unit, double samplingRate) {
        this.name = name;
        this.unit = unit;
        this.samplingRate = samplingRate;
        this.XUnit = "s";
    }

    public EquidistantDoubleSignal(double[] yValues, String unit, double samplingRate) {
        Arrays.stream(yValues).forEach(y -> this.yValues.add(y));
        this.unit = unit;
        this.samplingRate = samplingRate;
        this.XUnit = "s";
    }

    public void addValue(double yValue) {
        yValues.add(yValue);
    }

    public void setValueAt(int index, double yValue) {
        int indexGap = index - yValues.size();
        if (indexGap >= 0) {
            for (int i = 0; i < indexGap; i++) {
                yValues.add(null);
            }
            yValues.add(yValue);
        } else {
            yValues.set(index, yValue);
        }
    }

    public double getValueForIndex(int index) {
        if (index < 0 || index >= yValues.size()) {
            return Double.NaN;
        }
        Double value = yValues.get(index);
        return value == null ? Double.NaN : value;
    }

    public int getLength() {
        return yValues.size();
    }

    public String getName() {
        return name;
    }

    public double[] getYValues() {
        return yValues.stream().mapToDouble(entry -> entry != null ? entry : Double.NaN).toArray();
    }

    public double getSamplingRate() {
        return samplingRate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXUnit() {
        return XUnit;
    }

    public String getUnit() {
        return unit;
    }
}

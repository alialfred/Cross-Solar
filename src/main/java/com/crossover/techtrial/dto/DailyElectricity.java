package com.crossover.techtrial.dto;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DailyElectricity class will hold sum, average,minimum and maximum electricity
 * for a given day.
 *
 * @author Crossover
 *
 */
public class DailyElectricity implements Serializable {

    private static final long serialVersionUID = 3605549122072628877L;

    private LocalDate date;

    private Double sum;

    private Double average;

    private Double min;

    private Double max;

    public DailyElectricity() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return "DailyElectricity [date=" + date + ", sum=" + sum + ", average="
                + average + ", min=" + min + ", max=" + max + "]";
    }

}

package com.solarsmart.frontoffice.models.dto.response;


import lombok.Data;

@Data
public class CustomerStatistic {

    private long count;
    private long countLast;
    private double percentageToLast;

    public CustomerStatistic(long customerCurrentMonth, long customerLastMonth) {
        this.count = customerCurrentMonth;
        this.countLast = customerLastMonth;
        this.setPercentageToLast();
    }

    public void setPercentageToLast() {
        if (this.countLast == 0 ){
            this.percentageToLast = 100;
            return;
        }
        long difference = this.count - this.countLast;
//        this.percentageToLast = (double) (this.count * 100) /this.countLast;
        this.percentageToLast = (double) (difference/this.countLast) * 100;
    }
}

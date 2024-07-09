import { Injectable } from '@angular/core';
import {ChartData, ChartDataset} from "chart.js";
import {ChartOptions} from "chart.js/auto";

@Injectable({
  providedIn: 'root'
})
export class ChartService {

  constructor() { }

  public notShowingLabelOption() : ChartOptions{
    return {
      datasets: {
        line: {
          fill: false,
        }
      },
      scales: {
        x: {
          ticks: {
            callback: function(value, index, ticks) {
              return '';
            }
          }
        }
      },
    }
  }

  public createDataSet(legend: string = '',data: any[]): ChartDataset{
    return {
      data: data,
      label: legend,

      backgroundColor: 'rgba(53,156,236,0.51)',
      borderColor: 'rgba(53,156,236,0.51)',
      type: "line",
      borderWidth: Number.MIN_VALUE,
      fill: "origin",
      tension: 0.5
    }
  }

  public createLineChartData(legend: string = '',data: any[], labels: unknown[]): ChartData{

    // this.chartData = {
    //   labels: this.data.map(d => d.date.toLocaleTimeString()),
    //   datasets: [
    //     {
    //       data: this.data.map(d => d.value),
    //       label: this.domain,
    //
    //       backgroundColor: 'rgba(53,156,236,0.51)',
    //       borderColor: 'rgba(53,156,236,0.51)',
    //       type: "line",
    //       borderWidth: Number.MIN_VALUE,
    //       fill: "origin",
    //       tension: 0.5
    //     },
    //   ]
    // }
    return {
      labels: labels,
      datasets: [
        {
          data: data,
          label: legend,

          backgroundColor: 'rgba(53,156,236,0.51)',
          borderColor: 'rgba(53,156,236,0.51)',
          type: "line",
          borderWidth: Number.MIN_VALUE,
          fill: "origin",
          tension: 0.5
        },
      ],
    }
  }
}

import {Component, DoCheck, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ChartConfiguration, ChartOptions} from "chart.js/auto";
import {IDateStat} from "../../../models/core/ichart";

@Component({
  selector: 'app-date-statistic',
  templateUrl: './date-statistic.component.html',
  styleUrls: ['./date-statistic.component.css']
})
export class DateStatisticComponent implements OnInit, OnChanges{
  @Input({required: true}) data!: IDateStat
  chartData!: ChartConfiguration['data'];
  @Input() options?: ChartOptions

  clicked ?: {data: number, label: string}

  ngOnInit(): void {
    this.initChart()
  }
  chartClicked(event: { data: any; index: number }){

    this.clicked = {
      data: event.data,
      label: this.getLabel(event.index)
    }
  }

  initChartOptions(){
    if (!this.options){
      this.options = {
        responsive: true,
        elements: {
          line: {tension: 0.5},
        }
      }
    }
  }

  initChart(){
    this.chartData = {
      labels: this.data.data.map(d => d.tag),
      datasets: [
        {
          data: this.data.data.map(data => data.data),
          label: this.data.chartLabel,
          backgroundColor: 'rgba(53,156,236,0.51)',
          borderColor: 'rgba(53,156,236,0.51)',
          type: "bar",
          borderWidth: Number.MIN_VALUE,
          borderRadius: Number.MAX_VALUE
          // fill: "origin"
        },
      ]
    }

    this.initChartOptions();
  }

  getLabel(index: number){
    return this.data.data[index].label;
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['data'].currentValue !== changes['data'].previousValue){
      this.initChart()
    }
  }

}

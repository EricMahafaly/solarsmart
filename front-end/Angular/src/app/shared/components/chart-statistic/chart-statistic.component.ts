import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ChartConfiguration, ChartEvent} from "chart.js/auto";

@Component({
  selector: 'app-chart-statistic',
  templateUrl: './chart-statistic.component.html',
  styleUrls: ['./chart-statistic.component.css']
})
export class ChartStatisticComponent implements OnInit{
  @Input({required: true}) data !: ChartConfiguration['data'];
  @Input() options?: ChartConfiguration['options']

  @Output() clicked: EventEmitter<{data: any, index: number}> = new EventEmitter<{data: any; index: number}>()

  public chartClicked(event: {event?: ChartEvent, active?: any[]}): void {
    if (event.active?.length) {
      const clickedIndex = event.active[0].index;
      const datasetIndex = event.active[0].datasetIndex;


      const value = this.data.datasets[datasetIndex].data[clickedIndex];

      this.clicked.emit({
        data: value ,
        index: clickedIndex as number
      })
    }
  }

  ngOnInit(): void {

    // console.log("options = ", this.options)
  }
}

import {Component, Input, OnInit} from '@angular/core';
import {IDateStat} from "../../../models/core/ichart";
import {IMonthlyData} from "../../../models/core/date/idatedata";
import {fakeMonthlyData} from "../../../data/fakedata";

@Component({
  selector: 'app-monthly-statistic',
  templateUrl: './monthly-statistic.component.html',
  styleUrls: ['./monthly-statistic.component.css']
})
export class MonthlyStatisticComponent implements OnInit{
  @Input() data !: IMonthlyData[]
  @Input() dataType: string = 'Consommation';
  @Input() unit: string ='W'

  dateData !: IDateStat

  ngOnInit(): void {
    this.initDateData();
  }

  isLoaded(){
    return this.data != null;
  }

  initDateData(){
    this.dateData = {
      data: this.data,
      unit: this.unit,
      domain: this.dataType,
      chartLabel: new Date().getFullYear().toString()
    }
  }


}

import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {IWeeklyData} from "../../../models/core/date/idatedata";
import {IDateStat} from "../../../models/core/ichart";

@Component({
  selector: 'app-weekly-statistic',
  templateUrl: './weekly-statistic.component.html',
  styleUrls: ['./weekly-statistic.component.css']
})
export class WeeklyStatisticComponent implements OnInit, AfterViewInit{
  @Input({required: true}) data!: IWeeklyData[]
  @Input() dataType: string = 'Consommation';
  @Input() unit: string ='W'
  weeklyActive !: IWeeklyData;

  testData!: IDateStat


  ngOnInit(): void {
    if (this.data){
      this.weeklyActive = this.getWeeklyDataNow();
      this.refreshTestData();
    }
  }

  ngAfterViewInit() {

  }

  refreshTestData(){
    this.testData = {
      data: this.weeklyActive.days,
      unit: this.unit,
      domain: this.dataType,
      chartLabel: this.weeklyActive.label
    }
  }

  activeOption(weeklyData: IWeeklyData){
    this.weeklyActive = weeklyData;
    this.testData.chartLabel = this.weeklyActive.label
    this.testData.data = this.weeklyActive.days

    this.testData = Object.assign({}, this.testData)


  }

  getWeeklyDataNow(): IWeeklyData{
    // let now: Date = new Date();
    //
    // return <IWeeklyData>this.data.find(wd => {
    //   return wd.start_date <= now && wd.end_date >= now;
    // });

    let now: Date = new Date();
    // Obtenir la date actuelle sans l'heure
    let currentDate: Date = new Date(now.getFullYear(), now.getMonth(), now.getDate());

    return <IWeeklyData>this.data.find(wd => {
      // Comparer les dates sans l'heure
      let startDate: Date = new Date(wd.start_date.getFullYear(), wd.start_date.getMonth(), wd.start_date.getDate());
      let endDate: Date = new Date(wd.end_date.getFullYear(), wd.end_date.getMonth(), wd.end_date.getDate());

      return startDate <= currentDate && endDate >= currentDate;
    });
  }

  isActive(weeklyActive: IWeeklyData){
    return this.weeklyActive == weeklyActive;
  }
}

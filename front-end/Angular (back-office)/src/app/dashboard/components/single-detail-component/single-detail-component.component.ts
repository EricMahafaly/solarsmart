import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {DateFilter} from "../../../models/core/date/date-filter";
import {IComposantDetailModel} from "../../../models/api/composant.model";
import {DateRangeModel, ToDateRange} from "../../../models/core/date/date-range.model";
import {ISelectOption} from "../../../models/core/iselect";
import {ChartConfiguration, ChartOptions} from "chart.js/auto";
import {ChartService} from "../../../shared/services/chart/chart.service";
import {getActiveOption, getPlagesDatesOptions} from "../../../config/option-data";

@Component({
  selector: 'app-single-detail-component',
  templateUrl: './single-detail-component.component.html',
  styleUrls: ['./single-detail-component.component.scss']
})
export class SingleDetailComponentComponent implements OnInit, OnChanges {

  @Input({required: true}) domain !: string
  @Input({required: true}) unit !: string
  @Input() data!: IComposantDetailModel[]
  @Input() selected ?: {label: string, data: any}

  @Output() datesRange : EventEmitter<DateRangeModel> = new EventEmitter<DateRangeModel>();

  chartOption ?: ChartOptions
  chartData !: ChartConfiguration['data']

  protected filterShow: boolean = false;
  protected filterDates !: ISelectOption[];
  protected datesActive !: DateRangeModel;

  constructor(private chartService: ChartService) {
  }

  ngOnInit(): void {

    console.log("data = ", this.data)

    this.initChart();
    this.initFilterDates();
    this.setDatesActive()

  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['data'] && changes['data'].currentValue){
      this.initChart();
    }
  }



  onChangePage(index: number){

    const size = this.filterDates.length
    if (index < 0) index = size - 1
    if (index >= size) index = 0

    this.filterDates.forEach(option => {
      option.selected = false;
    })

    this.filterDates[index].selected = true;

    this.setDatesActive(this.filterDates[index])
  }

  toggleFilterOption(){
    this.filterShow = !this.filterShow;
  }

  setFilterState(state: boolean){
    this.filterShow = state;
  }

  setDatesActive(option?: ISelectOption){

    if (!option){
      this.datesActive = ToDateRange(getActiveOption(this.filterDates).value as Date[])
      return;
    }

    this.datesActive = ToDateRange(option.value as Date[]);

    this.datesRange.emit(this.datesActive)

  }

  getCurrentPage(){
    const size = this.filterDates.length;
    let index = 0;
    for (let i = 0; i < size; i++) {
      if (this.filterDates[i].selected){
        index = i;
        break;
      }
    }

    return index;
  }

  chartClicked(event: {data: any; index: number}){
    this.selected = {
      label: this.getLabel(event.index),
      data: event.data
    }
  }

  setDateValue(dates: DateRangeModel){
    this.datesActive = dates;
    this.datesRange.emit(dates)
  }

  getLabel(index: number): string{
    let dateVal: Date = this.data[index].date;
    return dateVal.toDateString() + ' ' + dateVal.toLocaleTimeString();
  }

  private initChart(){
    this.chartData = this.chartService
      .createLineChartData(this.domain, this.data.map(d => d.value),
        this.data.map(d => d.date.toLocaleTimeString()))

    this.initChartOptions()
  }

  private initChartOptions(){
    if (!this.chartOption){
      this.chartOption = this.chartService.notShowingLabelOption();
    }
  }

  getDates(): DateRangeModel{
    return this.datesActive
  }


  initFilterDates(){
    this.filterDates = getPlagesDatesOptions();
  }

  protected readonly DateFilter = DateFilter;
}

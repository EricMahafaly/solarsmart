import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {ChartEvent, ChartOptions} from "chart.js/auto";
import {ChartData} from "chart.js";
import {ComponentDetailDataModel} from "../../../models/core/component-detail-data.model";
import {PointChartInfoModel} from "../../../models/core/point-chart-info.model";
import {ISelectOption} from "../../../models/core/iselect";
import {ChartService} from "../../../shared/services/chart/chart.service";
import {getActiveOption, getPlagesDatesOptions} from "../../../config/option-data";
import {DateFilter} from "../../../models/core/date/date-filter";
import {DateRangeModel, ToDateRange} from "../../../models/core/date/date-range.model";

@Component({
  selector: 'app-component-detail',
  templateUrl: './component-detail.component.html',
  styleUrls: ['./component-detail.component.scss']
})
export class ComponentDetailComponent implements OnInit, OnChanges {
  protected options!: ChartOptions;
  protected chartData!: ChartData;

  protected readonly DateFilter = DateFilter;

  protected plagesDates !: ISelectOption[]
  protected plagesDatesIsShow: boolean = false
  protected datesActive !: DateRangeModel;

  @Input({required: true}) data!: ComponentDetailDataModel[];
  @Input() value?: PointChartInfoModel

  @Output() search: EventEmitter<Date> = new EventEmitter<Date>();
  @Output() datesRange: EventEmitter<DateRangeModel> = new EventEmitter<DateRangeModel>();

  constructor(private chartService: ChartService) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['data'] && changes['data'].currentValue){
      this.initChartData();
    }
  }

  ngOnInit(): void {
    this.initChartData();
    this.initFilterDateOption();
    this.setDatesActive()

  }

  toggleDateOptions(){
    this.plagesDatesIsShow = !this.plagesDatesIsShow;
  }

  setPlagesDateShow(show: boolean){
    this.plagesDatesIsShow = show;
  }

  onClicked(event: { event?: ChartEvent; active?: any[] }) {
    if (event.active?.length) {
      const clickedIndex = event.active[0].index;

      const componentDetailDataModel: ComponentDetailDataModel = this.data[clickedIndex];

      this.value = {
        domain: componentDetailDataModel.date.toLocaleTimeString(),
        index: clickedIndex,
        info: [{
          data: componentDetailDataModel.tension,
          label: 'Tension',
          unit: 'V'
        },{
          data: componentDetailDataModel.courant,
          label: 'Courant',
          unit: 'A'
        },{
          data: componentDetailDataModel.puissance,
          label: 'Puissance',
          unit: 'Kw'
        }]
      };
    }
  }

  initFilterDateOption(){
    this.plagesDates = getPlagesDatesOptions();
  }

  initChartData(){
    this.chartData = {
      labels: this.data.map(val => val.date),
      datasets: [
        {
          data: this.data.map(val => val.tension),
          label: 'Tension',
          type: "line",
        },
        {
          data: this.data.map(val => val.courant),
          label: 'Courant',
          type: "line",
        },
        {
          data: this.data.map(val => val.puissance),
          label: 'Puissance',
          type: "line",
        }]
    }

    this.options = this.chartService.notShowingLabelOption();
  }

  setDatesActive(option?: ISelectOption){

    if (!option){
      this.datesActive = ToDateRange(getActiveOption(this.plagesDates).value as Date[])
      return;
    }

    this.datesActive = ToDateRange(option.value as Date[]);

    this.datesRange.emit(this.datesActive)

  }

  onChangePage(index: number){
    this.value = undefined;

    const size = this.plagesDates.length

    if (index < 0) index = size - 1
    else if (index >= size) index = 0

    this.plagesDates.forEach(option => {
      option.selected = false;
    })

    this.plagesDates[index].selected = true;

    this.setDatesActive(this.plagesDates[index])
  }

  getDates(): DateRangeModel{
    // const dates: Date[] = this.datesActive.value as Date[]
    return this.datesActive
  }

  getCurrentPage(){
    const size = this.plagesDates.length;
    let index = 0;
    for (let i = 0; i < size; i++) {
      if (this.plagesDates[i].selected){
        index = i;
        break;
      }
    }

    return index;
  }

  setDateValue(dateRange: DateRangeModel){
    this.datesActive = dateRange;
    this.datesRange.emit(this.datesActive);
    this.search.emit(dateRange.start_date);
  }

}

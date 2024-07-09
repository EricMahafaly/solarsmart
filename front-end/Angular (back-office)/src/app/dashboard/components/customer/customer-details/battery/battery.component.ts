import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ISelectOption} from "../../../../../models/core/iselect";
import {IMonthlyData, IWeeklyData} from "../../../../../models/core/date/idatedata";
import {FilterDate} from "../../../../../models/core/date/filter-date";
import {ApiDataModel} from "../../../../../models/api/base/api-data.model";
import {IComposantDetailModel} from "../../../../../models/api/composant.model";
import {BatteryInfoModel} from "../../../../../models/api/info.model";
import {basicFilterDateNow} from "../../../../../config/filter-date.data";
import {BatteryService} from "../../../../services/battery/battery.service";
import {DateRangeModel} from "../../../../../models/core/date/date-range.model";
import {DateHelper} from "../../../../../shared/helper/date-helper";
import {ComponentDetailDataModel} from "../../../../../models/core/component-detail-data.model";
import {ComposantDetailsParamModel} from "../../../../../models/api/composant-details-param.model";
import {PointChartInfoModel} from "../../../../../models/core/point-chart-info.model";
import {RelaisStateModel} from "../../../../../models/api/relais-state.model";
import {ToastService} from "../../../../../shared/services/toast/toast.service";
import {ApiResponse} from "../../../../../models/api/base/api.model";
import {WebSocketService} from "../../../../services/socket/web-socket.service";

@Component({
  selector: 'app-battery',
  templateUrl: './battery.component.html',
  styleUrls: ['./battery.component.scss']
})
export class BatteryComponent implements OnInit, OnDestroy{

  protected readonly FilterDate = FilterDate;
  private id!: number;

  filterOptionState: boolean = false;
  filterOptions!: ISelectOption[]
  optionActive!: ISelectOption;

  infoData : ApiDataModel<BatteryInfoModel> = new ApiDataModel<BatteryInfoModel>(null);
  relaisInfoData: ApiDataModel<RelaisStateModel> = new ApiDataModel<RelaisStateModel>(null);
  percentageData : ApiDataModel<number> = new ApiDataModel<number>(null);
  timeUsageMonthlyData : ApiDataModel<IMonthlyData[]> = new ApiDataModel<IMonthlyData[]>([]);
  timeUsageWeeklyData : ApiDataModel<IWeeklyData[]> = new ApiDataModel<IWeeklyData[]>([]);
  energyData : ApiDataModel<IComposantDetailModel[]> = new ApiDataModel<IComposantDetailModel[]>([]);

  detailData: ApiDataModel<ComponentDetailDataModel[]> = new ApiDataModel<ComponentDetailDataModel[]>([])
  dates: DateRangeModel = DateHelper.getDateRange(new Date());
  datesEnergy: DateRangeModel = DateHelper.getDateRange(new Date());

  searchDetailVal?: PointChartInfoModel

  timeOut: any;

  constructor(private route: ActivatedRoute,
              private batteryService: BatteryService,
              private toastService: ToastService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = parseInt(params['id'])

    })

    // this.setDetailData()

    this.setInfo()
    this.initFilterDateOptions();
    this.setTimeUsageMonthlyData();
    this.setPercentageData();
    this.setEnergyData()
    this.setDetailData()

    this.timeOut = setInterval(() => {

      this.setPercentageData();
      this.setEnergyData()
      this.setDetailData()
      this.setPercentageData();
    }, 50000)
  }

  ngOnDestroy() {
    console.log("destroy")
    clearInterval(this.timeOut)
  }


  toggleFilterOption(){
    this.filterOptionState = !this.filterOptionState;
  }

  setOptionActive(option: ISelectOption){
    this.optionActive = option;

    this.setTimeUsageMonthlyData()
    this.setTimeUsageWeeklyData()
    // if (!this.timeUsageMonthlyData.haveData()) this.setTimeUsageMonthlyData()
    // if (!this.timeUsageWeeklyData.haveData()) this.setTimeUsageWeeklyData()
  }

  initFilterDateOptions(){
    this.filterOptions = basicFilterDateNow
    this.optionActive = this.filterOptions[0];
  }

  createComposantParamModel(dateStart: Date, dateEnd: Date | undefined){
    return new ComposantDetailsParamModel(dateStart, dateEnd);
  }

  setPercentageData(){
    this.batteryService.getPercentage(this.id).subscribe(
        ((data: number) =>{
          this.percentageData.data = data;
          this.percentageData.isLoaded()
        })
    )
  }

  setTimeUsageWeeklyData(){
    let now = new Date()
    if (this.optionActive.key != FilterDate.WEEK_BY_MONTH) return;
    this.timeUsageWeeklyData.loading = true;
    this.batteryService
        .getTimeUsageWeekly(now.getFullYear(), now.getMonth()+1, this.id).subscribe(
            (data: IWeeklyData[]) => {

              this.timeUsageWeeklyData.data = data;
              this.timeUsageWeeklyData.isLoaded();
            })
  }

  setTimeUsageMonthlyData(){
    let now = new Date()
    if (this.optionActive.key != FilterDate.MONTH_BY_YEAR) return;
    this.timeUsageMonthlyData.loading = true;
    this.batteryService.getTimeUsageMonthly(now.getFullYear(), this.id)
        .subscribe(
            (data: IMonthlyData[]) => {
              this.timeUsageMonthlyData.data = data;
              this.timeUsageMonthlyData.isLoaded();
            })

  }

  setInfo(){
    this.batteryService.getInfo(this.id)
      .subscribe((data: BatteryInfoModel) => {
        this.infoData.data = data
        this.infoData.isLoaded()

        this.relaisInfoData.data = data.relais_state;
        this.relaisInfoData.isLoaded();
      })
  }

  setDetailData(dates?: DateRangeModel){
    if (dates) this.dates = dates;
    const params = this.createComposantParamModel(this.dates.start_date, this.dates.end_date);

    this.batteryService.getDataDetail(this.id, params)
      .subscribe((data: ComponentDetailDataModel[]) => {
        this.detailData.data = data;
        this.detailData.isLoaded();
      })
  }

  setEnergyData(dates?: DateRangeModel){

    if (dates) this.datesEnergy = dates;

    // console.log("dates = ", dates)
    const params = this.createComposantParamModel(this.datesEnergy.start_date, this.datesEnergy.end_date);

    this.batteryService.getEnergyData(this.id, params)
      .subscribe((data: IComposantDetailModel[]) => {
        // console.log("energy data = ", data)
        this.energyData.data = data;
        this.energyData.isLoaded();
      })
  }

  findDetail(date: Date){
    this.batteryService.findDataDetail(this.id, {date: DateHelper.toISOString(date)})
      .subscribe((data: ComponentDetailDataModel) => {

        this.searchDetailVal = {
          domain: data.date.toLocaleTimeString(),
          info: [{
            data: data.tension,
            label: 'Tension',
            unit: 'V'
          },{
            data: data.courant,
            label: 'Courant',
            unit: 'A'
          },{
            data: data.puissance,
            label: 'Puissance',
            unit: 'Kw'
          }]
        }
      })
  }

  switchRelais(){
    this.relaisInfoData.loading = true;
    this.batteryService.switchRelais(this.id)
      .subscribe((response: ApiResponse<RelaisStateModel>) => {
        this.relaisInfoData.data = response.data
        this.relaisInfoData.isLoaded();
        this.toastService.success(response.message, 2500)
      })
  }

  onExport() {
    this.batteryService.exportExcel();
  }
}

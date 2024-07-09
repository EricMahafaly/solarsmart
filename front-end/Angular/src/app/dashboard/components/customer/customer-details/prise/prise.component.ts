import {Component, OnDestroy, OnInit} from '@angular/core';
import {ISelectOption} from "../../../../../models/core/iselect";
import {ApiDataModel} from "../../../../../models/api/base/api-data.model";
import {ComponentDetailDataModel} from "../../../../../models/core/component-detail-data.model";
import {IMonthlyData, IWeeklyData} from "../../../../../models/core/date/idatedata";
import {FilterDate} from "../../../../../models/core/date/filter-date";
import {PriseService} from "../../../../services/prise/prise.service";
import {basicFilterDateNow} from "../../../../../config/filter-date.data";
import {DateRangeModel} from "../../../../../models/core/date/date-range.model";
import {DateHelper} from "../../../../../shared/helper/date-helper";
import {ComposantDetailsParamModel} from "../../../../../models/api/composant-details-param.model";
import {ActivatedRoute} from "@angular/router";
import {RelaisStateModel} from "../../../../../models/api/relais-state.model";
import {PointChartInfoModel} from "../../../../../models/core/point-chart-info.model";
import {DateFilter} from "../../../../../models/core/date/date-filter";
import {IComposantDetailModel} from "../../../../../models/api/composant.model";
import {ApiResponse} from "../../../../../models/api/base/api.model";
import {ToastService} from "../../../../../shared/services/toast/toast.service";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-prise',
  templateUrl: './prise.component.html',
  styleUrls: ['./prise.component.scss']
})
export class PriseComponent implements OnInit, OnDestroy{
  protected readonly FilterDate = FilterDate;
  private id!: number;

  filterOptionState: boolean = false;
  filterOptions!: ISelectOption[]
  optionActive!: ISelectOption;

  relaisInfoData: ApiDataModel<RelaisStateModel> = new ApiDataModel<RelaisStateModel>(null);
  detailData : ApiDataModel<ComponentDetailDataModel[]> = new ApiDataModel<ComponentDetailDataModel[]>([])
  consommationMonthlyData: ApiDataModel<IMonthlyData[]> = new ApiDataModel<IMonthlyData[]>([])
  consommationWeeklyData: ApiDataModel<IWeeklyData[]> = new ApiDataModel<IWeeklyData[]>([])
  consommationData : ApiDataModel<IComposantDetailModel[]> = new ApiDataModel<IComposantDetailModel[]>([])

  searchDetailVal?: PointChartInfoModel;

  dates: DateRangeModel = DateHelper.getDateRange(new Date());
  consommationDate: DateRangeModel = DateHelper.getDateRange(new Date());

  timeout: any

  constructor(private route: ActivatedRoute,
              private priseService: PriseService,
              private toastService: ToastService) {
  }

  ngOnInit(): void{
    this.initFilterDateOptions()

    this.route.params.subscribe(params => {
      this.id = parseInt(params['id'])

    })

    this.setConsommationMonthly()
    this.setInfo();
    this.setDetailData()
    this.setConsommationData()

    this.timeout = setInterval(() => {
      this.setDetailData()
      this.setConsommationData()
      this.setConsommationMonthly()
    }, 50000)

  }

  ngOnDestroy() {
    clearInterval(this.timeout)
  }

  setFilterOptionState(show: boolean){
    this.filterOptionState = show;
  }

  setOptionActive(option: ISelectOption){
    this.optionActive = option;
    if (!this.consommationMonthlyData.haveData()) this.setConsommationMonthly()
    if (!this.consommationWeeklyData.haveData()) this.setConsommationWeekly()
  }

  toggleFilterOption(){
    this.filterOptionState = !this.filterOptionState;
  }

  initFilterDateOptions(){
    this.filterOptions = basicFilterDateNow;
    this.optionActive = this.filterOptions[0]
  }

  setInfo(){
    this.priseService.getInfo(this.id)
      .subscribe((data: RelaisStateModel) => {
        this.relaisInfoData.data = data
        this.relaisInfoData.isLoaded()
      })
  }

  setConsommationWeekly(){
    let now = new Date()
    if (this.optionActive.key != FilterDate.WEEK_BY_MONTH) return;
    this.priseService
      .getConsommationWeekly(now.getFullYear(), now.getMonth()+1, this.id).subscribe(
      (data: IWeeklyData[]) => {
        this.consommationWeeklyData.data = data;
        this.consommationWeeklyData.isLoaded();
      })
  }

  setConsommationMonthly(){
    let now = new Date()
    if (this.optionActive.key != FilterDate.MONTH_BY_YEAR) return;
    this.priseService.getConsommationMonthly(now.getFullYear(), this.id)
      .subscribe(
        (data: IMonthlyData[]) => {
          this.consommationMonthlyData.data = data;
          this.consommationMonthlyData.isLoaded();
        })
  }

  setConsommationData(dates?: DateRangeModel){
    if (dates) this.consommationDate = dates;
    const params =
      new ComposantDetailsParamModel(this.consommationDate.start_date, this.consommationDate.end_date);

    this.priseService.getConsommationData(this.id, params)
      .subscribe((data: IComposantDetailModel[]) => {
        this.consommationData.data = data;
        this.consommationData.isLoaded();
      })
  }

  setDetailData(dates?: DateRangeModel){
    if (dates) this.dates = dates;
    const params =
      new ComposantDetailsParamModel(this.dates.start_date, this.dates.end_date);

    this.priseService.getDataDetail(this.id, params)
      .subscribe((data: ComponentDetailDataModel[]) => {
        this.detailData.data = data;
        this.detailData.isLoaded();
      })
  }

  findDetail(date: Date){
    this.priseService.findDataDetail(this.id, {date: DateHelper.toISOString(date)})
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
    // this.relaisInfoData.loading = true;
    this.priseService.switchRelais(this.id)
      .subscribe((response: ApiResponse<RelaisStateModel>) => {
        this.relaisInfoData.data = response.data
        this.relaisInfoData.isLoaded();

        this.toastService.success(response.message, 2500)
      })
  }

  onExport() {
    this.priseService.exportExcel()
      .subscribe(data => {

        saveAs(data, `prise_data_${DateHelper.toISOString(new Date())}.xlsx`)
      })
  }
}

import {Component, OnDestroy, OnInit} from '@angular/core';
import {ISelectOption} from "../../../../../models/core/iselect";
import {ApiDataModel} from "../../../../../models/api/base/api-data.model";
import {ComponentDetailDataModel} from "../../../../../models/core/component-detail-data.model";
import {IMonthlyData, IWeeklyData} from "../../../../../models/core/date/idatedata";
import {DateRangeModel} from "../../../../../models/core/date/date-range.model";
import {DateHelper} from "../../../../../shared/helper/date-helper";
import {ComposantDetailsParamModel} from "../../../../../models/api/composant-details-param.model";
import {PanelService} from "../../../../services/panel/panel.service";
import {FilterDate} from "../../../../../models/core/date/filter-date";
import {basicFilterDateNow} from "../../../../../config/filter-date.data";
import {ActivatedRoute} from "@angular/router";
import {PanelInfoModel} from "../../../../../models/api/info.model";
import {DateFilter} from "../../../../../models/core/date/date-filter";
import {IComposantDetailModel} from "../../../../../models/api/composant.model";
import {PointChartInfoModel} from "../../../../../models/core/point-chart-info.model";
import {RelaisStateModel} from "../../../../../models/api/relais-state.model";
import {ToastService} from "../../../../../shared/services/toast/toast.service";
import {ApiResponse} from "../../../../../models/api/base/api.model";
import {saveAs} from "file-saver";

@Component({
  selector: 'app-panel',
  templateUrl: './panel.component.html',
  styleUrls: ['./panel.component.scss']
})
export class PanelComponent implements OnInit, OnDestroy{

  private id!: number;

  filterOptionState: boolean = false;
  filterOptions!: ISelectOption[]
  optionActive!: ISelectOption;

  infoData : ApiDataModel<PanelInfoModel> = new ApiDataModel<PanelInfoModel>(null);
  relaisInfoData: ApiDataModel<RelaisStateModel> = new ApiDataModel<RelaisStateModel>(null);
  detailData : ApiDataModel<ComponentDetailDataModel[]> = new ApiDataModel<ComponentDetailDataModel[]>([])
  productionMonthlyData: ApiDataModel<IMonthlyData[]> = new ApiDataModel<IMonthlyData[]>([])
  productionWeeklyData: ApiDataModel<IWeeklyData[]> = new ApiDataModel<IWeeklyData[]>([])
  productionData: ApiDataModel<IComposantDetailModel[]> = new ApiDataModel<IComposantDetailModel[]>([])

  protected readonly FilterDate = FilterDate;
  dates: DateRangeModel = DateHelper.getDateRange(new Date());
  productionDates: DateRangeModel = DateHelper.getDateRange(new Date());

  timeout: any

  constructor(private route: ActivatedRoute,
              private panelService: PanelService,
              private toastService: ToastService) {
  }

  ngOnInit(): void{
    this.route.params.subscribe(params => {
      this.id = parseInt(params['id'])

    })

    this.initFilterDateOptions()
    this.setInfoData();
    this.setDetailData();
    this.setProductionMonthlyData();
    this.setProductionData()

    this.timeout = setInterval(()=>{

      this.setDetailData();
      this.setProductionMonthlyData();
      this.setProductionData()
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
    if (!this.productionMonthlyData.haveData()) this.setProductionMonthlyData()
    if (!this.productionWeeklyData.haveData()) this.setProductionWeeklyData()
  }

  toggleFilterOption(){
    this.filterOptionState = !this.filterOptionState;
  }

  initFilterDateOptions(){
    this.filterOptions = basicFilterDateNow;
    this.optionActive = this.filterOptions[0]
  }

  setInfoData(){
    this.panelService.getInfo(this.id)
      .subscribe((data: PanelInfoModel) => {
        this.infoData.data = data
        this.infoData.isLoaded();

        this.relaisInfoData.data = data.relais_state;
        this.relaisInfoData.isLoaded();

      })
  }

  setProductionMonthlyData(){
    let now = new Date()
    if (this.optionActive.key != FilterDate.MONTH_BY_YEAR) return;
    this.productionMonthlyData.loading = true;
    this.panelService.getProductionMonthly(now.getFullYear(), this.id)
      .subscribe(
        (data: IMonthlyData[]) => {
          this.productionMonthlyData.data = data;
          this.productionMonthlyData.isLoaded();
        })
  }

  setProductionWeeklyData(){
    let now = new Date()
    if (this.optionActive.key != FilterDate.WEEK_BY_MONTH) return;
    this.panelService
      .getProductionWeekly(now.getFullYear(), now.getMonth()+1, this.id).subscribe(
      (data: IWeeklyData[]) => {
        this.productionWeeklyData.data = data;
        this.productionWeeklyData.isLoaded();
      })
  }

  setDetailData(dates?: DateRangeModel){
    if (dates) this.dates = dates;

    const params = new ComposantDetailsParamModel(this.dates.start_date, this.dates.end_date);

    this.panelService.getDataDetail(this.id, params)
      .subscribe((data: ComponentDetailDataModel[]) => {

        this.detailData.data = data;
        this.detailData.isLoaded();
      })
  }

  setProductionData(dates?: DateRangeModel) {
    if (dates) this.productionDates = dates;
    const params =
      new ComposantDetailsParamModel(this.productionDates.start_date, this.productionDates.end_date);

    this.panelService.getProduction(this.id, params)
      .subscribe((data: IComposantDetailModel[]) => {
        this.productionData.data = data;

        this.productionData.isLoaded();
      })
  }

  switchRelais(){
    this.relaisInfoData.loading = true;
    this.panelService.switchRelais(this.id)
      .subscribe((response: ApiResponse<RelaisStateModel>) => {
        this.relaisInfoData.data = response.data
        this.relaisInfoData.isLoaded();

        this.toastService.success(response.message, 2500)
      })
  }

  onExport() {
    this.panelService.exportExcel()
      .subscribe(data => {

        saveAs(data, `panel_data_${DateHelper.toISOString(new Date())}.xlsx`)
      })
  }
}

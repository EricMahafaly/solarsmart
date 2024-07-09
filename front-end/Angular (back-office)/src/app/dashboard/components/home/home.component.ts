import {Component, OnInit} from '@angular/core';
import {ApiDataModel} from "../../../models/api/base/api-data.model";
import {StatisticModuleModel} from "../../../models/api/statistic.model";
import {ModuleService} from "../../services/module/module.service";
import {RatingService} from "../../services/rating/rating.service";
import {RatingModel} from "../../../models/api/rating.model";
import {CustomerStatistic} from "../../../models/api/customer-info.model";
import {CustomerService} from "../../services/customer/customer.service";
import {ReportService} from "../../services/report/report.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit{

  moduleDataChart: any;
  moduleChartOptions: any;

  moduleStat: ApiDataModel<StatisticModuleModel> = new ApiDataModel<StatisticModuleModel>(null);
  avgRating: ApiDataModel<number> = new ApiDataModel<number>(null);
  ratings : ApiDataModel<RatingModel[]> = new ApiDataModel<RatingModel[]>([]);
  clientsStat: ApiDataModel<CustomerStatistic> = new ApiDataModel<CustomerStatistic>(null);
  reportUnsolved: ApiDataModel<number> = new ApiDataModel<number>(null)

  constructor(
    private moduleService: ModuleService,
    private ratingService: RatingService,
    private customerService: CustomerService,
    private reportService: ReportService) {
  }

  ngOnInit() {
    this.setModuleStat();
    this.setAverageRating();
    this.setFiveLatestRating();
    this.setClientStat();
    this.setReportUnsolved();
  }

  setAverageRating(){
    this.ratingService.getAvgScore()
      .subscribe(score => {
        this.avgRating.data = score
        this.avgRating.isLoaded()
      })
  }

  setFiveLatestRating(){
    this.ratingService.getFiveLatest()
      .subscribe(ratings => {
        this.ratings.data = ratings;
        this.ratings.isLoaded();
      })
  }

  setModuleStat(){
    this.moduleService.getStatistic()
      .subscribe(value => {
        this.moduleStat.data = value
        this.createModuleStatChart(value)
        this.moduleStat.isLoaded()
      })
  }

  setClientStat(){
    this.customerService.getStatisticCurrentMonth()
      .subscribe(clientStat =>{
        this.clientsStat.data = clientStat
        this.clientsStat.isLoaded();
      })
  }


  setReportUnsolved(){
    this.reportService.getUnsolvedReportCount()
      .subscribe(count => {
        this.reportUnsolved.data = count;
        this.reportUnsolved.isLoaded();
      })
  }

  createModuleStatChart(data: StatisticModuleModel){
    // let color = 'rgb(227, 243, 236)'
    this.moduleDataChart = {
      labels: ['Active', 'Inactive'],
      datasets: [
        {
          data: [data.used, data.unused],
          backgroundColor: ['rgb(99, 179, 237)', 'rgb(227, 243, 236)']
        }
      ]
    };

    this.moduleChartOptions = {
      plugins: {
        legend: {
          labels: {
            usePointStyle: true,
          }
        }
      }
    };
  }

  getCustomerColorState(percentage: number): string{
    if (percentage < 0) return "danger";
    return "success";
  }

  getCustomerStateIcon(percentage: number): string{
    if (percentage < 0) return "down";
    return "up";
  }

  getReportCountColor(count: number): string{
    if (count == 0) return "success";
    return "danger"
  }
}

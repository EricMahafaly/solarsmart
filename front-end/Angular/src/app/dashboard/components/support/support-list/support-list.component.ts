import {Component, OnInit} from '@angular/core';
import {Pagination} from "../../../../models/core/pagination.model";
import {ApiDataModel} from "../../../../models/api/base/api-data.model";
import {ReportModel, ReportStateEnum} from "../../../../models/api/report.model";
import {ReportService} from "../../../services/report/report.service";
import {SortDirection, SortFilterModel} from "../../../../models/core/sort";
import {FilterRequestModel, IFilterModel} from "../../../../models/api/base/filter.model";
import {AuthService} from "../../../services/authentication/auth.service";
import {ToastService} from "../../../../shared/services/toast/toast.service";

@Component({
  selector: 'app-support-list',
  templateUrl: './support-list.component.html',
  styleUrls: ['./support-list.component.scss']
})
export class SupportListComponent implements OnInit{

  pagination: Pagination = new Pagination();
  reports: ApiDataModel<ReportModel[]> = new ApiDataModel<ReportModel[]>([])
  collapseFilter: boolean =  true;
  label: string = 'Filter'

  sortDateFilter: SortFilterModel[] = []

  showFullText: boolean[] = []
  filterRequest?: FilterRequestModel[];

  reportActive?: ReportModel

  constructor(
    private reportService: ReportService,
    private authService: AuthService,
    private toastService: ToastService) {
  }
  ngOnInit(): void {
    this.setReports(this.filterRequest);
    this.initDateFilter()
  }

  initDateFilter(){
    this.sortDateFilter = [
      {
        label: 'Recently',
        active: false,
        sort: {
          field: 'createdDate',
          direction: SortDirection.DESC,
        }
      },
      {
        label: 'Older first',
        active: false,
        sort: {
          field: 'createdDate',
          direction: SortDirection.ASC,
        }
      }
    ];

  }

  initShowFullText(){
    this.reports.data.forEach(() => this.showFullText.push(false))
  }

  setReportDate(){
    this.reportService.getAll(this.pagination.toRequest())
      .subscribe(reports => {
        this.reports.data = reports.items
        this.pagination.totalItems = reports.totalItems
        this.initShowFullText();
        this.reports.isLoaded();
    })
  }

  filterReportData(filter: FilterRequestModel[]){
    this.reportService.filter(this.pagination.toRequest(), filter)
      .subscribe(reports =>{

        this.reports.data = reports.items;
        this.pagination.totalItems = reports.totalItems
      })
  }

  setReports(filter ?: FilterRequestModel[]){
    // this.reports.loading = true;
    if (filter) this.filterReportData(filter)
    else {
      this.setReportDate();
    }
  }

  filter(nav: {label: string, filter: {search: IFilterModel, state?: IFilterModel}}){
    this.label = nav.label;

    const userInfo = this.authService.getInformation();

    const baseCriteria: FilterRequestModel = {
      dataOption: 'any',
      criteria: [
        {
          filterKey: 'admin.id',
          operation: 'nu'
        },
        {
          filterKey: 'admin.id',
          operation: 'eq',
          value: userInfo.id
        }
      ]
    }

    const otherCriteria: FilterRequestModel = {
      dataOption: 'all',
      criteria: [nav.filter.search]
    }

    if (nav.filter.state) otherCriteria.criteria.push(nav.filter.state)

    this.filterRequest = [baseCriteria, otherCriteria]

    this.setReports(this.filterRequest)

  }

  private inactiveAllSort(){
    this.sortDateFilter.forEach(value => value.active = false)
  }

  close(report: ReportModel){

    if (report.state == ReportStateEnum.CLOSED) return;

    this.reportService.closeReport(report.id)
      .subscribe(resp => {
        this.toastService.success(resp.message)
        this.setReports(this.filterRequest)
      })
  }

  getStateIcon(report: ReportModel): string{
    return report.state === ReportStateEnum.CLOSED ? "fa-solid" : "fa-regular"
  }

  changePage(page: number){
    this.pagination.currentPage = page
    this.setReports(this.filterRequest)
  }

  sort(sortFilter: SortFilterModel){
    this.inactiveAllSort();
    sortFilter.active = true;

    this.pagination.sortBy = sortFilter.sort.field
    this.pagination.direction = sortFilter.sort.direction;

    this.setReports(this.filterRequest);
  }

  protected readonly ReportStateEnum = ReportStateEnum;

  toggleChat(report: ReportModel) {
    if (!this.reportActive || (this.reportActive && this.reportActive != report)) this.reportActive = report;
    else if(this.reportActive && this.reportActive === report) this.reportActive = undefined
  }
}

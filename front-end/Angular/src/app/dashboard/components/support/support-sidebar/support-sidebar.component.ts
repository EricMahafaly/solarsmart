import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ISidenavGroupItem, ISidenavItem} from "../../../../models/core/sidenav";
import {IFilterModel} from "../../../../models/api/base/filter.model";
import {ReportService} from "../../../services/report/report.service";
import {ApiDataModel} from "../../../../models/api/base/api-data.model";
import {ReportStateModel} from "../../../../models/api/report.model";
import {FormControl} from "@angular/forms";
import {debounceTime} from "rxjs";

@Component({
  selector: 'app-support-sidebar',
  templateUrl: './support-sidebar.component.html',
  styleUrls: ['./support-sidebar.component.scss']
})
export class SupportSidebarComponent implements OnInit{
  navData!: ISidenavGroupItem[]
  states: ApiDataModel<ReportStateModel[]> = new ApiDataModel<ReportStateModel[]>([])

  searchControl: FormControl = new FormControl('')

  stateFilter?: IFilterModel

  active!: {label: string, filter: {search: IFilterModel, state?: IFilterModel}}

  @Output('selected') navActive: EventEmitter<{label: string, filter: {search: IFilterModel, state?: IFilterModel}}> =
    new EventEmitter<{label: string, filter: {search: IFilterModel, state?: IFilterModel}}>()

  constructor(private reportService: ReportService) {
    this.setStatesData();
  }

  ngOnInit(): void {
    this.setStatesData();

    this.searchControl.valueChanges.pipe(
      debounceTime(300)
    ).subscribe( term => {
      let filter: IFilterModel = this.setSearchFilter(term)

      this.active = {
        label: 'search',
        filter: {
          search: filter,
          state: this.stateFilter
        }
      }

      this.navActive.emit(this.active)
    })
  }

  setSearchFilter(val: string = this.searchControl.value): IFilterModel{
    return  {
      filterKey: 'description',
      value: val,
      operation: 'cn'
    }
  }

  setStateFilter(nav: ISidenavItem){
    this.stateFilter = this.createFilter(nav);
  }


  setStatesData(){
    this.reportService.getAllReportState()
      .subscribe(states => {
        this.states.data = states;
        this.setNavData(states)
        this.states.isLoaded();
      })
  }


  onClick(nav: ISidenavItem){

    if (!nav.data){
      this.stateFilter = undefined
    }else{
      this.setStateFilter(nav)
    }

    this.active = {
      label: nav.label,
      filter: {
        state: this.stateFilter,
        search: this.setSearchFilter()
      }
    }

    this.navActive.emit(this.active)


  }

  setNavData(states: ReportStateModel[]){
    let navs: ISidenavItem[] = []

    navs.push(
      {
        icon: 'fa-solid fa-list-check',
        label: 'All',
      }
    )

    for (let state of states) {
      navs.push({
        data: state.id,
        label: state.state,
        icon: 'fa-solid fa-circle',
        iconColor: 'rgb(99, 179, 237)'
      })
    }

    this.navData = [
      {
        group: 'Filter',
        subTitle: '',
        items: navs
      }
    ];
  }

  createFilter(nav: ISidenavItem): IFilterModel{
    return {
      filterKey: 'state.id',
      value: nav.data,
      operation: 'eq'
    }
  }
}

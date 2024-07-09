import {Component, OnInit} from '@angular/core';
import {Pagination} from "../../../models/core/pagination.model";
import {FilterRequestModel, IFilterModel} from "../../../models/api/base/filter.model";
import {ApiDataModel} from "../../../models/api/base/api-data.model";
import {RatingModel} from "../../../models/api/rating.model";
import {RatingService} from "../../services/rating/rating.service";
import {ISelectOption} from "../../../models/core/iselect";
import {SortDirection, SortModel} from "../../../models/core/sort";

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.scss']
})
export class CommentComponent implements OnInit{

  pagination : Pagination = new Pagination();
  ratings: ApiDataModel<RatingModel[]> = new ApiDataModel<RatingModel[]>([])
  filters: {label: string, filter?: IFilterModel, active: boolean, icon: boolean}[] = []
  showFilterDate: boolean = false;
  filterDateOptions!: ISelectOption[];
  filterDateActive!: ISelectOption;

  filter?: IFilterModel

  constructor(
    private ratingService: RatingService) {
  }

  ngOnInit(): void {
    this.initFilter();
    this.setComments(this.filter)
    this.initFilterDate();
  }

  initFilter(){
    this.filters.push({label: 'All', active: true, icon: false})
    for (let i = 0; i <= 5; i++) {
      const filter: IFilterModel = {
        filterKey: 'score',
        value: i,
        operation: 'eq'
      }

      this.filters.push({label: i+'', filter, active: false, icon: true})
    }
  }

  initFilterDate(){

    const filter: ISelectOption[] = [
      {
        label: 'Recently',
        key: 1,
        value: {
          direction: SortDirection.DESC,
          field: 'date'
        },
        selected: true
      },
      {
        label: 'Anciently',
        key: 2,
        value: {
          direction: SortDirection.ASC,
          field: 'date'
        }
      }
    ]

    this.filterDateActive = filter[0];

    this.filterDateOptions = filter;
  }

  setRatingsData(){
    this.ratingService.list(this.pagination.toRequest())
      .subscribe(ratingsPage => {
        this.ratings.data = ratingsPage.items

        this.pagination.totalItems = ratingsPage.totalItems;

        this.ratings.isLoaded();
      })
  }

  filterRating(filterRequest: FilterRequestModel){
    this.ratingService.filter(this.pagination.toRequest(), filterRequest)
      .subscribe(pageResponse => {
        this.ratings.data = pageResponse.items

        this.pagination.totalItems = pageResponse.totalItems

        this.ratings.isLoaded();
      })
  }

  applyFilter(request: {label: string, filter?: IFilterModel, active: boolean}){
    this.filters.forEach(value => {
      value.active = false;
    })
    request.active = true

    this.filter = request.filter

    this.pagination = new Pagination();

    this.setComments(this.filter)

  }

  changePage(page: number){
    this.pagination.currentPage = page
    this.setComments(this.filter)
  }

  setComments(filter?: IFilterModel){
    if (filter){
      const criteriaModel: FilterRequestModel = {
        criteria: [filter],
        dataOption: 'all'
      }
      this.filterRating(criteriaModel)
    }else{
      this.setRatingsData()
    }
  }

  sort(option: ISelectOption){
    this.filterDateActive = option;
    const sort: SortModel = option.value as SortModel

    this.pagination.sortBy = sort.field
    this.pagination.direction = sort.direction


    this.setComments(this.filter);
  }
}

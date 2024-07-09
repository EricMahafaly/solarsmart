import {Component, OnInit} from '@angular/core';
import {ApiDataModel} from "../../../../models/api/base/api-data.model";
import {ModuleSolarModel} from "../../../../models/api/module.model";
import {ModuleService} from "../../../services/module/module.service";
import {Pagination} from "../../../../models/core/pagination.model";
import {PageResponseModel} from "../../../../models/api/base/page-response.model";
import {MatDateRangePicker} from "@angular/material/datepicker";
import {ToastService} from "../../../../shared/services/toast/toast.service";
import {Router} from "@angular/router";
import {TableLazyLoadEvent, TablePageEvent} from "primeng/table";
import {SortEvent} from "primeng/api";
import {Sort} from "@angular/material/sort";
import {ModuleOtherInfoDetailModel} from "../../../../models/api/info.model";
import {ConfirmationComponent} from "../../../../shared/components/confirmation/confirmation.component";
import {ModalService} from "@coreui/angular";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-module-list',
  templateUrl: './module-list.component.html',
  styleUrls: ['./module-list.component.scss']
})
export class ModuleListComponent implements OnInit{
  moduleData: ApiDataModel<ModuleSolarModel[]> = new ApiDataModel<ModuleSolarModel[]>([])
  modules!: ModuleSolarModel[]
  pagination: Pagination = new Pagination()

  cols!: {field: string, header: string}[];

  constructor(private moduleService: ModuleService,
              private toastService: ToastService,
              private modalService: NgbModal) {

    this.cols = [
      { field: 'reference', header: 'Reference' },
      { field: 'createdDate', header: 'Date de creation' }
    ];
  }

  ngOnInit(): void {
    this.pagination.pageSize = 5;
    this.setModuleData();
  }

  setModuleData(){
    this.moduleData.loading = true;
    this.moduleService.getAll(this.pagination.toRequest())
      .subscribe((data : PageResponseModel<ModuleSolarModel>) => {

        this.moduleData.data = data.items

        console.log("ato")
        this.pagination.totalItems = data.totalItems;
        this.moduleData.loading = false;
      })
  }

  changePage(page: number){
    // if (page ){
      this.pagination.currentPage = page
      // this.moduleData.loading = true;
      this.setModuleData();
    // }

  }

  confirmationDelete(id: number){
    const modalRef = this.modalService.open(ConfirmationComponent,
      {size: "sm", scrollable: false, keyboard: false, backdrop: "static", centered: true})

    modalRef.componentInstance.confirmChange.subscribe((data: boolean)=>{
      if (data) this.delete(id);
    })
  }

  delete(id: number){
    this.moduleService.delete(id)
      .subscribe(response => {
        this.pagination.currentPage = 1;
        // this.route.navigate(['']);
        this.setModuleData();
        this.toastService.success(response.message)
      })
  }

  sort(sort: Sort) {
    console.log("hey = ", sort)

    this.pagination.sortBy = sort.active
    this.pagination.direction = sort.direction

    this.setModuleData();
  }

  getRow() {
    return new Array(3);
  }
}

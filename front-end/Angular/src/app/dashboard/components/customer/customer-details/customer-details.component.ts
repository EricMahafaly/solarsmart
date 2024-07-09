import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ApiDataModel} from "../../../../models/api/base/api-data.model";
import {CustomerService} from "../../../services/customer/customer.service";
import {CustomerDetailModel} from "../../../../models/api/customer-detail.model";
import {ITabMenu} from "../../../../models/core/itabmenu";

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.scss']
})
export class CustomerDetailsComponent implements OnInit, AfterViewInit{
  private id!: number

  detailData : ApiDataModel<CustomerDetailModel> = new ApiDataModel<CustomerDetailModel>(null)
  constructor(private route: ActivatedRoute, private customerService: CustomerService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.id = parseInt(params['id'])
    });

    this.setDetailData();
  }

  setDetailData(){
    this.customerService.getDetail(this.id)
      .subscribe(data => {
        this.detailData.data = data;

        this.detailData.isLoaded()
      })
  }

  ngAfterViewInit(): void {
  }

  getTabs() : ITabMenu[] {
    return [
      {
        "icon": 'fas fa-battery',
        "title": "Battery",
        'link': "/user-space/customers/details/" + this.id + "/batteries/" + this.detailData.data.moduleId
      },
      {
        icon: 'fas fa-plug-circle-bolt',
        "title": "Plug",
        'link': "/user-space/customers/details/" + this.id + "/prises/" + this.detailData.data.moduleId
      },
      {
        icon: 'fa fa-solar-panel',
        "title": "Panel",
        'link': "/user-space/customers/details/" + this.id + "/panels/" + this.detailData.data.moduleId
      }
    ];
  }

}

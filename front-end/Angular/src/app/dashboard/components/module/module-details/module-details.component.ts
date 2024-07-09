import {Component, Input, OnInit} from '@angular/core';
import {ApiDataModel} from "../../../../models/api/base/api-data.model";
import {ModuleDetailModel} from "../../../../models/api/module-detail.model";
import {ModuleService} from "../../../services/module/module.service";
import {environment} from "../../../../environment";
import {BatteryInfoModel, PanelInfoModel} from "../../../../models/api/info.model";
import {BatteryService} from "../../../services/battery/battery.service";
import {PanelService} from "../../../services/panel/panel.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-module-details',
  templateUrl: './module-details.component.html',
  styleUrls: ['./module-details.component.scss']
})
export class ModuleDetailsComponent implements OnInit{
  id!: number;
  private env = environment.apiUrl;

  detailData: ApiDataModel<ModuleDetailModel> = new ApiDataModel<ModuleDetailModel>(null)

  collapse: boolean = true;


  constructor(private moduleService: ModuleService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {

    console.log("affiche")

    this.route.params.subscribe( params => {
      this.id = parseInt(params['id']);

      console.log(this.id)
    })

    this.setDetailData();
    // this.setDetailData()
        // throw new Error('Method not implemented.');
  }

  getDate(){
    return new Date();
  }

  setDetailData(){
    this.moduleService.getDetail(this.id)
      .subscribe(data => {
        this.detailData.data = data;
        this.detailData.isLoaded();
      })
  }

  getStateLabel(): string{
    return this.detailData.data.used ? "utilisé" : "inutilisé";
  }

  getStateClasse(): string{
    return this.detailData.data.used ? 'module-active': 'module-inactive';
  }

  getImageLink(){
    return `${this.env}/modules/${this.id}/qrcode`
  }

  collapseClass(): string{
    return this.collapse ? "fa-chevron-right" : "fa-chevron-down"
  }
}

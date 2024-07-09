import {Component, Input, OnInit} from '@angular/core';
import {TableColumnInfoType} from "../../../../../models/core/table/table-column-info-type";

@Component({
  selector: 'app-table-col-info',
  templateUrl: './table-col-info.component.html',
  styleUrls: ['./table-col-info.component.css']
})
export class TableColInfoComponent implements OnInit{
  @Input({required: true}) colType!: TableColumnInfoType;
  @Input({required: true}) inData!: any
  data = {label: '', image: '', badge: '', info: ''}

  ngOnInit(): void {
    if (this.inData != null){
      this.data.label = this.inData[this.colType.principalLabelKey];
      this.data.image = this.inData[this.colType.imageKey];
      this.data.info = this.inData[this.colType.infoKey];
    }
  }



}

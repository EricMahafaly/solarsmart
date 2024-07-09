import {Component, Input} from '@angular/core';
import {ITable} from "../../../models/core/table/table";

@Component({
  selector: 'app-dynamic-table',
  templateUrl: './dynamic-table.component.html',
  styleUrls: ['./dynamic-table.component.css']
})
export class DynamicTableComponent {
    @Input({required: true}) table!: ITable;


}

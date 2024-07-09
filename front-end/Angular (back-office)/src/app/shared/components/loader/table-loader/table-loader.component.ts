import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-table-loader',
  templateUrl: './table-loader.component.html',
  styleUrls: ['./table-loader.component.scss']
})
export class TableLoaderComponent {
  @Input() row: number = 3
  @Input() col: number = 3

  getColSize(){
    return 12/this.col;
  }

  getRow(){
    return new Array(this.row)
  }

  getCol(){
    return new Array(this.col);
  }
}

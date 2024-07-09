import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-list-loader',
  templateUrl: './list-loader.component.html',
  styleUrls: ['./list-loader.component.scss']
})
export class ListLoaderComponent {
  @Input('number') num: number = 5

  getNum(){
    return new Array(this.num);
  }
}

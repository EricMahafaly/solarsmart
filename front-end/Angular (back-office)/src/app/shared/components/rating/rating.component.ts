import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.scss']
})
export class RatingComponent implements OnInit{

  @Input() stars: number = 5
  @Input('value') starFill: number = 0
  @Input() activeClass: string = ''


  ngOnInit(): void {
    this.checkStarVal()
  }

  checkStarVal(){
    if (this.starFill <0){
      this.starFill = 0;
    }else if(this.starFill > 5) this.starFill = 5;
  }
  getNumbers(count: number = this.stars): number[] {
    return Array.from({ length: count }, (_, index) => index + 1);
  }
}

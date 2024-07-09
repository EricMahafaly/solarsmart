import {Component, Input, OnInit} from '@angular/core';
import {RatingModel} from "../../../../models/api/rating.model";

@Component({
  selector: 'app-comment-list',
  templateUrl: './comment-list.component.html',
  styleUrls: ['./comment-list.component.scss']
})
export class CommentListComponent implements OnInit{

  @Input('data') ratings !: RatingModel[]

  showFullTexts: boolean[] = [];


  ngOnInit(): void {
    this.initTruncateTextState();
  }

  initTruncateTextState(){
    this.ratings.forEach(() => this.showFullTexts.push(false))
  }

  haveData(): boolean{
    return this.ratings.length > 0;
  }


}

import {AfterViewInit, Component, ContentChild, ElementRef, Renderer2} from '@angular/core';

@Component({
  selector: 'app-skeleton-loader',
  templateUrl: './skeleton-loader.component.html',
  styleUrls: ['./skeleton-loader.component.scss']
})
export class SkeletonLoaderComponent implements AfterViewInit{
  constructor(private elt:ElementRef, private renderer: Renderer2) {
  }

  ngAfterViewInit() {
    const textNode = this.elt.nativeElement.childNodes[0];
    const textInput = textNode.nodeValue;

    console.log(textNode.childNodes[1].classList)

    // console.log("textInput = ", textNode.)
    // this.renderer.setValue(textNode, textInput.textNode)
  }
}

import {ChangeDetectorRef, Component, Input} from '@angular/core';
import {LoaderService} from "../../services/loader/loader.service";

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss']
})
export class LoaderComponent {
  @Input() text: string = 'loading ...'

  constructor(private loaderService: LoaderService, private cdRef: ChangeDetectorRef) {

  }

  ngOnInit() {
    this.init();
  }

  isLoading(){
    return this.loaderService.isLoading$;
  }

  init() {

  }
}

import {
  Component,
  ElementRef,
  EventEmitter, HostListener,
  Input, OnChanges,
  OnInit,
  Output,
  SimpleChanges,
  ViewChild
} from '@angular/core';
import {FormControl} from "@angular/forms";
import {debounceTime} from "rxjs";
import {ISelectOption} from "../../../models/core/iselect";

@Component({
  selector: 'app-select-generic',
  templateUrl: './select-generic.component.html',
  styleUrls: ['./select-generic.component.scss']
})
export class SelectGenericComponent implements OnInit, OnChanges{

  @Input() options : ISelectOption[] = []
  originalOptions : ISelectOption[]  = []
  @Input() show: boolean = false;
  @Input() withSearch: boolean = false;
  @Input() value: string = ''
  @Input({required: true}) parent?: HTMLElement;
  @Output() isShow = new EventEmitter<boolean>
  @Output() showChange = new EventEmitter<boolean>
  @Input() positionClass: string = ''

  @Input({alias: 'searchControl'}) searchControl: FormControl = new FormControl(this.value);

  @Output() selectChange = new EventEmitter<ISelectOption>();

  @ViewChild('menu') menu!: ElementRef;

  constructor(private el: ElementRef) {

  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: Event){

    if (!this.el.nativeElement.contains(event.target) && !this.parent?.contains(event.target as Node)){
      this.show = false;
      this.isShow.emit(false)
      this.showChange.emit(false)
    }
  }

  select(option: ISelectOption) {
    this.emitSelected(option)

    this.show = false;
    this.isShow.emit(this.show)
    this.showChange.emit(this.show)
  }

  emitSelected(option: ISelectOption){
    option.selected = true;
    this.options.forEach(op=>{
      if (op != option) op.selected = false;
    })
    this.selectChange.emit(option);
  }

  ngOnInit() {

    this.originalOptions = [...this.options];

    this.searchControl.valueChanges
      .pipe(debounceTime(200))
      .subscribe(term => {



        this.search(term);
        this.onValueChanging()
      });
  }

  search(label: string) {
    this.options = this.originalOptions.filter(
      option => option.label.toLocaleLowerCase().includes(label.toLocaleLowerCase())
    );
  }

  onValueChanging(){
    if (this.value === '' || (this.options.length ===1 && this.value !== this.options[0].label)){

      // console.log("options = ", this.options)

      this.options.forEach(op=>{ op.selected = false;})
    }else if (this.options.length === 1 && this.value === this.options[0].label) {
      this.emitSelected(this.options[0])
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['value']) this.searchControl.setValue(this.value)
  }
}

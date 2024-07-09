import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {
  AdditionalListResult,
  ModuleOtherInfoDetailModel,
  ModuleOtherInfoModel
} from "../../../../../models/api/info.model";
import {AdditionalInfoComponent} from "../../module-creation/additional-info/additional-info.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {DeleteModuleInfoModel} from "../../../../../models/core/delete.model";
import {AdditionalInfoService} from "../../../../services/module/additional-info/additional-info.service";
import {ConfirmationComponent} from "../../../../../shared/components/confirmation/confirmation.component";
import {ToastService} from "../../../../../shared/services/toast/toast.service";

@Component({
  selector: 'app-additional-info-list',
  templateUrl: './additional-info-list.component.html',
  styleUrls: ['./additional-info-list.component.scss']
})
export class AdditionalInfoListComponent implements OnInit, OnChanges{

  protected collapseState : boolean[] = []

  @Input({required: true}) data!: ModuleOtherInfoModel[];
  @Output() dataChange : EventEmitter<ModuleOtherInfoModel[]> = new EventEmitter<ModuleOtherInfoModel[]>()
  @Input() editable: boolean = false;
  @Input() deletable: boolean = false;
  // result: AdditionalListResult = {data: [], deletes: [], detailsDeletes: []}
  // @Output('result') deletesOutput: EventEmitter<AdditionalListResult> = new EventEmitter<AdditionalListResult>()

  constructor(
    private modalService: NgbModal,
    private additionalInfoService: AdditionalInfoService,
    private toastService: ToastService) {
  }

  ngOnInit(): void {
    this.initCollapse();
  }

  ngOnChanges(changes: SimpleChanges): void {

    if (changes['data']){
      this.initCollapse();
    }
  }

  collapse(id: number){
    for (let i = 0; i < this.collapseState.length; i++) {
      if (i != id)
      this.collapseState[i] = true;
    }

    this.collapseState[id] = !this.collapseState[id];
  }

  // setDataOupt

  initCollapse(){
    this.data.forEach(() => this.collapseState.push(true))
  }
  haveAction(): boolean{
    return this.editable || this.deletable;
  }

  confirmDelete(id: number){
    if (this.data ){
      const info: ModuleOtherInfoModel = this.data[id]

      if (info){

        if (!info.id){
          this.delete(id);
          return;
        }

        const modalRef = this.modalService.open(ConfirmationComponent,
          {size: "sm", scrollable: false, keyboard: false, backdrop: "static", centered: true})

        modalRef.componentInstance.confirmChange.subscribe((data: boolean)=>{
          if (data && info.id){

            this.additionalInfoService.delete(info.id).subscribe(value => {
              this.toastService.info("suppression effectué")
              this.delete(id)
            })
          }
        })
      }
    }
  }
  delete(index: number){

    const deletedData = this.data.splice(index, 1)
  }

  update(index: number){
    const modalRef = this.modalService.open(AdditionalInfoComponent,
      {size: "lg", scrollable: true})

    modalRef.componentInstance.data = this.data[index]

    modalRef.componentInstance.dataChange.subscribe((data: ModuleOtherInfoModel)=>{
      this.data[index] = data
    })

    this.dataChange.emit(this.data)
  }

  getIcon(index: number): string{
    return !this.collapseState[index] ? "fas fa-chevron-up" : "fas fa-chevron-down";
  }
}

import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild
} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ModuleOtherInfoDetailModel, ModuleOtherInfoModel} from "../../../../../models/api/info.model";
import {NgbActiveModal, NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {DeleteModuleInfoModel} from "../../../../../models/core/delete.model";
import {AdditionalInfoService} from "../../../../services/module/additional-info/additional-info.service";
import {ConfirmationComponent} from "../../../../../shared/components/confirmation/confirmation.component";
import {ToastService} from "../../../../../shared/services/toast/toast.service";

@Component({
  selector: 'app-additional-info',
  templateUrl: './additional-info.component.html',
  styleUrls: ['./additional-info.component.scss']
})
export class AdditionalInfoComponent implements OnInit{

  protected infoForm!: FormGroup
  @ViewChild('content') protected modal !: ElementRef

  @Input() visible: boolean = false;
  @Output() visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>(false);
  @Input() data?: ModuleOtherInfoModel
  @Output('detailsDeleted') detailsDeletedOutput :
    EventEmitter<DeleteModuleInfoModel> = new EventEmitter<DeleteModuleInfoModel>()
  @Output() dataChange: EventEmitter<ModuleOtherInfoModel> = new EventEmitter<ModuleOtherInfoModel>();

  constructor(
    private fb: FormBuilder,
    private activeModal: NgbActiveModal,
    private additionalInfoService: AdditionalInfoService,
    private modalService: NgbModal,
    private toastService: ToastService) {

  }

  ngOnInit(): void {
    this.refreshForm();
  }

  refreshForm(){
    this.infoForm = this.fb.group({
      description: [this.data?.description],
      name: [this.data?.name, Validators.required],
      details: this.fb.array(
        (this.data?.details || []).map(detail => this.fb.group({
          id: [detail.id],
          key: [detail.key, [Validators.required]],
          value: [detail.value, [Validators.required]],
        }))
      )
    })

    if (!this.data) this.addDetail();
  }

  getDetails() {
    return this.infoForm.get('details') as FormArray;
  }

  getDetailsGroup(){
    return this.getDetails().controls as FormGroup[];
  }

  addDetail() {
    const detailGroup = this.fb.group({
      key: ['', Validators.required],
      value: ['', Validators.required],
      description: ['']
    });

    this.getDetails().push(detailGroup);
  }

  hide(){
    this.activeModal.close()
    this.refreshForm()
  }

  onSubmit(){
    this.infoForm.markAllAsTouched();

    if (this.infoForm.valid){

      let formVal = this.infoForm.value;

      let data: ModuleOtherInfoModel = {
        id: this.data?.id,
        details: formVal.details,
        name: formVal.name,
        description: formVal.description
      }

      this.dataChange.emit(data)
      this.infoForm.reset()
      this.hide()
    }
  }

  confirmDelete(id: number){
    if (this.data ){
      const detailDeleted: ModuleOtherInfoDetailModel = this.data.details[id]

      if (detailDeleted){
        if (!detailDeleted.id){
          this.deleteDetail(id);
          return;
        }
        const modalRef = this.modalService.open(ConfirmationComponent,
          {size: "sm", scrollable: false, keyboard: false, backdrop: "static", centered: true})

        modalRef.componentInstance.confirmChange.subscribe((data: boolean)=>{
          if (data && detailDeleted.id){

            this.additionalInfoService.deleteDetail(detailDeleted.id).subscribe(value => {
              this.toastService.info("delete successful!")
              this.deleteDetail(id)
            })
          }
        })
      }
    }
  }

  deleteDetail(index: number) {
    if (index < 1) return;

    let array: FormArray = this.infoForm.get('details') as FormArray;
    array.removeAt(index)


    if (this.data){
      this.data.details.splice(index, 1)
      this.dataChange.emit(this.data)
    }
  }
}

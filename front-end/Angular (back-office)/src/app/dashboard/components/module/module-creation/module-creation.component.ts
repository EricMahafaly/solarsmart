import {Component, OnInit} from '@angular/core';
import {ISelectOption} from "../../../../models/core/iselect";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {BatteryService} from "../../../services/battery/battery.service";
import {ApiDataModel} from "../../../../models/api/base/api-data.model";
import {numberValidator} from "../../../../shared/validator/basic.validator";
import {PanelService} from "../../../services/panel/panel.service";
import {ModuleService} from "../../../services/module/module.service";
import {ModuleSolarRegistration} from "../../../../models/api/module.model";
import {ToastService} from "../../../../shared/services/toast/toast.service";
import {ActivatedRoute, Router} from "@angular/router";
import {BatteryDistinctModel, PanelDistinctModel} from "../../../../models/api/simple.model";
import {ModuleDetailModel} from "../../../../models/api/module-detail.model";
import {AdditionalListResult, ModuleOtherInfoModel} from "../../../../models/api/info.model";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AdditionalInfoComponent} from "./additional-info/additional-info.component";

@Component({
  selector: 'app-module-creation',
  templateUrl: './module-creation.component.html',
  styleUrls: ['./module-creation.component.scss']
})
export class ModuleCreationComponent implements OnInit{
  id?: number;

  batteryOptions: ApiDataModel<ISelectOption[]> = new ApiDataModel<ISelectOption[]>([])
  panelOptions: ApiDataModel<ISelectOption[]> = new ApiDataModel<ISelectOption[]>([])

  moduleForm!: FormGroup
  dataForm!: ModuleSolarRegistration;
  searchBattery: FormControl = new FormControl('');
  searchPanel: FormControl = new FormControl('');

  showPanel: boolean = false;
  showBattery: boolean = false;
  submitted: boolean = false;
  loaded: boolean = false;

  protected title: string = 'New module';

  constructor(
    private formBuilder: FormBuilder, private batteryService: BatteryService,
    private panelService: PanelService, private moduleService: ModuleService,
    private toastService: ToastService, private router: Router,
    private routes: ActivatedRoute, private modalService: NgbModal) {

  }

  ngOnInit(): void {
    this.routes.params.subscribe(params => {
      const id = params['id']

      if (id){
        this.id = parseInt(id)
        this.initData();
      }else{
        this.initForm()
        this.setBatteryOptions();
        this.setPanelOptions();
        this.loaded = true;
      }
    })
  }

  defaultForm(): ModuleSolarRegistration{
    return {
      battery: {
        marque: '',
        voltage: null,
        puissance: null,
      },
      panel: {
        marque: '',
        voltage: null,
        puissance: null
      },
      othersInfo: []
    }
  }

  addOtherInfo() {

    const modalRef = this.modalService.open(AdditionalInfoComponent,
      {size: "lg", scrollable: true})

    modalRef.componentInstance.dataChange.subscribe((data: ModuleOtherInfoModel)=>{
      this.dataForm.othersInfo.push(data)
    })
  }

  setModuleForm(data: ModuleSolarRegistration) {
    const { battery, panel, othersInfo } = data;

    this.moduleForm = this.formBuilder.group({
      battery: this.formBuilder.group({
        puissance: [battery?.puissance, [Validators.required, numberValidator(0)]],
        marque: [battery?.marque, Validators.required],
        voltage: [battery?.voltage, Validators.required]
      }),
      panel: this.formBuilder.group({
        puissance: [panel?.puissance, [Validators.required, numberValidator(0)]],
        marque: [panel?.marque, Validators.required],
        voltage: [panel?.voltage, Validators.required]
      })
    });
  }

  initForm(data?: ModuleSolarRegistration){
    if (!data){
      this.dataForm = this.defaultForm();
    }else{
      this.dataForm = data;
    }

    this.setModuleForm(this.dataForm)
  }

  setBatteryForm(option: ISelectOption){
    const batteryData: BatteryDistinctModel = option.value
    let batteryForm: FormGroup = this.moduleForm.get('battery') as FormGroup;

    batteryForm.controls['puissance'].setValue(batteryData.puissance)
    batteryForm.controls['marque'].setValue(batteryData.marque)
    batteryForm.controls['voltage'].setValue(batteryData.voltage)
  }

  setBatteryOptions(){
    this.batteryService.getAllDistinct()
      .subscribe((batteries: BatteryDistinctModel[]) =>{
        batteries.forEach(battery => {
          this.batteryOptions.data.push(this.createOption(battery))
        })

        this.batteryOptions.isLoaded();
      })
  }

  public toRegister(detail: ModuleDetailModel): ModuleSolarRegistration{
    return {
      othersInfo: detail.othersInfo,
      panel: {
        puissance: detail.panel.puissance,
        voltage: detail.panel.voltage,
        id: detail.panel.id,
        marque: detail.panel.marque
      },
      battery: {
        puissance: detail.battery.puissance,
        voltage: detail.battery.voltage,
        id: detail.battery.id,
        marque: detail.battery.marque
      }
    }
  }

  initData(){
    if (this.id){
      this.moduleService.getDetail(this.id)
        .subscribe((data: ModuleDetailModel) => {
          this.initForm(this.toRegister(data));
          this.dataForm = data;
          this.title = `Update of module: ${data.reference}`
          this.loaded = true;
        })
    }
  }

  setPanelForm(option: ISelectOption){
    const panelData: PanelDistinctModel = option.value
    let panelForm: FormGroup = this.moduleForm.get('panel') as FormGroup;

    panelForm.controls['puissance'].setValue(panelData.puissance)
    panelForm.controls['marque'].setValue(panelData.marque)
    panelForm.controls['voltage'].setValue(panelData.voltage)
  }

  setPanelOptions(){
    this.panelService.getAllDistinct()
      .subscribe((type: PanelDistinctModel[]) =>{

        type.forEach((type: PanelDistinctModel) => {
          this.panelOptions.data.push(this.createOption(type))
        })

        this.panelOptions.isLoaded();
      })
  }

  createOption(model: BatteryDistinctModel | PanelDistinctModel): ISelectOption{
    return {
      value: model,
      label: `${model.marque} (${model.puissance}W, ${model.voltage}V)`,
      key: model
    }
  }

  setDataForm(){
    const formValue = this.moduleForm.value;

    this.dataForm.battery = formValue.battery;
    this.dataForm.panel = formValue.panel;
  }

  getQrImage(): string{
    return this.id ? `http://localhost:8080/api/solar/modules/${this.id}/qrcode` : ''
  }

  onSubmit(){
    this.moduleForm.markAllAsTouched();

    if (this.moduleForm.valid){
      this.setDataForm();
      this.submitted = true;
      const update: boolean = !!this.id;

      // console.log(update)

      this.moduleService.saveOrUpdate(this.dataForm, this.id, update)
        .subscribe(resp => {
          if (resp.success){
            this.toastService.success(resp.message)
            this.router.navigate(['/user-space/modules/list']).then()
          }else{
            this.toastService.error(resp.message)
            this.submitted = false;
          }
        })
    }
  }
}

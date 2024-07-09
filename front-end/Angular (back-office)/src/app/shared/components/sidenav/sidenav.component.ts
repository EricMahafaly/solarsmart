import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {appSidebarData, navbarData} from "../../../config/nav-data";
import {Router} from "@angular/router";
import {ISidenavGroupItem, ISidenavItem} from "../../../models/core/sidenav";

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.scss']
})
export class SidenavComponent implements OnInit{

  @Input() navGroup !: ISidenavGroupItem[];
  @Output('onClick') navItemActive: EventEmitter<ISidenavItem> = new EventEmitter<ISidenavItem>()
  // navItems: ISidenavItem[] = navbarData

  constructor(public router: Router) {
    if (!this.navGroup) this.navGroup = appSidebarData;
    // else this.navItems = this.navGroup
  }

  ngOnInit(): void {

  }

  inactiveAll(){
    for (let i = 0; i < this.navGroup.length; i++){
      for (let j = 0; j < this.navGroup[i].items.length; j++){
        this.navGroup[i].items[j].active = false;
        // let item = this.navGroup[i].items[j];

      }
    }
  }

  onClickNav(nav: ISidenavItem){
    if (!nav.routerLink){

      this.inactiveAll();
      nav.active = true;
      this.navItemActive.emit(nav)
    }
  }
}

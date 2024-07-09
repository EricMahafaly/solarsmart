import {Component, Input, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import {ITabMenu} from "../../../models/core/itabmenu";
import {ISelectOption} from "../../../models/core/iselect";

@Component({
  selector: 'app-tab-menu',
  templateUrl: './tab-menu.component.html',
  styleUrls: ['./tab-menu.component.scss']
})
export class TabMenuComponent implements OnInit {

  @Input({required: true}) menus : ITabMenu[] = [];
  dMenus : ITabMenu[]  = []

  constructor(private router : Router) { }

  ngOnInit() {
    this.dMenus = [...this.menus];
  }

  isActive(route: string) {
    return this.router.url == route;
  }

}

import {Component, Input} from '@angular/core';
import {animate, state, style, transition, trigger} from "@angular/animations";
import {Router} from "@angular/router";
import {ISidenavItem} from "../../../../models/core/sidenav";

@Component({
  selector: 'app-sub-level-menu',
  templateUrl: './sub-level-menu.component.html',
  styleUrls: ['./sub-level-menu.component.scss'],
  animations: [
    trigger('submenu', [
      state('hidden, void', style({
        height: '0',
        overflow: 'hidden'
      })),
      state('visible', style({
        height: '*'
      })),
      transition('visible <=> hidden', [
        style({ overflow: 'hidden' }),
        animate('400ms cubic-bezier(0.86, 0, 0.07, 1)'),
      ]),
    ]),
  ]
})
export class SubLevelMenuComponent {
  @Input() data: ISidenavItem = {
    routerLink: '',
    icon: '',
    label: '',
    items: []
  }
  @Input() animating: boolean | undefined
  @Input() expanded?: boolean = false
  @Input() multiple: boolean = true

  constructor(public router: Router) {

  }

  handleClick(item: ISidenavItem){
    if (!this.multiple){
      if (this.data.items && this.data.items.length > 0){
        for (let modelItem of this.data.items){
          if (item !== modelItem && modelItem.expanded){
            modelItem.expanded = false;
          }
        }
      }
    }
    item.expanded = !item.expanded
  }

  getActiveClass(data: ISidenavItem): string{
    if ((data.routerLink && this.router.url.includes(data.routerLink)) || data.active)
      return 'active-sublevel'
    // return this.router.url.endsWith(data.routerLink) ? 'active-sublevel' : ''
    return ""
  }
}

import {Component, ElementRef, EventEmitter, HostListener, Input, OnInit, Output} from '@angular/core';
import {ISidenavItem} from "../../../models/core/sidenav";
import {AuthService} from "../../../dashboard/services/authentication/auth.service";
import {Router, RouterLink} from "@angular/router";
import {UserInfo} from "../../../models/api/info.model";

@Component({
  selector: 'app-dropdown',
  templateUrl: './dropdown.component.html',
  styleUrls: ['./dropdown.component.scss']
})
export class DropdownComponent implements OnInit{
  @Input({required: true}) show: boolean = false;
  @Output() isShow: EventEmitter<boolean> = new EventEmitter<boolean>(this.show)
  @Input({required: true}) parent?: HTMLElement;
  @Input({required: true}) data!: UserInfo

  constructor(private el: ElementRef, private auth: AuthService, private router: Router) {

  }

  ngOnInit(): void {
  }

  @HostListener('document:click', ['$event'])
  onClickOutside(event: Event){

    if (!this.el.nativeElement.contains(event.target) && !this.parent?.contains(event.target as Node)){
      this.show = false;
      this.isShow.emit(false)
    }
  }

  signOut(){
    this.auth.signOut()
      .subscribe(resp => {
        this.auth.clearToken();
        this.router.navigate(['/sign-in'])
      })
  }
}

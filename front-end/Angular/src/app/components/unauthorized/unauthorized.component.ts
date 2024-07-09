import { Component } from '@angular/core';
import {RouterLink, RouterLinkActive} from "@angular/router";

@Component({
  selector: 'app-unauthorized',
  templateUrl: './unauthorized.component.html',
  standalone: true,
  imports: [
    RouterLinkActive,
    RouterLink
  ],
  styleUrls: ['./unauthorized.component.scss']
})
export class UnauthorizedComponent {

}

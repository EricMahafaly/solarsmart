import {Component, OnInit} from '@angular/core';
import {ISidenavItem} from "../../../models/core/sidenav";
import {profileNavData} from "../../../config/nav-data";
import {ApiDataModel} from "../../../models/api/base/api-data.model";
import {UserInfo} from "../../../models/api/info.model";
import {UserService} from "../../../dashboard/services/user/user.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit{
  showProfilAction = false;
  isLoading = true;

  protected userInfoData : ApiDataModel<UserInfo> = new ApiDataModel<UserInfo>(null);

  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.setUserInfoData();
  }

  toggleProfilAction(){
    this.showProfilAction = !this.showProfilAction;
  }

  setShow(state: boolean) {
    this.showProfilAction = state;
  }

  setUserInfoData(){
      this.userService.getDetail()
        .subscribe((user: UserInfo)=>{

          this.userInfoData.data = user;
          this.userInfoData.isLoaded();
        })
  }
}

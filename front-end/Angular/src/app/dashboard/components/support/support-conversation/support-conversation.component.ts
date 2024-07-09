import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ReportService} from "../../../services/report/report.service";
import {ApiDataModel} from "../../../../models/api/base/api-data.model";
import {ReportCommentRequest, ReportDetail, SenderType} from "../../../../models/api/report.model";
import {AuthService} from "../../../services/authentication/auth.service";

@Component({
  selector: 'app-support-conversation',
  templateUrl: './support-conversation.component.html',
  styleUrls: ['./support-conversation.component.scss']
})
export class SupportConversationComponent implements OnInit, OnChanges {
  @Input({required: true}) reportId!: number;

  protected comments: ApiDataModel<ReportDetail> = new ApiDataModel<ReportDetail>(null)
  protected commentInput: string = ''

  constructor(
    private reportService: ReportService,
    private authService: AuthService) {
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['reportId'].currentValue !== changes['reportId'].previousValue)  {
      this.setCommentsData();
    }
  }

  ngOnInit(): void {
    this.setCommentsData();
  }

  setCommentsData(){
    this.comments.loading = true;
    this.reportService.getAllCommentsByReport(this.reportId)
      .subscribe(comments => {
        this.comments.data = comments;
        this.comments.isLoaded();
      })
  }



  protected readonly SenderType = SenderType;

  comment() {
    const userInfo = this.authService.getInformation();


    const comment: ReportCommentRequest = {
      senderType: SenderType.TECHNICIAN,
      text: this.commentInput,
      adminId: userInfo.id
    }
    this.reportService.commentReport(this.reportId, comment)
      .subscribe(response => {
        this.setCommentsData();
      })

    this.commentInput = ""
  }
}

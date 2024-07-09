package com.solarsmart.frontoffice.models.dto.response;

import com.solarsmart.frontoffice.models.entities.Customer;
import com.solarsmart.frontoffice.models.entities.Report;
import com.solarsmart.frontoffice.models.entities.ReportComment;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.util.Comparator;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ReportDetail extends ReportListResponse {

    private List<ReportCommentListResponse> comments;

    public ReportDetail(Report report) {
        super(report);

        List<ReportComment> reportComments = report.getComments();

        reportComments.sort(Comparator.comparing(ReportComment::getDate));

        List<ReportCommentListResponse> commentsResponse = reportComments
                .stream()
                .map(ReportCommentListResponse::new)
                .toList();

        this.setComments(commentsResponse);
    }
}

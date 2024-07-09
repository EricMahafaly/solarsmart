package com.solarsmart.frontoffice.models.dto.request;

import com.solarsmart.frontoffice.models.entities.ReportComment;
import com.solarsmart.frontoffice.models.entities.SenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.solarsmart.frontoffice.models.entities.ReportComment}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportCommentRequest implements Serializable {
    private String text;
    private SenderType senderType;
    private Long adminId;

    public ReportComment toModel(){
        ReportComment reportComment = new ReportComment();
        reportComment.setText(this.getText());
        reportComment.setSenderType(senderType);

        return reportComment;
    }
}
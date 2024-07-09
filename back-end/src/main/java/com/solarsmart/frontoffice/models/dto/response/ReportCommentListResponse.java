package com.solarsmart.frontoffice.models.dto.response;

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
public class ReportCommentListResponse implements Serializable {
    private Long id;
    private String text;
    private LocalDateTime date;
    private SenderType senderType;

    public ReportCommentListResponse(ReportComment comment){
        this.setId(comment.getId());
        this.setText(comment.getText());
        this.setDate(comment.getDate());
        this.setSenderType(comment.getSenderType());
    }
}
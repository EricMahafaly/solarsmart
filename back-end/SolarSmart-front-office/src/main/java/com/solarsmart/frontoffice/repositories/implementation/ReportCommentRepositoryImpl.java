package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.ReportComment;
import com.solarsmart.frontoffice.repositories.api.ReportCommentRepository;
import com.solarsmart.frontoffice.repositories.base.BaseRepositoryImpl;
import com.solarsmart.frontoffice.repositories.jpa.JpaReportCommentRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReportCommentRepositoryImpl extends BaseRepositoryImpl<ReportComment, JpaReportCommentRepository> implements ReportCommentRepository {
    public ReportCommentRepositoryImpl(JpaReportCommentRepository repository) {
        super(repository);
    }
}

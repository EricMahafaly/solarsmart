package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.ReportComment;
import com.solarsmart.frontoffice.repositories.api.ReportCommentRepository;
import com.solarsmart.frontoffice.services.api.ReportCommentService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ReportCommentServiceImpl extends BaseServiceImpl<ReportComment, ReportCommentRepository> implements ReportCommentService {
    public ReportCommentServiceImpl(ReportCommentRepository repository) {
        super(repository);
    }
}

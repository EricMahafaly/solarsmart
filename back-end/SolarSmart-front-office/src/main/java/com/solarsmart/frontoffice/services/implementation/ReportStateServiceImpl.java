package com.solarsmart.frontoffice.services.implementation;

import com.solarsmart.frontoffice.models.entities.ReportState;
import com.solarsmart.frontoffice.models.entities.ReportStateEnum;
import com.solarsmart.frontoffice.repositories.api.ReportStateRepository;
import com.solarsmart.frontoffice.services.api.ReportStateService;
import com.solarsmart.frontoffice.services.implementation.base.BaseServiceImpl;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReportStateServiceImpl extends BaseServiceImpl<ReportState, ReportStateRepository> implements ReportStateService {
    public ReportStateServiceImpl(ReportStateRepository repository) {
        super(repository);
    }

    @Override
    public Optional<ReportState> findByState(ReportStateEnum state) {
//        ReportState reportState = new ReportState();
//        reportState.setState(state);
//
//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnoreNullValues()
//                .withIgnoreCase("state")
//                .withIgnorePaths("id");
//
//        Example<ReportState> example = Example.of(reportState, matcher);
        return this.repository.findByState(state);
    }
}

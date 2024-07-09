package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.views.TimeUsageDaily;
import com.solarsmart.frontoffice.repositories.api.PriseTimeUsageRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PriseTimeUsageRepositoryImpl implements PriseTimeUsageRepository {
    @PersistenceContext
    private final EntityManager em;

    private TimeUsageDaily cast(Tuple tuple){
        if (tuple == null) return null;

        Long id = tuple.get("module_id", Long.class);
        Date date = tuple.get("date", Date.class);
        Double duration = tuple.get("duration", Double.class);

        return new TimeUsageDaily(id, date, duration);
    }
    @Override
    public Optional<TimeUsageDaily> getTimeUsageByModuleAndDate(long moduleId, LocalDate date) {

//        Query query = this.em
//                .createNativeQuery("select * from v_battery_time_usage_daily where module_id = :module_id and date = :date", List.class)
//                .setParameter("module_id", moduleId)
//                .setParameter("date", date);
//
//        List<Tuple> tuples = (List<Tuple>) query.getResultList();
//
//        Tuple tuple = !tuples.isEmpty() ? tuples.get(0) : null;

        return Optional.empty();
    }
}

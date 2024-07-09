package com.solarsmart.frontoffice.repositories.implementation;

import com.solarsmart.frontoffice.models.entities.Prise;
import com.solarsmart.frontoffice.models.views.PriseConsommation;
import com.solarsmart.frontoffice.repositories.api.PriseConsommationRepository;
import com.solarsmart.frontoffice.repositories.jpa.JpaPriseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PriseConsommationRepositoryImpl implements PriseConsommationRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    private final JpaPriseRepository priseRepository;

    @Override
    public List<PriseConsommation> findAllByModuleIdAndDateBetween(long moduleId, LocalDate date1, LocalDate date2) {
        Prise prise = this.priseRepository.findByModule_Id(moduleId).orElseThrow();
        String sql = "SELECT * FROM  v_prise_consommation_daily  where date between :startDate AND :endDate " +
                "and id = :priseId";

        List<Tuple> tuples = entityManager.createNativeQuery(sql, Tuple.class)
                .setParameter("startDate", date1)
                .setParameter("endDate", date2)
                .setParameter("priseId", prise.getId())
                .getResultList();

        List<PriseConsommation> priseConsommations = tuples.stream()
                .map(this::cast)
                .toList();

        return priseConsommations;
    }

    @Override
    public List<PriseConsommation> findAllByModuleIdAndMonthAndYear(long moduleId, int month, long year) {

        Prise prise = this.priseRepository.findByModule_Id(moduleId).orElseThrow();

        String sql = "SELECT * FROM  v_prise_consommation_daily where extract(YEAR from date) = :year " +
                "AND extract(MONTH from date) = :month and id = :priseId";

        List<Tuple> tuples =  entityManager.createNativeQuery(sql, Tuple.class)
                .setParameter("year", year)
                .setParameter("month", month)
                .setParameter("priseId", prise.getId())
                .getResultList();

        List<PriseConsommation> priseConsommations = tuples.stream()
                .map(this::cast)
                .toList();

        return priseConsommations;
    }

    private PriseConsommation cast(Tuple tuple){

        Long moduleIdVal = tuple.get("id", Long.class);
        long yearVal = tuple.get("year", Long.class);
        int monthVal = tuple.get("month", Integer.class);
        Date date = tuple.get("date", Date.class);
        double consommation = tuple.get("consommation", Double.class);

        return new PriseConsommation(moduleIdVal, yearVal, monthVal, date, consommation);
    }

    @Override
    public List<PriseConsommation> findAllByModuleIdAndYear(long moduleId, long year) {

        Prise prise = this.priseRepository.findByModule_Id(moduleId).orElseThrow();

        String sql = "SELECT * FROM  v_prise_consommation_daily where extract(YEAR from date) = :year" +
                " and id = :priseId";

        List<Tuple> tuples = entityManager.createNativeQuery(sql, Tuple.class)
                .setParameter("year", year)
                .setParameter("priseId", prise.getId())
                .getResultList();

        List<PriseConsommation> priseConsommations = tuples.stream()
                        .map(this::cast)
                        .toList();

        return priseConsommations;
    }
}

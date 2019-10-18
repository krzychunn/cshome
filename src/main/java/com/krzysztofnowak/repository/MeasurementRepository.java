package com.krzysztofnowak.repository;

import com.krzysztofnowak.entity.Measurement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Krzychu on 29.12.2016.
 */

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Long>{

    List<Measurement> findAll();

    @Query("SELECT m FROM Measurement m WHERE m.timeStamp = (SELECT MAX(timeStamp) FROM Measurement)")
    Measurement findLastMeasurement();

    @Query("SELECT m.id FROM Measurement m ORDER BY m.timeStamp DESC")
    Page<Integer> findLatestId(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Measurement m WHERE m.timeStamp = (SELECT MIN(timeStamp) FROM Measurement)")
    void deleteFirstMeasurement();
}

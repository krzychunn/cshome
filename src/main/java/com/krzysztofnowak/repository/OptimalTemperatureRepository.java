package com.krzysztofnowak.repository;

import com.krzysztofnowak.entity.OptimalTemperature;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Krzychu on 20.01.2017.
 */
public interface OptimalTemperatureRepository extends JpaRepository<OptimalTemperature, Long> {

    @Query("SELECT o FROM OptimalTemperature o WHERE o.timeStamp = (SELECT MAX(timeStamp) FROM OptimalTemperature)")
    OptimalTemperature findLastOptimalTemperature();

    @Query("SELECT o.id FROM OptimalTemperature o ORDER BY o.timeStamp DESC")
    Page<Integer> findLatestId(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM OptimalTemperature o WHERE o.timeStamp = (SELECT MIN(timeStamp) FROM OptimalTemperature)")
    void deleteFirstOptimalTemperature();
}

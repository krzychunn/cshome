package com.krzysztofnowak.repository;

import com.krzysztofnowak.entity.AirCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Krzychu on 09.02.2017.
 */

@Repository
public interface AirConditionRepository extends JpaRepository<AirCondition, Long> {

    @Query("SELECT a FROM AirCondition a WHERE a.timeStamp = (SELECT MAX(timeStamp) FROM AirCondition)")
    AirCondition findLastAirConditionState();

    @Query("SELECT a.id FROM AirCondition a ORDER BY a.timeStamp DESC")
    Page<Integer> findLatestId(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM AirCondition a WHERE a.timeStamp = (SELECT MIN(timeStamp) FROM AirCondition)")
    void deleteFirstAirCondition();
}

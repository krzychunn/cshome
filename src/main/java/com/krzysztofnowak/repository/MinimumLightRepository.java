package com.krzysztofnowak.repository;

import com.krzysztofnowak.entity.MinimumLight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Krzychu on 28.02.2017.
 */
public interface MinimumLightRepository extends JpaRepository<MinimumLight, Long> {

    @Query("SELECT m FROM MinimumLight m WHERE m.timeStamp = (SELECT MAX(timeStamp) FROM MinimumLight)")
    MinimumLight findLastMinimumLight();

    @Query("SELECT m.id FROM MinimumLight m ORDER BY m.timeStamp DESC")
    Page<Integer> findLatestId(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM MinimumLight m WHERE m.timeStamp = (SELECT MIN(timeStamp) FROM MinimumLight)")
    void deleteFirstMinimumLight();
}

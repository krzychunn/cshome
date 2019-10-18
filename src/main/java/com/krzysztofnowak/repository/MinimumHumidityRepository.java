package com.krzysztofnowak.repository;

import com.krzysztofnowak.entity.MinimumHumidity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Krzychu on 28.02.2017.
 */
public interface MinimumHumidityRepository extends JpaRepository<MinimumHumidity, Long> {

    @Query("SELECT m FROM MinimumHumidity m WHERE m.timeStamp = (SELECT MAX(timeStamp) FROM MinimumHumidity)")
    MinimumHumidity findLastMinimumHumidity();

    @Query("SELECT m.id FROM MinimumHumidity m ORDER BY m.timeStamp DESC")
    Page<Integer> findLatestId(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM MinimumHumidity m WHERE m.timeStamp = (SELECT MIN(timeStamp) FROM MinimumHumidity)")
    void deleteFirstMinimumHumidity();
}

package com.krzysztofnowak.repository;

import com.krzysztofnowak.entity.Light;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Krzychu on 02.03.2017.
 */
@Repository
public interface LightRepository extends JpaRepository<Light, Long> {

    @Query("SELECT l FROM Light l WHERE l.timeStamp = (SELECT MAX(timeStamp) FROM Light)")
    Light findLastLightState();

    @Query("SELECT l.id FROM Light l ORDER BY l.timeStamp DESC")
    Page<Integer> findLatestId(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Light l WHERE l.timeStamp = (SELECT MIN(timeStamp) FROM Light)")
    void deleteFirstLight();
}

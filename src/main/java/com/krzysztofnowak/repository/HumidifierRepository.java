package com.krzysztofnowak.repository;

import com.krzysztofnowak.entity.Humidifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Krzychu on 02.03.2017.
 */
public interface HumidifierRepository extends JpaRepository<Humidifier, Long> {

    @Query("SELECT h FROM Humidifier h WHERE h.timeStamp = (SELECT MAX(timeStamp) FROM Humidifier)")
    Humidifier findLastHumidifierState();

    @Query("SELECT h.id FROM Humidifier h ORDER BY h.timeStamp DESC")
    Page<Integer> findLatestId(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Humidifier h WHERE h.timeStamp = (SELECT MIN(timeStamp) FROM Humidifier)")
    void deleteFirstHumidifier();
}

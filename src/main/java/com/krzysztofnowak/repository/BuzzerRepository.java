package com.krzysztofnowak.repository;

import com.krzysztofnowak.entity.Buzzer;
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
 * Created by Krzychu on 30.12.2016.
 */

@Repository
public interface BuzzerRepository extends JpaRepository<Buzzer, Long> {

    @Query("SELECT b FROM Buzzer b WHERE b.timeStamp = (SELECT MAX(timeStamp) FROM Buzzer)")
    Buzzer findLastBuzzerState();

    @Query("SELECT b.id FROM Buzzer b ORDER BY b.timeStamp DESC")
    Page<Integer> findLatestId(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Buzzer b WHERE b.timeStamp = (SELECT MIN(timeStamp) FROM Buzzer)")
    void deleteFirstBuzzer();
}

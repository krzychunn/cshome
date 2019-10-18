package com.krzysztofnowak.repository;

import com.krzysztofnowak.entity.Heating;
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
public interface HeatingRepository extends JpaRepository<Heating, Long> {

    @Query("SELECT h FROM Heating h WHERE h.timeStamp = (SELECT MAX(timeStamp) FROM Heating)")
    Heating findLastHeatingState();

    @Query("SELECT h.id FROM Heating h ORDER BY h.timeStamp DESC")
    Page<Integer> findLatestId(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM Heating h WHERE h.timeStamp = (SELECT MIN(timeStamp) FROM Heating)")
    void deleteFirstHeating();
}

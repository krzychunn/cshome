package com.krzysztofnowak.repository;

import com.krzysztofnowak.entity.MaximumGas;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Krzychu on 31.12.2016.
 */
public interface MaximumGasRepository extends JpaRepository<MaximumGas, Long> {

    @Query("SELECT m FROM MaximumGas m WHERE m.timeStamp = (SELECT MAX(timeStamp) FROM MaximumGas)")
    MaximumGas findLastMaximumGas();

    @Query("SELECT m.id FROM MaximumGas m ORDER BY m.timeStamp DESC")
    Page<Integer> findLatestId(Pageable pageable);

    @Modifying
    @Transactional
    @Query("DELETE FROM MaximumGas m WHERE m.timeStamp = (SELECT MIN(timeStamp) FROM MaximumGas)")
    void deleteFirstMaximumGas();
}

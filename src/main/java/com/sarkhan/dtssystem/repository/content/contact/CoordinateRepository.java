package com.sarkhan.dtssystem.repository.content.contact;

import com.sarkhan.dtssystem.model.content.contact.Coordinate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoordinateRepository extends JpaRepository<Coordinate,Long> {

    @Query("SELECT c FROM Coordinate c ORDER BY c.id DESC")
    List<Coordinate> findLatestCoordinate(Pageable pageable);

}

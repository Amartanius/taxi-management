package fr.bcg.taximanagement.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bcg.taximanagement.entity.Ride;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long>{
	
	Page<Ride> findByDriverContaining(String driver, Pageable pageable);

}

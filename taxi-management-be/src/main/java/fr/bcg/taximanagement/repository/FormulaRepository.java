package fr.bcg.taximanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bcg.taximanagement.entity.Formula;

@Repository
public interface FormulaRepository extends JpaRepository<Formula, Long> {

	Optional<Formula> findByCityId(String cityId);

}

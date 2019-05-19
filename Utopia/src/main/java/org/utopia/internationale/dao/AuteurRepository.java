package org.utopia.internationale.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.utopia.internationale.entities.Auteur;

public interface AuteurRepository extends JpaRepository<Auteur, Long>{
	@Query("select au from Auteur au where au.nom like :x")
	public Page<Auteur> chercher(@Param("x")String mc, Pageable pageable);
}

package com.trader.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trader.platform.entity.TraderDetails;

public interface TraderRepository extends JpaRepository<TraderDetails, Integer> {
	TraderDetails findByEmail(String email);

}

package com.trader.platform.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.trader.platform.entity.TraderDetails;
import com.trader.platform.repository.TraderRepository;

@Service
public class TraderService {
	int traderId = 1;
	TraderRepository traderRepository;

	public TraderService(TraderRepository traderRepository) {
		super();
		this.traderRepository = traderRepository;
	}

	public List<TraderDetails> getAllDetails() {
		List<TraderDetails> traders = traderRepository.findAll();
		return traders;
	}

	public ResponseEntity<Object> registerNewTrader(TraderDetails traderDetails) {
		List<TraderDetails> traders = getAllDetails();
		String email = traderDetails.getEmail();
		traderDetails.setCreatedAt(LocalDateTime.now());
		if (traders.isEmpty()) {
			traderDetails.setId(traderId++);
			TraderDetails saved = traderRepository.save(traderDetails);
			return new ResponseEntity<Object>(saved, HttpStatus.CREATED);
		} else {
			for (TraderDetails traderdetails : traders) {
				System.out.println(traderdetails.getEmail());
				if (traderdetails.getEmail().equalsIgnoreCase(email)) {
					return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
				} else {
					traderDetails.setId(traderId++);
					TraderDetails saved = traderRepository.save(traderDetails);
					return new ResponseEntity<Object>(saved, HttpStatus.CREATED);
				}
			}
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);

	}

	public TraderDetails retriveByEmail(String email) {
		return traderRepository.findByEmail(email);
	}

	public void deleteById(int id) {
		traderRepository.deleteById(id);
	}

	public void updateTrader(TraderDetails traderDetails, int id) {
		traderDetails.setUpdatedAt(LocalDateTime.now());
		traderDetails.setId(id);
		traderRepository.save(traderDetails);
	}

	public void addFund(String email, double balance) {
		TraderDetails traderDetails = retriveByEmail(email);
		double existingBalance = traderDetails.getBalance();
		traderDetails.setBalance(balance + existingBalance);
		int id = traderDetails.getId();
		updateTrader(traderDetails, id);
	}

}

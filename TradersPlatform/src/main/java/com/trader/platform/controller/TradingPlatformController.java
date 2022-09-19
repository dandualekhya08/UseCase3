package com.trader.platform.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trader.platform.entity.TraderDetails;
import com.trader.platform.service.TraderService;

@RestController
public class TradingPlatformController {

	TraderService traderService;

	public TradingPlatformController(TraderService traderService) {
		super();
		this.traderService = traderService;
	}

	@RequestMapping(value = "trading/traders/all", method = RequestMethod.GET)
	public List<TraderDetails> retriveAllDetails() {
		return traderService.getAllDetails();

	}

	@RequestMapping(value = "/trading/traders/register", method = RequestMethod.POST)
	public ResponseEntity<Object> createNewTrader(@RequestBody TraderDetails traderDetails) {
		ResponseEntity<Object> created = traderService.registerNewTrader(traderDetails);
		return created;
	}

	@RequestMapping(value = "/trading/traders/{email}", method = RequestMethod.GET)
	public ResponseEntity<Object> retriveByEmail(@PathVariable String email) {
		TraderDetails trader = traderService.retriveByEmail(email);
		if (trader == null) {
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Object>(trader, HttpStatus.OK);
	}

	@RequestMapping(value = "/trading/traders", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateExistingTrader(@RequestBody TraderDetails traderDetails) {
		String email = traderDetails.getEmail();
		TraderDetails trader = traderService.retriveByEmail(email);
		int id = trader.getId();
		traderService.deleteById(id);
		traderService.updateTrader(traderDetails, id);
		trader = traderService.retriveByEmail(email);
		return new ResponseEntity<Object>(trader, HttpStatus.OK);
	}

	@RequestMapping(value = "/trading/traders/add", method = RequestMethod.PUT)
	public ResponseEntity<Object> addFund(@RequestBody TraderDetails traderdetails) {
		String email = traderdetails.getEmail();
		double balance = traderdetails.getBalance();
		TraderDetails ts = traderService.retriveByEmail(email);
		if (ts == null) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		traderService.addFund(email, balance);
		ts = traderService.retriveByEmail(email);
		return new ResponseEntity<Object>(ts, HttpStatus.OK);
	}

}

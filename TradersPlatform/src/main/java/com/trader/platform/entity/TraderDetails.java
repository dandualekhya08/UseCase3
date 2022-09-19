package com.trader.platform.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TraderDetails {
	@Id // @GeneratedValue
	int id;
	String name;
	String email;
	double balance;
	LocalDateTime createdAt;
	LocalDateTime updatedAt;

	public TraderDetails() {
		super();
	}

	public TraderDetails(int id, String name, String email, double balance, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		super();
		this.id = id++;
		this.name = name;
		this.email = email;
		this.balance = balance;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "TraderDetails [id=" + id + ", name=" + name + ", email=" + email + ", balance=" + balance
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}

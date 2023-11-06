package com.example.demo.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Entity
@Table(name = "claim")
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "claim_Number")
	private Integer claimNumber;
	@OneToOne(fetch = FetchType.EAGER)
	private Customer customer;
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Policy> policies = new HashSet<>();

	public Claim(Integer id, Integer claimNumber, Customer customer, Set<Policy> policies) {
		super();
		this.id = id;
		this.claimNumber = claimNumber;
		this.customer = customer;
		this.policies = policies;
	}

	public Claim() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(Integer claimNumber) {
		this.claimNumber = claimNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Set<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(Set<Policy> policies) {
		this.policies = policies;
	}

	@Override
	public String toString() {
		return "Claim [id=" + id + ", claimNumber=" + claimNumber + ", customer=" + customer + ", policy=" + policies
				+ "]";
	}

}

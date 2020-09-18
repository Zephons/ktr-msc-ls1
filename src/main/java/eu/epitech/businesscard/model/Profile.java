package eu.epitech.businesscard.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7971716588359715446L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long id;
	
	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String name;
	
	private String companyName;
	
	@Column(columnDefinition = "text")
	private String email;
	
	private String phoneNumber;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Card> cards = new ArrayList<Card>();
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	public Profile() {
	}

	public Profile(String username, String name, String companyName, String email, String phoneNumber, List<Card> cards,
			String password) {
		this.username = username;
		this.name = name;
		this.companyName = companyName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.cards = cards;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(Card card) {
		if (card != null) {
			this.cards.add(card);
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

package eu.epitech.businesscard.service;

import java.util.List;

import eu.epitech.businesscard.model.Card;
import eu.epitech.businesscard.model.Profile;

public interface CardService {
	
	public Card createCard(Profile profile, Card card);
	
	public Card addCard(Profile profile, Profile anotherProfile);
	
	public List<Card> cardList();
	
	public void deleteCard(Card card);

}

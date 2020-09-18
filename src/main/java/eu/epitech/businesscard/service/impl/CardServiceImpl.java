package eu.epitech.businesscard.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.epitech.businesscard.model.Card;
import eu.epitech.businesscard.model.Profile;
import eu.epitech.businesscard.repository.CardRepository;
import eu.epitech.businesscard.service.CardService;

@Service
@Transactional
public class CardServiceImpl implements CardService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private CardRepository cardRepository;
	
	@Autowired
	public CardServiceImpl(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}

	@Override
	public Card createCard(Profile profile, Card card) {
		profile.setCards(card);
		LOGGER.info(String.format("New business card added. Contact: %1$s", card.getEmail()));
		return card;
	}
	
	@Override
	public Card addCard(Profile profile, Profile anotherProfile) {
		Card card = new Card();
		card.setName(anotherProfile.getName());
		card.setCompanyName(anotherProfile.getCompanyName());
		card.setEmail(anotherProfile.getEmail());
		card.setPhoneNumber(anotherProfile.getPhoneNumber());
		profile.setCards(card);
		LOGGER.info(String.format("New business card added: %1$s", card.getName()));
		return card;
	}

	@Override
	public List<Card> cardList() {
		return cardRepository.findAll();
	}

	@Override
	public void deleteCard(Card card) {
		cardRepository.delete(card);
	}	
}

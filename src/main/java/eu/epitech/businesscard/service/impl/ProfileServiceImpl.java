package eu.epitech.businesscard.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import eu.epitech.businesscard.model.Profile;
import eu.epitech.businesscard.repository.ProfileRepository;
import eu.epitech.businesscard.service.ProfileService;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private ProfileRepository profileRepository;
	
	@Autowired
	public ProfileServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
			ProfileRepository profileRepository) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.profileRepository = profileRepository;
	}

	@Override
	public Profile createProfile(String username, String password, String name) {
		String encryptedPassword = bCryptPasswordEncoder.encode(password);
		Profile profile = new Profile();
		profile.setPassword(encryptedPassword);
		profile.setUsername(username);
		profileRepository.save(profile);
		LOGGER.info("New user password: " + password);
		return profile;
	}
	
	@Override
	public Profile findByUsername(String username) {
		return profileRepository.findByUsername(username);
	}

	@Override
	public List<Profile> profileList() {
		return profileRepository.findAll();
	}

	@Override
	public void deleteProfile(Profile profile) {
		profileRepository.delete(profile);;
	}
}

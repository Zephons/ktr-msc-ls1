package eu.epitech.businesscard.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import eu.epitech.businesscard.model.Profile;
import eu.epitech.businesscard.service.ProfileService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private ProfileService profileService;

	@Autowired
	public UserDetailsServiceImpl(ProfileService profileService) {
		this.profileService = profileService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Profile profile = profileService.findByUsername(username);
		if (profile == null) {
			throw new UsernameNotFoundException("Username " + username + " was not found.");
		}
		return new User(profile.getUsername(), profile.getPassword(), null);
	}
}

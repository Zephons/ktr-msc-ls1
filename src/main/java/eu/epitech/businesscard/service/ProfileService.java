package eu.epitech.businesscard.service;

import java.util.List;

import eu.epitech.businesscard.model.Profile;

public interface ProfileService {
	
	public Profile createProfile(String username, String password, String name);
	
	public Profile findByUsername(String username);
	
	public List<Profile> profileList();
	
	public void deleteProfile(Profile profile);

}

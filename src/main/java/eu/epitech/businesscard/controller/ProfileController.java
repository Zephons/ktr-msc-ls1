package eu.epitech.businesscard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.epitech.businesscard.model.Profile;
import eu.epitech.businesscard.service.ProfileService;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

	private ProfileService profileService;
	
	@Autowired
	public ProfileController(ProfileService profileService) {
		this.profileService = profileService;
	}

	@GetMapping("/list")
	public ResponseEntity<?> getProfiles() {
		List<Profile> profiles = profileService.profileList();
		if (profiles.isEmpty()) {
			return new ResponseEntity<> ("No profile found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(profiles, HttpStatus.OK);
	}
	
	@GetMapping("/{username}/cards")
	public ResponseEntity<?> getUserCards(@PathVariable String username) {
		Profile profile = profileService.findByUsername(username);
		if (profile == null) {
			return new ResponseEntity<> ("No profile found.", HttpStatus.NOT_FOUND);
		}
		else if (profile.getCards() == null) {
			return new ResponseEntity<> ("This user has saved any business card.", HttpStatus.OK);
		}
		return new ResponseEntity<>(profile.getCards(), HttpStatus.OK);
	}
}

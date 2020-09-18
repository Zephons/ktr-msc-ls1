package eu.epitech.businesscard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import eu.epitech.businesscard.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{

	public Profile findByUsername(String username);
	
}

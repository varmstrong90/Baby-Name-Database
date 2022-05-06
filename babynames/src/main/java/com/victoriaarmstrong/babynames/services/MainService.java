package com.victoriaarmstrong.babynames.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victoriaarmstrong.babynames.models.BabyName;
import com.victoriaarmstrong.babynames.models.User;
import com.victoriaarmstrong.babynames.repositories.BabyNameRepository;

@Service
public class MainService {

	@Autowired
	private BabyNameRepository babyRepo;
	
	@Autowired
	private UserService userService;
	
	// Find all babyNames
	public List<BabyName> allBabyNames(){
		return babyRepo.findAll();
	}
	
	// Find one babyName using id
	public BabyName findOneBabyName(Long id) {
		Optional<BabyName> optionalBabyName = babyRepo.findById(id);
		if(optionalBabyName.isPresent()) {
			return optionalBabyName.get();
		}else {
		
			return null;
		}
	}
	
	// create babyName
	public BabyName createBabyName(BabyName babyName) {
		return babyRepo.save(babyName);
	}
	
	// update babyName
	public BabyName updateBabyName(BabyName babyName) {
		return babyRepo.save(babyName);
	}
	
	// delete babyName using id
	public void deleteBabyName(Long id) {
		babyRepo.deleteById(id);
	}
	
	// like a babyName
	public void likeBabyName(Long babyNameId, Long userId) {
		// 1. get user 
		User user = userService.findOneUser(userId);
		// 2. get babyName
		BabyName babyName = this.findOneBabyName(babyNameId);
		// 3. add user to the babyName
		babyName.getLikedUsers().add(user);
		// 4. save 		
		babyRepo.save(babyName);
	}
	
	public void unlikeBabyName(Long babyNameId, Long userId) {
		// 1. get user 
		User user = userService.findOneUser(userId);
		// 2. get babyName
		BabyName babyName = this.findOneBabyName(babyNameId);
		// 3. remove 
		babyName.getLikedUsers().remove(user);
		// 4. save 		
		babyRepo.save(babyName);
	}
	

}
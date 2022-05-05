package com.example.masteryourself.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.masteryourself.model.ActivityModel;
import com.example.masteryourself.model.ActivityRepository;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository arepository;
	
	public Page<ActivityModel> listAll(int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber - 1, 5);
		
		return arepository.findAll(pageable);
	}
	
}

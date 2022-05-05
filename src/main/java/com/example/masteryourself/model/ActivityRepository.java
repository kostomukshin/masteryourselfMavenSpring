package com.example.masteryourself.model;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;


public interface ActivityRepository extends JpaRepository<ActivityModel, Long> {
	
	List<ActivityModel> findByActivity(String activity);
	List<ActivityModel> findByUser(User user);

}

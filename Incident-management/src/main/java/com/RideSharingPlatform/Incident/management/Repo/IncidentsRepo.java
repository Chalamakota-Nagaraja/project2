package com.RideSharingPlatform.Incident.management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RideSharingPlatform.Incident.management.entities.Incidents;

public interface IncidentsRepo extends JpaRepository<Incidents,String> {

}

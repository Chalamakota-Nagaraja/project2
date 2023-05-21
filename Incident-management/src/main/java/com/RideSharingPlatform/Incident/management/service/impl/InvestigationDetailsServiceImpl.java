package com.RideSharingPlatform.Incident.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RideSharingPlatform.Incident.management.Repo.InvestigationDetailsRepo;
import com.RideSharingPlatform.Incident.management.entities.InvestigationDetails;
import com.RideSharingPlatform.Incident.management.service.InvestigationDetailsService;
@Service
public class InvestigationDetailsServiceImpl implements InvestigationDetailsService {
	@Autowired
InvestigationDetailsRepo investigationDetailsRepo;
	@Override
	public InvestigationDetails addInvestigationReport(InvestigationDetails investigationDetails) {
		// TODO Auto-generated method stub
		return investigationDetailsRepo.save(investigationDetails);
	}
	
}

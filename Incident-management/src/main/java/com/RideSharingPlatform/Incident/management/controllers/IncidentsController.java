package com.RideSharingPlatform.Incident.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.RideSharingPlatform.Incident.management.entities.Incidents;
import com.RideSharingPlatform.Incident.management.entities.InvestigationDetails;
import com.RideSharingPlatform.Incident.management.service.impl.IncidentsServiceImp;
import com.RideSharingPlatform.Incident.management.service.impl.InvestigationDetailsServiceImpl;

import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class IncidentsController {
	@Autowired
	IncidentsServiceImp incSerImp;
	@Autowired
	InvestigationDetailsServiceImpl investigationDetailsServiceImpl;

	@PostMapping("/api/incidents/report")
	String insertReport(@RequestBody Incidents incidents) {
		incSerImp.insertIncident(incidents);
		return "incident added";
	}

	@GetMapping("/api/incidents")

	List<Incidents> getIncidents() {
		return incSerImp.getIncidents();
	}

	@GetMapping("/api/incidents/{id}")
	Incidents getIncidnetsById(@PathVariable String id) {
		return incSerImp.getIncidentsById(id);
	}

	@PutMapping("/api/incidents/{id}/investigationreport")
	String investigationReport(@PathVariable String id, @RequestBody String Jsondata)
			throws JsonMappingException, JsonProcessingException {
		JSONObject jsnobject = new JSONObject(Jsondata);

		JSONObject investigationReportJson = jsnobject.getJSONObject("investigationDetails");
		ObjectMapper objectMapper = new ObjectMapper();

		InvestigationDetails investigationDetails = objectMapper.readValue(investigationReportJson.toString(),
				InvestigationDetails.class);
	
		Incidents incident = incSerImp.getIncidentsById(id);
		incident.setStatus("closed");
		incSerImp.updateIncidentById(incident);
		investigationDetails.setIncidents(incident);
		investigationDetailsServiceImpl.addInvestigationReport(investigationDetails);
		return "updated" + id;
	}
}

package com.phorest.salon.clientservices.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phorest.salon.clientservices.constants.PhorestConstants;
import com.phorest.salon.clientservices.dto.LoyalClientDTO;
import com.phorest.salon.clientservices.jpa.client.Appointments;
import com.phorest.salon.clientservices.jpa.client.Client;
import com.phorest.salon.clientservices.jpa.client.Purchases;
import com.phorest.salon.clientservices.jpa.client.Services;
import com.phorest.salon.clientservices.jpa.repository.AppointmentRepository;

@Service
public class PhorestClientServices {


	@Autowired
	private AppointmentRepository appointmentrepo;


	DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern(PhorestConstants.DATEFORMAT);

	public List<LoyalClientDTO> getLoyaltyClientByDate(String date) {
		List<Appointments> apppointmentsList = appointmentrepo
				.findAllAppointMentsByDate(LocalDateTime.parse(date + " 00:00:00 +0000", dateformatter));

		HashMap<String, Integer> map = new HashMap<>();
		List<LoyalClientDTO> loyalClientList = new ArrayList<>();
		for (Appointments appointments : apppointmentsList) {
			LoyalClientDTO dto = new LoyalClientDTO();
			Integer points = 0;

			Set<Purchases> purchases = appointments.getPurchases();
			for (Purchases p : purchases) {
				points = points + p.getLoyalty_points();
			}

			Set<Services> services = appointments.getServices();
			for (Services s : services) {
				points = points + s.getLoyalty_points();
			}

			Client client = appointments.getClient();
			String clientId = client.getId();

			if (map.get(clientId) == null) {
				map.put(clientId, points);
			} else {
				map.put(clientId, map.get(clientId) + points);
			}

			dto.setFirst_name(client.getFirst_name());
			dto.setLast_name(client.getLast_name());
			dto.setEmail(client.getEmail());
			dto.setGender(client.getGender());
			dto.setPhone(client.getPhone());
			dto.setLoyalty_points(map.get(clientId));

			loyalClientList.add(dto);
		}

		Collections.sort(loyalClientList);
		return loyalClientList;
	}
}

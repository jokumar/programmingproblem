package com.phorest.salon.clientservices.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.phorest.salon.clientservices.constants.PhorestConstants;
import com.phorest.salon.clientservices.jpa.client.Appointments;
import com.phorest.salon.clientservices.jpa.repository.AppointmentRepository;
import com.phorest.salon.clientservices.jpa.repository.ClientRepository;

import antlr.StringUtils;

@Component
public class PhorestAppointmentCSVProcessor implements PhorestCSVProcessor {

	@Autowired
	private ClientRepository clientrepo;

	@Autowired
	private AppointmentRepository appointmentrepo;

	private DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern(PhorestConstants.DATEFORMAT);

	@Override
	public Boolean processCSV(MultipartFile file) {
		BufferedReader br1;
		List<Appointments> listOfAppointments = new ArrayList<>();
		try {

			int count = 0;
			String line;
			InputStream is = file.getInputStream();
			br1 = new BufferedReader(new InputStreamReader(is));
			while ((line = br1.readLine()) != null) {
				if (count >= 1) {
					String[] arrayofAppointments = line.split(",");
					Appointments appointments = new Appointments();
					appointments.setId(arrayofAppointments[0]);

					appointments.setClient(clientrepo.findById(arrayofAppointments[1]).get());

					appointments.setStart_time(LocalDateTime.parse(arrayofAppointments[2], dateformatter));

					appointments.setEnd_time(LocalDateTime.parse(arrayofAppointments[3], dateformatter));

					listOfAppointments.add(appointments);

				}
				count++;
			}
			List<Appointments> savedAppoitments = appointmentrepo.saveAll(listOfAppointments);

			if (savedAppoitments != null && savedAppoitments.size() > 0)
				return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}

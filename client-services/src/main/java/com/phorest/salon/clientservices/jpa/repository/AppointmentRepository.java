/**
 * 
 */
package com.phorest.salon.clientservices.jpa.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.phorest.salon.clientservices.jpa.client.Appointments;

/**
 * @author elma
 *
 */
@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, String> {

	@Query("from Appointments as app join app.client as c where :time < app.start_time and c.banned = false")   
	public List<Appointments> findAllAppointMentsByDate(@Param("time") LocalDateTime time);
}

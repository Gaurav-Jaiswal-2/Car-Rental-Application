package com.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.entities.Reservation;
import com.application.sevices.ReservationService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;

	@PostMapping
	public ResponseEntity<Reservation> add(@RequestBody Reservation reservation) {
		Reservation savedReservation = reservationService.save(reservation);
		return ResponseEntity.ok(savedReservation);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Reservation> update(@PathVariable Integer id, @RequestBody Reservation reservation) {
		Reservation updatedReservation = reservationService.update(id, reservation);
		return ResponseEntity.ok(updatedReservation);
	}

	@GetMapping("/")
	public ResponseEntity<List<Reservation>> get() {
		List<Reservation> reservations = reservationService.get();
		return ResponseEntity.ok(reservations);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Reservation> get(@PathVariable Integer id) {
		Reservation reservation = reservationService.get(id);
		return ResponseEntity.ok(reservation);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
		Boolean deleted = reservationService.delete(id);
		return ResponseEntity.ok(deleted);
	}
}
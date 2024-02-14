package com.application.sevices;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.application.entities.Reservation;
import com.application.exception.DetailsNotFoundException;

@Service
public class ReservationService {

	private final String filePath = "Reservation.ser";

	public Reservation save(Reservation reservation) {

		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
			outputStream.writeObject(reservation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reservation;
	}

	public Reservation update(Integer id, Reservation reservation) {
		List<Reservation> reservations = get();
		for (int i = 0; i < reservations.size(); i++) {
			if (reservations.get(i).getId().equals(id)) {
				reservations.set(i, reservation);
				break;
			}
		}
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			for (Reservation res : reservations) {
				outputStream.writeObject(reservation);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return reservation;
	}

	public List<Reservation> get() {
		List<Reservation> reservations = new ArrayList<>();
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			while (true) {
				try {
					Reservation reservation = (Reservation) inputStream.readObject();
					reservations.add(reservation);

				} catch (EOFException e) {
					break;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return reservations;
	}

	public Reservation get(Integer id) {
		List<Reservation> reservations = get();
		System.out.println(reservations.size());
		for (Reservation reservation : reservations) {
			if (reservation.getId().equals(id)) {
				return reservation;
			}
		}
		throw new DetailsNotFoundException("Reservation is not found with ID : " + id);
	}

	public Boolean delete(Integer id) {
		List<Reservation> reservations = get();
		boolean reservationExists = reservations.stream()
				.anyMatch(reservation -> Objects.equals(reservation.getId(), id));
		if (!reservationExists) {
			throw new DetailsNotFoundException("Reservation not found with ID: " + id);
		}
		reservations.removeIf(reservation -> reservation.getId().equals(id));
		saveReservations(reservations);
		return true;
	}

	private void saveReservations(List<Reservation> reservations) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			for (Reservation reservation : reservations) {
				outputStream.writeObject(reservation);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

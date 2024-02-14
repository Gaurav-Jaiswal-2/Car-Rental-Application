package com.application.sevices;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.application.entities.Car;
import com.application.exception.DetailsNotFoundException;

@Service
public class CarService {

	private final String filePath = "Car.ser";

	public Car save(Car car) {
		File file = new File(filePath);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath, true))) {
				outputStream.writeObject(car);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return car;
	}

	public Car update(Integer id, Car updatedCar) {
		List<Car> cars = get();
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getId().equals(id)) {
				cars.set(i, updatedCar);
				break;
			}
		}
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			for (Car car : cars) {
				outputStream.writeObject(car);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return updatedCar;
	}

	// Retrieves a list of all cars from the file.
	public List<Car> get() {
		List<Car> cars = new ArrayList<>();
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
			while (true) {
				try {
					Car car = (Car) inputStream.readObject();
					cars.add(car);
				} catch (EOFException e) {
					break;
				}
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cars;
	}

	public Car get(Integer id) {
		List<Car> cars = get();
		for (Car car : cars) {
			if (car.getId().equals(id)) {
				return car;
			}
		}
		throw new DetailsNotFoundException("Car is not found with ID : " + id);
	}

	public Boolean delete(Integer id) {
		List<Car> cars = get();
		boolean carExists = cars.stream().anyMatch(car -> Objects.equals(car.getId(), id));
		if (!carExists) {
			throw new DetailsNotFoundException("Car not found with ID: " + id);
		}
		cars.removeIf(car -> car.getId().equals(id));
		saveCars(cars);
		return true;
	}

	private void saveCars(List<Car> cars) {
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
			for (Car car : cars) {
				outputStream.writeObject(car);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
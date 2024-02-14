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

import com.application.entities.Car;
import com.application.sevices.CarService;

@RestController
@RequestMapping("/car")
public class CarController {

	@Autowired
	private CarService carService;

	@PostMapping
	public ResponseEntity<Car> add(@RequestBody Car car) {
		Car savedCar = carService.save(car);
		return ResponseEntity.ok(savedCar);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Car> updateCar(@PathVariable Integer id, @RequestBody Car car) {
		Car updatedCar = carService.update(id, car);
		return ResponseEntity.ok(updatedCar);
	}

	@GetMapping
	public ResponseEntity<List<Car>> get() {
		List<Car> cars = carService.get();
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Car> get(@PathVariable Integer id) {
		Car car = carService.get(id);
		return ResponseEntity.ok(car);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
		Boolean deleted = carService.delete(id);
		return ResponseEntity.ok(deleted);
	}
}

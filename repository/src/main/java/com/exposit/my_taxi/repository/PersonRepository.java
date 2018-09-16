package com.exposit.my_taxi.repository;

import com.exposit.my_taxi.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
//    Person findById(Long id);
}

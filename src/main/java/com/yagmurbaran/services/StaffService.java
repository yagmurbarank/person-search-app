package com.yagmurbaran.services;

import com.yagmurbaran.entity.Staff;
import com.yagmurbaran.exception.DatabaseAccessException;
import com.yagmurbaran.exception.ServiceException;
import com.yagmurbaran.repository.StaffRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    private final StaffRepository repository;

    public StaffService(StaffRepository repository) {
        this.repository = repository;
        addDummyData();
    }

    public Optional<Staff> get(Long id) {
        try {
            return repository.findById(id);
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Error accessing database while retrieving staff with id: " + id, e);
        }
    }

    public Page<Staff> list(Pageable pageable) {
        try {
            return repository.findAll(pageable);
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Error accessing database while retrieving staff list.", e);
        } catch (Exception e) {
            throw new ServiceException("Unexpected error retrieving staff list.", e);
        }
    }

    public Page<Staff> list(Pageable pageable, Specification<Staff> filter) {
        try {
            return repository.findAll(filter, pageable);
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Error accessing database while retrieving filtered staff list.", e);
        } catch (Exception e) {
            throw new ServiceException("Unexpected error retrieving filtered staff list.", e);
        }
    }


    private void addDummyData() {
        try {
            if (repository.count() == 0) { // Prevent duplicate entries
                List<Staff> dummyPersons = List.of(
                        createPerson("12345678901", "John", "Smith"),
                        createPerson("23456789012", "Emily", "Brown"),
                        createPerson("34567890123", "Michael", "Davis"),
                        createPerson("45678901234", "Jessica", "Wilson"),
                        createPerson("56789012345", "David", "Garcia"),
                        createPerson("67890123456", "Ashley", "Rodriguez"),
                        createPerson("78901234567", "Christopher", "Martinez"),
                        createPerson("89012345678", "Brittany", "Anderson"),
                        createPerson("90123456789", "Daniel", "Taylor"),
                        createPerson("01234567890", "Amanda", "Moore")
                );
                repository.saveAll(dummyPersons);
            }
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Error accessing database while adding dummy data.", e);
        } catch (Exception e) {
            throw new ServiceException("Unexpected error adding dummy data to the repository.", e);
        }
    }

    private Staff createPerson(String identityNumber, String firstName, String lastName) {
        Staff staff = new Staff();
        staff.setFirstName(firstName);
        staff.setLastName(lastName);
        return staff;
    }
}


package com.yagmurbaran.service;

import com.yagmurbaran.model.Staff;
import com.yagmurbaran.exception.DatabaseAccessException;
import com.yagmurbaran.exception.ServiceException;
import com.yagmurbaran.repository.StaffRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StaffService {

    private final StaffRepository repository;

    public StaffService(StaffRepository repository) {
        this.repository = repository;
        addDummyData();
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
                        createPerson("23456789013", "Emily", "Brown"),
                        createPerson("34567890123", "Michael", "Davis"),
                        createPerson("45678901235", "Jessica", "Wilson"),
                        createPerson("56789012345", "David", "Garcia"),
                        createPerson("67890123457", "Ashley", "Rodriguez"),
                        createPerson("78901234567", "Christopher", "Martinez"),
                        createPerson("89012345679", "Brittany", "Anderson"),
                        createPerson("90123456789", "Daniel", "Taylor"),
                        createPerson("01234567891", "Amanda", "Moore")
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

    public void addStaff(String identityNumber, String firstName, String lastName) {
        try {
            Staff newStaff = createPerson(identityNumber, firstName, lastName);
            repository.save(newStaff); // Save the new staff person
        } catch (DataAccessException e) {
            throw new DatabaseAccessException("Error accessing database while adding new staff.", e);
        } catch (Exception e) {
            throw new ServiceException("Unexpected error adding new staff.", e);
        }
    }
    public void deleteStaff(Staff staff) {
        repository.delete(staff);
    }
}

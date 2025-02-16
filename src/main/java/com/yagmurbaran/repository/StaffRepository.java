package com.yagmurbaran.repository;

import com.yagmurbaran.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository
        extends
        JpaRepository<Staff, Long>,
        JpaSpecificationExecutor<Staff> {

}

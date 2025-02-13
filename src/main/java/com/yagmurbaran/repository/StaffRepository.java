package com.yagmurbaran.repository;

import com.yagmurbaran.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StaffRepository
        extends
        JpaRepository<Staff, Long>,
        JpaSpecificationExecutor<Staff> {

}

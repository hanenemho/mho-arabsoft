package org.sid.catservice.dao;

import java.util.List;
import java.util.Optional;

import org.sid.catservice.entities.GroupeTaxation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupeTaxationDao  extends JpaRepository<GroupeTaxation, Long>{
     
}
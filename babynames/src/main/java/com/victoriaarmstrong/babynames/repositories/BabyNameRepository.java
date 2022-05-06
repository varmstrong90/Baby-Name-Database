package com.victoriaarmstrong.babynames.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.victoriaarmstrong.babynames.models.BabyName;

@Repository
public interface BabyNameRepository extends CrudRepository<BabyName, Long> {
	List<BabyName> findAll();


}

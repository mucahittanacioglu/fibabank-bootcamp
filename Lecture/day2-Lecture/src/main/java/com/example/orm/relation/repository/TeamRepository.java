package com.example.orm.relation.repository;


import com.example.orm.relation.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team,Long> {

}

package com.example.orm.relation.repository;


import com.example.orm.relation.entity.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player,Long> {
        @Query("select p from Player p where p.team.teamId = :teamId ")
        List<Player> findAllByTeamId(@Param("teamId")long teamId);
}
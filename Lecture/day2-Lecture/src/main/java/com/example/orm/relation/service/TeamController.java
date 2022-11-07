package com.example.orm.relation.service;

import com.example.orm.relation.entity.Player;
import com.example.orm.relation.entity.Team;
import com.example.orm.relation.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/sports")
public class TeamController {
    @Autowired
    private TeamRepository teamRepository;

    @GetMapping("team/insert")
    @ResponseBody
    public String insertTeam(){
        Team teamA = new Team(0,"Fenerbahçe");
        teamA.setPlayerList(new ArrayList<>());

        Player player1 = new Player(0,"Ali Ucar",45);
        player1.setTeam(teamA);
        teamA.getPlayerList().add(player1);

        Player player2 = new Player(0,"Vehbi Turan",25.4);
        player2.setTeam(teamA);
        teamA.getPlayerList().add(player2);

        Player player3 = new Player(0,"Mehmet Şimşek",33.2);
        player3.setTeam(teamA);
        teamA.getPlayerList().add(player3);

        teamRepository.save(teamA);
        return "Team Added: ";
    }
}

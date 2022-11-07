package com.example.orm.relation.service;

import com.example.orm.relation.entity.Player;
import com.example.orm.relation.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sports")
public class PlayerController {
    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/players/byteam")
    @ResponseBody
    public String getPlayersByTeam(){
        long teamId = 1;
        List<Player> players = playerRepository.findAllByTeamId(teamId);
        int total_score=0;
        for(Player p :players){
            total_score+=p.getAverageScore();
        }

        return ""+total_score/players.size();
    }
}

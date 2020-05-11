package com.seeker.apimuca;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seeker.entities.Personagem;
import com.seeker.utils.UtilsPerson;

@RestController
@RequestMapping(value = "/muinfo")
public class MuInfo {

	@GetMapping(value = "/{guild}")
	public ResponseEntity<List<Personagem>> findAll(@PathVariable String guild){
		List<Personagem> list = UtilsPerson.getCharsGuild(guild);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{guild}/{nick}")
	public ResponseEntity<Personagem> findById(@PathVariable String guild, @PathVariable String nick){
		List<Personagem> list = UtilsPerson.getCharsGuild(guild);
		Personagem obj = UtilsPerson.findPersonagem(list, nick);
		return ResponseEntity.ok().body(obj);
	}
}

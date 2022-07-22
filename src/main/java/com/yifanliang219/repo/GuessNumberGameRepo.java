package com.yifanliang219.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yifanliang219.entity.Game;

@Repository
public interface GuessNumberGameRepo extends CrudRepository<Game, Integer> {

	@Query("SELECT COUNT(r) FROM Round r WHERE r.gameId = :gameId")
	int getNumberOfRoundCompletedOfGame(@Param("gameId") int gameId);
	
}

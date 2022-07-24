package com.yifanliang219.repo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yifanliang219.entity.Game;
import com.yifanliang219.entity.Round;

@Repository
public interface GuessNumberGameRepo extends CrudRepository<Game, Integer> {

	@Query("SELECT COUNT(r) FROM Round r WHERE r.roundId.gameId = :gameId")
	int getNumberOfRoundCompletedOfGame(@Param("gameId") int gameId);
	
	@Query("Select r FROM Round r WHERE r.roundId.gameId = :gameId")
	List<Round> getRoundsOfGame(@Param("gameId") int gameId, Sort sort);
}

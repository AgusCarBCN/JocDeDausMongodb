package carnerero.agustin.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import carnerero.agustin.model.Game;
import carnerero.agustin.model.Player;


@Repository
public interface GameRepository extends MongoRepository<Game, Long> {
	public List<Game> findByPlayer(Player player);
	public int countByPlayer(Player player);
	public List<Game> findAllByPlayer(Player player);
	
}

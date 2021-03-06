package carnerero.agustin.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import carnerero.agustin.entity.Game;
import carnerero.agustin.entity.Player;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class JocDeDausMongodbApplicationTests {

	@Autowired
	JocDeDausService service;

	@Test
	@Order(1)
	public void createPlayerTest() {
		Player player = new Player("player");
		Player playerCreated = service.createPlayer(player);
		assertNotNull(playerCreated);
	}

	@Test
	@Order(2)
	public void getAllPlayersTest() {
		List<Player> players = service.getPlayers();
		assertThat(players).size().isGreaterThan(0);
	}

	@Test
	@Order(3)
	public void getOnePlayer() {
		List<Player> players = service.getPlayers();
		long id = players.get(0).getId();
		Player player = service.getPlayer(id);
		assertNotNull(player);
	}

	@Test
	@Order(4)
	public void createGame() {
		List<Player> players = service.getPlayers();
		long id = players.get(0).getId();
		Game newGame = new Game();
		service.createGame(id, newGame);
		assertNotNull(newGame);
	}

	@Test
	@Order(5)
	public void upDatePlayerName() {
		List<Player> players = service.getPlayers();
		Player player = players.get(0);
		String nameBefore = player.getName();
		player.setName("nuevoNombre");
		service.updatePlayerName(player);
		assertNotEquals(nameBefore, player.getName());
	}

	@Test
	@Order(6)
	public void getGamesByPlayer() {
		List<Player> players = service.getPlayers();
		long id = players.get(0).getId();
		List<Game> games = service.getGamesByPlayer(id);
		assertThat(games).size().isGreaterThan(0);
	}

	@Test
	@Order(7)
	public void deleteGamesByPlayer() {
		List<Player> players = service.getPlayers();
		long id = players.get(0).getId();
		List<Game> games = service.getGamesByPlayer(id);
		service.deleteGamesByPlayer(id);
		games = service.getGamesByPlayer(id);
		assertThat(games).size().isEqualTo(0);
	}

	@Test
	@Order(8)
	public void averagePlayer() {
		double average = service.averageRanking();
		assertThat(average).isGreaterThanOrEqualTo(0);
	}

	@Test
	@Order(9)
	public void getTheBestPlayer() {
		List<Player> players = service.theBestPlayer();
		assertNotNull(players);
	}

	@Test
	@Order(10)
	public void getTheWorstPlayer() {
		List<Player> players = service.theWorstPlayer();
		assertNotNull(players);
	}

}

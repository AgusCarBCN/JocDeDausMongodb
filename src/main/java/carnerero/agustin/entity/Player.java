package carnerero.agustin.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "players")
public class Player {

	@Id
	private Long id;
	private String name;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dateReg;

	private int winGames;
	private int lostGames;
	private double average;
	private int totalGames;

	{
		Random rnd = new Random();
		id = rnd.nextLong(10000);
		name = "ANONYMOUS";
	}
	@DBRef(lazy = true)
	private List<Game> games;

	public Player(String name) {

		dateReg = new Date();
		games = new ArrayList<>();
		this.name = name;

	}

	public void addGame(Game game) {
		games.add(game);
	}

	public void setAverage(double average) {
		this.average = average;

	}

	public Game plays() {
		return new Game();
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

}

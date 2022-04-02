package carnerero.agustin.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
	private String name ;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date date;
	private int winGames;
	private int lostGames;
	private	double average;
	private int totalGames;
	private static int counter;
	@DBRef(lazy = true)
	private List<Game> games;
	{
		id=(long) counter++;		
		name = "ANONIM";
		date = new Date();
		games=new ArrayList<>();
	}
	static {
		counter=1;
	}
	public Player(String name) {
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

}

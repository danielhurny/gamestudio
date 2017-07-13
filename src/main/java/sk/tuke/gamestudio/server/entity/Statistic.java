package sk.tuke.gamestudio.server.entity;

import java.util.List;

public class Statistic {
	int bestScore;
	List<String> bestPlayers;
	int gamesPlayed;

	public Statistic(){
		
	}

	public int getBestScore() {
		return bestScore;
	}

	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}

	public String getBestPlayers() {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<bestPlayers.size();i++){
			sb.append(bestPlayers.get(i)+"\n");
		}
		return sb.toString();
		
	}

	public void setBestPlayers(List<String> bestPlayers) {
		this.bestPlayers = bestPlayers;
	}

	public int getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(int gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}
	
	
	
}

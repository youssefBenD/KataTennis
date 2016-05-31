package model;

public class Player {

	private String firstName;
	private String lastName;
	private String Country;
	private int age;
	private int gameScore;
	private int nbrGameWon;

	public Player() {
		super();
	}

	public Player(String firstName, String lastName, String country, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		Country = country;
		this.age = age;
		this.gameScore = 0;
		this.nbrGameWon = 0;
	}
	
	public void winPoint(){
		this.gameScore++;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGameScore() {
		return gameScore;
	}

	public void setGameScore(int gameScore) {
		this.gameScore = gameScore;
	}

	public int getNbrGameWon() {
		return nbrGameWon;
	}

	public void setNbrGameWon(int nbrGameWon) {
		this.nbrGameWon = nbrGameWon;
	}

	@Override
	public String toString() {
		return "Player [firstName=" + firstName + ", lastName=" + lastName + ", Country=" + Country + ", age=" + age
				+ ", gameScore=" + gameScore + ", nbrGameWon=" + nbrGameWon + "]";
	}

}

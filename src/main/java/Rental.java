import movie.Movie;

public class Rental
{
	public Rental (Movie movie, int daysRented) {
		this.movie 		= movie;
		this.daysRented = daysRented;
	}
	
	public int getDaysRented () {
		return daysRented;
	}
	
	public Movie getMovie () {
		return movie;
	}
	
	Movie movie;
	private int daysRented;

	double amount()
	{
		return movie.amountfor(daysRented);
	}

}
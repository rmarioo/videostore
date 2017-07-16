import junit.framework.TestCase;
import org.junit.Assert;

import static org.hamcrest.core.Is.is;

public class VideoStoreTest extends TestCase
{
	public VideoStoreTest (String name) {
		super (name);
	}
	
	protected void setUp ()  {
		statement = new Statement("Fred");
	}
	
	public void testSingleNewReleaseStatement () {
		statement.addRental (new Rental (new Movie ("The Cell", Movie.NEW_RELEASE), 3));
		statement.create();
		Assert.assertThat(statement.totalAmount(),is(9d));
		Assert.assertThat(statement.frequentFliersPoints(),is(2));
	}

	public void testDualNewReleaseStatement () {
		statement.addRental (new Rental (new Movie ("The Cell", Movie.NEW_RELEASE), 3));
		statement.addRental (new Rental (new Movie ("The Tigger Movie", Movie.NEW_RELEASE), 3));

		statement.create();

		Assert.assertThat(statement.totalAmount(),is(18d));
		Assert.assertThat(statement.frequentFliersPoints(),is(4));
	}

	public void testSingleChildrensStatement () {
		statement.addRental (new Rental (new Movie ("The Tigger Movie", Movie.CHILDRENS), 3));
		statement.create();

		Assert.assertThat(statement.totalAmount(),is(1.5d));
		Assert.assertThat(statement.frequentFliersPoints(),is(1));
	}
	
	public void testMultipleRegularStatement () {
		statement.addRental (new Rental (new Movie ("Plan 9 from Outer Space", Movie.REGULAR), 1));
		statement.addRental (new Rental (new Movie ("8 1/2", Movie.REGULAR), 2));
		statement.addRental (new Rental (new Movie ("Eraserhead", Movie.REGULAR), 3));
		
		assertEquals ("Rental Record for Fred\n" +
			"\tPlan 9 from Outer Space\t2.0\n" +
			"\t8 1/2\t2.0\n" +
			"\tEraserhead\t3.5\n" +
			"You owed 7.5\n" +
			"You earned 3 frequent renter points\n", statement.create());
	}

	private Statement statement;
}
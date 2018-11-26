import java.io.Serializable;
import java.util.Comparator;

public class Book implements Serializable {

	public String author;

	public String title;

	public String[] genres;

	public String[] allStrings;

	private static final long serialVersionUID = 53;

	public Book() {

		initVariables();

	}

	public Book(String[] bookStrings) {

		initVariables();

		String[] tempStrings;
		tempStrings = new String[12];
		for (int i = 0; i < tempStrings.length; i++) {
			tempStrings[i] = "Blank";
		}

		tempStrings = bookStrings;
		for (int i = 0; i < allStrings.length; i++) {
			allStrings[i] = tempStrings[i];
		}

		for (int i = 0; i < allStrings.length; i++) {
			if (i == 0)
				title = allStrings[i];

			if (i == 1)
				author = allStrings[i];

			if (i > 1)
				genres[i - 2] = allStrings[i];
		}

	}

	public void setAuthor(String newAuthor) {

		author = newAuthor;
	}

	public void setTitle(String newTitle) {

		title = newTitle;
	}

	public void setGenres(String[] newGenres) {
		String[] settingGenres;
		settingGenres = new String[10];

		for (int i = 0; i < 10; i++) {
			settingGenres[i] = "Blank";
		}
		settingGenres = newGenres;
		for (int i = 0; i < settingGenres.length; i++) {
			genres[i] = settingGenres[i];
		}

	}

	public String getAuthor() {

		return author;
	}

	public String getTitle() {

		return title;
	}

	public String[] getGenres() {

		return genres;
	}

	public String allInOne() {
		String allTheStrings;

		allTheStrings = "   " + title + "  " + author + "  " + genres[0] + "  " + genres[1] + "  " + genres[2] + "  "
				+ genres[3] + "  " + genres[4] + "  " + genres[5] + "  " + genres[6] + "  " + genres[7] + "  "
				+ genres[8] + "  " + genres[9] + "  ";

		return allTheStrings;
	}

	public void initVariables() {
		title = "Blank";
		author = "Blank";

		genres = new String[10];
		allStrings = new String[12];

		for (int i = 0; i < 10; i++) {
			genres[i] = "Blank";
		}

		for (int i = 0; i < allStrings.length; i++) {
			allStrings[i] = "Blank";
		}
	}

	public static Comparator<Book> AuthorComparator = new Comparator<Book>() {
		public int compare(Book b1, Book b2) {

			String bookAuthName1 = b1.getAuthor().toLowerCase();
			String bookAuthName2 = b2.getAuthor().toLowerCase();

			return bookAuthName1.compareTo(bookAuthName2);
		}
	};

	public static Comparator<Book> TitleComparator = new Comparator<Book>() {
		public int compare(Book b1, Book b2) {

			String bookTitleName1 = b1.getTitle().toLowerCase();
			String bookTitleName2 = b2.getTitle().toLowerCase();

			return bookTitleName1.compareTo(bookTitleName2);
		}
	};

}
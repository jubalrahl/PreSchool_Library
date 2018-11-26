
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.HashSet;
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class LibraryDisplay extends JFrame {

	public List<Book> mainBooks;
	public List<Book> displayBooks;
	public List<String> unwantedWords;

	public AddBookPanel addBookPanel;
	public EditBookPanel editBookPanel;
	public SearchPanel searchPanel;
	public JPanel twoPanelHolder;
	public JPanel displayListPanel;
	public JScrollPane scrollListPanel;

	public URL imgURL;
	public ImageIcon startUpPic;
	public BufferedImage welcome;
	public JLabel testingPic;

	public LibraryDisplay() {

		// Initialize variables
		initvari();
		initActList();

	}

	private void initActList() {

		// Add Book Listener
		addBookPanel.addBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent d) {

				addingBooks();

			}
		});

		// Search Author ActionListener
		searchPanel.searchAuthorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent b) {

				boolean checkEmpty = false;
				String tempAuth = searchPanel.searchTextField.getText();

				if (searchPanel.searchTextField.getText().trim().isEmpty()
						|| searchPanel.searchTextField.getText() == null)
					checkEmpty = true;

				if (checkEmpty == false)
					searchAuthorList(tempAuth);

				updateDisplayPanel(displayBooks);

			}
		});

		// Search Title ActionListener
		searchPanel.searchTitleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				boolean checkEmpty = false;
				String tempTitle = searchPanel.searchTextField.getText();
				if (searchPanel.searchTextField.getText().trim().isEmpty()
						|| searchPanel.searchTextField.getText() == null)
					checkEmpty = true;

				if (checkEmpty == false)
					searchTitleList(tempTitle);

				updateDisplayPanel(displayBooks);

				searchPanel.searchTextField.setText("");
			}
		});

		// Search Genre ActionLister
		searchPanel.searchGenreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {
				boolean checkEmpty = false;
				String tempGenres = searchPanel.searchTextField.getText();

				if (searchPanel.searchTextField.getText().trim().isEmpty()
						|| searchPanel.searchTextField.getText() == null)
					checkEmpty = true;

				if (checkEmpty == false)
					searchGenresList(tempGenres);

				updateDisplayPanel(displayBooks);
				searchPanel.searchTextField.setText("");

			}
		});

		// ShowAll ActionLister
		searchPanel.showAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {

				getLibrary();

				updateDisplayPanel(mainBooks);
				searchPanel.searchTextField.setText("");

			}
		});

		// Sort By Author
		searchPanel.sortByAuthorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {

				sortByAuthor();
				updateDisplayPanel(displayBooks);
				searchPanel.searchTextField.setText("");

			}
		});

		// Sort By Title
		searchPanel.sortByTitleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {

				sortByTitle();
				updateDisplayPanel(displayBooks);
				searchPanel.searchTextField.setText("");

			}
		});

	}

	protected void saveLibrary() {
		try {
			FileOutputStream fileOutLibrary = new FileOutputStream("Preschool Library");

			ObjectOutputStream libraryOut = new ObjectOutputStream(fileOutLibrary);
			libraryOut.writeObject(mainBooks);
			libraryOut.close();
			fileOutLibrary.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException f) {

			f.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	protected void getLibrary() {
		try {
			FileInputStream fileInLibrary = new FileInputStream("Preschool Library");

			ObjectInputStream libraryIn = new ObjectInputStream(fileInLibrary);

			ArrayList<Book> readingLibrary = new ArrayList<Book>();

			try {
				readingLibrary = (ArrayList<Book>) libraryIn.readObject();
			} catch (ClassNotFoundException e) {

				e.printStackTrace();
			}

			libraryIn.close();
			fileInLibrary.close();

			mainBooks = readingLibrary;
			displayBooks = readingLibrary;

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException f) {

			f.printStackTrace();
		}
		;
	}

	private void initvari() {
		this.setTitle("Children's Library");

		mainBooks = new ArrayList<Book>();
		displayBooks = new ArrayList<Book>();
		unwantedWords = new ArrayList<String>();
		// Loading up arraylist used to ignore common words during a search
		String s = "i for in a to and or the an by if is";
		String[] words = s.split(" ");
		for (String str : words) {
			unwantedWords.add(str);
		}

		searchPanel = new SearchPanel();
		twoPanelHolder = new JPanel();
		displayListPanel = new JPanel();
		addBookPanel = new AddBookPanel();
		editBookPanel = new EditBookPanel();

		getLibrary();

		displayListPanel.setLayout(new GridLayout(0, 4));

		// Startup pic and url
		imgURL = LibraryDisplay.class.getResource("/images/WelcomePic.jpg");
		try {
			welcome = ImageIO.read(imgURL);
		} catch (IOException e) {

			e.printStackTrace();
		}
		startUpPic = new ImageIcon();
		startUpPic.setImage(welcome);
		testingPic = new JLabel(startUpPic);
		displayListPanel.add(testingPic);
		scrollListPanel = new JScrollPane(displayListPanel);

		scrollListPanel.setPreferredSize(new Dimension(1000, 350));
		searchPanel.setPreferredSize(new Dimension(200, 1000));
		addBookPanel.setPreferredSize(new Dimension(1000, 200));
		editBookPanel.setPreferredSize(new Dimension(1000, 200));
		twoPanelHolder.setPreferredSize(new Dimension(1150, 1000));

		scrollListPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollListPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		twoPanelHolder.add(scrollListPanel, BorderLayout.NORTH);
		twoPanelHolder.add(addBookPanel, BorderLayout.SOUTH);

		getContentPane().add(searchPanel, BorderLayout.WEST);
		getContentPane().add(twoPanelHolder, BorderLayout.EAST);

	}

	public void addingBooks() {
		// Look for previously saved file
		getLibrary();

		String[] sendStrings;
		sendStrings = new String[12];
		for (int i = 0; i < sendStrings.length; i++) {

			sendStrings[i] = "Blank";
		}

		for (int i = 0; i < sendStrings.length; i++) {

			sendStrings[i] = addBookPanel.addBookTexts[i].getText();
		}

		addBook(sendStrings);

		saveLibrary();

		for (int fillBook = 0; fillBook < addBookPanel.addBookTexts.length; fillBook++) {
			addBookPanel.addBookTexts[fillBook].setText("");
		}
	}

	public void updateDisplayPanel(List<Book> displayBooks2) {
		displayListPanel.removeAll();
		displayListPanel.setLayout(new GridBagLayout());
		GridBagConstraints d = new GridBagConstraints();

		List<JButton> infDelBut = new ArrayList<JButton>();
		List<JButton> infEdiBut = new ArrayList<JButton>();
		List<JLabel> shoLab = new ArrayList<JLabel>();
		List<Book> displayBookArray = new ArrayList<Book>();

		displayBookArray = displayBooks2;

		// Make delete buttons and actionlistener
		for (int arrButt = 0; arrButt < displayBookArray.size(); arrButt++) {
			final int boMar = arrButt;
			infDelBut.add(arrButt, new JButton("Delete"));
			infDelBut.get(arrButt).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent b) {

					deleteBooks(displayBooks.get(boMar));

				}
			});
		}

		// Make edit buttons and actionlistener
		for (int arrButt = 0; arrButt < displayBookArray.size(); arrButt++) {
			final int boMar = arrButt;
			infEdiBut.add(arrButt, new JButton("Edit"));
			infEdiBut.get(arrButt).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent b) {

					editBooks(displayBooks.get(boMar));

				}
			});
		}

		int testSho = 1; // Keeps track of number of books on labels
		int colLab = 1; // Keep track of switching colors

		// Load up labels with book strings
		for (Book shBoo : displayBookArray) {

			shoLab.add(new JLabel("  " + testSho));
			testSho++;

			for (int e = 0; e < 12; e++) {
				shoLab.add(new JLabel("   " + shBoo.allStrings[e] + "   "));
			}

		}

		int counter = 0; // Keep track of buttons
		int counter2 = 0; // Keep track of JLabels

		// Add buttons and labels to display panel
		for (int yaxis = 0; yaxis < displayBookArray.size(); yaxis++) {

			for (int xaxis = 0; xaxis < 1; xaxis++) {

				d.fill = GridBagConstraints.HORIZONTAL;
				d.weightx = 0.5;
				d.gridx = 0;
				d.gridy = yaxis;
				displayListPanel.add(infEdiBut.get(counter), d);

				d.fill = GridBagConstraints.HORIZONTAL;
				d.weightx = 0.5;
				d.gridx = 1;
				d.gridy = yaxis;
				displayListPanel.add(infDelBut.get(counter), d);
				counter++;

				if (colLab == 1)
					colLab = 0;

				else if (colLab == 0)
					colLab = 1;

				for (int q = 0; q < 13; q++) {

					shoLab.get(counter2).setOpaque(true);

					if (colLab == 1)
						shoLab.get(counter2).setBackground(Color.LIGHT_GRAY);

					if (colLab == 0)
						shoLab.get(counter2).setBackground(Color.WHITE);

					d.weightx = 0.5;
					d.gridx = q + 2;
					d.gridy = yaxis;
					displayListPanel.add(shoLab.get(counter2), d);
					counter2++;

				}

			}

		}

		displayListPanel.revalidate();
		searchPanel.revalidate();
		displayListPanel.repaint();
		searchPanel.repaint();
		this.revalidate();
		this.repaint();
	}

	public void editBooks(Book bookToEdit) {

		Book tempBook = new Book();
		tempBook = bookToEdit;

		int reply = JOptionPane.showConfirmDialog(null,
				"Are you sure that you want to edit  " + tempBook.getTitle() + " ? ", "Are You Sure?",
				JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {

			for (int g = 0; g < tempBook.allStrings.length; g++) {
				editBookPanel.addBookTexts[g].setText(tempBook.allStrings[g]);
			}

			twoPanelHolder.remove(addBookPanel);
			twoPanelHolder.add(editBookPanel, BorderLayout.SOUTH);
			twoPanelHolder.revalidate();
			twoPanelHolder.repaint();

			editButtonAction(tempBook);

		}

		else {
			// Do nothing
		}

	}

	public void deleteBooks(Book bookToDelete) {

		Book tempBook = new Book();
		tempBook = bookToDelete;

		int reply = JOptionPane.showConfirmDialog(null,
				"Are you sure that you want to delete  " + tempBook.getTitle() + " ? ", "Are You Sure?",
				JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {

			mainBooks.remove(tempBook);
			displayBooks.remove(tempBook);

			saveLibrary();

			updateDisplayPanel(displayBooks);

		}

		else {
			// do nothing
		}

	}

	@SuppressWarnings("unchecked")
	public void searchAuthorList(String tempA) {

		List<Book> tempBookArrays = new ArrayList<Book>();
		String tempAu = tempA.toLowerCase();

		tempBookArrays = mainBooks.stream().filter(b -> b.getAuthor().toLowerCase().equals(tempAu))
				.collect(Collectors.toList());

		if (tempBookArrays.isEmpty())
			tempBookArrays = diffSearchAuthor(tempAu);

		displayBooks = tempBookArrays;
		searchPanel.searchTextField.setText("");
		updateDisplayPanel(displayBooks);
	}

	public ArrayList<Book> diffSearchAuthor(String tempAr) {
		String[] seaAuthor = new String[30];
		String[] seaAuthor2 = new String[30];
		seaAuthor = tempAr.split(" ");
		List<Book> tempBookArrays2 = new ArrayList<Book>();

		for (Book seaBook : mainBooks) {
			seaAuthor2 = seaBook.getAuthor().toLowerCase().split(" ");

			for (String st1 : seaAuthor) {
				st1 = st1.replaceAll(",", " ").replaceAll(";", " ").replaceAll(":", " ").replace('"', ' ')
						.replaceAll("'", " ").replaceAll("!", " ");

				boolean checkWords = false;

				for (String unwantedWord : unwantedWords) {
					{
						if (unwantedWord.trim().equals(st1.trim()))
							checkWords = true;
					}

				}

				if (checkWords == false) {
					for (String st2 : seaAuthor2) {

						st2 = st2.replaceAll(",", " ").replaceAll(";", " ").replaceAll(":", " ").replace('"', ' ')
								.replaceAll("'", " ").replaceAll("!", " ");

						if (st1.trim().equals(st2.trim())) {
							tempBookArrays2.add(seaBook);
							break;
						}

					}
				}

			}
		}

		Set<Book> filtering = new HashSet<>(tempBookArrays2);
		tempBookArrays2.clear();
		tempBookArrays2.addAll(filtering);
		return (ArrayList<Book>) tempBookArrays2;
	}

	public void searchTitleList(String tempTits) {

		List<Book> tempBookArrays = new ArrayList<Book>();
		String tempTi = tempTits.toLowerCase();

		tempBookArrays = mainBooks.stream().filter(b -> b.getTitle().toLowerCase().equals(tempTi))
				.collect(Collectors.toList());

		if (tempBookArrays.isEmpty())
			tempBookArrays = diffSearchTitle(tempTi);

		displayBooks = tempBookArrays;
		searchPanel.searchTextField.setText("");
		updateDisplayPanel(displayBooks);
	}

	public ArrayList<Book> diffSearchTitle(String tempTi) {
		String[] seaTitle = new String[30];
		String[] seaTitle2 = new String[30];

		seaTitle = tempTi.split(" ");
		List<Book> tempBookArrays2 = new ArrayList<Book>();

		for (Book seaBook : mainBooks) {

			seaTitle2 = seaBook.getTitle().toLowerCase().split(" ");

			for (String st1 : seaTitle) {

				st1 = st1.replaceAll(",", " ").replaceAll(";", " ").replaceAll(":", " ").replace('"', ' ')
						.replaceAll("'", " ").replaceAll("!", " ");
				boolean checkWords = false;

				for (String unwantedWord : unwantedWords) {
					if (unwantedWord.trim().equals(st1.trim()))
						checkWords = true;
				}

				if (checkWords == false) {
					for (String st2 : seaTitle2) {

						st2 = st2.replaceAll(",", " ").replaceAll(";", " ").replaceAll(":", " ").replace('"', ' ')
								.replaceAll("'", " ").replaceAll("!", " ");

						if (st1.trim().equals(st2.trim())) {
							tempBookArrays2.add(seaBook);
							break;
						}

					}
				}

			}
		}

		Set<Book> filtering = new HashSet<>(tempBookArrays2);
		tempBookArrays2.clear();
		tempBookArrays2.addAll(filtering);
		return (ArrayList<Book>) tempBookArrays2;
	}

	public void searchGenresList(String genres) {

		List<Book> tempBookArrays = new ArrayList<Book>();
		String tempGe = genres.toLowerCase();
		boolean checkMatch = false;

		for (Book seaBook : mainBooks) {
			for (String geArr : seaBook.getGenres()) {
				if (geArr.toLowerCase().equals(tempGe)) {
					tempBookArrays.add(seaBook);
					checkMatch = true;
				}
			}
		}

		if (checkMatch == false)
			tempBookArrays = diffSearchGenres(tempGe);

		displayBooks = tempBookArrays;
		searchPanel.searchTextField.setText("");
		updateDisplayPanel(displayBooks);
	}

	public ArrayList<Book> diffSearchGenres(String tempGe) {
		String[] seaGenre = new String[30];
		String[] seaGenre2 = new String[30];
		boolean isEmpty = true;

		seaGenre = tempGe.toLowerCase().split(" ");
		List<Book> tempBookArrays3 = new ArrayList<Book>();

		for (Book seaBook : mainBooks) {

			seaGenre2 = seaBook.getGenres();

			for (String st1 : seaGenre) {
				st1 = st1.replaceAll(",", " ").replaceAll(";", " ").replaceAll(":", " ").replace('"', ' ')
						.replaceAll("'", " ").replaceAll("!", " ");

				boolean checkWords = false;
				for (String unwantedWord : unwantedWords) {
					if (unwantedWord.trim().equals(st1.trim()))
						checkWords = true;
				}

				if (checkWords == false) {
					for (String st2 : seaGenre2) {

						st2 = st2.replaceAll(",", " ").replaceAll(";", " ").replaceAll(":", " ").replace('"', ' ')
								.replaceAll("'", " ").replaceAll("!", " ").toLowerCase();

						if (st1.trim().equals(st2.trim())) {
							tempBookArrays3.add(seaBook);
							isEmpty = false;
							break;
						}

					}
				}

			}

		}

		if (isEmpty == true) {
			tempBookArrays3 = genreLastResort(seaGenre);
		}

		Set<Book> filtering = new HashSet<>(tempBookArrays3);
		tempBookArrays3.clear();
		tempBookArrays3.addAll(filtering);
		return (ArrayList<Book>) tempBookArrays3;

	}

	@SuppressWarnings("unchecked")
	public void sortByTitle() {

		Collections.sort(displayBooks, Book.TitleComparator);
		updateDisplayPanel(displayBooks);

	}

	@SuppressWarnings("unchecked")
	public void sortByAuthor() {

		Collections.sort(displayBooks, Book.AuthorComparator);
		updateDisplayPanel(displayBooks);
	}

	public void editButtonAction(Book edits) {
		editBookPanel.editBookButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent c) {

				Book tempBook = new Book();
				tempBook = edits;

				String[] sendStrings;
				sendStrings = new String[12];
				for (int i = 0; i < sendStrings.length; i++) {

					sendStrings[i] = "Blank";
				}

				for (int i = 0; i < sendStrings.length; i++) {

					sendStrings[i] = editBookPanel.addBookTexts[i].getText();
				}

				// Make edited book to replace original book
				Book editedBook = new Book(sendStrings);

				// Go through the main list and replace the original book w/edited book
				for (int b = 0; b < mainBooks.size(); b++) {

					if (tempBook == mainBooks.get(b)) {
						mainBooks.set(b, editedBook);
						break;
					} else
						continue;
				}

				saveLibrary();
				// Go through the display list and replace the original book w/edited book
				for (int b = 0; b < displayBooks.size(); b++) {

					if (tempBook == displayBooks.get(b)) {
						displayBooks.set(b, editedBook);
						break;
					} else
						continue;
				}
				// Update the display panel with new info
				updateDisplayPanel(displayBooks);

				// Clear out the edit panel text fields
				for (int fillBook = 0; fillBook < editBookPanel.addBookTexts.length; fillBook++) {
					editBookPanel.addBookTexts[fillBook].setText("");
				}

				twoPanelHolder.remove(editBookPanel);
				twoPanelHolder.add(addBookPanel, BorderLayout.SOUTH);
				twoPanelHolder.revalidate();
				twoPanelHolder.repaint();

				updateDisplayPanel(displayBooks);
				searchPanel.searchTextField.setText("");

			}
		});
	}

	public void addBook(String[] allParts) {
		mainBooks.add(new Book(allParts));
	}

	public List<Book> genreLastResort(String[] seaGenre) {
		List<Book> tempBoArr = new ArrayList<Book>();
		String[] searchingGenre = new String[30];

		for (Book seaBook : mainBooks) {

			searchingGenre = seaBook.getGenres();

			for (String st1 : seaGenre) {
				st1 = st1.replaceAll(",", " ").replaceAll(";", " ").replaceAll(":", " ").replace('"', ' ')
						.replaceAll("'", " ").replaceAll("!", " ");

				boolean checkWords = false;
				for (String unwantedWord : unwantedWords) {
					if (unwantedWord.trim().equals(st1.trim()))
						checkWords = true;
				}

				if (checkWords == false) {
					for (String st2 : searchingGenre) {

						st2 = st2.replaceAll(",", " ").replaceAll(";", " ").replaceAll(":", " ").replace('"', ' ')
								.replaceAll("'", " ").replaceAll("!", " ").toLowerCase();
						String[] lastCheck = st2.split(" ");
						for (String st3 : lastCheck) {
							if (st1.trim().equals(st3.trim())) {
								tempBoArr.add(seaBook);
								break;
							}
						}
					}
				}

			}

		}

		return tempBoArr;
	}

}

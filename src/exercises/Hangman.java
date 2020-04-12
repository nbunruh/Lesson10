package exercises;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import examples.FileHelper;

public class Hangman extends KeyAdapter {

	Stack<String> puzzles = new Stack<String>();
	ArrayList<JLabel> boxes = new ArrayList<JLabel>();
	int lives = 9;
	JLabel livesLabel = new JLabel("" + lives);

	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		hangman.addPuzzles();
		hangman.createUI();
	}

	private void addPuzzles() {
//		puzzles.push("defenestrate");
//		puzzles.push("fancypants");
//		puzzles.push("elements");
		List<String> words = FileHelper.loadFileContentsIntoArrayList("resource/words.txt");
		for (int i = 0; i < words.size(); i++) {
			puzzles.push(words.get(i));
		}
	}

	JPanel panel = new JPanel();
	private String puzzle;

	private void createUI() {
//		playDeathKnell();
		JFrame frame = new JFrame("June's Hangman");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel.add(livesLabel);
		loadNextPuzzle();
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
	}

	private void loadNextPuzzle() {
		removeBoxes();
		lives = 9;
		livesLabel.setText("" + lives);
		puzzle = puzzles.pop();
		Random rand = new Random();
		int num = rand.nextInt(puzzles.size());
		puzzle = puzzles.get(num);
		try {
			if (!puzzle.matches("[A-Za-z]+")) {
				throw new Exception("Word " + puzzle + " contains special characters!");
			}
			System.out.println("puzzle is now " + puzzle);
			createBoxes();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			loadNextPuzzle();
		}
//		System.out.println("puzzle is now " + puzzle);
//		createBoxes();
	}

	public void keyTyped(KeyEvent arg0) {
		System.out.println(arg0.getKeyChar());
		updateBoxesWithUserInput(arg0.getKeyChar());
		if (lives == 0) {
			playDeathKnell();
			loadNextPuzzle();
		}
	}

	private void updateBoxesWithUserInput(char keyChar) {
		boolean gotOne = false;
		for (int i = 0; i < puzzle.length(); i++) {
			if (puzzle.charAt(i) == keyChar) {
				boxes.get(i).setText("" + keyChar);
				gotOne = true;
			}
		}
		if (!gotOne) {
			livesLabel.setText("" + --lives);
	} else {
			boolean wordFinished = true;
			for (int i = 0; i < boxes.size(); i++) {
				if (boxes.get(i).getText() == "_") {
					wordFinished = false;
				}
			}
			if (wordFinished) {
				loadNextPuzzle();
			}
		}
	}

	void createBoxes() {
		for (int i = 0; i < puzzle.length(); i++) {
			JLabel textField = new JLabel("_");
			boxes.add(textField);
			panel.add(textField);
		}
	}

	void removeBoxes() {
		for (JLabel box : boxes) {
			panel.remove(box);
		}
		boxes.clear();
	}
	
	public void playDeathKnell() {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("resource/funeral-march.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			Thread.sleep(8400);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}





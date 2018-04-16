package se.aceone.atg;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class HomeAssignment {

	private static final Logger log = Logger.getLogger(HomeAssignment.class.getName());

	private static void actionPerformedButton(ActionEvent e) {
		log.info("Button cmd: " + e.getActionCommand());
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		log.info("Build UI");
		// Create and set up the window.
		JFrame frame = new JFrame("HomeAssignment");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel compsToExperiment = new JPanel();

		compsToExperiment.setLayout(new GridBagLayout());

		// Add buttons to experiment with Grid Layout
		JButton greenButton = new JButton("Grön");
		greenButton.addActionListener(HomeAssignment::actionPerformedButton);

		GridBagConstraints constr = new GridBagConstraints();
		constr.gridx = 0;
		constr.gridy = 0;
		constr.insets = new Insets(5, 5, 5, 5);
		compsToExperiment.add(greenButton, constr);
		JButton redButton = new JButton("Röd");
		redButton.addActionListener(HomeAssignment::actionPerformedButton);

		constr.gridx = 1;
		constr.gridy = 0;
		compsToExperiment.add(redButton, constr);

		final JComboBox<String> comboBox = new JComboBox<>(new String[] { "Grön", "Röd" });
		comboBox.addActionListener(e -> log.info("ComboBox selected: " + comboBox.getSelectedItem()));
		constr.gridx = 0;
		constr.gridy = 1;
		compsToExperiment.add(comboBox, constr);

		try {
			BufferedImage blueDot = ImageIO.read(new File("blue_sphere.png"));
			JLabel blueDotLabel = new JLabel(new ImageIcon(blueDot.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
			constr.gridx = 1;
			constr.gridy = 1;
			constr.anchor = GridBagConstraints.WEST;
			compsToExperiment.add(blueDotLabel, constr);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Faled to read image", e);
		}

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(compsToExperiment, BorderLayout.WEST);

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);

		log.info("Display Window");
		// Display the window.
		frame.setSize(300, 200);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(HomeAssignment::createAndShowGUI);
	}
}

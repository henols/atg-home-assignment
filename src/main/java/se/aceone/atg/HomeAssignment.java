package se.aceone.atg;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
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

	private static void createAndShowGUI() {
		log.info("Build UI");
		JFrame frame = new JFrame("HomeAssignment");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		GridBagConstraints constr = new GridBagConstraints();
		constr.insets = new Insets(5, 5, 5, 5);

		JButton greenButton = new JButton("Grön");
		greenButton.addActionListener(HomeAssignment::actionPerformedButton);
		constr.gridx = 0;
		constr.gridy = 0;
		mainPanel.add(greenButton, constr);

		JButton redButton = new JButton("Röd");
		redButton.addActionListener(HomeAssignment::actionPerformedButton);
		constr.gridx = 1;
		constr.gridy = 0;
		mainPanel.add(redButton, constr);

		JComboBox<String> comboBox = new JComboBox<>(new String[] { "Grön", "Röd" });
		comboBox.addActionListener(e -> log.info("ComboBox selected: " + comboBox.getSelectedItem()));
		constr.gridx = 0;
		constr.gridy = 1;
		mainPanel.add(comboBox, constr);

		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			BufferedImage blueDot = ImageIO.read(classloader.getResourceAsStream("blue_sphere.png"));
			JLabel blueDotLabel = new JLabel(new ImageIcon(blueDot.getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
			constr.gridx = 1;
			constr.gridy = 1;
			constr.anchor = GridBagConstraints.WEST;
			mainPanel.add(blueDotLabel, constr);
		} catch (IOException e) {
			log.log(Level.SEVERE, "Faled to read image", e);
		}

		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(mainPanel, BorderLayout.WEST);

		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);

		log.info("Display Window");
		frame.setSize(300, 200);
		frame.setVisible(true);
	}

	private static void actionPerformedButton(ActionEvent e) {
		log.info("Button cmd: " + e.getActionCommand());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(HomeAssignment::createAndShowGUI);
	}
}

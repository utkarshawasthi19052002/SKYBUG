import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RandomNumberGenerator extends JFrame {
    private JLabel resultLabel;
    private JButton generateButton;

    public RandomNumberGenerator() {
        setTitle("Random_Rover");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        resultLabel = new JLabel("Click 'Generate' to get a random number");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
        resultLabel.setForeground(Color.BLUE);

        generateButton = new JButton("Generate");
        generateButton.setFont(new Font("Arial", Font.PLAIN, 16));
        generateButton.setBackground(Color.BLACK);
        generateButton.setForeground(Color.YELLOW);
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRandomNumber();
            }
        });

        setLayout(new GridLayout(2, 1));
        add(resultLabel);
        add(generateButton);
        getContentPane().setBackground(Color.BLACK);
    }

    private void generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        resultLabel.setText("Random Number: " + randomNumber);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                new RandomNumberGenerator().setVisible(true);
            }
        });
    }
}

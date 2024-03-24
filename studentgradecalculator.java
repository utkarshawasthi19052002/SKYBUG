import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame implements ActionListener {

    private JTextField txtSubject1, txtSubject2, txtSubject3, txtSubject4, txtSubject5;
    private JButton btnCalculate;
    private JLabel lblGrade, lblPercentage, lblAverage;

    public StudentGradeCalculator() {
        
        setTitle("Student Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridLayout(8, 2, 5, 10));
        setResizable(false); 

      
        add(new JLabel("Subject 1:")).setFont(new Font("Arial", Font.BOLD, 13));
        txtSubject1 = new JTextField();
        add(txtSubject1);

        add(new JLabel("Subject 2:")).setFont(new Font("Arial", Font.BOLD, 13));
        txtSubject2 = new JTextField();
        add(txtSubject2);

        add(new JLabel("Subject 3:")).setFont(new Font("Arial", Font.BOLD, 13));
        txtSubject3 = new JTextField();
        add(txtSubject3);

        add(new JLabel("Subject 4:")).setFont(new Font("Arial", Font.BOLD, 13));
        txtSubject4 = new JTextField();
        add(txtSubject4);

        add(new JLabel("Subject 5:")).setFont(new Font("Arial", Font.BOLD, 13));
        txtSubject5 = new JTextField();
        add(txtSubject5);

        btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(this);
        add(btnCalculate).setFont(new Font("Arial", Font.BOLD, 16));
        
        add(new JLabel()); 
        
     

lblGrade = new JLabel("Grade: ");
lblGrade.setFont(new Font("Arial", Font.BOLD, 16));
add(lblGrade);

lblPercentage = new JLabel("Percentage: ");
lblPercentage.setFont(new Font("Arial", Font.BOLD, 16));
add(lblPercentage);

lblAverage = new JLabel("Average: ");
lblAverage.setFont(new Font("Arial", Font.BOLD, 16));
add(lblAverage);

// Subsequent code...

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnCalculate) {
            calculateResults();
        }
    }

    private void calculateResults() {
        try {
            double subject1 = Double.parseDouble(txtSubject1.getText());
            double subject2 = Double.parseDouble(txtSubject2.getText());
            double subject3 = Double.parseDouble(txtSubject3.getText());
            double subject4 = Double.parseDouble(txtSubject4.getText());
            double subject5 = Double.parseDouble(txtSubject5.getText());

            double totalMarks = subject1 + subject2 + subject3 + subject4 + subject5;
            double percentage = (totalMarks / 500) * 100;
            double average = totalMarks / 5;

            String grade = calculateGrade(percentage);

            lblGrade.setText("Grade: " + grade);
            lblPercentage.setText("Percentage: " + String.format("%.2f", percentage) + "%");
            lblAverage.setText("Average: " + String.format("%.2f", average));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numeric values for all subjects.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new StudentGradeCalculator().setVisible(true);
        });
    }
}

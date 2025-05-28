
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FootballQuizApp extends JFrame implements ActionListener {
    String[] questions = {
        "Who won the 2022 FIFA World Cup?",
        "Which country has won the most World Cups?",
        "Which player has the most Ballon d'Or awards?",
        "Which club has won the most UEFA Champions League titles?",
        "Who is known as 'El Fenomeno'?",
        "Who scored the 'Hand of God' goal?",
        "What year was the first FIFA World Cup held?",
        "Which player won the Ballon D'Or during Ronaldo-Messi Saga?",
        "Which team is nicknamed 'Los Blancos'?",
        "Who is the all-time top scorer in World Cup history?"
    };

    String[][] options = {
        {"Brazil", "France", "Argentina", "Germany"},
        {"Brazil", "Germany", "Italy", "Argentina"},
        {"Messi", "Ronaldo", "Zidane", "Ronaldinho"},
        {"AC Milan", "Liverpool", "Real Madrid", "Barcelona"},
        {"Ronaldinho", "Ronaldo Nazário", "Cristiano Ronaldo", "Messi"},
        {"Pele", "Messi", "Maradona", "Klose"},
        {"1920", "1930", "1950", "1960"},
        {"Benzema", "Antony", "Modric", "Rodri"},
        {"Liverpool", "Manchester United", "Real Madrid", "Bayern Munich"},
        {"Ronaldo", "Messi", "Miroslav Klose", "Pele"}
    };

    int[] answers = {2, 0, 0, 2, 1, 2, 1, 2, 2, 2}; 

    int currentQuestion = 0;
    int score = 0;

    JLabel questionLabel;
    JRadioButton[] choices;
    ButtonGroup group;
    JButton nextButton;
    JButton restartButton;

    public FootballQuizApp() {
        setTitle("Are you a true football fan?");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));

        questionLabel = new JLabel();
        questionLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(questionLabel);

        choices = new JRadioButton[4];
        group = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            choices[i] = new JRadioButton();
            choices[i].setFont(new Font("Arial", Font.PLAIN, 14));
            group.add(choices[i]);
            add(choices[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(this);
        add(nextButton);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> restartQuiz());
        restartButton.setVisible(false); 
        add(restartButton);

        loadQuestion();
        setVisible(true);
    }

    void loadQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText("Question " + (currentQuestion + 1) + ": " + questions[currentQuestion]);
            for (int i = 0; i < 4; i++) {
                choices[i].setText(options[currentQuestion][i]);
                choices[i].setSelected(false);
                choices[i].setVisible(true);
            }
            nextButton.setVisible(true);
            restartButton.setVisible(false);
        } else {
            showResult();
        }
    }

    void showResult() {
        questionLabel.setText("Quiz finished! Your score: " + score + " out of " + questions.length);
        for (JRadioButton choice : choices) {
            choice.setVisible(false);
        }
        nextButton.setVisible(false);
        restartButton.setVisible(true);
    }

    void restartQuiz() {
        currentQuestion = 0;
        score = 0;
        loadQuestion();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int selected = -1;
        for (int i = 0; i < 4; i++) {
            if (choices[i].isSelected()) {
                selected = i;
                break;
            }
        }

        if (selected == -1) {
            JOptionPane.showMessageDialog(this, "Please select an option.");
        } else {
            if (selected == answers[currentQuestion]) {
                score++;
            }
            currentQuestion++;
            loadQuestion();
        }
    }

    public static void main(String[] args) {
        FootballQuizApp fpq = new FootballQuizApp();
    }
}

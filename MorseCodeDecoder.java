import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MorseCodeDecoder extends JFrame {
    private JTextArea inputArea;
    private JLabel outputLabel;
    private MorseTree morseTree;

    public MorseCodeDecoder() {
        morseTree = new MorseTree();

        // Frame
        setTitle("Morse Code Decoder");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input 
        inputArea = new JTextArea(5, 40);
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(inputArea);
        add(scrollPane, BorderLayout.NORTH);

        // Decode button
        JPanel buttonPanel = new JPanel();
        JButton decodeButton = new JButton("Decode");
        decodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String morseCode = inputArea.getText().trim();
                String decodedMessage = morseTree.decode(morseCode);
                outputLabel.setText("Decoded Message: " + decodedMessage);
            }
        });
        buttonPanel.add(decodeButton);
        add(buttonPanel, BorderLayout.CENTER);
        outputLabel = new JLabel("Decoded Message: ");
        outputLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(outputLabel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MorseCodeDecoder frame = new MorseCodeDecoder();
            frame.setVisible(true);
        });
    }
}

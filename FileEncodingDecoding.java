import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FileEncodingDecoding {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("File Encoding and Decoding System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.add(panel);

        JTextField inputField = new JTextField(20);
        JTextField outputField = new JTextField(20);
        JButton encodeButton = new JButton("Encode");
        JButton decodeButton = new JButton("Decode");

        panel.add(new JLabel("Input File:"));
        panel.add(inputField);
        panel.add(new JLabel("Output File:"));
        panel.add(outputField);
        panel.add(encodeButton);
        panel.add(decodeButton);

        encodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputFilePath = inputField.getText();
                String outputFilePath = outputField.getText();

                try {
                    encodeFile(inputFilePath, outputFilePath);
                    JOptionPane.showMessageDialog(frame, "File Encoding Completed!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        decodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputFilePath = inputField.getText();
                String outputFilePath = outputField.getText();

                try {
                    decodeFile(inputFilePath, outputFilePath);
                    JOptionPane.showMessageDialog(frame, "File Decoding Completed!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error",
                            JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        frame.setSize(400, 150);
        frame.setVisible(true);
    }

    private static void encodeFile(String inputFilePath, String outputFilePath) throws Exception {
        byte[] fileBytes = Files.readAllBytes(Paths.get(inputFilePath));
        byte[] encodedBytes = Base64.getEncoder().encode(fileBytes);
        Files.write(Paths.get(outputFilePath), encodedBytes);
    }

    private static void decodeFile(String inputFilePath, String outputFilePath) throws Exception {
        byte[] encodedBytes = Files.readAllBytes(Paths.get(inputFilePath));
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        Files.write(Paths.get(outputFilePath), decodedBytes);
    }
}
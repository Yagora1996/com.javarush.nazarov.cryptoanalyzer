import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class MainApp extends JFrame {
    private JTabbedPane tabbedPane;

    private JTextField filePathField;
    private JTextField keyField;
    private JTextField outputPathField;
    private JButton startButton;
    private JTextField filePathFieldDecrypt;
    private JTextField keyFieldDecrypt;
    private JTextField outputPathFieldDecrypt;
    private JButton startButtonDecrypt;
    private JTextField filePathFieldBrute;
    private JTextField outputPathFieldBrute;
    private JButton startButtonBrute;
    private JTextField keyFieldBrute;

    public MainApp() {
        setTitle("Crypto Application");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        JPanel encryptionPanel = createEncryptionTab();
        tabbedPane.add("Шифрование", encryptionPanel);

        JPanel decryptionPanel = createDecryptionTab();

        tabbedPane.add("Расшифровка", decryptionPanel);

        JPanel bruteForcePanel = createBruteTab();

        tabbedPane.add("Brute Force", bruteForcePanel);

        add(tabbedPane);
    }

    private JPanel createEncryptionTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel fileLabel = new JLabel("Путь к файлу:");
        filePathField = new JTextField();
        JButton browseButton = new JButton("Выбрать файл");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePathField.setText(selectedFile.getAbsolutePath());
                }
            }
        });
        JLabel keyLabel = new JLabel("Номер ключа (0-40):");
        keyField = new JTextField();

        JLabel outputLabel = new JLabel("Путь для сохранения:");
        outputPathField = new JTextField();
        JButton outputBrowseButton = new JButton("Выбрать путь");
        outputBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedDir = fileChooser.getSelectedFile();
                    outputPathField.setText(selectedDir.getAbsolutePath());
                }
            }
        });

        startButton = new JButton("Старт");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFilePath = filePathField.getText();
                String outputDirectory = outputPathField.getText();
                String keyText = keyField.getText();

                try {
                    int key = Integer.parseInt(keyText);
                    if (key < 0 || key > 40) {
                        JOptionPane.showMessageDialog(null, "Ключ должен быть от 0 до 40", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String encrypt = Cipher.encrypt(FileManager.FileHandler.readFile(inputFilePath), key);
                    FileManager.FileHandler.writeFile(encrypt, outputDirectory);

                    JOptionPane.showMessageDialog(null, "Шифрование завершено", "Готово", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Введите корректное значение ключа", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        panel.add(fileLabel);
        panel.add(filePathField);
        panel.add(browseButton);
        panel.add(keyLabel);
        panel.add(keyField);
        panel.add(new JLabel());
        panel.add(outputLabel);
        panel.add(outputPathField);
        panel.add(outputBrowseButton);
        panel.add(new JLabel());
        panel.add(startButton);

        return panel;
    }

    private JPanel createDecryptionTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel fileLabel = new JLabel("Путь к файлу:");
        filePathFieldDecrypt = new JTextField();
        JButton browseButton = new JButton("Выбрать файл");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePathFieldDecrypt.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        JLabel keyLabel = new JLabel("Номер ключа (0-40):");
        keyFieldDecrypt = new JTextField();

        JLabel outputLabel = new JLabel("Путь для сохранения:");
        outputPathFieldDecrypt = new JTextField();
        JButton outputBrowseButton = new JButton("Выбрать путь");
        outputBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedDir = fileChooser.getSelectedFile();
                    outputPathFieldDecrypt.setText(selectedDir.getAbsolutePath());
                }
            }
        });

        startButtonDecrypt = new JButton("Старт");
        startButtonDecrypt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFilePath = filePathFieldDecrypt.getText();
                String outputDirectory = outputPathFieldDecrypt.getText();
                String keyText = keyFieldDecrypt.getText();

                try {
                    int key = Integer.parseInt(keyText);
                    if (key < 0 || key > 40) {
                        JOptionPane.showMessageDialog(null, "Ключ должен быть от 0 до 40", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String decrypt = Cipher.decrypt(FileManager.FileHandler.readFile(inputFilePath), key);
                    FileManager.FileHandler.writeFile(decrypt, outputDirectory);


                    JOptionPane.showMessageDialog(null, "Дешифрование завершено", "Готово", JOptionPane.INFORMATION_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Введите корректное значение ключа", "Ошибка", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });




        panel.add(fileLabel);
        panel.add(filePathFieldDecrypt);
        panel.add(browseButton);
        panel.add(keyLabel);
        panel.add(keyFieldDecrypt);
        panel.add(new JLabel());
        panel.add(outputLabel);
        panel.add(outputPathFieldDecrypt);
        panel.add(outputBrowseButton);
        panel.add(new JLabel());
        panel.add(startButtonDecrypt);

        return panel;
    }

    private JPanel createBruteTab() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));


        JLabel fileLabel = new JLabel("Путь к файлу:");
        filePathFieldBrute = new JTextField();
        JButton browseButton = new JButton("Выбрать файл");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    filePathFieldBrute.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        JLabel outputLabel = new JLabel("Путь для сохранения:");
        outputPathFieldBrute = new JTextField();
        JButton outputBrowseButton = new JButton("Выбрать путь");
        outputBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedDir = fileChooser.getSelectedFile();
                    outputPathFieldBrute.setText(selectedDir.getAbsolutePath());
                }
            }
        });

        startButtonBrute = new JButton("Старт");
        startButtonBrute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputFilePath = filePathFieldBrute.getText();
                String outputDirectory = outputPathFieldBrute.getText();

                try {
                    String bruteDiscrypt = BruteForce.decryptByBruteForce(FileManager.FileHandler.readFile(inputFilePath));
                    FileManager.FileHandler.writeFile(bruteDiscrypt, outputDirectory);
                    } catch (Exception d) {

                }



            }
        });




        panel.add(fileLabel);
        panel.add(filePathFieldBrute);
        panel.add(browseButton);
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(new JLabel());
        panel.add(outputLabel);
        panel.add(outputPathFieldBrute);
        panel.add(outputBrowseButton);
        panel.add(new JLabel());
        panel.add(startButtonBrute);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainApp app = new MainApp();
                app.setVisible(true);
            }
        });
    }
}

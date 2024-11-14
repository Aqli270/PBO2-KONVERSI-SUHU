/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tugas.pkg2;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KonversiSuhu extends JFrame {
    private JTextField txtSuhu;
    private JLabel lblHasil;
    private JRadioButton rbtnKeFahrenheit, rbtnKeCelsius, rbtnKeReamur, rbtnKeKelvin;
    private ButtonGroup group;

    public KonversiSuhu() {
        setTitle("Aplikasi Konversi Suhu");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        txtSuhu = new JTextField(10);
        txtSuhu.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != '.' && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Hanya izinkan angka dan titik desimal
                }
            }
        });

        lblHasil = new JLabel("Hasil Konversi: ");
        
        rbtnKeFahrenheit = new JRadioButton("Celsius ke Fahrenheit", true);
        rbtnKeCelsius = new JRadioButton("Fahrenheit ke Celsius");
        rbtnKeReamur = new JRadioButton("Celsius ke Reamur");
        rbtnKeKelvin = new JRadioButton("Celsius ke Kelvin");
        
        group = new ButtonGroup();
        group.add(rbtnKeFahrenheit);
        group.add(rbtnKeCelsius);
        group.add(rbtnKeReamur);
        group.add(rbtnKeKelvin);

        JButton btnKonversi = new JButton("Konversi");
        btnKonversi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                konversiSuhu();
            }
        });

        // Konversi otomatis saat nilai input berubah
        txtSuhu.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                konversiSuhu();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                konversiSuhu();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                konversiSuhu();
            }
        });

        add(new JLabel("Masukkan Suhu: "));
        add(txtSuhu);
        add(rbtnKeFahrenheit);
        add(rbtnKeCelsius);
        add(rbtnKeReamur);
        add(rbtnKeKelvin);
        add(btnKonversi);
        add(lblHasil);
    }

    private void konversiSuhu() {
        try {
            double suhu = Double.parseDouble(txtSuhu.getText());
            double hasil = 0;

            if (rbtnKeFahrenheit.isSelected()) {
                hasil = (suhu * 9 / 5) + 32; // Celsius to Fahrenheit
                lblHasil.setText("Hasil Konversi: " + hasil + " °F");
            } else if (rbtnKeCelsius.isSelected()) {
                hasil = (suhu - 32) * 5 / 9; // Fahrenheit to Celsius
                lblHasil.setText("Hasil Konversi: " + hasil + " °C");
            } else if (rbtnKeReamur.isSelected()) {
                hasil = suhu * 4 / 5; // Celsius to Reamur
                lblHasil.setText("Hasil Konversi: " + hasil + " °R");
            } else if (rbtnKeKelvin.isSelected()) {
                hasil = suhu + 273.15; // Celsius to Kelvin
                lblHasil.setText("Hasil Konversi: " + hasil + " K");
            }
        } catch (NumberFormatException ex) {
            lblHasil.setText("Hasil Konversi: Input tidak valid!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KonversiSuhu app = new KonversiSuhu();
            app.setVisible(true);
        });
    }
}

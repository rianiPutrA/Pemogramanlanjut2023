package com.riyan.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainScreen extends JFrame {
    private JPanel panelMain;
    private JList jListMahasiswa;
    private JButton buttonFilter;
    private JTextField textFieldFilter;
    private JTextField textFieldNim;
    private JTextField textFieldNama;
    private JTextField textFieldIpk;
    private JButton buttonSave;
    private JButton buttonDelete;
    private JButton buttonClear;
    private List<Mahasiswa> arrayListMahasiswa = new ArrayList<>();
    private DefaultListModel defaultListModel = new DefaultListModel();

    class Mahasiswa {
        private String nim;
        private String nama;
        private float ipk;

        public String getNim() {
            return nim;
        }

        public void setNim(String nim) {
            this.nim = nim;
        }

        public String getNama() {
            return nama;
        }

        public void setNama(String nama) {
            this.nama = nama;
        }

        public float getIpk() {
            return ipk;
        }

        public void setIpk(float ipk) {
            this.ipk = ipk;
        }
    }


    public MainScreen() {
        super("Data Mahasiswa");
        this.setContentPane(panelMain);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();

        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = textFieldNim.getText();
                String nama = textFieldNama.getText();
                float ipk = Float.parseFloat(textFieldIpk.getText());

                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setIpk(ipk);
                mahasiswa.setNama(nama);
                mahasiswa.setNim(nim);

                arrayListMahasiswa.add(mahasiswa);
                clearForm();

                fromListMahasiswaToListModel();
            }
        });
        jListMahasiswa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = jListMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().equals(nama)) {
                        Mahasiswa mahasiswa = arrayListMahasiswa.get(i);
                        textFieldIpk.setText( String.valueOf(mahasiswa.getIpk()));
                        textFieldNama.setText((mahasiswa.getNama()));
                        textFieldNim.setText((mahasiswa.getNim()));
                        break;
                    }
                }

            }
        });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jListMahasiswa.getSelectedIndex();

                if (index < 0)
                    return;

                String nama = jListMahasiswa.getSelectedValue().toString();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().equals(nama) ) {
                        arrayListMahasiswa.remove(i);
                        break;
                    }
                }

                clearForm();
                fromListMahasiswaToListModel();
            }
        });
        buttonFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String keyword = textFieldFilter.getText();

                List<String> filtered = new ArrayList<>();

                for (int i = 0; i < arrayListMahasiswa.size(); i++) {
                    if (arrayListMahasiswa.get(i).getNama().contains(keyword)) {
                        filtered.add(arrayListMahasiswa.get(i).getNama());
                    }
                }

                refreshListModel(filtered);
            }
        });
    }

    private void fromListMahasiswaToListModel() {
        List<String> listNamaMahasiswa = new ArrayList<>();

        for (int i = 0; i < arrayListMahasiswa.size(); i++) {
            listNamaMahasiswa.add(
                    arrayListMahasiswa.get(i).getNama()
            );
        }

        refreshListModel(listNamaMahasiswa);
    }

    private void clearForm() {
        textFieldIpk.setText("");
        textFieldNim.setText("");
        textFieldNama.setText("");
    }

    private void refreshListModel(List<String> List) {
        defaultListModel.clear();
        defaultListModel.addAll(List);
        jListMahasiswa.setModel(defaultListModel);
    }

    public static void main(String [] args) {
        MainScreen mainScreen = new MainScreen();
        mainScreen.setVisible(true);
    }

}

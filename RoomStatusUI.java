import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


class RoomStatusUI extends JFrame {
    private JButton[] odaButonlari = new JButton[10];
    private ArrayList<String[]> ziyaretciler;

    public RoomStatusUI(ArrayList<String[]> ziyaretciler) {
        this.ziyaretciler = ziyaretciler;

        setTitle("Oda Durumu Ekranı");
        setSize(1920, 1080);             // Full HD
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        ImagePanel backgroundPanel = new ImagePanel("images/arkaplan.jpg", 0.3f);
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        int startX = 150;
        int startY = 150;
        int btnWidth = 150;
        int btnHeight = 100;
        int gapX = 80;
        int gapY = 80;

        for (int i = 0; i < 10; i++) {
            final int odaNumarasi = i + 1;  // final olması önemli
            int x = startX + (i % 5) * (btnWidth + gapX);
            int y = startY + (i / 5) * (btnHeight + gapY);

            odaButonlari[i] = new JButton("Oda " + odaNumarasi);
            odaButonlari[i].setBounds(x, y, btnWidth, btnHeight);
            odaButonlari[i].setFont(new Font("Arial", Font.BOLD, 18));

            odaButonlari[i].addActionListener(e -> {
                String mesaj = "Bu oda boştur.";
                for (String[] z : ziyaretciler) {
                    try {
                        int zOdaNo = Integer.parseInt(z[2]);
                        if (zOdaNo == odaNumarasi) {
                            mesaj = "Oda " + zOdaNo + "’da kalan kişi:\n" +
                                    "Ad Soyad: " + z[0] + "\n" +
                                    "Telefon : " + z[1];
                            break;
                        }
                    } catch (Exception ex) {
                        // Geçersiz oda numarası atla
                    }
                }
                JOptionPane.showMessageDialog(null, mesaj);
            });

            add(odaButonlari[i]);
        }

        guncelleOdaDurumlari();
    }

    private void guncelleOdaDurumlari() {
        for (JButton btn : odaButonlari) {
            btn.setBackground(Color.GREEN);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.setForeground(Color.BLACK);
        }

        for (String[] z : ziyaretciler) {
            try {
                int odaNo = Integer.parseInt(z[2]);
                if (odaNo >= 1 && odaNo <= 10) {
                    odaButonlari[odaNo - 1].setBackground(Color.RED);
                    odaButonlari[odaNo - 1].setForeground(Color.WHITE);
                }
            } catch (Exception e) {
                System.out.println("Hatalı oda numarası: " + z[2]);
            }
        }
    }
}

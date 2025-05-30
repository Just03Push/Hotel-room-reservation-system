import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;

public class ReceptionUI extends JFrame {

    private JTextField txtAdSoyad, txtTelefon, txtOdaNo;
    private JTextField txtGirisTarihi, txtCikisTarihi;
    private JButton btnKaydet, btnListele, btnOdaDurumlari, btnSil;
    private JTable tblKayitlar;
    private DefaultTableModel model;

    private ArrayList<String[]> ziyaretciler = new ArrayList<>();

    public ReceptionUI() {
        setTitle("Ziyaretçi Kayıt Ekranı");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Arkaplan paneli
        ImagePanel backgroundPanel = new ImagePanel("images/arkaplan.jpg", 0.3f);
        setContentPane(backgroundPanel);
        backgroundPanel.setLayout(null);

        JLabel lblAdSoyad = new JLabel("Ad Soyad:");
        JLabel lblTelefon = new JLabel("Telefon:");
        JLabel lblOdaNo = new JLabel("Oda No:");
        JLabel lblGirisTarihi = new JLabel("Giriş Tarihi (yyyy-MM-dd):");
        JLabel lblCikisTarihi = new JLabel("Çıkış Tarihi (yyyy-MM-dd):");

        txtAdSoyad = new JTextField();
        txtTelefon = new JTextField();
        txtOdaNo = new JTextField();
        txtGirisTarihi = new JTextField();
        txtCikisTarihi = new JTextField();

        btnKaydet = new JButton("Kaydet");
        btnListele = new JButton("Listele");
        btnOdaDurumlari = new JButton("Oda Durumları");
        btnSil = new JButton("Sil");

        // Yerleşim
        lblAdSoyad.setBounds(80, 80, 180, 50);
        txtAdSoyad.setBounds(280, 80, 400, 50);

        lblTelefon.setBounds(80, 160, 180, 50);
        txtTelefon.setBounds(280, 160, 400, 50);

        lblOdaNo.setBounds(80, 240, 180, 50);
        txtOdaNo.setBounds(280, 240, 400, 50);

        lblGirisTarihi.setBounds(80, 320, 220, 50);
        txtGirisTarihi.setBounds(300, 320, 380, 50);

        lblCikisTarihi.setBounds(80, 400, 220, 50);
        txtCikisTarihi.setBounds(300, 400, 380, 50);

        btnKaydet.setBounds(720, 80, 200, 50);
        btnListele.setBounds(720, 160, 200, 50);
        btnOdaDurumlari.setBounds(720, 240, 250, 50);
        btnSil.setBounds(720, 400, 200, 50);

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Ad Soyad", "Telefon", "Oda No", "Giriş Tarihi", "Çıkış Tarihi"});

        tblKayitlar = new JTable(model);
        tblKayitlar.setFont(new Font("Arial", Font.BOLD, 20));
        tblKayitlar.setRowHeight(35);
        tblKayitlar.setForeground(Color.BLUE);
        tblKayitlar.setSelectionBackground(new Color(0, 120, 215));  // Mavi
        tblKayitlar.setSelectionForeground(Color.RED);
        JScrollPane scrollPane = new JScrollPane(tblKayitlar);
        scrollPane.setBounds(80, 480, 1680, 520);

        // Şeffaf arkaplan ayarı
        tblKayitlar.setOpaque(false);
        ((DefaultTableCellRenderer) tblKayitlar.getDefaultRenderer(Object.class)).setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Ekleme
        add(lblAdSoyad);
        add(txtAdSoyad);
        add(lblTelefon);
        add(txtTelefon);
        add(lblOdaNo);
        add(txtOdaNo);
        add(lblGirisTarihi);
        add(txtGirisTarihi);
        add(lblCikisTarihi);
        add(txtCikisTarihi);
        add(btnKaydet);
        add(btnListele);
        add(btnOdaDurumlari);
        add(btnSil);
        add(scrollPane);

        // Kaydet butonu
        btnKaydet.addActionListener(e -> {
            String ad = txtAdSoyad.getText().trim();
            String tel = txtTelefon.getText().trim();
            String oda = txtOdaNo.getText().trim();
            String giris = txtGirisTarihi.getText().trim();
            String cikis = txtCikisTarihi.getText().trim();

            if (ad.isEmpty() || tel.isEmpty() || oda.isEmpty() || giris.isEmpty() || cikis.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurun!");
                return;
            }

            // Tarih format kontrolü
            if (!giris.matches("\\d{4}-\\d{2}-\\d{2}") || !cikis.matches("\\d{4}-\\d{2}-\\d{2}")) {
                JOptionPane.showMessageDialog(null, "Tarihleri yyyy-MM-dd formatında giriniz!");
                return;
            }

            if (giris.compareTo(cikis) > 0) {
                JOptionPane.showMessageDialog(null, "Giriş tarihi çıkış tarihinden büyük olamaz!");
                return;
            }

            int odaNo;
            try {
                odaNo = Integer.parseInt(oda);
                if (odaNo < 1 || odaNo > 10) {
                    JOptionPane.showMessageDialog(null, "Oda numarası 1 ile 10 arasında olmalıdır!");
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Oda numarası geçerli bir sayı olmalıdır!");
                return;
            }

            // Aynı oda, aynı tarih aralığında dolu mu kontrol
            for (String[] z : ziyaretciler) {
                try {
                    int mevcutOda = Integer.parseInt(z[2]);
                    if (mevcutOda == odaNo) {
                        String mevcutGiris = z[3];
                        String mevcutCikis = z[4];
                        // Çakışma kontrolü
                        if (!(cikis.compareTo(mevcutGiris) < 0 || giris.compareTo(mevcutCikis) > 0)) {
                            JOptionPane.showMessageDialog(null, "Seçilen tarihlerde bu oda doludur!");
                            return;
                        }
                    }
                } catch (Exception ex) {
                    // Hatalı kayıtları yoksay
                }
            }

            ziyaretciler.add(new String[]{ad, tel, oda, giris, cikis});
            JOptionPane.showMessageDialog(null, "Kayıt başarıyla eklendi.");

            txtAdSoyad.setText("");
            txtTelefon.setText("");
            txtOdaNo.setText("");
            txtGirisTarihi.setText("");
            txtCikisTarihi.setText("");
        });

        // Listele butonu
        btnListele.addActionListener(e -> {
            model.setRowCount(0);
            for (String[] z : ziyaretciler) {
                model.addRow(z);
            }
        });

        // Oda durumları butonu
        btnOdaDurumlari.addActionListener(e -> {
            RoomStatusUI roomUI = new RoomStatusUI(ziyaretciler);
            roomUI.setVisible(true);
        });

        // Silme butonu
        btnSil.addActionListener(e -> {
            int seciliSatir = tblKayitlar.getSelectedRow();
            if (seciliSatir == -1) {
                JOptionPane.showMessageDialog(null, "Lütfen silmek için bir kayıt seçin!");
                return;
            }

            // Modelden verileri al
            String adSil = (String) model.getValueAt(seciliSatir, 0);
            String telSil = (String) model.getValueAt(seciliSatir, 1);
            String odaNoSil = (String) model.getValueAt(seciliSatir, 2);
            String girisSil = (String) model.getValueAt(seciliSatir, 3);
            String cikisSil = (String) model.getValueAt(seciliSatir, 4);

            // Modelden satırı kaldır
            model.removeRow(seciliSatir);

            // ArrayList'ten kaydı kaldır
            ziyaretciler.removeIf(z -> z[0].equals(adSil) && z[1].equals(telSil) && z[2].equals(odaNoSil)
                    && z[3].equals(girisSil) && z[4].equals(cikisSil));

            JOptionPane.showMessageDialog(null, "Kayıt başarıyla silindi.");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReceptionUI().setVisible(true));
    }
}

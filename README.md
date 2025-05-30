# Oda Rezervasyon ve Yönetim Sistemi

Bu proje, Java Swing kullanarak geliştirilmiş basit bir otel oda rezervasyon ve yönetim uygulamasıdır.  
Ziyaretçi kayıtlarını tutar, oda durumlarını gösterir, tarih aralığında çakışan kayıtları engeller ve kayıt silme işlemi yapılabilir.

---

## Özellikler

- Ziyaretçi bilgileri (Ad Soyad, Telefon, Oda No, Giriş Tarihi, Çıkış Tarihi) kaydetme  
- Tarih formatı kontrolü (`yyyy-MM-dd`)  
- Aynı oda için tarih aralığında doluluk kontrolü  
- Kayıtları listeleme  
- Kayıt silme  
- Oda durumlarını renklerle gösterme (Boş: Yeşil, Dolu: Kırmızı)  
- Kullanıcı dostu grafik arayüz (Java Swing)  
- Arka plan görseli ile görsel tasarım desteklenmiş

---

## Ekran Görüntüleri

### Ziyaretçi Kayıt Ekranı
![Ziyaretçi Kayıt](images/ziyaretci_kayit.png)

### Oda Durumları Ekranı
![Oda Durumları](images/oda_durumlari.png)

---

## Kullanım

1. Projeyi klonlayın veya indirin.  
2. `ReceptionUI` sınıfını çalıştırarak programı başlatın.  
3. Ziyaretçi bilgilerini form aracılığıyla girin:  
   - Ad Soyad  
   - Telefon  
   - Oda No (1-10 arası)  
   - Giriş Tarihi (yyyy-MM-dd formatında)  
   - Çıkış Tarihi (yyyy-MM-dd formatında)  
4. "Kaydet" butonuyla kayıtları ekleyin.  
5. "Listele" ile kayıtlar tabloya yansıtılır.  
6. "Oda Durumları" butonuyla odaların doluluk durumunu görebilirsiniz.  
7. Kayıtları seçip "Sil" butonuyla silebilirsiniz.

---

## Gereksinimler

- Java JDK 8 veya üzeri  
- Swing kütüphanesi (Java standardıyla gelir)  
- Görsel arka plan için `images/arkaplan.jpg` dosyasının proje dizininde olması

---

## Dosya Yapısı

- `ReceptionUI.java` — Ana kullanıcı arayüzü ve iş mantığı  
- `RoomStatusUI.java` — Oda durumlarını gösteren arayüz  
- `ImagePanel.java` — Arka plan resmi için özel panel sınıfı  
- `images/arkaplan.jpg` — Arka plan resmi (proje dizininde olmalı)




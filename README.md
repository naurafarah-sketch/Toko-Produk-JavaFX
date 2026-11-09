# 🛒 Aplikasi Pencatatan Stok Produk Toko

![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-21-2196F3?style=for-the-badge&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)

Aplikasi desktop GUI berbasis **JavaFX** dan **Maven** untuk mengelola pencatatan stok produk toko secara interaktif. Aplikasi ini dibangun dengan menerapkan arsitektur *Model-View-Controller* (MVC), prinsip Pemrograman Berorientasi Objek (OOP), serta antarmuka *Full-Color* bergradasi yang modern.

---

## 📱 Tampilan Antarmuka (UI)

<p align="center">
  <b>1. Halaman Form Input Produk</b><br>
  <img src="docs/form.png" width="85%" alt="Halaman Form Input Produk"><br><br>
  <b>2. Halaman Detail Struk Produk</b><br>
  <img src="docs/detail.png" width="85%" alt="Halaman Detail Produk"><br><br>
  <b>3. Halaman Tabel Stok & Live Search</b><br>
  <img src="docs/daftar.png" width="85%" alt="Halaman Tabel Stok Produk">
</p>

---

## ✨ Fitur Utama

- ➕ **Form Input Produk**: Input nama produk, pilihan kategori (*Makanan* / *Minuman*), serta harga produk dengan validasi input (menolak nama kosong atau harga non-positif).
- 📊 **Tabel Stok & Banner Statistik Real-time**: Menampilkan seluruh produk pada `TableView` dan menghitung secara otomatis jumlah total produk serta **Total Nilai Rupiah Inventaris**.
- 🔍 **Live Search & Filter Kategori**: Filtering otomatis begitu nama/kode produk diketik pada kolom pencarian tanpa perlu menekan tombol tambahan.
- 📑 **Halaman Detail Dinamis**: Menampilkan rincian struk/nota data produk terpilih berbasis objek yang dikirim antar-layar via `Navigator`.
- 🗑️ **Hapus Data dengan Konfirmasi**: Fitur hapus barang yang dilengkapi dialog konfirmasi interaktif (`Alert`).
- 🎨 **Desain Antarmuka Full-Color Modern**: Penataan CSS khusus (`app.css`) dengan gradasi warna Deep Indigo, Emerald Green, Amber Gold, dan efek animasi *hover*.

---

## 🛠️ Teknologi & Stack

| Komponen | Teknologi |
| :--- | :--- |
| **Bahasa Pemrograman** | Java (JDK 17+) |
| **GUI Framework** | JavaFX 21 (`javafx-controls`, `javafx-fxml`) |
| **Build Tool** | Apache Maven |
| **Layout & UI Design** | FXML & Scene Builder |
| **Styling** | Custom CSS3 (`app.css`) |
| **Penyimpanan Data** | In-Memory RAM (`ObservableList`) |

---

## 📂 Struktur Proyek

```text
Toko-Produk/
├── docs/
│   ├── form.png                           # Screenshot Form Input
│   ├── detail.png                         # Screenshot Detail Produk
│   └── daftar.png                         # Screenshot Tabel Stok
├── src/
│   └── main/
│       ├── java/
│       │   └── kelas/
│       │       ├── MainApp.java           # Entry Point Utama Aplikasi
│       │       ├── Navigator.java         # Manager Navigasi Layar
│       │       ├── model/
│       │       │   └── Produk.java        # Class Model & Validasi
│       │       ├── data/
│       │       │   └── DataProduk.java    # Logika Data RAM
│       │       └── controller/
│       │           ├── FormController.java    # Controller Form Input
│       │           ├── DetailController.java  # Controller Detail Struk
│       │           └── DaftarController.java  # Controller Tabel & Search
│       └── resources/
│           └── kelas/
│               ├── form.fxml              # Design FXML Form Input
│               ├── detail.fxml            # Design FXML Detail Produk
│               ├── daftar.fxml            # Design FXML Tabel Stok
│               └── styles/
│                   └── app.css            # Custom CSS Full Color
├── pom.xml                                # Konfigurasi Maven Dependencies
└── README.md
```

---

## 🚀 Cara Menjalankan Aplikasi

### Prasyarat System
1. **JDK 17** atau versi terbaru.
2. **Apache Maven** terkonfigurasi.
3. IDE (Apache NetBeans / VS Code / IntelliJ IDEA).

### Jalankan via Command Line / Terminal

```bash
# 1. Clone Repositori
git clone https://github.com/naurafarah-sketch/Toko-Produk.git
cd Toko-Produk

# 2. Compile & Run Aplikasi
mvn clean javafx:run
```

---

## 🧪 Pengujian Fitur

| Skenario Uji | Tindakan | Hasil yang Diharapkan |
| :--- | :--- | :--- |
| **Validasi Input Kosong** | Kosongkan nama produk lalu simpan | Tampil peringatan merah: *"Nama produk tidak boleh kosong."* |
| **Validasi Harga Non-Positif** | Isi harga `0` atau angka negatif | Tampil peringatan merah: *"Harga produk harus lebih dari nol."* |
| **Input Data Valid** | Isi *"Nasi Goreng Spesial"*, *"Makanan"*, `25000` | Tersimpan dengan kode unik, berpindah ke tampilan Detail. |
| **Live Search** | Ketik nama/kode di kolom pencarian | Tabel menyaring barang & statistik nilai inventaris langsung dihitung. |
| **Hapus Produk** | Klik baris tabel lalu tekan tombol Hapus | Dialog konfirmasi muncul (`Alert`), data terhapus jika memilih Yes. |

---

## 👤 Penulis

- **Naura Farah** - [*naurafarah-sketch*](https://github.com/naurafarah-sketch)

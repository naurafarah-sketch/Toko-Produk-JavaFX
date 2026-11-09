package kelas.controller;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import kelas.Navigator;
import kelas.data.DataProduk;
import kelas.model.Produk;

public class DaftarController {
    @FXML private TableView<Produk> tabel;
    @FXML private TableColumn<Produk, String> kodeCol;
    @FXML private TableColumn<Produk, String> namaCol;
    @FXML private TableColumn<Produk, String> kategoriCol;
    @FXML private TableColumn<Produk, Long> hargaCol;
    
    @FXML private TextField cariField;
    @FXML private ComboBox<String> filterKategoriBox;
    @FXML private Label statLabel;
    @FXML private Label pesanLabel;

    private Navigator navigator;
    private DataProduk data;
    
    private final ObservableList<Produk> dataTampilan = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        kodeCol.setCellValueFactory(new PropertyValueFactory<>("kode"));
        namaCol.setCellValueFactory(new PropertyValueFactory<>("nama"));
        kategoriCol.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        hargaCol.setCellValueFactory(new PropertyValueFactory<>("harga"));
        
        // Pilihan Filter Kategori
        filterKategoriBox.getItems().addAll("Semua Kategori", "Makanan", "Minuman");
        filterKategoriBox.setValue("Semua Kategori");
        
        tabel.setItems(dataTampilan);
        tabel.setPlaceholder(new Label("Belum ada produk yang tersimpan."));

        // Live Search: Pencarian otomatis saat diketik
        cariField.textProperty().addListener((obs, oldVal, newVal) -> cari());
        
        // Live Filter: Filter otomatis saat pilihan dropdown diubah
        filterKategoriBox.valueProperty().addListener((obs, oldVal, newVal) -> cari());
    }

    public void setKonteks(Navigator navigator, DataProduk data) {
        this.navigator = navigator;
        this.data = data;
        cari();
    }

    @FXML
    private void cari() {
        if (data == null) return;
        
        dataTampilan.clear();
        String kata = cariField.getText().trim().toLowerCase();
        String kategoriDipilih = filterKategoriBox.getValue();
        
        long totalNilaiInventaris = 0;
        
        for (Produk produk : data.getDaftar()) {
            boolean cocokKata = produk.getNama().toLowerCase().contains(kata) || produk.getKode().toLowerCase().contains(kata);
            boolean cocokKategori = "Semua Kategori".equals(kategoriDipilih) || produk.getKategori().equals(kategoriDipilih);
            
            if (cocokKata && cocokKategori) {
                dataTampilan.add(produk);
                totalNilaiInventaris += produk.getHarga();
            }
        }
        
        // Format Rupiah untuk Statistik Nilai Inventaris
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("id-ID"));
        statLabel.setText("📊 Total Produk: " + dataTampilan.size() + " | Total Nilai Inventaris: " + rupiah.format(totalNilaiInventaris));
        pesanLabel.setText("Menampilkan " + dataTampilan.size() + " dari " + data.getDaftar().size() + " produk.");
    }

    @FXML
    private void resetPencarian() {
        cariField.clear();
        filterKategoriBox.setValue("Semua Kategori");
        cari();
    }

    @FXML
    private void tambah() {
        try {
            navigator.bukaForm();
        } catch (IOException e) {
            pesanLabel.setText("Form gagal dibuka.");
            e.printStackTrace();
        }
    }

    @FXML
    private void detail() {
        Produk produk = tabel.getSelectionModel().getSelectedItem();
        if (produk == null) {
            pesanLabel.setText("⚠️ Pilih salah satu produk di tabel terlebih dahulu.");
            return;
        }
        try {
            navigator.bukaDetail(produk);
        } catch (IOException e) {
            pesanLabel.setText("Detail gagal dibuka.");
            e.printStackTrace();
        }
    }

    @FXML
    private void hapus() {
        Produk produk = tabel.getSelectionModel().getSelectedItem();
        if (produk == null) {
            pesanLabel.setText("⚠️ Pilih produk yang ingin dihapus terlebih dahulu.");
            return;
        }

        // Pop-up dialog konfirmasi hapus produk
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION, 
                "Apakah Anda yakin ingin menghapus produk '" + produk.getNama() + "' (" + produk.getKode() + ")?", 
                ButtonType.YES, ButtonType.NO);
        dialog.setTitle("Konfirmasi Hapus Produk");
        dialog.setHeaderText("Hapus Produk");
        dialog.initOwner(tabel.getScene().getWindow());

        ButtonType respon = dialog.showAndWait().orElse(ButtonType.NO);
        if (respon == ButtonType.YES) {
            data.hapus(produk);
            cari();
            pesanLabel.setText("✅ Produk '" + produk.getNama() + "' berhasil dihapus.");
        }
    }
}
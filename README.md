# PA_PBO_KELOMPOK1
**Tema: Aplikasi Pengelolaan Keuangan Desa**


# Deskripsi Project

Pengelolaan keuangan desa merupakan salah satu aspek penting dalam administrasi pemerintahan desa. Dengan adanya alokasi dana yang diterima dari pemerintah pusat maupun daerah, desa memiliki tanggung jawab untuk mengelola dana tersebut secara transparan, akuntabel, dan efisien. Dana desa digunakan untuk membiayai berbagai kegiatan, termasuk pembangunan infrastruktur, pemberdayaan masyarakat, dan kegiatan-kegiatan lain yang bermanfaat bagi masyarakat desa.
Namun, dalam praktiknya, pengelolaan keuangan desa sering kali menghadapi sejumlah tantangan. Pengelolaan keuangan secara manual atau tanpa sistem yang terstruktur dapat menyebabkan kesalahan pencatatan, kehilangan data, serta lambatnya proses pelaporan keuangan. Hal ini dapat berdampak pada efektivitas penggunaan dana desa dan berpotensi menimbulkan ketidakpercayaan masyarakat terhadap transparansi pengelolaan dana desa.
Seiring dengan perkembangan teknologi informasi, penggunaan aplikasi berbasis komputer telah terbukti dapat meningkatkan efisiensi dan akurasi dalam pengelolaan keuangan. Oleh karena itu, diperlukan sebuah aplikasi pengelolaan keuangan desa yang dapat membantu Kepala Desa dan Sekretaris dalam melakukan pencatatan, pemantauan, serta pelaporan keuangan desa secara terstruktur dan mudah diakses.
Aplikasi ini dirancang untuk memberikan kemudahan bagi pengguna dalam mengelola berbagai transaksi keuangan desa, baik pemasukan maupun pengeluaran, serta mengelola data bidang dan kegiatan yang ada. Dengan adanya sistem login yang memberikan hak akses sesuai dengan peran pengguna, aplikasi ini juga diharapkan dapat menjaga keamanan data dan mencegah akses yang tidak sah. Selain itu, aplikasi ini menyediakan fitur riwayat pengeluaran dan pemasukan yang dapat diakses kapan saja oleh pengguna yang berwenang, sehingga mendukung transparansi dan akuntabilitas dalam pengelolaan keuangan desa.

# Flowchart
![PBO Diagram](https://github.com/user-attachments/assets/71131889-3c82-4c24-a6cb-06498480f3b5)

Aplikasi berbasis menu ini memiliki alur kerja yang dimulai dari proses Login, di mana pengguna diminta memasukkan username dan password untuk mengakses sistem. Jika kredensial salah, pengguna harus mencoba lagi hingga benar. Setelah berhasil login, pengguna diarahkan ke Menu Utama, yang berisi beberapa pilihan fungsi: Lihat Data, Tambah Data, Edit Data, Hapus Data, Cari Data, Riwayat, dan Logout.
Pada fungsi Lihat Data, sistem menampilkan data yang tersimpan berdasarkan label bidang dan kegiatan yang diinputkan. Data mencakup informasi seperti kode, nama, anggaran, dan detail lainnya. Pada menu Tambah Data, pengguna dapat memasukkan data baru, termasuk label bidang, kegiatan, kode, nama, dan anggaran. Data yang berhasil ditambahkan akan disimpan dan muncul di daftar utama. Fungsi Edit Data memungkinkan pengguna memperbarui data dengan cara memasukkan kode data yang ingin diubah, lalu mengganti informasi seperti nama, anggaran, atau elemen lainnya, yang kemudian disimpan dalam sistem.
Melalui menu Hapus Data, pengguna dapat menghapus data tertentu dengan memasukkan kode data yang relevan, dan sistem akan memberikan konfirmasi keberhasilan penghapusan. Pada menu Cari Data, pengguna dapat mencari data berdasarkan kriteria tertentu seperti kode, nama, atau bidang, dengan hasil pencarian yang sesuai akan ditampilkan. Fungsi Riwayat mencatat aktivitas pada sistem, seperti penambahan, pengubahan, dan penghapusan data, lengkap dengan informasi waktu dan pengguna yang melakukan aksi tersebut. Log ini berguna untuk melacak setiap perubahan dalam sistem. Akhirnya, melalui menu Logout, pengguna dapat keluar dari aplikasi dan kembali ke halaman login, mengakhiri sesi kerja mereka.

# ERD
![LogicalPBO](https://github.com/user-attachments/assets/429bd5d8-dc13-42dd-9809-800adcaa9bd5)

# Relational
![RelationalPBO](https://github.com/user-attachments/assets/069c39e3-96bd-4ac5-ae70-a58717901cff)

# Penjelasan Code Program

```
public void koneksiDatabase() throws SQLException, SQLException {
         try {
             // Memuat JDBC Driver MySQL
             Class.forName("com.mysql.cj.jdbc.Driver");  // Driver MySQL
             
             // Menjalin koneksi dengan database
             Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadesa", "root", "password");  // Ganti dengan username dan password yang sesuai
             St = Con.createStatement(); // Membuat statement untuk eksekusi query
             System.out.println("Koneksi Berhasil!");
         } catch (ClassNotFoundException | SQLException e) {
             System.out.println("Koneksi Gagal: " + e.getMessage());
             throw new SQLException("Koneksi gagal: " + e.getMessage());  // Throw exception untuk penanganan lebih lanjut
         }
         
    }
```
Metode `koneksiDatabase()` ini bertanggung jawab untuk membangun koneksi ke database MySQL. Pertama, ia mencoba memuat driver JDBC MySQL menggunakan `Class.forName("com.mysql.cj.jdbc.Driver")`. Jika berhasil, ia kemudian mencoba menjalin koneksi ke database dengan menggunakan `DriverManager.getConnection()`, yang memerlukan URL database, username, dan password sebagai argumen.

Setelah berhasil terhubung, metode ini membuat objek `Statement` dengan memanggil `Con.createStatement()`, yang digunakan untuk mengeksekusi query SQL. Jika koneksi berhasil, akan muncul pesan "Koneksi Berhasil!". 

Namun, jika terjadi kesalahan saat memuat driver atau saat menghubungkan ke database, metode ini akan menangkap pengecualian dan mencetak pesan kesalahan. Selanjutnya, metode ini melemparkan `SQLException` untuk penanganan lebih lanjut, memungkinkan bagian lain dari aplikasi untuk menangani kesalahan tersebut secara tepat.

Sebagai catatan, dalam deklarasi metode ada dua `SQLException` yang tertulis, seharusnya cukup satu saja. Berikut adalah perbaikan yang tepat untuk deklarasi metode:

```java
public void koneksiDatabase() throws SQLException {
```



```
package Model;

/**
 *
 * @author Danil Pratama
 */
public class Akun {
    private String username;
    private String password;
    private String status;

    // Constructor
    public Akun(String username, String password, String status) {
        this.username = username;
        this.password = password;
        this.status = status;
    }

    // Getter and Setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Abstract method to get role (akan di override pada subclass)
    public String getRole() {
        return "Akun Umum";  // Default role
    }

    boolean login(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
```
Class `Akun` merepresentasikan akun pengguna dalam aplikasi. Class ini memiliki tiga atribut: `username`, `password`, dan `status`, yang menyimpan informasi dasar tentang akun. Konstruktor digunakan untuk menginisialisasi objek `Akun` dengan nilai-nilai ini.

Terdapat juga metode getter dan setter untuk setiap atribut, yang memungkinkan pengambilan dan pengaturan nilai. Metode `getRole` mengembalikan peran default "Akun Umum", yang dapat dioverride oleh subclass untuk memberikan peran yang lebih spesifik.

Metode `login` seharusnya memverifikasi kredensial pengguna, tetapi saat ini hanya melempar pengecualian, menunjukkan bahwa implementasi lebih lanjut diperlukan. Secara keseluruhan, class ini menyediakan dasar untuk pengelolaan akun yang dapat diperluas dalam aplikasi.


```
package Model;

import java.sql.*;
/**
 *
 * @author Danil Pratama
 */
public class Database {
    // Public properties untuk akses dari class lain
    public static Connection connection = null;
    public static PreparedStatement preparedStatement = null;
    public static ResultSet resultSet = null;

    // Konfigurasi database
    private final static String DB_HOST = "localhost";
    private final static String DB_NAME = "datadesa";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "";

    // Constructor untuk memuat driver JDBC
    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL berhasil dimuat!");
        } catch (ClassNotFoundException e) {
            System.err.println("Gagal memuat driver MySQL!");
            e.printStackTrace();
        }
    }

    // Metode untuk membuat koneksi ke database
    public static Connection connect() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://" + DB_HOST + "/" + DB_NAME + "?useSSL=false";
                connection = DriverManager.getConnection(url, DB_USERNAME, DB_PASSWORD);
                System.out.println("Koneksi ke database berhasil!");
            } catch (SQLException e) {
                System.err.println("Gagal terhubung ke database!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Metode untuk memutus koneksi dari database
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Koneksi ke database berhasil ditutup!");
            } catch (SQLException e) {
                System.err.println("Gagal menutup koneksi database!");
                e.printStackTrace();
            }
        }
    }

    // Metode untuk mendapatkan PreparedStatement
    public static PreparedStatement getPreparedStatement(String query) throws SQLException {
        if (connection == null) {
            connect();
        }
        preparedStatement = connection.prepareStatement(query);
        return preparedStatement;
    }

    // Metode untuk mengeksekusi query SELECT
    public static ResultSet executeQuery(String query) throws SQLException {
        if (connection == null) {
            connect();
        }
        Statement statement = connection.createStatement();
        resultSet = statement.executeQuery(query);
        return resultSet;
    }

    // Metode untuk mengeksekusi query INSERT, UPDATE, DELETE
    public static int executeUpdate(String query) throws SQLException {
        if (connection == null) {
            connect();
        }
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    // Menutup resources PreparedStatement dan ResultSet
    public static void closeResources() {
        try {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            System.out.println("Resources berhasil ditutup!");
        } catch (SQLException e) {
            System.err.println("Gagal menutup resources!");
            e.printStackTrace();
        }
    }
}
```
Class `Database` ini bertugas untuk mengelola koneksi ke database MySQL, memudahkan proses eksekusi query SQL, dan menangani sumber daya yang terkait. Kelas ini memiliki beberapa atribut statis, termasuk `connection`, `preparedStatement`, dan `resultSet`, yang diakses oleh kelas lain untuk berinteraksi dengan database. Pada konstruktor, kelas ini memuat driver JDBC MySQL dengan `Class.forName()`, memberikan konfirmasi bahwa driver berhasil dimuat atau menampilkan kesalahan jika gagal.

Metode `connect()` bertanggung jawab untuk membuka koneksi ke database jika belum terhubung. Jika koneksi berhasil, pesan konfirmasi ditampilkan; jika tidak, kesalahan ditangani dengan menampilkan pesan error. Sebaliknya, metode `disconnect()` digunakan untuk menutup koneksi, memastikan tidak ada kebocoran sumber daya.

Dalam hal eksekusi query, terdapat metode `getPreparedStatement(String query)` yang mengembalikan objek `PreparedStatement` untuk query yang disiapkan, sementara metode `executeQuery(String query)` dan `executeUpdate(String query)` digunakan untuk menjalankan query SELECT serta INSERT, UPDATE, dan DELETE secara berturut-turut. Kedua metode ini juga memastikan bahwa koneksi sudah aktif sebelum mengeksekusi query.

Untuk menutup sumber daya yang digunakan, metode `closeResources()` menutup `PreparedStatement` dan `ResultSet`, serta memberikan umpan balik apakah proses penutupan berhasil atau tidak. Secara keseluruhan, class `Database` menyediakan struktur yang rapi dan efektif untuk mengelola interaksi antara aplikasi dan database, meningkatkan pemisahan tanggung jawab dalam kode.


```
package Model;

import java.sql.SQLException;
/**
 *
 * @author Danil Pratama
 */
public class Kegiatan {
    private String kode;
    private String bidang;
    private String kegiatan;
    private String anggaran;
    private String realisasi;
    private String sisaAnggaran;
    
    // Constructor untuk menginisialisasi objek Kegiatan dengan data
    public Kegiatan(String kode, String bidang, String kegiatan, String anggaran, String realisasi, String sisaAnggaran) {
        this.kode = kode;
        this.bidang = bidang;
        this.kegiatan = kegiatan;
        this.anggaran = anggaran;
        this.realisasi = realisasi;
        this.sisaAnggaran = sisaAnggaran;
    }
    
    // Getter dan Setter untuk masing-masing atribut
    public String getKode() { return kode; }
    public String getBidang() { return bidang; }
    public String getKegiatan() { return kegiatan; }
    public String getAnggaran() { return anggaran; }
    public String getRealisasi() { return realisasi; }
    public String getSisaAnggaran() { return sisaAnggaran; }
    
    // Menyimpan data ke database
public void simpanData() throws SQLException {
    // Membuat objek Kegiatan dengan data yang ingin disimpan
    Kegiatan kegiatan = new Kegiatan("01", "Penyelenggara", "Administrasi Pemerintahan", "50.000.000", "40.000.000", "10.000.000");

    // Membuat objek koneksi
    koneksi db = new koneksi();

    try {
        // Pastikan koneksi berhasil
        db.koneksiDatabase();

        // Menyimpan data ke database dengan memanggil metode simpanData dari kelas koneksi
        db.simpanData(kegiatan.getKode(), kegiatan.getBidang(), kegiatan.getKegiatan(), kegiatan.getAnggaran(), kegiatan.getRealisasi(), kegiatan.getSisaAnggaran());
        
    } catch (SQLException e) {
        // Menangani exception jika terjadi kesalahan saat menyimpan data
        System.out.println("Terjadi error saat menyimpan data: " + e.getMessage());
    } finally {
        // Menutup koneksi setelah selesai
        db.close();
    }
}
    
    
}
```

Class `Kegiatan` merepresentasikan kegiatan dengan atribut seperti kode, bidang, jenis kegiatan, anggaran, realisasi, dan sisa anggaran. Konstruktor digunakan untuk menginisialisasi objek dengan data yang diberikan, dan terdapat metode getter untuk masing-masing atribut. Metode `simpanData()` berfungsi untuk menyimpan data kegiatan ke database. Di dalam metode ini, objek `Kegiatan` baru dibuat, meskipun ini tampaknya tidak diperlukan karena data sudah tersedia dalam objek saat ini. Objek `koneksi` dibuat untuk mengelola koneksi ke database, dan setelah memastikan koneksi berhasil, data disimpan dengan memanggil metode `simpanData` pada objek `koneksi`. Jika terjadi kesalahan selama penyimpanan, exception `SQLException` ditangkap dan pesan kesalahan ditampilkan. Koneksi kemudian ditutup dalam blok `finally` untuk memastikan sumber daya dibebaskan. Secara keseluruhan, class ini menyediakan struktur untuk mengelola informasi kegiatan dan menyimpannya ke dalam database, berperan penting dalam sistem pengelolaan keuangan desa.

```
package Model;

import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Danil Pratama
 */
public class KepalaDesa extends Akun {

    // Atribut khusus KepalaDesa
    private final String role = "KepalaDesa";

    // Constructor
    public KepalaDesa(String username, String password, String status) {
        super(username, password, status);  // Memanggil constructor superclass
    }

    // Override metode getRole untuk mengembalikan role Kepala Desa
    @Override
    public String getRole() {
        return this.role;
    }

    // Metode login untuk Kepala Desa
    public static final boolean loginKepalaDesa(String username, String password) {
        try {
            String query = "SELECT * FROM kepala_desa WHERE username = ? AND password = ?";
            Database.preparedStatement = Database.getPreparedStatement(query);
            Database.preparedStatement.setString(1, username);
            Database.preparedStatement.setString(2, password);
            Database.resultSet = Database.preparedStatement.executeQuery();

            if (Database.resultSet.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Username atau password salah!");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Login gagal! " + e.getMessage());
        } finally {
            Database.closeResources();
        }
        return false;
    }

    // Metode untuk mendapatkan status Kepala Desa berdasarkan username
    public static final String getStatus(String username) {
        try {
            String query = "SELECT status FROM kepala_desa WHERE username = ?";
            Database.preparedStatement = Database.getPreparedStatement(query);
            Database.preparedStatement.setString(1, username);
            Database.resultSet = Database.preparedStatement.executeQuery();

            if (Database.resultSet.next()) {
                return Database.resultSet.getString("status");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan status! " + e.getMessage());
        } finally {
            Database.closeResources();
        }
        return null;
    }
}
```
Class `KepalaDesa` merupakan subclass dari `Akun` yang merepresentasikan akun khusus untuk Kepala Desa dalam aplikasi. Class ini memiliki atribut khusus `role` yang diinisialisasi dengan nilai "KepalaDesa". Konstruktor class ini memanggil konstruktor superclass `Akun` untuk mengatur `username`, `password`, dan `status` saat objek dibuat. Metode `getRole()` dioverride untuk mengembalikan peran Kepala Desa.

Class ini juga memiliki dua metode statis penting. Metode `loginKepalaDesa(String username, String password)` digunakan untuk melakukan proses login. Metode ini menyiapkan query SQL untuk memeriksa kredensial pengguna dalam tabel `kepala_desa` di database. Jika pengguna ditemukan, login berhasil, jika tidak, pesan kesalahan ditampilkan menggunakan `JOptionPane`. Metode ini juga menangani exception yang mungkin terjadi dan menutup sumber daya database setelah proses selesai.

Metode `getStatus(String username)` berfungsi untuk mengambil status Kepala Desa berdasarkan username. Seperti pada metode login, metode ini menggunakan query SQL untuk mendapatkan informasi status dari database. Jika status berhasil diambil, nilainya akan dikembalikan; jika terjadi kesalahan, pesan kesalahan ditampilkan, dan sumber daya ditutup.

Secara keseluruhan, class `KepalaDesa` menyediakan fungsionalitas login dan pengambilan status untuk Kepala Desa, serta memperluas class `Akun` dengan tambahan atribut dan metode yang relevan.

```
package Model;

import javax.swing.JOptionPane;
/**
 *
 * @author Danil Pratama
 */
public class ProsesLogin {

    public static void login(String username, String password) {
        Akun akun = null;

        if (username.equals("danil")) {
            akun = new KepalaDesa(username, password, "Kepala Desa");
        } else if (username.equals("bilo")) {
            akun = new Sekretaris(username, password, "Sekretaris");
        }

        if (akun != null && akun.login(username, password)) {
            System.out.println("Login sukses sebagai " + akun.getRole());
        } else {
            JOptionPane.showMessageDialog(null, "Username atau password salah!");
        }
    }
}
```
Class `ProsesLogin` berfungsi untuk mengelola proses login pengguna dalam aplikasi. Metode statis `login(String username, String password)` menerima parameter username dan password untuk melakukan otentikasi.

Di dalam metode ini, pertama-tama dideklarasikan objek `Akun` yang diinisialisasi dengan nilai `null`. Selanjutnya, terdapat pemeriksaan kondisi untuk menentukan jenis pengguna berdasarkan username. Jika username adalah "danil", objek `KepalaDesa` dibuat; jika username adalah "bilo", objek `Sekretaris` dibuat. Masing-masing objek ini diinisialisasi dengan username, password, dan status yang sesuai.

Setelah objek akun dihasilkan, metode `login()` pada objek `akun` dipanggil untuk memverifikasi kredensial pengguna. Jika login berhasil (metode `login()` mengembalikan `true`), maka pesan sukses ditampilkan di konsol dengan mencetak peran pengguna menggunakan `akun.getRole()`. Jika login gagal, sebuah dialog pesan kesalahan ditampilkan menggunakan `JOptionPane`, yang memberi tahu pengguna bahwa username atau password yang dimasukkan salah.

Secara keseluruhan, class `ProsesLogin` menyediakan mekanisme sederhana untuk menangani login bagi dua peran pengguna yang berbeda dalam aplikasi, yaitu Kepala Desa dan Sekretaris.

```
package Model;

import javax.swing.JOptionPane;
/**
 *
 * @author Danil Pratama
 */
public class Sekretaris extends Akun {

    // Atribut khusus Sekretaris
    private final String role = "Sekretaris";

    // Constructor
    public Sekretaris(String username, String password, String status) {
        super(username, password, status);  // Memanggil constructor superclass
    }

    // Override metode getRole untuk mengembalikan role Sekretaris
    @Override
    public String getRole() {
        return this.role;
    }

    // Metode login untuk Sekretaris
    public static final boolean loginSekretaris(String username, String password) {
        try {
            String query = "SELECT * FROM sekretaris WHERE username = ? AND password = ?";
            Database.preparedStatement = Database.getPreparedStatement(query);
            Database.preparedStatement.setString(1, username);
            Database.preparedStatement.setString(2, password);
            Database.resultSet = Database.preparedStatement.executeQuery();

            if (Database.resultSet.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Username atau password salah!");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Login gagal! " + e.getMessage());
        } finally {
            Database.closeResources();
        }
        return false;
    }

    // Metode untuk mendapatkan status Sekretaris berdasarkan username
    public static final String getStatus(String username) {
        try {
            String query = "SELECT status FROM sekretaris WHERE username = ?";
            Database.preparedStatement = Database.getPreparedStatement(query);
            Database.preparedStatement.setString(1, username);
            Database.resultSet = Database.preparedStatement.executeQuery();

            if (Database.resultSet.next()) {
                return Database.resultSet.getString("status");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan status! " + e.getMessage());
        } finally {
            Database.closeResources();
        }
        return null;
    }
}
```
Class `Sekretaris` merupakan subclass dari `Akun` yang dirancang untuk merepresentasikan akun Sekretaris dalam aplikasi. Di dalam class ini, terdapat atribut khusus `role` yang ditetapkan sebagai "Sekretaris". Konstruktor `Sekretaris` memanggil konstruktor superclass `Akun`, yang memungkinkan pengaturan atribut seperti `username`, `password`, dan `status` saat objek dibuat.

Metode `getRole()` dioverride untuk mengembalikan nilai dari atribut `role`, yang menunjukkan bahwa pengguna ini adalah seorang Sekretaris. 

Class ini juga menyediakan dua metode statis yang penting untuk fungsionalitas login dan pengambilan status. Metode `loginSekretaris(String username, String password)` digunakan untuk memverifikasi kredensial pengguna. Metode ini menyiapkan query SQL untuk mencocokkan username dan password dalam tabel `sekretaris` di database. Jika pengguna ditemukan, metode ini mengembalikan `true`, yang menandakan login berhasil; jika tidak, sebuah dialog kesalahan ditampilkan menggunakan `JOptionPane`, yang memberi tahu bahwa username atau password salah. Exception yang mungkin terjadi selama proses ditangani dengan baik, dan sumber daya database ditutup setelah proses selesai.

Metode `getStatus(String username)` berfungsi untuk mengambil status Sekretaris berdasarkan username yang diberikan. Seperti pada metode login, query SQL digunakan untuk mendapatkan informasi status dari database. Jika status berhasil diambil, nilainya dikembalikan; jika terjadi kesalahan, pesan kesalahan ditampilkan, dan sumber daya ditutup.

Secara keseluruhan, class `Sekretaris` melengkapi class `Akun` dengan menambahkan fungsionalitas dan atribut spesifik untuk Sekretaris, serta menyediakan metode untuk login dan mendapatkan status yang relevan dalam konteks aplikasi pengelolaan keuangan desa.

```
package Model;

/**
 *
 * @author Danil Pratama
 */
public abstract class User {
    private final String Username;
    private final String Password;
    private final String Status;

    public User(String username, String password, String status){
        this.Username = username;
        this.Password = password;
        this.Status = status;
    }


    public abstract String getRole();
}
```
Class `User` adalah sebuah class abstrak yang berfungsi sebagai dasar untuk representasi pengguna dalam aplikasi. Class ini memiliki tiga atribut yang bersifat final, yaitu `Username`, `Password`, dan `Status`, yang menyimpan informasi dasar mengenai pengguna. Constructor `User` digunakan untuk menginisialisasi atribut-atribut ini saat objek dibuat.

Karena class ini adalah abstrak, ia tidak dapat diinstansiasi langsung; sebaliknya, class ini dirancang untuk diinherit oleh subclass lain yang akan menyediakan implementasi untuk metode-metode yang dideklarasikan dalam class ini. Metode `getRole()` dideklarasikan sebagai metode abstrak, yang berarti setiap subclass yang mewarisi class `User` harus memberikan implementasi spesifik untuk metode ini, sesuai dengan peran pengguna yang bersangkutan. 

Dengan demikian, class `User` menyediakan kerangka kerja yang konsisten untuk semua jenis pengguna dalam aplikasi, memungkinkan penanganan yang lebih terstruktur dan terorganisir terhadap berbagai peran pengguna seperti Kepala Desa dan Sekretaris yang diimplementasikan dalam subclass mereka masing-masing.

```
package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Danil Pratama
 */
public class koneksi {
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/datadesa";  // Ganti sesuai dengan database Anda
    private String user = "root";
    private String password = "";
    private Connection Con;
    private Statement St;
    
    // Constructor untuk melakukan koneksi ke database

    /**
     *
     * @throws SQLException
     * @throws SQLException
     */
    public void koneksiDatabase() throws SQLException, SQLException {
         try {
             // Memuat JDBC Driver MySQL
             Class.forName("com.mysql.cj.jdbc.Driver");  // Driver MySQL
             
             // Menjalin koneksi dengan database
             Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadesa", "root", "password");  // Ganti dengan username dan password yang sesuai
             St = Con.createStatement(); // Membuat statement untuk eksekusi query
             System.out.println("Koneksi Berhasil!");
         } catch (ClassNotFoundException | SQLException e) {
             System.out.println("Koneksi Gagal: " + e.getMessage());
             throw new SQLException("Koneksi gagal: " + e.getMessage());  // Throw exception untuk penanganan lebih lanjut
         }
         
    }
    // Menyimpan data ke database
    public void simpanData() throws SQLException {
        // Membuat objek Kegiatan
        Kegiatan kegiatan = new Kegiatan("01", "Penyelenggara", "Administrasi Pemerintahan", "50.000.000", "40.000.000", "10.000.000");
        
        // Mendapatkan koneksi dan statement dari kelas koneksi
        koneksi db = new koneksi();
        db.koneksiDatabase();  // Pastikan koneksi berhasil
        
        // Menyimpan data ke database dengan memanggil metode simpanData
        db.simpanData(kegiatan.getKode(), kegiatan.getBidang(), kegiatan.getKegiatan(), kegiatan.getAnggaran(), kegiatan.getRealisasi(), kegiatan.getSisaAnggaran());
    }
    
    // Mendapatkan koneksi ke database
    public Connection getConnection() {
        return Con;
    }
        // Mendapatkan statement untuk eksekusi query
    public Statement getStatement() {
        return St;
    }
    
    // Menutup koneksi dan statement
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (Con != null) Con.close();
        } catch (SQLException e) {
            System.out.println("Error saat menutup koneksi: " + e.getMessage());
        }
    }

    public void simpanData(String kode, String bidang, String kegiatan, String anggaran, String realisasi, String sisaAnggaran) {
        try {
            // Query SQL untuk memasukkan data
            String query = "INSERT INTO kegiatan (kode, bidang, kegiatan, anggaran, realisasi, sisaAnggaran) VALUES ('"
                    + kode + "', '" + bidang + "', '" + kegiatan + "', '" + anggaran + "', '" + realisasi + "', '" + sisaAnggaran + "')";
            St.executeUpdate(query);  // Menjalankan query untuk menyimpan data
            System.out.println("Data berhasil disimpan!");
        } catch (SQLException e) {
            System.out.println("Error saat menyimpan data: " + e.getMessage());
        }
    }

}
```
Class `koneksi` dirancang untuk mengelola koneksi ke database MySQL serta operasi terkait, seperti menyimpan data. Di dalam class ini, terdapat beberapa atribut penting, termasuk `Connection` untuk merepresentasikan koneksi ke database, dan `Statement` untuk mengeksekusi perintah SQL. 

Pada constructor, metode `koneksiDatabase()` bertanggung jawab untuk menginisialisasi koneksi ke database. Metode ini pertama-tama memuat driver JDBC MySQL menggunakan `Class.forName()`, kemudian menggunakan `DriverManager.getConnection()` untuk menjalin koneksi dengan database yang ditentukan. Jika koneksi berhasil, objek `Statement` dibuat untuk mengeksekusi query SQL. Jika terjadi kesalahan selama proses ini, pengecualian akan ditangkap, dan pesan kesalahan akan dicetak ke konsol.

Metode `simpanData()` ditujukan untuk menyimpan data ke dalam database. Metode ini membuat objek `Kegiatan` dengan informasi yang telah ditentukan, dan memanggil `koneksiDatabase()` untuk memastikan koneksi aktif. Selanjutnya, data kegiatan disimpan menggunakan metode `simpanData(String kode, String bidang, String kegiatan, String anggaran, String realisasi, String sisaAnggaran)` yang mengatur query SQL untuk menyisipkan data ke tabel `kegiatan`. Jika penyimpanan berhasil, pesan konfirmasi ditampilkan.

Metode `getConnection()` dan `getStatement()` digunakan untuk mendapatkan objek koneksi dan statement saat diperlukan oleh kelas lain. Terakhir, metode `close()` menutup koneksi dan statement untuk membebaskan sumber daya, dengan penanganan kesalahan yang mencetak pesan jika terjadi masalah saat penutupan.

Secara keseluruhan, class `koneksi` menyediakan antarmuka untuk berinteraksi dengan database, termasuk pengelolaan koneksi dan eksekusi query, yang merupakan bagian penting dari aplikasi pengelolaan keuangan desa.

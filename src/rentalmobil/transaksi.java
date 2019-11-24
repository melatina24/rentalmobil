/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentalmobil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rentalmobil.koneksi;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author HP
 */
public class transaksi extends javax.swing.JFrame {
    private DefaultTableModel _table;
    Connection _Cnn;
    koneksi getCnn = new koneksi();
    String hak_akses;
    int idpegawai,idmobil,idsopir;
    
    /**
     * Creates new form transaksi
     */
    public transaksi(String Akses) {
        initComponents();
         hak_akses = Akses;
         LoadData();
         form_clear();
         tampil_petugas();
         tampil_mobil();
         tampil_sopir();
    }
    private void LoadData(){
        String[] kolom = {"KODE","PENYEWA","PETUGAS","MOBIL","SOPIR","TGL PINJAM","TGL KEMBALI","LAMA","TOTAL BAYAR"};
        _table = new DefaultTableModel(null,kolom){
        Class[] types = new Class[]{
            java.lang.String.class,
            java.lang.String.class,
            java.lang.String.class,
            java.lang.String.class,
            java.lang.String.class,
            java.lang.String.class,
            java.lang.String.class,
            java.lang.String.class,
            java.lang.String.class
        };
        public Class getColumnClass(int columnIndex){
            return types [columnIndex];
        }
        public boolean isCellEditable(int row,int col){
            int cola = _table.getColumnCount();
            return (col < cola )? false:true;
        }
    };
        tbl_transaksi.setModel(_table);
        try {
            _Cnn = null;
            koneksi getCnn = new koneksi();
            _Cnn = getCnn.getConnection();
            HapusTable();
            String sql = ""+
                    "select inventaris.kd_inventaris,inventaris.nama_penyewa,petugas.nama,mobil.merk,sopir.nama_sopir,inventaris.tgl_pinjam,inventaris.tgl_kembali,inventaris.lama_sewa,inventaris.total_bayar from inventaris,petugas,mobil,sopir where petugas.kd_petugas=inventaris.petugas_kd and mobil.kd_mobil=inventaris.mobil_kd and sopir.kd_sopir=inventaris.sopir_kd";
                     Statement stm = _Cnn.createStatement();
                     ResultSet rs = stm.executeQuery(sql);
                    while(rs.next()){
                    String xkd_inventaris = rs.getString(1);
                    String xkd_penyewa = rs.getString(2);
                    String xkd_pegawai = rs.getString("nama");
                    String xkd_mobil = rs.getString("merk");
                    String xkd_sopir = rs.getString("nama_sopir");
                    String xtgl_pinjam = rs.getString(6);
                    String xtgl_kembali = rs.getString(7);
                    String xlamasewa = rs.getString(8);
                    String xtotalbayar = rs.getString(9);
                Object[] data = {xkd_inventaris,xkd_penyewa,xkd_pegawai,xkd_mobil,xkd_sopir,xtgl_pinjam,xtgl_kembali,xlamasewa,xtotalbayar};
                _table.addRow(data);
                }
                tbl_transaksi.getColumnModel().getColumn(0).setPreferredWidth(75);
                tbl_transaksi.getColumnModel().getColumn(1).setPreferredWidth(600);
                tbl_transaksi.getColumnModel().getColumn(2).setPreferredWidth(600);
                tbl_transaksi.getColumnModel().getColumn(3).setPreferredWidth(600);
                tbl_transaksi.getColumnModel().getColumn(4).setPreferredWidth(600);
                tbl_transaksi.getColumnModel().getColumn(5).setPreferredWidth(600);
                tbl_transaksi.getColumnModel().getColumn(6).setPreferredWidth(600);
                tbl_transaksi.getColumnModel().getColumn(7).setPreferredWidth(600);
                tbl_transaksi.getColumnModel().getColumn(8).setPreferredWidth(600);
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Error : "+ ex);
        }
    }
    public void HapusTable(){
        int row = _table.getRowCount();
        for(int i = 0;i<row;i++){
            _table.removeRow(i);
        }
    
    }
     private void form_clear(){
        txt_kd.setEnabled(true);
        btnsimpan.setEnabled(true);
        btnEdit.setEnabled(false);
        btnHapus.setEnabled(false);
        txt_kd.setText("");
        txt_penyewa.setText("");
        cmb_pegawai.setSelectedIndex(0);
        cmbmobil.setSelectedIndex(0);
        cmbsopir.setSelectedIndex(0);
        txt_tglpinjam.setText("");
        txt_tglkembali.setText("");
        txt_lamasewa.setText("");
        txt_ttlbayar.setText("");
        
    }
    private void tampil_petugas(){
          try {
              _Cnn = null;
            koneksi getCnn = new koneksi();
            _Cnn = getCnn.getConnection();
            String sql = " select * from petugas";
            Statement stm = _Cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                cmb_pegawai.addItem(rs.getString("nama"));
            }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(this, e);
          }
          LoadData();
     }
    private void tampil_mobil(){
          try {
              _Cnn = null;
            koneksi getCnn = new koneksi();
            _Cnn = getCnn.getConnection();
            String sql = " select * from mobil";
            Statement stm = _Cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                cmbmobil.addItem(rs.getString("merk"));
            }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(this, e);
          }
          LoadData();
     }
    private void tampil_sopir(){
          try {
              _Cnn = null;
            koneksi getCnn = new koneksi();
            _Cnn = getCnn.getConnection();
            String sql = " select * from sopir";
            Statement stm = _Cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                cmbsopir.addItem(rs.getString("nama_sopir"));
            }
          } catch (Exception e) {
              JOptionPane.showMessageDialog(this, e);
          }
          LoadData();
     }
          
     


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_kd = new javax.swing.JTextField();
        txt_penyewa = new javax.swing.JTextField();
        txt_lamasewa = new javax.swing.JTextField();
        txt_ttlbayar = new javax.swing.JTextField();
        cmb_pegawai = new javax.swing.JComboBox<>();
        txt_tglpinjam = new javax.swing.JTextField();
        txt_tglkembali = new javax.swing.JTextField();
        cmbmobil = new javax.swing.JComboBox<>();
        cmbsopir = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnsimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(51, 102, 255));

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Transaksi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1002, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Kd Transaksi");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Nama Penyewa");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Nama Sopir");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Nama Pegawai");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Tanggal Kembali");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Nama Mobil");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Tanggal Pinjam");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Lama sewa");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Total Bayar");

        txt_kd.setEnabled(false);

        txt_penyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_penyewaActionPerformed(evt);
            }
        });

        txt_lamasewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lamasewaActionPerformed(evt);
            }
        });

        cmb_pegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH" }));
        cmb_pegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_pegawaiActionPerformed(evt);
            }
        });

        cmbmobil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH" }));
        cmbmobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmobilActionPerformed(evt);
            }
        });

        cmbsopir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH" }));
        cmbsopir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbsopirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_kd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_penyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel5))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_tglpinjam, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(txt_tglkembali, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_lamasewa, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbsopir, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbmobil, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmb_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_ttlbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_kd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txt_tglpinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_penyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_tglkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txt_lamasewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmb_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txt_ttlbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbmobil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmbsopir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(112, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_transaksi);

        jButton2.setBackground(new java.awt.Color(0, 204, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rentalmobil/img/icons8-back-40.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 204, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rentalmobil/img/icons8-search-20.png"))); // NOI18N

        btnEdit.setBackground(new java.awt.Color(0, 204, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rentalmobil/img/icons8-edit-40.png"))); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(0, 204, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rentalmobil/img/icons8-delete-40.png"))); // NOI18N
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnsimpan.setBackground(new java.awt.Color(0, 204, 255));
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rentalmobil/img/icons8-save-40.png"))); // NOI18N
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnsimpan)
                        .addGap(7, 7, 7)
                        .addComponent(btnHapus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addGap(147, 147, 147)
                        .addComponent(jButton2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus)
                    .addComponent(btnsimpan))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1222, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        int idpegawai = 2; 
        int idmobil = 2;
        int idsopir = 2;
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlinsert = "insert into inventaris values(NULL,'"+txt_penyewa.getText()+"','"+idpegawai+"','"+idmobil+"','"+idsopir+"','"+txt_tglpinjam.getText()+"','"+txt_tglkembali.getText()+"','"+txt_lamasewa.getText()+"','"+txt_ttlbayar.getText()+"')";
            Statement stat = _Cnn.createStatement();
            stat.executeUpdate(sqlinsert);
            form_clear();
            LoadData(); 
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }

    }//GEN-LAST:event_btnsimpanActionPerformed

    private void txt_lamasewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lamasewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lamasewaActionPerformed

    private void txt_penyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_penyewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_penyewaActionPerformed

    private void cmb_pegawaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmb_pegawaiActionPerformed
        // TODO add your handling code here:
        try {
              _Cnn = null;
            koneksi getCnn = new koneksi();
            _Cnn = getCnn.getConnection();
            String sql = " select * from petugas where nama='"+cmb_pegawai.getSelectedItem()+"'";
            Statement stm = _Cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                idpegawai = rs.getInt("kd_petugas");
            } 
          } catch (Exception e) {
              JOptionPane.showMessageDialog(this, e);
          }
    }//GEN-LAST:event_cmb_pegawaiActionPerformed

    private void cmbmobilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbmobilActionPerformed
        // TODO add your handling code here:
        try {
              _Cnn = null;
            koneksi getCnn = new koneksi();
            _Cnn = getCnn.getConnection();
            String sql = " select * from mobil where merk='"+cmbmobil.getSelectedItem()+"'";
            Statement stm = _Cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                idmobil = rs.getInt("kd_mobil");
            } 
          } catch (Exception e) {
              JOptionPane.showMessageDialog(this, e);
          }
    }//GEN-LAST:event_cmbmobilActionPerformed

    private void cmbsopirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbsopirActionPerformed
        // TODO add your handling code here:
        try {
              _Cnn = null;
            koneksi getCnn = new koneksi();
            _Cnn = getCnn.getConnection();
            String sql = " select * from sopir where nama_sopir='"+cmbsopir.getSelectedItem()+"'";
            Statement stm = _Cnn.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                idsopir = rs.getInt("kd_sopir");
            } 
          } catch (Exception e) {
              JOptionPane.showMessageDialog(this, e);
          }
    }//GEN-LAST:event_cmbsopirActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        
        
        try {
            _Cnn = null;
            _Cnn = getCnn.getConnection();
            String sqlupdate = "update inventaris set nama_penyewa='"+txt_penyewa.getText()+"',petugas_kd='"+idpegawai+"',mobil_kd='"+idmobil+"',sopir_kd='"+idsopir+"',tgl_pinjam='"+txt_tglpinjam.getText()+"',tgl_kembali='"+txt_tglkembali.getText()+"',lama_sewa='"+txt_lamasewa.getText()+"',total_bayar='"+txt_ttlbayar.getText()+"' where kd_inventaris='"+txt_kd.getText()+"'";
            Statement stat = _Cnn.createStatement();
            stat.executeUpdate(sqlupdate);
            form_clear();
            LoadData(); 
            JOptionPane.showMessageDialog(null, "Data Berhasil DiEdit!!!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        // TODO add your handling code here:
        int jawab = JOptionPane.showConfirmDialog(this, "Yakin untuk menghapus data ini ?","Konfirmasi",
                    JOptionPane.YES_NO_OPTION);
            if(jawab == JOptionPane.YES_OPTION){
                 try {
                    _Cnn = null;
                    _Cnn = getCnn.getConnection();
                    String sqldelete = "delete from inventaris where kd_inventaris = '"+txt_kd.getText()+"'";
                    Statement stm = _Cnn.createStatement();
                    stm.executeUpdate(sqldelete);
                    form_clear();
                    LoadData();
                    JOptionPane.showMessageDialog(null,"Data Berhasil Dihapus");
                } catch (Exception e) {
                     JOptionPane.showConfirmDialog(null, "Ada Kesalahan Input","Informasi",
                            JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
                }
            
            }
    }//GEN-LAST:event_btnHapusActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        halutama hal =  new halutama(hak_akses);
        this.dispose();
        hal.show();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JComboBox<String> cmb_pegawai;
    private javax.swing.JComboBox<String> cmbmobil;
    private javax.swing.JComboBox<String> cmbsopir;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_kd;
    private javax.swing.JTextField txt_lamasewa;
    private javax.swing.JTextField txt_penyewa;
    private javax.swing.JTextField txt_tglkembali;
    private javax.swing.JTextField txt_tglpinjam;
    private javax.swing.JTextField txt_ttlbayar;
    // End of variables declaration//GEN-END:variables
}

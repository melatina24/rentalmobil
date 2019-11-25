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
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnsimpan = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();

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
                .addContainerGap(1183, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

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

        txt_kd.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_kd.setEnabled(false);

        txt_penyewa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_penyewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_penyewaActionPerformed(evt);
            }
        });

        txt_lamasewa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_lamasewa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_lamasewaActionPerformed(evt);
            }
        });

        txt_ttlbayar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_ttlbayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ttlbayarActionPerformed(evt);
            }
        });

        cmb_pegawai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmb_pegawai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH" }));
        cmb_pegawai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmb_pegawaiActionPerformed(evt);
            }
        });

        txt_tglpinjam.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txt_tglkembali.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        cmbmobil.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbmobil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH" }));
        cmbmobil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbmobilActionPerformed(evt);
            }
        });

        cmbsopir.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cmbsopir.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PILIH" }));
        cmbsopir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbsopirActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Hari");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("RP.");

        jPanel6.setBackground(new java.awt.Color(102, 102, 255));

        btnsimpan.setBackground(new java.awt.Color(0, 204, 255));
        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rentalmobil/img/icons8-save-all-60.png"))); // NOI18N
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(0, 204, 255));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rentalmobil/img/icons8-edit-file-60.png"))); // NOI18N
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setBackground(new java.awt.Color(0, 204, 255));
        btnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rentalmobil/img/icons8-delete-bin-60.png"))); // NOI18N
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 204, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rentalmobil/img/icons8-refresh-60.png"))); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnsimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(btnHapus)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnsimpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        jPanel7.setBackground(new java.awt.Color(102, 102, 255));

        jButton1.setBackground(new java.awt.Color(0, 204, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rentalmobil/img/icons8-back-60.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Courier New", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Kembali");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(22, 22, 22))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel7)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbsopir, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbmobil, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_kd, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmb_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_penyewa, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(jLabel12)))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel6)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(txt_lamasewa, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addGap(6, 6, 6))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ttlbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_tglkembali, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_tglpinjam, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))))
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
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_penyewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tglkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cmb_pegawai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cmbmobil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txt_lamasewa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbsopir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)
                                .addComponent(txt_ttlbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel11)))
                        .addGap(33, 33, 33)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );

        jPanel8.setBackground(new java.awt.Color(51, 102, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText(" Designed & Developed By Latifa Febriani & Melatina");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(465, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(320, 320, 320))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void txt_lamasewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_lamasewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_lamasewaActionPerformed

    private void txt_penyewaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_penyewaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_penyewaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        halutama menu = new halutama(hak_akses);
        this.dispose();
        menu.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_ttlbayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ttlbayarActionPerformed
    
    }//GEN-LAST:event_txt_ttlbayarActionPerformed

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
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JTextField txt_kd;
    private javax.swing.JTextField txt_lamasewa;
    private javax.swing.JTextField txt_penyewa;
    private javax.swing.JTextField txt_tglkembali;
    private javax.swing.JTextField txt_tglpinjam;
    private javax.swing.JTextField txt_ttlbayar;
    // End of variables declaration//GEN-END:variables
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.lang.String;

/**
 *
 * @author owner
 */
public class TransaksiBackend {
    public int id_detail_transaksi,id_transaksi,id_barang,harga,jumlah,total;
    public enum jenis_barang{
        Kering(1),Basah(2);
        private int value;
        
        private jenis_barang(int value) {
        this.value = value;
        }

        public int getJenis() {
            return value;
        }
        //Just for testing from some SO answers, but no use
        public void setJenis(int value) {
            this.value = value;
        }
    }

    public TransaksiBackend() {
    }

    public TransaksiBackend(int id_detail_transaksi, int id_transaksi, int id_barang, int harga, int jumlah, int total) {
        this.id_detail_transaksi = id_detail_transaksi;
        this.id_transaksi = id_transaksi;
        this.id_barang = id_barang;
        this.harga = harga;
        this.jumlah = jumlah;
        this.total = total;
    }

    public int getId_detail_transaksi() {
        return id_detail_transaksi;
    }

    public void setId_detail_transaksi(int id_detail_transaksi) {
        this.id_detail_transaksi = id_detail_transaksi;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    
    public TransaksiBackend getById(int id){
        TransaksiBackend tr = new TransaksiBackend();
        ResultSet rs = DB_Helper.selectQuery("SELECT * FROM detail_transaksi "
        +" WHERE id_detail_transaksi = '" + id + "'");
        
        try {
            while (rs.next()) {
                tr = new TransaksiBackend(); 
                tr.setId_detail_transaksi(rs.getInt("id_detail_transaksi"));
                tr.setId_transaksi(rs.getInt("id_transaksi"));
                tr.setId_barang(rs.getInt("id_barang"));
                tr.setHarga(rs.getInt("harga"));
                tr.setJumlah(rs.getInt("jumlah"));
                tr.setTotal(rs.getInt("total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tr;
    }
    public ArrayList<TransaksiBackend> getAll(){
        ArrayList<TransaksiBackend> ListTransaksi = new ArrayList();
        ResultSet rs = DB_Helper.selectQuery("SELECT * FROM detail_transaksi");
        
        try {
            while (rs.next()) {                
                TransaksiBackend tr = new TransaksiBackend();
                tr.setId_detail_transaksi(rs.getInt("id_detail_transaksi"));
                tr.setId_transaksi(rs.getInt("id_transaksi"));
                tr.setId_barang(rs.getInt("id_barang"));
                tr.setHarga(rs.getInt("harga"));
                tr.setJumlah(rs.getInt("jumlah"));
                tr.setTotal(rs.getInt("total"));
                ListTransaksi.add(tr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListTransaksi;
    }
    
    public void save(){
        if(getById(id_detail_transaksi).getId_detail_transaksi()== 0){
            String SQL = "INSERT INTO detail_transaksi (harga,jumlah,total) VALUES("
                    +"      '"+ this.harga +"', "
                    +"      '"+ this.jumlah +"', "
                    +"      '"+ this.total +"' "
                    +"      );";
            this.id_barang = DB_Helper.insertQueryGetId(SQL);
        }
        else{
            String SQL = "UPDATE detail_transaksi SET"
                    +"      harga = '"+ this.harga +"', "
                    +"      jumlah = '"+ this.jumlah +"', "
                    +"      total = '"+ this.total +"' "
                    +"      WHERE id_detail_transaksi= '" + this.id_detail_transaksi+ "' ";
            DB_Helper.executeQuery(SQL);
        }
    }
    public void delete(){
        String SQL = "DELETE FROM detail_transaksi WHERE id_detail_transaksi = '"+ this.id_detail_transaksi + "'";
        DB_Helper.executeQuery(SQL);
    }
    
//
//    @Override
//    public String toString() {
//        return nama_barang;
//    }
}

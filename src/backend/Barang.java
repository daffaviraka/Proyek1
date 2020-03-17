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
public class Barang {
    public String nama_barang;
    public int id_barang,stok,harga;
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

    public Barang() {
    }

    
    public Barang(String nama_barang, int id_barang, int stok, int harga) {
        this.nama_barang = nama_barang;
        this.id_barang = id_barang;
        this.stok = stok;
        this.harga = harga;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    public Barang getById(int id){
        Barang br = new Barang();
        ResultSet rs = DB_Helper.selectQuery("SELECT * FROM Barang "
        +" WHERE id_barang = '" + id + "'");
        
        try {
            while (rs.next()) {
                br = new Barang(); 
                br.setId_barang(rs.getInt("id_barang"));
                br.setNama_barang(rs.getString("nama_barang"));
                br.setStok(rs.getInt("stok"));
                br.setHarga(rs.getInt("harga"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return br;
    }
    public ArrayList<Barang> getAll(){
        ArrayList<Barang> ListBarang = new ArrayList();
        ResultSet rs = DB_Helper.selectQuery("SELECT * FROM barang");
        
        try {
            while (rs.next()) {                
                Barang br = new Barang();
                br.setId_barang(rs.getInt("id_barang"));
                br.setNama_barang(rs.getString("nama_barang"));
                br.setStok(rs.getInt("stok"));
                br.setHarga(rs.getInt("harga"));
                ListBarang.add(br);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ListBarang;
    }
    
    public void save(){
        if(getById(id_barang).getId_barang()== 0){
            String SQL = "INSERT INTO barang (nama_barang,stok,harga) VALUES("
                    +"      '"+ this.nama_barang +"', "
                    +"      '"+ this.stok +"', "
                    +"      '"+ this.harga +"' "
                    +"      );";
            this.id_barang = DB_Helper.insertQueryGetId(SQL);
        }
        else{
            String SQL = "UPDATE barang SET"
                    +"      nama_barang = '"+ this.nama_barang +"', "
                    +"      stok = '"+ this.stok +"', "
                    +"      harga = '"+ this.harga +"' "
                    +"      WHERE id_barang = '" + this.id_barang+ "' ";
            DB_Helper.executeQuery(SQL);
        }
    }
    public void delete(){
        String SQL = "DELETE FROM barang WHERE id_barang = '"+ this.id_barang + "'";
        DB_Helper.executeQuery(SQL);
    }
    

    @Override
    public String toString() {
        return nama_barang;
    }
}

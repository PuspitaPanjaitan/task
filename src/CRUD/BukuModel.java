package CRUD;

import java.util.*;
import Entity.*;
import crud_sys.ConnectDB;
import java.sql.*;


public class BukuModel {

	public List<Buku> findAll() {
		try{
		List<Buku> listBuku = new ArrayList<>();	
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement("select * from buku");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Buku b = new Buku();
				b.setId(rs.getInt("id"));
				b.setJudul(rs.getString("judul"));
				b.setKategori(rs.getString("kategori"));
				b.setPenulis(rs.getString("penulis"));
				b.setTahun_terbit(rs.getInt("tahun_terbit"));
				listBuku.add(b);
			}
			
			return listBuku; 
			}catch (Exception e) {
					return null;

			 }
		
	}
	
	public Buku find(int id)
	{
		try {
			Buku b = new Buku();	
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement ("select * from buku where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				b.setId(rs.getInt("id"));
				b.setJudul(rs.getString("judul"));
				b.setKategori(rs.getString("kategori"));
				b.setPenulis(rs.getString("penulis"));
				b.setTahun_terbit(rs.getInt("tahun_terbit"));		
			}
			
			return b; 
			
		}catch (Exception e) {
			return null;

			 }
		
	}
	
	
	
	public boolean create(Buku b) { 
	try {
			
		PreparedStatement ps = ConnectDB.getConnection().prepareStatement (
				"INSERT INTO buku (judul, kategori, penulis, tahun_terbit) VALUES(?,?,?,?)");
		ps.setString(1, b.getJudul());
		ps.setString(2, b.getKategori());
		ps.setString(3, b.getPenulis());
		ps.setInt(4, b.getTahun_terbit());
		return ps.executeUpdate() > 0;
		
		
	}catch (Exception e) {
		return false;}
	}
	
	
	public boolean edit(Buku b) { 
		try {
				
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement (
					"update buku set judul=?, kategori=?, penulis=?, tahun_terbit=? where id=?");
			ps.setString(1, b.getJudul());
			ps.setString(2, b.getKategori());
			ps.setString(3, b.getPenulis());
			ps.setInt(4, b.getTahun_terbit());
			ps.setInt(5, b.getId());
			return ps.executeUpdate() > 0;
			
			
		}catch (Exception e) {
			return false;}
		}
	
	public boolean delete(Buku b) { 
		try {
				
			PreparedStatement ps = ConnectDB.getConnection().prepareStatement (
					"delete from buku WHERE id=?");
			ps.setInt(1, b.getId());
			return ps.executeUpdate() > 0;
			
			
		}catch (Exception e) {
			return false;}
		}
}
	



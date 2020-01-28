package Entity;

public class Buku {

	private int id;
	private String judul;
	private String kategori;
	private String penulis;
	private int tahun_terbit;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJudul() {
		return judul;
	}
	public void setJudul(String judul) {
		this.judul = judul;
	}
	public String getKategori() {
		return kategori;
	}
	public void setKategori(String kategori) {
		this.kategori = kategori;
	}
	public String getPenulis() {
		return penulis;
	}
	public void setPenulis(String penulis) {
		this.penulis = penulis;
	}
	public int getTahun_terbit() {
		return tahun_terbit;
	}
	public void setTahun_terbit(int tahun_terbit) {
		this.tahun_terbit = tahun_terbit;
	}
	
	public Buku() {
		super();
	}
	
	
	
	
}

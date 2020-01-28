 package Form;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TableColumn;
import java.util.*;
import javax.swing.JOptionPane;

import Entity.*;
import CRUD.*;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;




public class JFrameBuku {

	protected Shell shell;
	private Table tableBuku;
	private Text textId;
	private Text textJudul;
	private Text textPenulis;
	private Text textKategori;
	private Text textTahunTerbit;
	private BukuModel bm = new BukuModel();




	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			JFrameBuku window = new JFrameBuku();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void FillData() {
		tableBuku.removeAll();
		for (Buku b : bm.findAll()) {
			TableItem tableItem = new TableItem(tableBuku, SWT.NONE);
			tableItem.setText(new String[] {String.valueOf(b.getId()), 
					b.getJudul(), b.getKategori(), b.getPenulis(), String.valueOf(b.getTahun_terbit()) });
		}
	} 
	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		FillData();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		FillData();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(490, 492);
		shell.setText("SWT Application");
		
		tableBuku = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		tableBuku.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] selection = tableBuku.getSelection();
				int id = Integer.parseInt(selection[0].getText());
				Buku b = bm.find(id);
				textId.setText(String.valueOf(b.getId()));
				textJudul.setText(b.getJudul());
				textKategori.setText(b.getKategori());
				textPenulis.setText(b.getPenulis());
				textTahunTerbit.setText(String.valueOf(b.getTahun_terbit()));
				
			}
		});
		tableBuku.setBounds(10, 10, 454, 154);
		tableBuku.setHeaderVisible(true);
		tableBuku.setLinesVisible(true);
		
		TableColumn tblclmnId = new TableColumn(tableBuku, SWT.NONE);
		tblclmnId.setWidth(58);
		tblclmnId.setText("Id");
		
		TableColumn tblclmnJudul = new TableColumn(tableBuku, SWT.NONE);
		tblclmnJudul.setWidth(100);
		tblclmnJudul.setText("Judul");
		
		TableColumn tblclmnKategori = new TableColumn(tableBuku, SWT.NONE);
		tblclmnKategori.setWidth(100);
		tblclmnKategori.setText("Kategori");
		
		TableColumn tblclmnPenulis = new TableColumn(tableBuku, SWT.NONE);
		tblclmnPenulis.setWidth(100);
		tblclmnPenulis.setText("Penulis");
		
		TableColumn tblclmnTahunTerbit = new TableColumn(tableBuku, SWT.NONE);
		tblclmnTahunTerbit.setWidth(100);
		tblclmnTahunTerbit.setText("Tahun Terbit");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(96, 198, 32, 15);
		lblNewLabel.setText("Id");
		
		textId = new Text(shell, SWT.BORDER);
		textId.setBounds(154, 195, 256, 21);
		
		Label lblJudul = new Label(shell, SWT.NONE);
		lblJudul.setText("Judul");
		lblJudul.setBounds(77, 233, 70, 15);
		
		textJudul = new Text(shell, SWT.BORDER);
		textJudul.setBounds(154, 230, 256, 21);
		
		textPenulis = new Text(shell, SWT.BORDER);
		textPenulis.setBounds(154, 303, 256, 21);
		
		Label lblPenulis = new Label(shell, SWT.NONE);
		lblPenulis.setText("Penulis");
		lblPenulis.setBounds(77, 306, 70, 15);
		
		textKategori = new Text(shell, SWT.BORDER);
		textKategori.setBounds(154, 268, 256, 21);
			
		Label lblKategori = new Label(shell, SWT.NONE);
		lblKategori.setText("Kategori");
		lblKategori.setBounds(77, 271, 70, 15);
		
		textTahunTerbit = new Text(shell, SWT.BORDER);
		textTahunTerbit.setBounds(154, 341, 256, 21);
		
		Label lblTahunTebit = new Label(shell, SWT.NONE);
		lblTahunTebit.setText("Tahun Tebit");
		lblTahunTebit.setBounds(77, 344, 70, 15);
		
		Button btnSave = new Button(shell, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Buku b = new Buku();
				b.setJudul(textJudul.getText());
				b.setKategori(textKategori.getText());
				b.setPenulis(textPenulis.getText());
				b.setTahun_terbit(Integer.parseInt(textTahunTerbit.getText()));
				if(bm.create(b)) {
					JOptionPane.showMessageDialog(null, "Data Sudah ditambahkan");
					FillData();
				}
				else
					JOptionPane.showMessageDialog(null, "Data Gagal ditambahkan");
				}
				
		});

			
		btnSave.setBounds(154, 378, 75, 25);
		btnSave.setText("Simpan");
		
		Button btnHapus = new Button(shell, SWT.NONE);
		btnHapus.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin?", "Confirm", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION) 
				{
					TableItem[] selection = tableBuku.getSelection();
					int id = Integer.parseInt(selection[0].getText());
					Buku b = bm.find(id);
					bm.delete(b);
					FillData();
				}
			}
		});
		btnHapus.setBounds(335, 378, 75, 25);
		btnHapus.setText("Hapus");
		
		Button btnUbah = new Button(shell, SWT.NONE);
		btnUbah.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Buku b = new Buku();
				b.setId(Integer.parseInt(textId.getText()));
				b.setJudul(textJudul.getText());
				b.setPenulis(textPenulis.getText());
				b.setKategori(textKategori.getText());
				b.setTahun_terbit(Integer.parseInt(textTahunTerbit.getText()));
				if(bm.edit(b)) {
					JOptionPane.showMessageDialog(null, "Data Sudah diubah");
					FillData();
			}else
				JOptionPane.showMessageDialog(null, "Data Gagal diubah");
			}
		});
		btnUbah.setBounds(247, 378, 75, 25);
		btnUbah.setText("Ubah");
		
	
		

	}
}

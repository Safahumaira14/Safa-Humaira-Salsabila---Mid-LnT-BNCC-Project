package main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	Scanner scan = new Scanner(System.in);
	ArrayList<String> namaList = new ArrayList<>();
	ArrayList<String> IDList = new ArrayList<>();
	ArrayList<String> jenisKelaminList = new ArrayList<>();
	ArrayList<String> jabatanList = new ArrayList<>();
	ArrayList<Double> gajiList = new ArrayList<>();
	public Main() {
		int menu;
		do {
			System.out.println("- PT ChipiChapa -");
			System.out.println("1. Insert Data Karyawan");
			System.out.println("2. View Data Karyawan");
			System.out.println("3. Update Data Karyawan");
			System.out.println("4. Delete Data Karywan");
			System.out.print(">> ");
			menu = scan.nextInt(); scan.nextLine();
			
			switch (menu) {
			case 1:
				inputKaryawan();
				break;
			case 2:
				viewKaryawan();
				break;
			case 3:
				updateKaryawan();
				break;
			case 4:
				deleteKaryawan();
				break;
			case 5:
				System.out.println("ENTER to return");
				break;
			default:
				break;
			}
		} while (menu != 5);
	}

	private void deleteKaryawan() {
		viewKaryawan();
		int nomor;
		do {
			System.out.printf("Input nomor urutan karyawan yang ingin dihapus [1 - %d] : ", (namaList.size()));
			nomor = scan.nextInt(); scan.nextLine();			
		} while (nomor < 1 || nomor > namaList.size());
		
		int hapus = nomor - 1;
		String newID = IDList.get(hapus);
		System.out.println("Karyawan dengan kode " + newID + " berhasil di hapus");
		
		namaList.remove(hapus);
		jabatanList.remove(hapus);
		jenisKelaminList.remove(hapus);
	}

	private void updateKaryawan() {
		viewKaryawan();
		int nomor;
		
		do {
			System.out.printf("Input nomor urutan karyawan yang ingin diupdate [1 - %d] : ", (namaList.size()));
			nomor = scan.nextInt(); scan.nextLine();			
		} while (nomor < 1 || nomor > namaList.size());
			
		int toUpdate  = nomor - 1;
		
		String newNama, newjenisKelamin, newJabatan;
		do {
			System.out.print("Input nama karywan: ");
			newNama = scan.nextLine();
		} while (newNama.length() <= 3);
		do {
			System.out.print("Input jenis kelamin [Laki-Laki | Perempuan]: ");
			newjenisKelamin = scan.nextLine();
		} while (!(newjenisKelamin.equals("Laki-Laki") || newjenisKelamin.equals("Perempuan")));
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin]: ");
			newJabatan = scan.nextLine();
		} while (!(newJabatan.equals("Manager") || newJabatan.equals("Supervisor") || newJabatan.equals("Admin")));
		
		double newGaji = gajiList.get(toUpdate);
		if (newJabatan.equals("Manager")) {
			newGaji = 8000000;
		}else if (newJabatan.equals("Supervisor")) {
			newGaji = 6000000;
		}else if (newJabatan.equals("Admin")) {
			newGaji = 4000000;
		}
		
		double bonusPersen = 0;
		if (newJabatan.equals("Manager")) {
			bonusPersen = 0.10;
		}else if (newJabatan.equals("Supervisor")) {
			bonusPersen = 0.075;
		}else if (newJabatan.equals("Admin")) {
			bonusPersen = 0.05;
		}
		String newID = IDList.get(toUpdate);
		System.out.println("Berhasil mengupdate karyawan dengan id " + newID);
		System.out.println("Enter to return");
		
		namaList.set(toUpdate, newNama);
		jenisKelaminList.set(toUpdate, newjenisKelamin);
		jabatanList.set(toUpdate, newJabatan);
		gajiList.set(toUpdate, newGaji);
		
		
	}

	private void viewKaryawan() {
		if (namaList.isEmpty()) {
			System.out.println("Belum ada data karyawan");
			
		}else {
			sortAscending();
			System.out.println("|----|-----------------|------------------------------|---------------|---------------|-------------|");
			System.out.println("|No  |Kode Karyawan    |Nama Karyawan                 |Jenis Kelamin  |Jabatan        |Gaji Karyawan|");
			System.out.println("|----|-----------------|------------------------------|---------------|---------------|-------------|");
			for (int i = 0; i < namaList.size(); i++) {
				System.out.println("|" + (i+1) + "|" + IDList.get(i) + "|" + namaList.get(i) + "|" + jenisKelaminList.get(i) + "|" + jabatanList.get(i) + "        |" + gajiList.get(i));
			}
			System.out.println("|----|-----------------|------------------------------|---------------|---------------|-------------|");
		}
		
	}

	private void sortAscending() {
		for (int i = 0; i < namaList.size(); i++) {
			for (int j = 0; j < namaList.size() - 1; j++) {
				if (namaList.get(i).compareToIgnoreCase(namaList.get(j)) < 0 ) {
					String nama = namaList.get(i);
					namaList.set(i, namaList.get(j));
					namaList.set(j, nama);
					
					String ID = IDList.get(i);
					IDList.set(i, IDList.get(j));
					IDList.set(j, ID);
					
					String jenisKelamin = jenisKelaminList.get(i);
					jenisKelaminList.set(i, jenisKelaminList.get(j));
					jenisKelaminList.set(j, jenisKelamin);
					
					String jabatan = jabatanList.get(i);
					jabatanList.set(i, jabatanList.get(j));
					jabatanList.set(j, jabatan);
					
					Double gaji = gajiList.get(i);
					gajiList.set(i, gajiList.get(j));
					gajiList.set(j, gaji);
				}
			}
		}
		
	}

	private void inputKaryawan() {
		String nama, jenisKelamin, ID = "", jabatan;
		double gaji;
		do {
			System.out.print("Input nama karywan: ");
			nama = scan.nextLine();
		} while (nama.length() <= 3);
		
		do {
			System.out.print("Input jenis kelamin [Laki-Laki | Perempuan]: ");
			jenisKelamin = scan.nextLine();
		} while (!(jenisKelamin.equals("Laki-Laki") || jenisKelamin.equals("Perempuan")));
		
		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin]: ");
			jabatan = scan.nextLine();
		} while (!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")));
		
		gaji = calculateGaji(jabatan);
		
		
		double bonusPersen = 0;
//		if (jabatan.equals("Manager")) {
//			bonusPersen = 0.10;
//		}else if (jabatan.equals("Supervisor")) {
//			bonusPersen = 0.075;
//		}else if (jabatan.equals("Admin")) {
//			bonusPersen = 0.05;
//		}
		
		int calculateJabatan = countJabatan(jabatan);
		if (calculateJabatan >= 1) {
			if (jabatan.equals("Manager")) {
				bonusPersen = 0.10;
			}else if (jabatan.equals("Supervisor")) {
				bonusPersen = 0.075;
			}else if (jabatan.equals("Admin")) {
				bonusPersen = 0.05;
			}
		}
		double bonus = bonusPersen * gaji;
		gaji = gaji + bonus;
		
		Random rand = new Random();
		ID = "" + (char) (rand.nextInt(26) + 'A')
		+ (char) (rand.nextInt(26) + 'A')
		+ "-" + rand.nextInt(10)
		+ rand.nextInt(10)
		+ rand.nextInt(10)
		+ rand.nextInt(10);
		
		System.out.println("Berhasil menambahkan karyawan dengan id " + ID);
		System.out.println("Bonus sebesar " + (bonusPersen * 100) + "% telah diberikan kepada karywan dengan id " + ID);
	
		namaList.add(nama);
		IDList.add(ID);
		jabatanList.add(jabatan);
		jenisKelaminList.add(jenisKelamin);
		gajiList.add(gaji);
	
	}

	private int countJabatan(String jabatan) {
		int count = 0;
		for (String title : jabatanList) {
			if (title.equals(jabatan)) {
				count++;
			}
		}
		return count;
	}

	private double calculateGaji(String jabatan) {
		double gaji = 0;
		if (jabatan.equals("Manager")) {
			gaji = 8000000;
		}else if (jabatan.equals("Supervisor")) {
			gaji = 6000000;
		}else if (jabatan.equals("Admin")) {
			gaji = 4000000;
		}
		return gaji;
	}

	public static void main(String[] args) {
		new Main();

	}

}

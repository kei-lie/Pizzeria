package Pica;

public class Pica {
	String adrese, piedevas, merces, klients, uzkodas, dzeriens;
	int TelNr;
	int izmers;
	double cena;
	boolean vaiUzk, vaiDzer;
	
	public void PicaBezPieg(String klients, String merces, String piedevas, String uzkodas, String dzeriens, 
			int izmers, double cena, boolean vaiUzk, boolean vaiDzer) {
		this.klients = klients;
		this.merces = merces;
		this.piedevas = piedevas;
		this.vaiUzk = vaiUzk;
		this.vaiDzer = vaiDzer;
		if(vaiUzk == true) this.uzkodas = uzkodas;
		else this.uzkodas = "nav";
		if(vaiDzer == true) this.dzeriens = dzeriens;
		else this.dzeriens = "nav";
		this.izmers = izmers;
		this.cena = cena;
		
	}
	
	public void PicaArPieg(String adrese, String klients, String merces, String piedevas, String uzkodas, 
			String dzeriens, int TelNr, int izmers,double cena, boolean vaiUzk, boolean vaiDzer) {
		this.adrese = adrese;
		this.klients = klients;
		this.TelNr = TelNr;
		this.merces = merces;
		this.piedevas = piedevas;
		this.vaiUzk = vaiUzk;
		this.vaiDzer = vaiDzer;
		if(vaiUzk == true) this.uzkodas = uzkodas;
		else this.uzkodas = "nav";
		if(vaiDzer == true) this.dzeriens = dzeriens;
		else this.dzeriens = "nav";
		this.izmers = izmers;
		this.cena = cena;
		
	}
	
}

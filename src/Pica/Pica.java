package Pica;

public class Pica {
	String adrese, piedevas, merces, klients, uzkodas, dzeriens;
	int TelNr;
	int izmers;
	double cena;
	boolean piegade, vaiUzk, vaiDzer;
	
	public Pica(String klients, String merces, String piedevas, String uzkodas, String dzeriens, 
			int izmers, double cena, boolean piegade, boolean vaiUzk, boolean vaiDzer) {
		this.piegade = piegade;
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
	
	public Pica(String adrese, String klients, String merces, String piedevas, String uzkodas, 
			String dzeriens, int TelNr, int izmers, double cena, boolean piegade, boolean vaiUzk, boolean vaiDzer) {
		this.piegade = piegade;
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
	  
	public static double cena(String merces, String piedevas, int izmers, boolean piegade, boolean vaiUzk, boolean vaiDzer) {
		double cena = 0.0;
		if(izmers == 25) {
			cena = 3;
			if(vaiUzk == true || vaiDzer == true) cena += 3.5;
			if(piegade == true) cena += 5;
		}
		else if(izmers == 30) {
			cena = 5;
			if(vaiUzk == true || vaiDzer == true) cena += 3.5;
			if(piegade == true) cena += 5;
		}
		else if(izmers == 40) {
			cena = 7;
			if(vaiUzk == true || vaiDzer == true) cena += 3.5;
			if(piegade == true) cena += 5;
		}
		else if(izmers == 45) {
			cena = 8.5;
			if(vaiUzk == true || vaiDzer == true) cena += 3.5;
			if(piegade == true) cena += 5;
		}
		else if(izmers == 50) {
			cena = 10;
			if(vaiUzk == true || vaiDzer == true) cena += 3.5;
			if(piegade == true) cena += 5;
		}
		 return cena;
	}
	
	@Override
	public String toString() {
	    String teksts = "";
	    teksts += "Klients: " + this.klients + "\n";
	    
	    if (this.piegade == true) {
	    	teksts += "Piegāde: ir.\n";
	    	teksts += "Adrese: " + this.adrese + "\n";
	    teksts += "Telefona numurs: +371 " + this.TelNr + "\n";
	    }else if(this.piegade == false) teksts += "Piegāde: nav.\n";
	    
	    teksts += "Izmērs: " + this.izmers + " cm\n";
	    teksts += "Mērce: " + this.merces + "\n";
	    teksts += "Piedevas: " + this.piedevas + "\n";
	    if (this.vaiUzk == true) teksts += "Uzkodas: " + this.uzkodas + "\n";
	    if (this.vaiDzer == true) teksts += "Dzērieni: " + this.dzeriens + "\n";
	    teksts += "Cena: " + this.cena + " EUR\n";
	    return teksts;
	}
	
}

package Pica;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class Picerija {
	
	public static String virknesParbaude(String zinojums, String noklusejums) {
		String virkne;
		do {
			virkne = JOptionPane.showInputDialog(zinojums, noklusejums);
			if(virkne == null) return null;
			
			virkne = virkne.trim();
		}while(!Pattern.matches("^[\\p{L} .]+$", virkne));
		return virkne;
	}
	
	
	static Queue<Pica> Pasutijumi = new LinkedList<>();
	static Queue<Pica> Izpilditie = new LinkedList<>();
	static String[] darbibas = {"Darbinieks", "Klients", "Apturēt"};
	static String[] merces = {"Kečups", "Majonēze", "Asā mērce", "Ķiploku mērce", "Siera mērce"};
	static String[] piedevas = {"Siers", "Tomāti", "Peperoni", "Sīpoli", "Ananasi"};
	static String[] klients = {"Jānis", "Pēteris", "Anna", "Līga", "Otto"};
	static String[] uzkodas = {"Ķiploku grauzdiņi", "Fri kartupeļi", "Vistas nageti", "Mocrellas nūjiņas", "Salāti"};
	static String[] dzerieni = {"Ūdens", "Vīnogu sula", "Coca-cola", "Zaļā tēja", "Kafija"};
	static int[] izmers = {25, 30, 40, 45, 50};
	static boolean piegade, vaiUzk, vaiDzer;
	static String[] adrese = {"Zāļu iela 54", "Krūmu iela 2", "O. Kalpaka iela 39"};
	static int[] TelNr = {26748391, 29185046, 62371948, 26897425, 29516038};
	static double maks = 0.0;
	
	public static void main(String[] args) {
		int izvele, opc;
		Random rand = new Random();
		Pica pas = null;
		
		do {
		izvele = JOptionPane.showOptionDialog(null,"Izvēlies statusu:", "Statuss", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, darbibas, darbibas[0]);
		
		switch(izvele) {
		case 0:
			
			int skaits = rand.nextInt(5)+1;
			String m, p, k, u, dz, ad;
			int izm, tel;
			
			
			for(int i=0; i<skaits; i++) {
				int pieg = rand.nextInt(2);
				piegade = (pieg == 0)? true:false;
				int uzk = rand.nextInt(2);
				vaiUzk = (uzk == 0)? true:false;
				int dzer = rand.nextInt(2);
				vaiDzer = (dzer == 0)? true:false;
				
				m = merces[rand.nextInt(5)];
				p = piedevas[rand.nextInt(5)];
				u = uzkodas[rand.nextInt(5)];
				dz = dzerieni[rand.nextInt(5)];
				izm = izmers[rand.nextInt(5)];
				k = klients[rand.nextInt(5)];
				ad = adrese[rand.nextInt(3)];
				tel = TelNr[rand.nextInt(5)];
				
				double cena = Pica.cena(m, p, izm, piegade, vaiUzk, vaiDzer);
				if(piegade)
				    pas = new Pica(ad, k, m, p, u, dz, tel, izm, cena, piegade, vaiUzk, vaiDzer);
				else
				    pas = new Pica(k, m, p, u, dz, izm, cena, piegade, vaiUzk, vaiDzer);
				
				Pasutijumi.add(pas);
			}
			
			
			String[] opcijas = {"Apkalpot", "Apskatīt esošos pasūtījumus", 
					"Apskatīt izpildītos pasūtījumus", "Uztaisīt sev picu", "Mainīt statusu"};
			do {
			opc = JOptionPane.showOptionDialog(null,"Izvēlies darbību:", "Opcijas", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.INFORMATION_MESSAGE, null, opcijas, opcijas[0]);
			switch(opc) {
			case 0:
				if(!Pasutijumi.isEmpty()) {
					Pica apkalpota = Pasutijumi.poll();
					Izpilditie.add(apkalpota);
					maks += apkalpota.cena;  
					JOptionPane.showMessageDialog(null,
							"Klients apkalpots."
							+ "\nIekasētā nauda: " +apkalpota.cena+"\nKopējā summa: "+maks, "Informācija", JOptionPane.INFORMATION_MESSAGE);
				}else JOptionPane.showMessageDialog(null,
						"Nav klientu, ej atpūsties.", "Informācija", JOptionPane.INFORMATION_MESSAGE);
				break;
				
			case 1:
				if (!Pasutijumi.isEmpty()) {
				    String teksts = "Pasūtījumu skaits: " + Pasutijumi.size() +
				            "\n_________________________________\n";

				    for (Pica pizza : Pasutijumi) {
				        teksts += pizza +
				                "\n_________________________________\n";
				    }
				

					
					JTextArea ta = new JTextArea(teksts, 10, 40);
					ta.setEditable(false);
					
					JScrollPane sp = new JScrollPane(ta);
					sp.setVerticalScrollBarPolicy(
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					
					JOptionPane.showMessageDialog(null, sp, "Esošie pasūtījumi:",
							JOptionPane.PLAIN_MESSAGE);
					
				} else {
					JOptionPane.showMessageDialog(null,
							"Nav aktīvu pasūtījumu!", "Malacis", JOptionPane.INFORMATION_MESSAGE);
							
				}
				break;
				
			case 2:
				if (!Izpilditie.isEmpty()) {
				    String teksts = "Izpildīto pasūtījumu skaits: " + Izpilditie.size() +
				            "\n_________________________________\n";

				    for (Pica pizza : Izpilditie) {
				        teksts += pizza +
				                "\n_________________________________\n";
				    }
				

					
					JTextArea ta = new JTextArea(teksts, 10, 40);
					ta.setEditable(false);
					
					JScrollPane sp = new JScrollPane(ta);
					sp.setVerticalScrollBarPolicy(
							ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
					
					JOptionPane.showMessageDialog(null, sp, "Izpildītie pasūtījumi:",
							JOptionPane.PLAIN_MESSAGE);
					
				} else {
					JOptionPane.showMessageDialog(null,
							"Nav izpildītu pasūtījumu! Ķeries pie darba!", "Aij aij aij.", JOptionPane.ERROR_MESSAGE);
							
				}
				break;
				
			case 3:
				JOptionPane.showMessageDialog(null, "Picu sev uztaisīt nevari, tad jāņem pauze un jāiet uzsūtīt!", 
						"Tā nevar!", JOptionPane.WARNING_MESSAGE);
				break;
				
			case 4:
				if(!Pasutijumi.isEmpty() && Izpilditie.isEmpty())
					JOptionPane.showMessageDialog(null, "Tu nopietni ņem pauzi? Tu neesi pat neko izdarījis!", 
							"Aij aij aij", JOptionPane.WARNING_MESSAGE);
				else if(!Pasutijumi.isEmpty() && !Izpilditie.isEmpty())
						JOptionPane.showMessageDialog(null, "Nu tu jau neesi izdarījis visus pasūtījumus, "
								+ "bet nu labi...", 
								"Nu gan slinkais", JOptionPane.WARNING_MESSAGE);
				else if(Pasutijumi.isEmpty() && !Izpilditie.isEmpty())
							JOptionPane.showMessageDialog(null, "Tu visu izpildīji? "
									+ "Tad tik tiešām esi nopelnījis pauzi!", 
									"Malacis!", JOptionPane.WARNING_MESSAGE);
				break;
			}
			}while(opc != 4);
			break;
		case 1:
			String[] klients = {"Pasūtīt picu", "Cik ir pirms manis?", "Iet prom"};
			JOptionPane.showMessageDialog(null, "Sveicināti picērijā!", "Sveiki.", JOptionPane.PLAIN_MESSAGE);
			
			
			do {
				opc = JOptionPane.showOptionDialog(null,"Izvēlies darbību:", "Opcijas", 
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, null, klients, klients[0]);
				switch(opc) {
				case 0:
					if(maks>5.0) {
					int poga = JOptionPane.showConfirmDialog(null, "Vai sūti ar piegādi?", "Pasūtījuma informācija",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(poga == -1)
					break;
					
					boolean piegade = (poga == 0)? true:false;
					
					poga = JOptionPane.showConfirmDialog(null, "Vai ņemsi uzkodas?", "Pasūtījuma informācija",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					boolean uzk = (poga == 0)? true:false;
					if(uzk == true) 
						{int uz = JOptionPane.showOptionDialog(null,"kādas uzkodas ņemsi?", "Opcijas", 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.INFORMATION_MESSAGE, null, uzkodas, uzkodas[0]);
						}
					poga = JOptionPane.showConfirmDialog(null, "Vai ņemsi dzērienu?", "Pasūtījuma informācija",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					boolean dze = (poga == 0)? true:false;
					if(dze == true) 	{
						int dzrns = JOptionPane.showOptionDialog(null,"kādas uzkodas ņemsi?", "Opcijas", 
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, null, dzerieni, dzerieni[0]);
					}
				}else
					JOptionPane.showMessageDialog(null, "Nepietiek naudas!! Ej strādāt!", "Bezdarbnieks...", 
							JOptionPane.ERROR_MESSAGE);
					break;
					
				case 1:
					if (!Pasutijumi.isEmpty()) {
				        String cilveks = virknesParbaude("Kā tevi sauc?", "Kate");
				        if (cilveks == null) break;

				        int kartasNr = 0;
				        boolean atrasts = false;

				        for (Pica picaObj  : Pasutijumi) {
				            if (picaObj .klients.equalsIgnoreCase(cilveks)) {
				                atrasts = true;
				                break;
				            }
				            kartasNr++;
				        }

				        if (atrasts) {
				            JOptionPane.showMessageDialog(null,
				                "Pirms tevis stāv " + kartasNr + " cilvēki",
				                "Informācija", JOptionPane.INFORMATION_MESSAGE);
				        } else {
				            JOptionPane.showMessageDialog(null,
				                "Nemānies, tu pat neesi pasūtījis!", "Brīdinājums",
				                JOptionPane.WARNING_MESSAGE);
				        }
				    } else {
				        JOptionPane.showMessageDialog(null,
				            "Rindā neviens nestāv!", "Tukša rinda",
				            JOptionPane.INFORMATION_MESSAGE);
				    }
					break;
					
				case 2:
					
					JOptionPane.showMessageDialog(null, "Vai mēs tiešām esam tik neveiksmīga picērija? "
							+ "Jeb tev nepietiek naudas?", "Nākat atpakaļ...", JOptionPane.QUESTION_MESSAGE);
					
					break;
				
				}
				
			}while(opc != 2);
			
			}
		
		
		
		}while(izvele != 2);
	}

}

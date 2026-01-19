package Pica;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.Timer;


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
	static String[] TelNr = {"+371 26748391", "+371 29185046", "+371 62371948", "+371 26897425", "+371 29516038"};
	static double maks = 0.0;
	static boolean klientsAktivs = false;
	static String aktivaisKlients = null;
	static boolean klientsRindaAktiva = false; 

	
	public static void main(String[] args) {
		int izvele, opc;
		Random rand = new Random();
		Pica pas = null;
		
		//laiks, pēc kura, ja atrodies klienta interfeisā, notiek apkalposana 
		Timer apkalposana = new Timer(3000, e -> {
		    
		    if (klientsAktivs && klientsRindaAktiva && !Pasutijumi.isEmpty()) {
		        Pica gatava = Pasutijumi.poll();   
		        Izpilditie.add(gatava);             
		        maks += gatava.cena;

		       
		        if (gatava.klients.equalsIgnoreCase(aktivaisKlients)) {
		            JOptionPane.showMessageDialog(null, "Jūsu pica ir gatava!",
		                "Gatavs!", JOptionPane.INFORMATION_MESSAGE
		            );
		        }
		    }
		});
		apkalposana.start();

		
		do {
		izvele = JOptionPane.showOptionDialog(null,"Izvēlies statusu:", "Statuss", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, darbibas, darbibas[0]);
		
		switch(izvele) {
		case 0:
			
			int skaits = rand.nextInt(5)+1;
			String m, p, k, u, dz, ad, tel;
			int izm;
			
			
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
				if(maks>5.0) {
				JOptionPane.showMessageDialog(null, "Tu par daudz gribi, bet ok.", 
						"Tā nevar!", JOptionPane.WARNING_MESSAGE);
				
				 int mer = JOptionPane.showOptionDialog(null,"Kādas mērces liksi?", "Opcijas",
		                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, merces, merces[0]);
		            String merce = merces[mer];

		            int pied = JOptionPane.showOptionDialog(null,"Kādas piedevas liksi?", "Opcijas",
		                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, piedevas, piedevas[0]);
		            String piedeva = piedevas[pied];

		            String uz = "nav";
		            String dzrns = "nav";
		            boolean vaiUzk = false;
		            boolean vaiDzer = false;

		            int izmInd = JOptionPane.showOptionDialog(null, "Cik lielu picu ņemsi?",
		                    "Opcijas", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
		                    new String[]{"25 cm","30 cm","40 cm","45 cm","50 cm"}, "25 cm");
		            int izmr = izmers[izmInd];

		            double cena = Pica.cena(merce, piedeva, izmr, false, false, false);
		            maks -= cena;
		            Pica darbiniekaPica = new Pica("Darbinieks", 
		            		merce, piedeva, uz, dzrns, izmr, cena, false, vaiUzk, vaiDzer);

		            Izpilditie.add(darbiniekaPica);
		            maks += darbiniekaPica.cena;

		            JOptionPane.showMessageDialog(null,
		                    "Tava pica saglabāta izpildīto pasūtijumu sarakstā - "
		                    + "protams, tev arī ir jāmaksā", "Darbinieks",
		                    JOptionPane.INFORMATION_MESSAGE);
		            maks -= cena;
		            JOptionPane.showMessageDialog(null, "Atlikusī nauda makā: "+maks+" EUR", "Naudas lietas.", JOptionPane.PLAIN_MESSAGE);
				}else JOptionPane.showMessageDialog(null, "Tev nav naudas!", 
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
							JOptionPane.showMessageDialog(null, "Tu visu izdarīji? "
									+ "Tad tik tiešām esi nopelnījis pauzi!", 
									"Malacis!", JOptionPane.WARNING_MESSAGE);
				break;
			}
			}while(opc != 4);
			break;
		case 1:
			
			klientsAktivs = true;
			String[] klients = {"Pasūtīt picu", "Cik ir pirms manis?", "Iet prom"};
			JOptionPane.showMessageDialog(null, "Sveicināti picērijā!", "Sveiki.", JOptionPane.PLAIN_MESSAGE);
			
			
			do {
				opc = JOptionPane.showOptionDialog(null,"Izvēlies darbību:", "Opcijas", 
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, null, klients, klients[0]);
				switch(opc) {
				case 0:
					if(maks>5.0) {
						String adr = null;
						String talr = null;
						String uz = "nav";
						String dzrns = "nav";

						String kl = virknesParbaude("Kā jūs sauc?", "Agita");
						aktivaisKlients = kl;

					int poga = JOptionPane.showConfirmDialog(null, "Vai sūti ar piegādi?", "Pasūtījuma informācija",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(poga == -1)
					break;
					
					boolean piegade = (poga == 0)? true:false;
					if (piegade) {
						do {
					    adr = virknesParbaude("Jūsu adrese:", "Vānes iela");
						}while(adr == null);
					    do {
					        talr = JOptionPane.showInputDialog("Ievadi tālruņa numuru");
					    } while (talr == null || !Pattern.matches("^[+]{1}[1-9]{3}[2-9][0-9]{7}$", talr));
					}

					
					poga = JOptionPane.showConfirmDialog(null, "Vai ņemsi uzkodas?", "Pasūtījuma informācija",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					vaiUzk = (poga == 0)? true:false;
					if (vaiUzk) {
					    int uzIndex = JOptionPane.showOptionDialog(
					        null, "Kādas uzkodas ņemsi?", "Opcijas",
					        JOptionPane.DEFAULT_OPTION,
					        JOptionPane.INFORMATION_MESSAGE,
					        null, uzkodas, uzkodas[0]
					    );
					    uz = uzkodas[uzIndex];
					}

					poga = JOptionPane.showConfirmDialog(null, "Vai ņemsi dzērienu?", "Pasūtījuma informācija",
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					vaiDzer = (poga == 0)? true:false;
					if (vaiDzer) {
					    int dzIndex = JOptionPane.showOptionDialog(
					        null, "Kādu dzērienu ņemsi?", "Opcijas",
					        JOptionPane.DEFAULT_OPTION,
					        JOptionPane.INFORMATION_MESSAGE,
					        null, dzerieni, dzerieni[0]
					    );
					    dzrns = dzerieni[dzIndex];
					}

					
					String[] izmersTeksts = {"25 cm", "30 cm", "40 cm", "45 cm", "50 cm"};

					int izmeraIndekss = JOptionPane.showOptionDialog(null, "Cik lielu picu ņemsi?",			//Lai varētu izvēlēties int tipa opcijas
					        "Opcijas",  JOptionPane.DEFAULT_OPTION,  JOptionPane.INFORMATION_MESSAGE, 
					        null,  izmersTeksts,  izmersTeksts[0]);
					int izmeri = izmers[izmeraIndekss];

					
					int mer = JOptionPane.showOptionDialog(null,"kādas mērces liksi?", "Opcijas", 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.INFORMATION_MESSAGE, null, merces, merces[0]);
					String merce = merces[mer];
					
					int pied = JOptionPane.showOptionDialog(null,"kādas piedevas liksi?", "Opcijas", 
							JOptionPane.DEFAULT_OPTION, 
							JOptionPane.INFORMATION_MESSAGE, null, piedevas, piedevas[0]);
					String piedeva = piedevas[pied];
					
					double cena = Pica.cena(merce, piedeva, izmeri, piegade, vaiUzk, vaiDzer);
					maks -= cena;
					if (piegade)
					    pas = new Pica(adr, kl, merce, piedeva, uz, dzrns, talr,
					                   izmeri, cena, piegade, vaiUzk, vaiDzer);
					else
					    pas = new Pica(kl, merce, piedeva, uz, dzrns,
					                   izmeri, cena, piegade, vaiUzk, vaiDzer);

					
					Pasutijumi.add(pas);
					klientsRindaAktiva = true;
					maks -= cena;
					JOptionPane.showMessageDialog(null, "Atlikusī nauda makā: "+maks+" EUR", "Naudas lietas.", JOptionPane.PLAIN_MESSAGE);
				}else
					JOptionPane.showMessageDialog(null, "Nepietiek naudas!! Ej strādāt!", "Bezdarbnieks...", 
							JOptionPane.ERROR_MESSAGE);
					break;
					
				case 1:
					if (!Pasutijumi.isEmpty()) {
						

				        String cilveks = virknesParbaude("Kā tevi sauc?", "Agita");
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
					klientsAktivs = false;
					aktivaisKlients = null;
					klientsRindaAktiva = false;

					break;
				}
				
			}while(opc != 2);
			
		case 2:
			JOptionPane.showMessageDialog(null, "Līdz citai reizei!", "Uz redzi.", JOptionPane.PLAIN_MESSAGE);
			break;
			
			}
		
		
		
		}while(izvele != 2);
	}

}

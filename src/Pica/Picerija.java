package Pica;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


public class Picerija {
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
	static double c;
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
				c = Pica.cena(m, p, izm, piegade, vaiUzk, vaiDzer);
				
				if(piegade == true) {
					pas = new Pica(ad, k, m, p, u, dz, tel, izm, c, piegade, vaiUzk, vaiDzer);
				}else
					pas = new Pica(k, m, p, u, dz, izm, c, piegade, vaiUzk, vaiDzer);
				
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
					Izpilditie.add(Pasutijumi.poll());
					maks += c;
					JOptionPane.showMessageDialog(null,
							"Klients apkalpots."
							+ "\nIekasētā nauda: " +maks, "Informācija", JOptionPane.INFORMATION_MESSAGE);
				}
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
				
				break;
			}
			}while(opc != 4);
			break;
		case 1:
			
			break;
			}
		
		
		
		}while(izvele != 2);
	}

}

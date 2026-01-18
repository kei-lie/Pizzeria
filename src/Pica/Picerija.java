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
	static String[] darbibas = {"Darbinieks", "Klients", "Apturēt"};
	static String[] merces = {"Kečups", "Majonēze", "Asā mērce", "Ķiploku mērce", "Siera mērce"};
	static String[] piedevas = {"Siers", "Tomāti", "Peperoni", "Sīpoli", "Ananasi"};
	static String[] klients = {"Jānis", "Pēteris", "Anna", "Līga", "Otto"};
	static String[] uzkodas = {"Ķiploku grauzdiņi", "Fri kartupeļi", "Vistas nageti", "Mocrellas nūjiņas", "Salāti"};
	static String[] dzerieni = {"Ūdens", "Vīnogu sula", "Coca-cola", "Zaļā tēja", "Kafija"};
	static int[] izmers = {25, 30, 40, 45, 50};
	static boolean piegade;
	static String[] adrese = {"Zāļu iela 54", "Krūmu iela 2", "O. Kalpaka iela 39"};
	
	public static void main(String[] args) {
		int izvele;
		Random rand = new Random();
	//	Pica pas = null;
		
		
		
		
		
		do {
		izvele = JOptionPane.showOptionDialog(null,"Izvēlies statusu:", "Statuss", 
				JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, darbibas, darbibas[0]);
		switch(izvele) {
		case 0:
			
			int skaits = rand.nextInt(5)+1;
			String m, p, k, u, dz;
			int pieg = rand.nextInt(2)+1;
			piegade = (pieg == 0)? true:false;
			
			for(int i=0; i<=skaits; i++) {
				m = merces[rand.nextInt(5)];
				p = piedevas[rand.nextInt(5)];
				u = uzkodas[rand.nextInt(5)];
				dz = dzerieni[rand.nextInt(5)];
				k = klients[rand.nextInt(5)];
				
			//	Pasutijumi.add(pas);
			}
		/*	if (!Pasutijumi.isEmpty()) {
				String teksts = "Pasūtījumu skaits: " + skaits +
						"\n_________________________________\n";
				
				for (int i = 0; i < skaits; i++) {
					teksts += pas.get(i).getInfo()+
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
			*/
			JOptionPane.showMessageDialog(null, skaits, "Pārbaude\n", JOptionPane.INFORMATION_MESSAGE);
			String[] opcijas = {"Apkalpot", "Apskatīt esošos pasūtījumus", 
					"Apskatīt izpildītos pasūtījumus", "Uztaisīt sev picu", "Apturēt"};
			
			int opc = JOptionPane.showOptionDialog(null,"Izvēlies darbību:", "Opcijas", 
					JOptionPane.DEFAULT_OPTION, 
					JOptionPane.INFORMATION_MESSAGE, null, opcijas, opcijas[0]);
			switch(opc) {
			case 0:
				
				break;
			}
			}
			break;
		}while(izvele != 2);
	}

}

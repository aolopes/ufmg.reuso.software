package br.ufmg.reuso.ui;
import javax.swing.JOptionPane;


public class ScreenSelecionaTemporizador {

		private static boolean valorSelecionado = true;
		private static ScreenSelecionaTemporizador instancia;

		public static boolean getValorSelecionado() {
			return ScreenSelecionaTemporizador.valorSelecionado;
		}
		
		
		public static void ScreenSeleciona() {
		
			
			if(instancia==null) {
				
				//int input = JOptionPane.showConfirmDialog(null,"Deseja jogar com temporização?", "Temporização", JOptionPane.YES_NO_OPTION);
				
				//if(input==0) {
				//	ScreenSelecionaTemporizador.valorSelecionado = true;
				//}
				
				//else {
				//	ScreenSelecionaTemporizador.valorSelecionado = false;
				//}
				
				//instancia = new ScreenSelecionaTemporizador();
				
			}
			
		}
		

}
	

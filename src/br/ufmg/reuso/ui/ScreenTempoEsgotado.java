package br.ufmg.reuso.ui;

import javax.swing.JOptionPane;

public class ScreenTempoEsgotado{


	private static boolean ativo = true;
	private static ScreenTempoEsgotado instancia;
	
	private ScreenTempoEsgotado() 
	{
	}
	
	public static ScreenTempoEsgotado novaScreen(boolean var) {
		
		if(instancia==null)
		{
			instancia = new ScreenTempoEsgotado();
			ScreenTempoEsgotado.setAtivo(false);
		}
	
		ScreenTempoEsgotado.setAtivo(var);
		JOptionPane.showMessageDialog(null, "Seu tempo para tomada de decisão se esgotou!", "Tempo Esgotado", JOptionPane.ERROR_MESSAGE);

		return instancia;
	}
	
	public static void setAtivo(boolean var) {
		ScreenTempoEsgotado.ativo = var;
	}
	
	public static boolean getAtivo() {
		
		return ScreenTempoEsgotado.ativo;
		
	}
	
}

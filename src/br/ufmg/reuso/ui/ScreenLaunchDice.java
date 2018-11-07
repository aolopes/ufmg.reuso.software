/*
 * Federal University of Minas Gerais 
 * Department of Computer Science
 * Simules-SPL Project
 *
 * Created by Alisson
 * Date: 18/07/2011
 */
package br.ufmg.reuso.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//===========================================================================//
//======================Início da classe ScreenLaunchDice====================//
//===========================================================================//

/**
 * @author Alisson [AS] A caixa de diálogo intermediária que solicita 
 * ao usuário a lançar os dados.
 * 
 * Recebe como string o nome do Jogador e a mensagem a ser exibida.
 * 
 *	boolean Retorno = createAndShowLaunchDice(String NomeDoJogador, String Mensagem).getReturn() ;
 * 
 * Ou instanciando a classe ScreeLaunchDice como uma janela de diálogo.
 * 
 */
public class ScreenLaunchDice extends JDialog implements ActionListener{

	private static final long serialVersionUID = 1L;

	private Boolean boolReturn;
	
	//===========================================================================//
	/**
	 * Construtor da janela de solicitação de lançamento dos dados
	 * @param tabuleiro 
	 */
	public ScreenLaunchDice(ScreenTabuleiro tabuleiro, String NomeDoJogador, String Mensagem) {
		super(tabuleiro);
		
		this.setLocationRelativeTo(tabuleiro);
		this.setLayout(new BorderLayout());
		
		boolReturn = false;

	
		JPanel Tpanel = new JPanel();
		Tpanel.setLayout(null);

		Dimension dimensionPanel = new Dimension(300, 200);
		Tpanel.setPreferredSize(dimensionPanel);

		Dimension dimensionButton = new Dimension(120, 30);

		// Calcula a posição inicial e final dos botões na tela
		// de modo a deixá-los centralizados.

		int posX = (dimensionPanel.width / 2) - (dimensionButton.width / 2);
		int posY = (dimensionPanel.height / 10);

		// Rótulo do nome do jogador
		JLabel LabelGamer = new JLabel( NomeDoJogador,JLabel.CENTER);
		LabelGamer.setPreferredSize(dimensionButton);		
		LabelGamer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		posY = 2*(dimensionPanel.height / 10)- (dimensionButton.height);
		LabelGamer.setBounds(posX, posY, dimensionButton.width,
				dimensionButton.height);
		Tpanel.add(LabelGamer);

		String Mensagem1 = "";
		String Mensagem2 = "";
		if( Mensagem.length()>45){
			int j = 0;
			for(int i = 45; i >= 0; i--){
				if( Mensagem.charAt(i) == ' ' ){
					j = i;
					i = 0;
				}
				Mensagem1 = Mensagem.substring(0, j);
				Mensagem2 = Mensagem.substring(j+1, Mensagem.length());
			}
			
		}
		else {
			Mensagem1 = Mensagem;
		}
		// Rótulo com a mensagem
		JLabel LabelInfo = new JLabel(Mensagem1,JLabel.CENTER);
		LabelInfo.setPreferredSize(dimensionButton);		
		posY = 5*(dimensionPanel.height /10 ) - (dimensionButton.height);
		LabelInfo.setBounds(5, posY, dimensionPanel.width-10, dimensionButton.height);
		Tpanel.add(LabelInfo);
		
		// Rótulo com a mensagem
		JLabel LabelInfo2 = new JLabel(Mensagem2 ,JLabel.CENTER);
		LabelInfo2.setPreferredSize(dimensionButton);
		posY = 5*(dimensionPanel.height /10 );
		LabelInfo2.setBounds(5, posY, dimensionPanel.width-10, dimensionButton.height);
		Tpanel.add(LabelInfo2);
		
		// Botão de lançar os dados		
		JButton buttonLaunch = new JButton("Lan�ar Dados");
		buttonLaunch.setMnemonic(KeyEvent.VK_L);
		buttonLaunch.setActionCommand("OK");
		buttonLaunch.setPreferredSize(dimensionButton);
		posY = (9 * (dimensionPanel.height / 10)) - (dimensionButton.height);
		buttonLaunch.setBounds(posX, posY, dimensionButton.width,
				dimensionButton.height);		
		buttonLaunch.addActionListener(this);
		Tpanel.add(buttonLaunch);
		
		getRootPane().setDefaultButton(buttonLaunch);		
		
		add(Tpanel);

		setResizable(false);

	}

	//===========================================================================//
	/** Controlador de eventos dos botões. */
	@Override
	public void actionPerformed(ActionEvent e) {

			ScreenLaunchDice.this.dispose();
			boolReturn = true;
		
	}
	
	//===========================================================================//
	/**
	 * Retorna true se o jogador pressionou jogar dado ou 
	 * false se o jogador fechou a janela.
	 */
	public boolean getReturn() {
		return boolReturn;
	}

	//===========================================================================//
	/**
	 * Cria a tela e a faz visível. Por segurança é o método chamado pela Thread
	 * para construção da GUI
	 * Recebe um string com o nome do jogador e retorna a caixa de dialogo.
	 * @param tabuleiro 
	 */
	public static ScreenLaunchDice createAndShowLaunchDice(ScreenTabuleiro tabuleiro, String NomeDoJogador, String Mensagem) {

		// Cria e configura a tela.
		ScreenLaunchDice scr = new ScreenLaunchDice(tabuleiro, NomeDoJogador, Mensagem);
		scr.rootPane.setOpaque(true);
		scr.pack();
		scr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		scr.addWindowListener(ScreenInteraction.getScreenInteraction().windowsExitGame());
		scr.setModal(true);
		scr.setLocationRelativeTo(null);
		scr.setVisible(true);

		return scr;
	}

	/****************************************************************************/
	/**************************** TEST FUNCTION *********************************/
	/****************************************************************************/
	public static void main(String[] args) {
		// O uso da Thread com a utilização de invokeLater tem a
		// função da construção total da GUI para somente então
		// apresentá-la na tela.
		final ScreenTabuleiro tabuleiro = ScreenTabuleiro.createAndShowTabuleiro(null, null);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				System.out.println(createAndShowLaunchDice(tabuleiro, "Alisson", "Lance os dados para definir a ordem do jogo!").getReturn());
				System.out.println(createAndShowLaunchDice(tabuleiro, "Alisson","Voc� empatou com outro jogador! Lance os dados novamente!").getReturn());

			}
		});
	}

}//Fim da classe
//===========================================================================//
//=========================Fim da classe ScreenLaunchDice====================//
//===========================================================================//



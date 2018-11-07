package br.ufmg.reuso.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *         A caixa de diálogo intermediária que define a
 *         configuração do jogo ou início do jogo com configuração padrão.
 * 
 *         String Retorno = createAndShowGetModo().getReturn() ;
 * 
 *         Ou instanciando a classe ScreenModo como uma janela de diálogo.
 * 
 */
public class ScreenStart extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	static String startString = "Start";
	static String configString = "config";
	static String criarProjetoString = "Projeto";

	private String stringReturn;

	// =====================================================================================//
	/**
	 * Construtor da janela de seleção início ou configuração
	 */
	public ScreenStart(ScreenTabuleiro tabuleiro) {
		super(tabuleiro);

		this.setLayout(new BorderLayout());

		// Cria os botões de acesso.

		JPanel Tpanel = new JPanel();
		Tpanel.setLayout(null);

		Dimension dimensionPanel = new Dimension(300, 150);
		Tpanel.setPreferredSize(dimensionPanel);

		Dimension dimensionButton = new Dimension(120, 30);

		//Cálculo da posição dos botões na tela (centralizados)
		
		int posX = (dimensionPanel.width / 2) - (dimensionButton.width / 2);
		int posY = (dimensionPanel.height / 5);

		JButton buttonStart = new JButton("Come�ar jogo");
		buttonStart.setMnemonic(KeyEvent.VK_S);

		buttonStart.setActionCommand(startString);
		buttonStart.setPreferredSize(dimensionButton);
		buttonStart.setBounds(posX, posY, dimensionButton.width,
				dimensionButton.height);

		posY = (3 * (dimensionPanel.height / 5)) - (dimensionButton.height);

		JButton buttonConfig = new JButton("Configura��es");
		buttonConfig.setMnemonic(KeyEvent.VK_C);
		buttonConfig.setActionCommand(configString);
		buttonConfig.setPreferredSize(dimensionButton);
		buttonConfig.setBounds(posX, posY, dimensionButton.width,
				dimensionButton.height);

		posY = (4 * (dimensionPanel.height / 5)) - (dimensionButton.height);
		
		JButton buttonProjeto = new JButton("Criar Projeto");
		buttonProjeto.setMnemonic(KeyEvent. VK_P);
		buttonProjeto.setActionCommand(criarProjetoString);
		buttonProjeto.setPreferredSize(dimensionButton);
		buttonProjeto.setBounds(posX, posY, dimensionButton.width,
				dimensionButton.height);


		// Registra os objetos no controle de eventos.
		
		buttonStart.addActionListener(this);
		buttonConfig.addActionListener(this);
		buttonProjeto.addActionListener(this);

		Tpanel.add(buttonStart);
		Tpanel.add(buttonConfig);
		Tpanel.add(buttonProjeto);
		add(Tpanel);

		getRootPane().setDefaultButton(buttonStart);

		setResizable(false);	
	}

	// =====================================================================================//
	/** Controlador de eventos dos botões. */
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand() == "config") {			
			stringReturn = e.getActionCommand();
			ScreenTabuleiro tabuleiro = ScreenTabuleiro.createAndShowTabuleiro(null, null);
			ScreenModo.createAndShowGetModo(tabuleiro).getModeGame();
			
		} else if (e.getActionCommand() == "Start") {
			ScreenSelecionaTemporizador.ScreenSeleciona();
			ScreenStart.this.dispose();
			stringReturn = e.getActionCommand();
			
		} else {
			ScreenTabuleiro tabuleiro = ScreenTabuleiro.createAndShowTabuleiro(null, null);
			ScreenCreateProject.createAndShowGetCProject(tabuleiro);
			ScreenStart.this.dispose();	
			stringReturn = e.getActionCommand();
			
		}
	}

	// =====================================================================================//
	/**
	 * Retorna o modo do jogo selecionado na tela
	 */
	public String getReturn() {
		return stringReturn;
	}

	// =====================================================================================//
	/**
	 * Retorna um ImageIcon ou null se o caminho for inválido.
	 * 
	 * @param path
	 *            - String com o caminho da imagem
	 * @return - ImageIcon para ser inserido em um JLabel
	 */
	
	protected static ImageIcon createImageIcon(String path) {
		File fl = new File(path);
		if (fl.isFile()) {
			return new ImageIcon(path);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Cria a tela e a faz visível. Por segurança é o método chamado pela Thread
	 * para construção da GUI
	 * 
	 * @param tabuleiro
	 *            - tabuleiro atual
	 * @return - tela com as opções.
	 */
	
	public static ScreenStart createAndShowGetModo(ScreenTabuleiro tabuleiro) {

		// Cria e configura a tela.
		ScreenStart scr = new ScreenStart(tabuleiro);
		scr.rootPane.setOpaque(true);
		scr.pack();		
		scr.setModal(true);
		scr.setLocationRelativeTo(null);
		scr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		scr.addWindowListener(ScreenInteraction.getScreenInteraction().windowsExitGame());
		scr.setVisible(true);
		return scr;
	}
	
	public static void main(String[] args) {

		final ScreenTabuleiro tabuleiro = ScreenTabuleiro
				.createAndShowTabuleiro(null, null);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				System.out.println(createAndShowGetModo(tabuleiro).getReturn());
			}
		});
	}

}

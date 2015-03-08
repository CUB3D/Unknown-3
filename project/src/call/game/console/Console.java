package call.game.console;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Console implements ActionListener
{
	private static Console instance;
	
	private static Set<ICommandListener> listeners = new HashSet<ICommandListener>();
	
	private JTextField input;
	
	private static JTextArea inputLog;
	
	
	private JFrame frame;
	
	private Console()
	{
		this.frame = new JFrame("Console");
		
		this.frame.setSize(300, 300);
		this.frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setLayout(null);
		this.frame.setResizable(false);
		
		this.input = new JTextField();
		this.input.setBounds(2, 245, 290, 25);
		this.input.addActionListener(this);
		this.frame.add(input);
		
		inputLog = new JTextArea();
		inputLog.setBounds(2, 2, 290, 240);
		inputLog.setEditable(false);
		this.frame.add(inputLog);
		
		registerComandListener(new DebugCommand());
		registerComandListener(new EntityListCommand());
	}
	
	private void sendCommand()
	{
		boolean b = false;
		
		for(ICommandListener icl : listeners)
			if(icl.onCommand(this, input.getText()))
			{
				b = true;
				break;
			}
		
		String inp;
		
		if(!b)
			inp = "Unknown command: " + input.getText();
		else
			inp = input.getText();
		
		printLine(inp);
		input.setText("");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		sendCommand();
	}
	
	public static void printLine(String s)
	{
		inputLog.setText(inputLog.getText() + s + "\n");
	}
	
	public static void registerComandListener(ICommandListener listen)
	{
		listeners.add(listen);
	}

	public static void open()
	{
		getInstance().frame.setVisible(true);
	}
	
	public static Console getInstance()
	{
		if(instance == null)
			instance = new Console();
		
		return instance;
	}
}

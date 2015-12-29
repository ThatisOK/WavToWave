import javax.swing.*;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;

public class Main extends JFrame implements ActionListener {

	private JFileChooser file;
	private JMenu menu;
	private JMenuItem open;
	private JMenuItem close;
	private JMenuBar bar;

	public Main() {
		menu = new JMenu("文件");
		open = new JMenuItem("打开");
		close = new JMenuItem("退出");
		bar = new JMenuBar();
		menu.add(open);
		menu.add(close);
		bar.add(menu);
		this.setJMenuBar(bar);
		open.addActionListener(this);
		close.addActionListener(this);
		file = new JFileChooser();
		file.setFileFilter(new FileNameExtensionFilter("音频", "wav"));
		int action = file.showOpenDialog(this);
		if (action == JFileChooser.CANCEL_OPTION) {
			System.exit(0);
		}
		String filename = file.getSelectedFile().getAbsolutePath();
		this.drawWave(filename);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JMenuItem item = (JMenuItem) e.getSource();
		if (item == open) {
			file.showOpenDialog(this);
			String filename = file.getSelectedFile().getAbsolutePath();
			this.drawWave(filename);
		}
		if (item == close) {
			System.exit(0);
		}

	}

	public void drawWave(String filename) {
		WavReader reader = new WavReader(filename);
		int[][] data = reader.getData();
		int channel = reader.getChannels();
		int BitsPerSample = reader.getBitsPerSample();
		DrawWave dw = new DrawWave(data, BitsPerSample, channel);
		this.add(dw);
		this.setTitle(filename);
		this.setSize(800, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {

		Main frame = new Main();

	}

}

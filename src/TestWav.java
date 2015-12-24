import java.io.*;

import javax.swing.JFrame;

public class TestWav {
	public static void main(String[] args) {
		String fileName = "/Users/mengchun/Downloads/WaveAccess-master/rawwavs/wav_40_16_2_pcm.wav";
		JFrame frame = new JFrame();
		WavReader reader = new WavReader();
		BufferedInputStream in = reader.openWav(fileName);
		int[][] data = reader.getData(in);
		int channel = reader.getChannels();
		int BitsPerSample = reader.getBitsPerSample();
		DrawWave dw = new DrawWave(data, BitsPerSample, channel);
		frame.add(dw);
		frame.setTitle(fileName);
		frame.setSize(800, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}
}

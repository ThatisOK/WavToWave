import java.awt.*;
import java.io.BufferedInputStream;

import javax.swing.*;
public class DrawSingleWave extends JPanel {
	
	private int[] data;
	private int BitsPerSample;
	
	public DrawSingleWave(int[] data, int BitsPerSample){
		this.data = data;
		this.BitsPerSample = BitsPerSample;
	}
	
	public void paintComponent(Graphics g){
		int width = this.getWidth();
		int height = this.getHeight();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);		
		int length = data.length;
		int prex = 0;
		int prey = 0;
		int x = 0;
		int y = 0;
		g.setColor(Color.red);
		//k保证可以画下所有的数据
		int k = length / (width-1);
		for(int i=0; i<width; ++i){  
            x = i;
            if(this.BitsPerSample == 8)
            	y = data[i * k]; 
            else if(BitsPerSample == 16)
            	y = height- (int)( data[i * k]/200+height/2);
            if(i!=0){  
                g.drawLine(x, y, prex, prey);  
            }  
            prex = x;  
            prey = y;  
        }  
	}
	
//	public static void main(String[] args){
//		String fileName = "/Users/mengchun/Downloads/WaveAccess-master/rawwavs/wav_40_16_1_pcm.wav";
//		JFrame frame = new JFrame();  
//		WavReader reader = new WavReader();
//		BufferedInputStream in = reader.openWav(fileName);
//		int[] data = reader.getData(in)[0];
//		DrawWave dw = new DrawWave(data);
//		frame.add(dw);  
//        frame.setTitle(fileName);  
//        frame.setSize(800, 400);  
//        frame.setLocationRelativeTo(null);  
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//        frame.setVisible(true); 
//		
//	}
	

}

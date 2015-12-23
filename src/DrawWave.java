
import java.awt.*;
import java.io.BufferedInputStream;

import javax.swing.*;
public class DrawWave extends JPanel {
	
	private int[][] data;
	private int BitsPerSample;
	private int channel;
	
	public DrawWave(int[][] data, int BitsPerSample, int channel){
		this.data = data;
		this.BitsPerSample = BitsPerSample;
		this.channel = channel;
	}
	
	public void paintComponent(Graphics g){
		int width = this.getWidth();
		int height = this.getHeight();
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);		
		if(this.channel > 1){
			int length = data[0].length;
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
	            	y = data[0][i * k]; 
	            else if(this.BitsPerSample == 16)
	            	y = height- (int)( data[0][i * k]/200+height/2);    
	            if(i!=0){  
	                g.drawLine(x, y, prex, prey);  
	            }  
	            prex = x;  
	            prey = y;  
	        } 
			g.setColor(Color.green);
			x = 0;
			y = 0;
			prex = 0;
			prey = 0;
			//k保证可以画下所有的数据
			for(int i=0; i<width; ++i){  
	            x = i;  
	            if(this.BitsPerSample == 8)
	            	y = data[1][i * k]; 
	            else if(this.BitsPerSample == 16)   
	            	y = height- (int)( data[1][i * k]/200+height/2);    
	            if(i!=0){  
	                g.drawLine(x, y, prex, prey);  
	            }  
	            prex = x;  
	            prey = y;  
	        }
		}else{
			int length = data[0].length;
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
	            	y = data[0][i * k]; 
	            else if(this.BitsPerSample == 16)
	            	y = height- (int)( data[0][i * k]/200+height/2);    
	            if(i!=0){  
	                g.drawLine(x, y, prex, prey);  
	            }  
	            prex = x;  
	            prey = y;  
	        } 
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


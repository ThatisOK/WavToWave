import java.io.*;

public class Test {
	
	public static void main(String[] args){
		try {
			BufferedInputStream in = new BufferedInputStream(new FileInputStream("/Users/mengchun/Downloads/WaveAccess-master/rawwavs/wav_40_16_1_pcm.wav"));
//			int i = in.read();
			byte[] b = new byte[4];
			long[] l = new long[4];
			in.read(b);
			System.out.println(new String(b));
		    for(int i=0; i<4; i++){
		    	l[i] = in.read();
		    }
			System.out.println(l[0] | (l[1]<<8) | (l[2]<<16) | (l[3]<<24) );
			in.read(b);
			System.out.println(new String(b));
			in.read(b);
			System.out.println(new String(b));
			for(int i=0; i<4; i++){
		    	l[i] = in.read();
		    }
			System.out.println(l[0] | (l[1]<<8) | (l[2]<<16) | (l[3]<<24) );
//			while(i != -1){
//				System.out.println(i);
//				i = in.read();
//			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

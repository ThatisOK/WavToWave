import java.io.*;
public class WavReader {
	
	private String fileName;
//	private int data[][];
	/*
	 * RIFF WAVE Chunk
	 */
	private String riffID;
	private long riffSize;
	private String riffType;
	
	/*
	 * Format Chunk
	 */
	private String formatId;
	private long formatSize;
	
	/*
	 * WAVE_FORMAT
	 */
	private int formatTag;
	private int channels;
	private long SamplesPerSec;
	private long AvgBytesPerSec;
	private int BlockAlign;
	private int BitsPerSample;
	private int Additional;
	
	/*
	 *  Fact Chunk
	 */
	private String factId;
	private long factSize;
	
	/*
	 * Data Chunk
	 */
	private String dataId;
	private long dataSize;
	
	
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getRiffID() {
		return riffID;
	}

	public void setRiffID(String riffID) {
		this.riffID = riffID;
	}

	public long getRiffSize() {
		return riffSize;
	}

	public void setRiffSize(long riffSize) {
		this.riffSize = riffSize;
	}

	public String getRiffType() {
		return riffType;
	}

	public void setRiffType(String riffType) {
		this.riffType = riffType;
	}

	public String getFormatId() {
		return formatId;
	}

	public void setFormatId(String formatId) {
		this.formatId = formatId;
	}

	public long getFormatSize() {
		return formatSize;
	}

	public void setFormatSize(long formatSize) {
		this.formatSize = formatSize;
	}

	public int getFormatTag() {
		return formatTag;
	}

	public void setFormatTag(int formatTag) {
		this.formatTag = formatTag;
	}

	public int getChannels() {
		return channels;
	}

	public void setChannels(int channels) {
		this.channels = channels;
	}

	public long getSamplesPerSec() {
		return SamplesPerSec;
	}

	public void setSamplesPerSec(long samplesPerSec) {
		SamplesPerSec = samplesPerSec;
	}

	public long getAvgBytesPerSec() {
		return AvgBytesPerSec;
	}

	public void setAvgBytesPerSec(long avgBytesPerSec) {
		AvgBytesPerSec = avgBytesPerSec;
	}

	public int getBlockAlign() {
		return BlockAlign;
	}

	public void setBlockAlign(int blockAlign) {
		BlockAlign = blockAlign;
	}

	public int getBitsPerSample() {
		return BitsPerSample;
	}

	public void setBitsPerSample(int bitsPerSample) {
		BitsPerSample = bitsPerSample;
	}

	public int getAdditional() {
		return Additional;
	}

	public void setAdditional(int additional) {
		Additional = additional;
	}

	public String getFactId() {
		return factId;
	}

	public void setFactId(String factId) {
		this.factId = factId;
	}

	public long getFactSize() {
		return factSize;
	}

	public void setFactSize(long factSize) {
		this.factSize = factSize;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

	public long getDataSize() {
		return dataSize;
	}

	public void setDataSize(long dataSize) {
		this.dataSize = dataSize;
	}

	public BufferedInputStream openWav(String fileName){
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return in;
	}
	
	public int[][] getData(BufferedInputStream in){
		int[][] data = null;
		int len = 4;
		this.riffID = this.getString(in, len);
		System.out.println("RIFFID:"+this.riffID);
		this.riffSize = this.getLong(in, len);
		this.riffType = this.getString(in, len);
		System.out.println("RIFF类型:"+this.riffType);
		this.formatId = this.getString(in, len);
		System.out.println("Format:"+this.formatId);
		this.formatSize = this.getLong(in, len);
		this.formatTag = this.getInt(in, 2);
		this.setChannels(this.getInt(in, 2));
		System.out.println("声道:"+this.channels);
		this.SamplesPerSec = this.getLong(in, len);
		System.out.println("采样频率:"+this.SamplesPerSec);
		this.AvgBytesPerSec = this.getLong(in, len);
		this.BlockAlign = this.getInt(in, 2);
		this.BitsPerSample = this.getInt(in, 2);
		System.out.println("采样位数:"+this.BitsPerSample);
		this.Additional = 0;
		if(this.factSize == 18){
			this.Additional = this.getInt(in, 2);
		}
		this.dataId = this.getString(in, len);
		System.out.println("DataID:"+this.dataId);
		this.dataSize = this.getLong(in, len);
//		System.out.println(this.dataSize);
		int length = (int) (this.dataSize / (this.BitsPerSample / 8) / this.channels);
		data = new int[this.channels][length];
		for(int i=0; i<length; i++){
			for(int j=0; j<this.channels; j++){
				if(this.BitsPerSample == 8){
					try {
						data[j][i] = in.read();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(this.BitsPerSample == 16){
					data[j][i] = this.getInt(in, 2);
				}
			}
		}
		return data;
		
	}
	
	public String getString(BufferedInputStream in, int len){
		byte[] b = new byte[len];
		try {
			if(in.read(b) != len){
				throw new IOException("no more data!!!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(b);
	}
	
	public long getLong(BufferedInputStream in, int len){
		long[] l = new long[len];
		for(int i=0; i<len; i++){
			try {
				l[i] = in.read();
				if(l[i] == -1){
					throw new IOException("no more data!!!");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return l[0] | (l[1] << 8) | (l[2] << 16) | (l[3] << 24);
		
	}
	
	public int getInt(BufferedInputStream in, int len){
		byte[] b = new byte[2];
		int result = 0;
		try {
			if (in.read(b) != 2)
				throw new IOException("no more data!!!");
			result = (b[0] & 0x000000FF) | (((int) b[1]) << 8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	

}

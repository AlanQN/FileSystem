package model;

/**
 * 已打开文件表：用来记录打开或建立文件的相关内容
 * fileName：文件绝对路径名
 * attribute：文件的属性，用 1个字节表示，所以此用 char 类型
 * diskNumber：文件起始盘块号
 * length：文件长度，文件占用的字节数
 * flag：操作类型，用“0”表示以读操作方式打开文件，用“1”表示以写操作方式打开文件
 * read：读文件的位置，文件打开时 dnum 为文件起始盘块号，bnum 为“0”
 * write：写文件的位置，文件刚建立时 dnum 为文件起始盘块号，bnum 为“0 ，打开文件时 dnum 和 bnum 为文件的末尾位置
 */

public class OpenFile {
	
	private String fileName;
	private int fileAttribute; 
	private int diskNumber; 
	private int length;
	private int flag;
	private Pointer read; 
	private Pointer write;
	
 	public OpenFile() {
 		
 		fileName = new String();
 		diskNumber = -1;
 		length = 0;
 		flag = 0;
 		
 	}
 	
 	
 	
	public OpenFile(String fileName, int fileAttribute, int diskNumber, int length, int flag, Pointer read,
			Pointer write) {
		super();
		this.fileName = fileName;
		this.fileAttribute = fileAttribute;
		this.diskNumber = diskNumber;
		this.length = length;
		this.flag = flag;
		this.read = read;
		this.write = write;
	}



	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFileAttribute() {
		return fileAttribute;
	}
	public void setFileAttribute(int fileAttribute) {
		this.fileAttribute = fileAttribute;
	}
	public int getDiskNumber() {
		return diskNumber;
	}
	public void setDiskNumber(int diskNumber) {
		this.diskNumber = diskNumber;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public Pointer getRead() {
		return read;
	}
	public void setRead(Pointer read) {
		this.read = read;
	}
	public Pointer getWrite() {
		return write;
	}
	public void setWrite(Pointer write) {
		this.write = write;
	} 
 	
}

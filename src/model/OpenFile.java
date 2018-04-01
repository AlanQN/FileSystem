package model;

/**
 * �Ѵ��ļ���������¼�򿪻����ļ����������
 * fileName���ļ�����·����
 * attribute���ļ������ԣ��� 1���ֽڱ�ʾ�����Դ��� char ����
 * diskNumber���ļ���ʼ�̿��
 * length���ļ����ȣ��ļ�ռ�õ��ֽ���
 * flag���������ͣ��á�0����ʾ�Զ�������ʽ���ļ����á�1����ʾ��д������ʽ���ļ�
 * read�����ļ���λ�ã��ļ���ʱ dnum Ϊ�ļ���ʼ�̿�ţ�bnum Ϊ��0��
 * write��д�ļ���λ�ã��ļ��ս���ʱ dnum Ϊ�ļ���ʼ�̿�ţ�bnum Ϊ��0 �����ļ�ʱ dnum �� bnum Ϊ�ļ���ĩβλ��
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

package model;
/**
 * �ļ�����ϵͳ�� 
 * 	��ʼ��FAT����128�ռ128���ֽڣ�2�����������
 * 		1����ǰ�����������������ڴ洢FAT��,����fat[0] = -1�� fat[1] = -1
 * 		2����������������ڴ洢ϵͳ���ݣ���fat[2] = -1
 * 		3�������ʼ��Ϊ���п죬��fat[n] = 0 (2 < n < 128)
 */
public class FAT {

	public  static FAT fat;
	private int[] item;
	
	public FAT() {
		
		item = new int[128];
		item[0] = -1;
		item[1] = -1;
		item[2] = -1;
		for(int i = 3; i < 128; i++) {
			
			item[i] = 0;
		}
	}
	
	
	/**
	 * 
	 */
	public static FAT getInstance() {
		
		if(fat == null) {
			fat = new FAT();
		}
		
		return fat;
	}
	
	/**
	 * ����fat�����ҿ��еĴ����̿飬������ҳɹ����������ţ����򷵻�-1��
	 */
	public int getFreeDisk() {
		
		for(int i = 3; i < 128; i++) {
			
			if(item[i] == 0) {
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * ���ݸ����ļ������̿�������������һ������̿�����;
	 * @param diskIndex �ļ���ʼ�̿���
	 * @return   �ļ���һ���̿���
	 */
	public int nextDiskIndex(int diskIndex) throws ArrayIndexOutOfBoundsException{
		
		if(diskIndex < 3 || diskIndex > 127) {
			throw new ArrayIndexOutOfBoundsException(diskIndex);
		}
		return item[diskIndex];
	}
	
	
	
	/**
	 * ������FAT������ļ���ʼ���̿�ţ���ȡ�ļ�ռ�ݴſ鳤��
	 * ����ֵ�� �ļ����ڣ��򷵻��ļ����ȣ����򷵻�-1
	 * ������ beginDiskNum �ļ���ʼ���̿�� 
	 */
	public int getFileLength(int beginDiskNum) {
		
		if(item[beginDiskNum] == 0) {
			
			System.out.println("�ļ���ʼ���̿�Ŵ��󣡣���");
			return -1;
		}
		int len = 0;
		while(beginDiskNum != -1) {
			
			len++;
			beginDiskNum = item[beginDiskNum];
		}
		
		return len;
	}
	
	/**
	 * ������ ��ȡ���п��д��̿����
	 * ����ֵ�� ���ؿ��д��̿����
	 * ������ ��
	 */
	public int getFreeDiskNum() {
		
		int n = 0;
		for(int i = 3; i < 128; i++) {
			 
			if(item[i] == 0) n++;
		}
		
		return n;
	}
	
	public int[] getItem() {
		return item;
	}

	
	
//---------------------------------------------------------------------------	



}

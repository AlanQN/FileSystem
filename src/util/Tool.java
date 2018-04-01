package util;

import model.Disk;
import model.FolderNode;

public class Tool {

	/**
	 * �жϵ�ǰĿ¼�Ƿ���ʣ��Ŀռ�����µ�Ŀ¼��
	 * ����У��򷵻ط������Ŀ¼��ı�ţ����򷵻�-1;
	 */
	public static int remaindedCapacity(Disk[] disks, int folderIndex) {
		
		for(int i = 0; i < 8; i++) {
			
			if(disks[folderIndex].getFolderNode()[i].getNodeBeginDisk() == 0) return i;
		}
		return -1;
	}

	

}

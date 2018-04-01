package util;

import model.Disk;
import model.FolderNode;

public class Tool {

	/**
	 * 判断当前目录是否还有剩余的空间分配新的目录项
	 * 如果有，则返回分配给新目录项的编号，否则返回-1;
	 */
	public static int remaindedCapacity(Disk[] disks, int folderIndex) {
		
		for(int i = 0; i < 8; i++) {
			
			if(disks[folderIndex].getFolderNode()[i].getNodeBeginDisk() == 0) return i;
		}
		return -1;
	}

	

}

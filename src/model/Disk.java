package model;

//import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

/**
 * 	Disk 用于模拟磁盘块：
 * 	content： 用于保存文件中的数据
 *  folderNode：存储目录中8个目录项对应的索引,
 *  	1.folderNode[i] = 0, 表示空闲磁盘块
 *  	2.其他，folderNode[i]表示未被使用
 *  
 */

public class Disk {
	
	private String content;
	private FolderNode[] folderNode = new FolderNode[8];;
	
	public Disk() {
		
		content = new String();
		
		for(int i = 0; i < 8; i++) { 
			
			folderNode[i] = new FolderNode();
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public FolderNode[] getFolderNode() {
		return folderNode;
	}

	public void setFolderNode(FolderNode[] folderNode) {
		this.folderNode = folderNode;
	}


}

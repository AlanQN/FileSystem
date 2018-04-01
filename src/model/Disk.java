package model;

//import jdk.internal.org.objectweb.asm.tree.IntInsnNode;

/**
 * 	Disk ����ģ����̿飺
 * 	content�� ���ڱ����ļ��е�����
 *  folderNode���洢Ŀ¼��8��Ŀ¼���Ӧ������,
 *  	1.folderNode[i] = 0, ��ʾ���д��̿�
 *  	2.������folderNode[i]��ʾδ��ʹ��
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

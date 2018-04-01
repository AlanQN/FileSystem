package model;

/**
 * 锟角硷拷锟侥硷拷/目录锟矫碉拷目录锟筋，锟斤拷锟叫帮拷锟斤拷
 * 	1.锟节碉拷路锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷目录锟斤拷锟节碉拷路锟斤拷
 * 	2.锟节碉拷锟斤拷锟酵ｏ拷锟斤拷锟斤拷锟斤拷募锟斤拷锟斤拷锟斤拷示锟侥硷拷锟斤拷锟酵ｏ拷 锟斤拷锟斤拷锟侥柯硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷
 * 	3.锟节碉拷锟斤拷锟皆ｏ拷锟斤拷锟斤拷锟斤拷锟斤拷锟侥硷拷锟斤拷目录
 * 	4.锟节碉拷锟斤拷始锟教号ｏ拷 锟斤拷锟捷存储锟侥碉拷一锟斤拷锟教匡拷锟�
 * 	5.锟斤拷锟饺ｏ拷 占锟斤拷锟教匡拷锟斤拷锟侥�
 */
public class FolderNode {
	private String nodePathName;  //3 锟斤拷锟街节ｏ拷锟节碉拷路锟斤拷
	private String nodeType;   // 2锟斤拷锟街节ｏ拷锟节碉拷锟斤拷锟斤拷
	private int  nodeAttritute; //1锟斤拷锟街节ｏ拷 锟节碉拷锟斤拷锟斤拷
	private int  nodeBeginDisk; //1锟斤拷锟街节ｏ拷锟节碉拷锟斤拷始锟教猴拷
	private int  nodeLength;  //1锟斤拷锟街节ｏ拷锟斤拷锟斤拷
	private int readType = 1;// 0代表隐藏 ，1代表可读 
    
	
    public FolderNode() {
    	
    	nodeAttritute = 0;   //锟斤拷示锟矫登硷拷锟斤拷未锟斤拷使锟斤拷
    	nodeBeginDisk = 0;   //锟斤拷示锟矫登硷拷锟斤拷未锟斤拷使锟斤拷
    }
    /**
     * 锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷一锟斤拷目录锟角硷拷锟筋，锟斤拷锟斤拷始锟斤拷一些锟斤拷息
     * 锟斤拷锟斤拷值锟斤拷 锟斤拷锟斤拷实锟斤拷
     * @param attribute  目录锟斤拷锟斤拷锟皆ｏ拷锟斤拷锟斤拷锟斤拷募锟斤拷锟斤拷锟侥柯�
     * @param nodePathName  锟侥硷拷锟斤拷
     * @param nodeBeginDisk 锟侥硷拷锟斤拷始锟斤拷锟教猴拷
     */
    public FolderNode(int attribute,String nodePathName,int  nodeBeginDisk) {
    	
    	this.nodeAttritute = attribute;
    	this.nodePathName = nodePathName;
    	this.nodeBeginDisk = nodeBeginDisk;
    	this.nodeLength = 1;
    }

    /**
     *
     */  
    public void initFolderNode(String nodePathName, String nodeType, int  nodeAttritute, 
    		int  nodeBeginDisk, int  nodeLength) {
    	
    	this.nodePathName = nodePathName;
    	this.nodeType = new String(nodeType);
    	this.nodeAttritute = nodeAttritute;
    	this.nodeBeginDisk = nodeBeginDisk;
    	this.nodeLength = nodeLength;
    }
    
	public String getNodePathName() {
		return nodePathName;
	}

	public void setNodePathName(String nodePathName) {
		this.nodePathName = nodePathName;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public int getNodeAttritute() {
		return nodeAttritute;
	}

	public void setNodeAttritute(int nodeAttritute) {
		this.nodeAttritute = nodeAttritute;
	}

	public int getNodeBeginDisk() {
		return nodeBeginDisk;
	}

	public void setNodeBeginDisk(int nodeBeginDisk) {
		this.nodeBeginDisk = nodeBeginDisk;
	}

	public int getNodeLength() {
		return nodeLength;
	}

	public void setNodeLength(int nodeLength) {
		this.nodeLength = nodeLength;
	}
    
	//设置文件为只读
	public void setOnlyReadType(){
		this.readType = 0;
	}
	
	//设置文件为可读可写
	public void setCanWriteReadType(){
		this.readType = 1;
	}
	
    public int getReadType(){
    	return this.readType;
    }
	
}

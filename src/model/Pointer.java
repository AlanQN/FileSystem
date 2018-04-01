package model;
/**
 * 已打开文件的读、写指针的结构
 * dnum: 磁盘盘块号
 * bnum: 磁盘盘块内的第几个字节
 */

public class Pointer {

    private int dnum;  
    private int bnum;    
    
    public Pointer() {}

	public int getDnum() {
		return dnum;
	}

	public void setDnum(int dnum) {
		this.dnum = dnum;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	};
    
    
}

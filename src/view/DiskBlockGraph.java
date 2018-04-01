package view;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Pos;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * 此为磁盘块视�?
 * 其中灰色表示未分配的可以分配的磁盘块，蓝色的表示已经分配的已用磁盘块
 */

public class DiskBlockGraph extends GridPane {

	private int row;	//磁盘块的行数
	private int column;	//磁盘块的列数
	private int totalNum;	//总块�?
	private static final double BLOCK_WIDTH = 26;	//磁盘块的宽度
	private static final double BLOCK_HEIGHT = 18;	//磁盘块的高度
	private List<Label> diskBlockList = new ArrayList<>();	//磁盘块集�?
	private static final String MARGIN_COLOR = "#B9D3EE";	//边缘颜色

	//构�?�函�?
	public DiskBlockGraph() {
		//默认10�?10�?
		this(10, 10);
	}

	public DiskBlockGraph(int row, int column) {
		//接收参数
		this.row = row;
		this.column = column;
		this.totalNum = row * column;
		//初始化视�?
		initBlockGraph();
		//分配前三�?
		allocatedDiskBlock(0);
		allocatedDiskBlock(1);
		allocatedDiskBlock(2);
	}

	//初始化磁盘块视图
	private void initBlockGraph() {
		//添加磁盘�?
		for(int i = 0; i < column; i++) {
			for(int j = 0; j < row; j++) {
				//创建磁盘�?
				Label diskBlock = new Label();
				//编号
				int value = i * row + j;
				diskBlock.setText(String.valueOf(value));
				//为磁盘块设置样式
				diskBlock.setAlignment(Pos.CENTER);
				diskBlock.setPrefSize(BLOCK_WIDTH, BLOCK_HEIGHT);
				this.setFreeStyle(diskBlock);
				//添加此磁盘块视图�?
				this.add(diskBlock, j, i);
				//添加到磁盘块集合
				diskBlockList.add(diskBlock);
			}
		}
		//居中
		this.setAlignment(Pos.CENTER);
	}

	//分配�?块磁盘块
	public void allocatedDiskBlock(int position) {
		//�?测位置是否合�?
		if (position >= 0 && position < totalNum) {
			//获取对应位置的磁盘对�?
			Label block = diskBlockList.get(position);
			//改变磁盘块样�?
			this.setAllocatedStyle(block);
		} else {
/*			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("错误");
			alert.setHeaderText(null);
			alert.setContentText("磁盘块不存在，无法完成分配\n请输入正确的磁盘块号");
			alert.showAndWait();*/
		}
		return;
	}
	
	/**
	 * 描述：设置未分配磁盘块号样式
	 * 返回值： 
	 * 参数: position 磁盘块号
	 */
	public void freeDiskBlock(int position) {
		//�?测位置是否合�?
		if (position >= 0 && position < totalNum) {
			//获取对应位置的磁盘对�?
			Label block = diskBlockList.get(position);
			//改变磁盘块样�?
			this.setFreeStyle(block);
		} else {
/*			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("错误");
			alert.setHeaderText(null);
			alert.setContentText("磁盘块不存在，无法完成分配\n请输入正确的磁盘块号");
			alert.showAndWait();*/
		}
		return;
	}

	//分配�?组磁盘块
	public void allocateDiskBlocks(ArrayList<Integer> positionList) {
		if (positionList != null && positionList.size() > 0) {
			for(int i = 0; i < positionList.size(); i++) {
				//逐块分配磁盘
				if(positionList.get(i) != 0)
					allocatedDiskBlock(i);
				else
					freeDiskBlock(i);
			}
		}
	}
	

	//未分配子项的样式
	private void setFreeStyle(Label block) {
		block.setStyle("-fx-background-color:#D0D0D000; -fx-border-width:1.5; -fx-border-color:"+MARGIN_COLOR+";");
	}

	//已分配子项的样式
	private void setAllocatedStyle(Label block) {
		block.setStyle("-fx-background-color:#6699ff; -fx-border-width:1.5; -fx-border-color:"+MARGIN_COLOR+";");
	}
	
}

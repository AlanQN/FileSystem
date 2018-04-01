package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.colorchooser.ColorSelectionModel;

import com.sun.accessibility.internal.resources.accessibility;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * 此类为FAT文件配置表的视图
 *
 */

public class FATGraph extends VBox {

	private static FATGraph instance = null;	//单一实例
	private int itemCount;	//项的数目
	private List<Label> FATItemList = new ArrayList<>();	//文件配置表子项集�?
	private static final int ITEM_WIDTH = 100;	//子项宽度
	private static final int ITEM_HEIGHT = 10;	//子项高度
	private static int colorsIndex = 0;
	private static int[] colorsList = new int[128];
	//private static final int SCROLL_HEIGHT = 500;	//滚动面板高度
	private static final String MARGIN_COLOR = "#B9D3EE";	//边缘颜色
	public static String[] colors = {"#44DDDD","#33ff99","#EEAD0E","#ff6699","#FFFF00"}; //打开不同文件时 FAT表的颜色
	//获取实例
	public static FATGraph getInstance() {
		if (instance == null) {
			instance = new FATGraph();
		}
		return instance;
	}

	public static FATGraph getInstance(int itemCount) {
		if (instance == null) {
			instance = new FATGraph(itemCount);
		}
		return instance;
	}

	//构�?�方�?
	private FATGraph() {
		this(128);	//默认子项�?128�?
	}

	private FATGraph(int itemCount) {
		//接收参数
		this.itemCount = itemCount;
		//初始化视�?
		this.initFATGraph();
	}
	
	
	
	//初始化文件配置表视图
	private void initFATGraph() {
		//创建标题
		Label title = new Label("文件配置表");
		//设置透明
		title.setStyle("-fx-background:#FFFFFF00;");
		//指定样式
		title.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 18));
		title.setTextFill(Color.CORNFLOWERBLUE);
		//设置间距
		//title.setPadding(new Insets(10, 0, 5, 0));
		//添加标题
		this.getChildren().add(title);

		//添加说明
		//块号
		Label blockTip = new Label("磁盘块号");
		//设置透明
		blockTip.setStyle("-fx-background:#FFFFFF00;");
		//样式
		blockTip.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		blockTip.setTextFill(Color.CORNFLOWERBLUE);
		blockTip.setAlignment(Pos.CENTER);
		blockTip.setPrefWidth(ITEM_WIDTH);
		//值标�?
		Label valueTip = new Label("使用情况");
		//设置透明
		valueTip.setStyle("-fx-background:#FFFFFF00;");
		//样式
		valueTip.setFont(Font.font("Arial", FontWeight.BOLD, 14));
		valueTip.setTextFill(Color.CORNFLOWERBLUE);
		valueTip.setAlignment(Pos.CENTER);
		valueTip.setPrefWidth(ITEM_WIDTH);
		//添加标签到试�?
		HBox tipBox = new HBox();
		//设置透明
		tipBox.setStyle("-fx-background:#FFFFFF00;");
		tipBox.getChildren().addAll(blockTip, valueTip);
		this.getChildren().add(tipBox);
		
		//创建子项
		GridPane itemPane = new GridPane();	//装载子项的布�?容器
		//设置透明
		itemPane.setStyle("-fx-background:#FFFFFF00;");
		for(int i = 0; i < itemCount; i++) {
			//表项序号
			Label itemNumber = new Label(String.valueOf(i));
			itemNumber.setAlignment(Pos.CENTER);
			itemNumber.setPrefSize(ITEM_WIDTH, ITEM_HEIGHT);
			itemNumber.setStyle("-fx-background-color:#D0D0D000; -fx-border-width:1.5; -fx-border-color:"+MARGIN_COLOR+";");
			//子项
			Label item = new Label("0");
			item.setAlignment(Pos.CENTER);
			item.setPrefSize(ITEM_WIDTH, ITEM_HEIGHT);
			this.setFreeStyle(item);
			//添加子项到子项集合中
			FATItemList.add(item);
			//添加子项到父容器�?
			itemPane.add(itemNumber, 0, i);
			itemPane.add(item, 1, i);
		}
		//用滑动面板装载子项集�?
		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(itemPane);
		//设置样式
		scrollPane.setStyle("-fx-background-color:#FFFFFF00; -fx-border-width:1; -fx-border-color:"+MARGIN_COLOR+";");
		//scrollPane.setPadding(new Insets(5));
		scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrollPane.setFitToWidth(true);
		//scrollPane.setFitToHeight(true);
		//scrollPane.setPrefHeight(SCROLL_HEIGHT);
		scrollPane.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		//去除边框
		scrollPane.getStyleClass().add("edge-to-edge");
		//添加子项
		this.getChildren().add(scrollPane);

		//设置视图背景�?
		this.setStyle("-fx-background-color:#FFFFFF00;");
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);
	}

	//未分配子项的样式
	private void setFreeStyle(Label item) {
		item.setStyle("-fx-background-color:#D0D0D000; -fx-border-width:1.5; -fx-border-color:"+MARGIN_COLOR+";");
	}

	//已分配子项的样式
	public void setAllocatedStyle(Label item) {
		item.setStyle("-fx-background-color:#6699ff; -fx-border-width:1.5; -fx-border-color:"+MARGIN_COLOR+";");
	}

	//设置打开子项的样式
	public void setOpenStyle(Label item, String color) {
		
		item.setStyle("-fx-background-color:"+color+"; -fx-border-width:1.5; -fx-border-color:"+MARGIN_COLOR+";");
	}

	public void initColorsList(){
		for(int i = 0;i<128;i++){
			colorsList[i] = -1;
		}
	}
		
	//双击打开样式
	public void setOpeningStyle(List<Integer> list,int index) {
		System.out.println(index);
		if (list != null) {
			for (Integer i : list) {
				Label item = FATItemList.get(i);
				this.setOpenStyle(item, colors[index]);	
			}
		}
	}

	

	//双击打开样式
	public void setClosingStyle(List<Integer> list) {
		if (list != null) {
			for (Integer index : list) {
				Label item = FATItemList.get(index);
				this.setAllocatedStyle(item);
			}
		}
	}

	//分配子项
	public void allocateFATItem(int position, int value) {
		//获取子项
		Label item = FATItemList.get(position);
		//将�?�设置为0
		item.setText(String.valueOf(value));
		//设置样式
		if (value != 0) {
			this.setAllocatedStyle(item);
		} else {
			this.setFreeStyle(item);
		}
	}

	//回收子项
	public void recycleFATItem(int position) {
		//获取子项
		Label item = FATItemList.get(position);
		//将�?�设置为0
		item.setText("0");
	}

}

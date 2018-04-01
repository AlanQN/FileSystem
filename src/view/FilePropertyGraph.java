package view;




import model.MyFile;
import view.FileDirectoryTreeGraph.MyTreeItem;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

	

public class FilePropertyGraph extends Stage{
	   public  MyTreeItem  chooseTreeItem;
	   private Label name;      //命名标签
	   private int attribute;	//属性
	   private Label ItemAttribute;//属性标签
	   private Label path;     //路径	
	   private static final int STAGE_WIDTH = 250;
	   private static final int STAGE_HEIGHT = 300;
	   public static int readType;//1代表只读       0代表隐藏
	   
	  public FilePropertyGraph(MyTreeItem selectedItem){
			initGraph(selectedItem);
	
		}
		public void initGraph(MyTreeItem selectedItem){
			VBox pane = new VBox();
			//标签
			this.name= new Label("名字:           "+selectedItem.getFolderNode().getNodePathName());
			this.name.setLayoutX(100);
			this.attribute = selectedItem.getAttribute();
				if(this.attribute == MyFile.FILE_VALUE){
					ItemAttribute = new Label("类型:           "+"File");
				}else if(this.attribute==MyFile.FOLDER_VALUE){
					ItemAttribute = new Label("类型:           "+"Floder");
				}
			ItemAttribute.setLayoutX(100);
			
			this.path = new Label("路径:           "+selectedItem.getPath());
			this.path.setLayoutX(100);
			
			//属性单选框
			Label choose = new Label("属性:");
			final ToggleGroup group =new ToggleGroup();
			RadioButton rb1 = new RadioButton("可读可写");
			RadioButton rb2 = new RadioButton("只读");
			rb1.setUserData("可读可写");
			rb1.setPadding(new Insets(0,0,0,60));
			rb2.setUserData("只读");
			rb2.setPadding(new Insets(0,0,0,60));
			rb1.setToggleGroup(group);
			rb2.setToggleGroup(group);
			
			
			//确认取消按钮及点击事件
			javafx.scene.control.Button agree = new javafx.scene.control.Button("确认");
			javafx.scene.control.Button cancel = new javafx.scene.control.Button("取消");
			agree.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					
					if(group.getSelectedToggle().getUserData() == "只读"){
						selectedItem.getFolderNode().setOnlyReadType();//设置属性为只读
					}else{
						selectedItem.getFolderNode().setCanWriteReadType();
					}
					System.out.println(readType);
					FilePropertyGraph.this.hide();
				}
			});
			
	
			
			cancel.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					// TODO Auto-generated method stub
					FilePropertyGraph.this.hide();
					
				}
			});
			
			HBox hbox = new HBox();
			hbox.getChildren().addAll(agree, cancel);
			hbox.setAlignment(Pos.CENTER);
			hbox.setSpacing(50);
			
			pane.getChildren().addAll(this.name,ItemAttribute,this.path,choose,rb1,rb2);
			pane.getChildren().add(hbox);
			pane.setAlignment(Pos.CENTER_LEFT);
			pane.setPadding(new Insets(25));
			pane.setSpacing(20);
			Scene scene = new Scene(pane, STAGE_WIDTH, STAGE_HEIGHT, Color.WHITE);
			this.setScene(scene);
			
			
		}
		
		/*
		 *  @return   readType 1表示可读 0表示隐藏
		 */
		 public int getReadtype() {
			return readType;
		}
		
}

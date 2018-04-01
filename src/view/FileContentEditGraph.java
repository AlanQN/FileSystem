package view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import service.FileSystem;
import util.StringMethod;
import view.FileDirectoryTreeGraph.MyTreeItem;

/**
 * 此类为文件内容编辑窗�?
 *
 */

public class FileContentEditGraph extends Stage {

	private FileMenuBar menuBar;	//菜单�? -- 包括保存等操�?
	private TextArea contentArea;	//文本内容�?
	private String fileName;	//窗口的名称，也是文件的名�?
	private String pathName;    //文件路径
	private MyTreeItem treeItem;	//对应的文件结点

	//获取文件路径
	public String getPathName() {
		return pathName;
	}

	private static final int STAGE_WIDTH = 800;	//文本编辑器默认宽�?
	private static final int STAGE_HEIGHT = 500;	//文本编辑器默认高�?

	//构�?�函�?
	public FileContentEditGraph() {
		//初始化窗�?
		this(null, "无标题");
	}

	public FileContentEditGraph(MyTreeItem treeItem, String filaname) {
		//接受参数
		this.fileName = filaname;
		System.out.println(filaname);
		this.treeItem = treeItem;
		//初始化窗�?
		this.initGraph(); 
	}

	public FileContentEditGraph(MyTreeItem treeItem, String filename, String pathName) {

		//初始化
		this(treeItem, filename);
		//初始化文件路径
		this.pathName = pathName;
	}

	/**
	 * 描述：关闭文本编辑器
	 */
	private void closeStage() {

		this.close();
	}
	//初始化视�?
	private void initGraph() {
		VBox root = new VBox();
		//创建编辑区域
		this.contentArea = new TextArea();
		//设置自动换行
		this.contentArea.setWrapText(true);
		//设置默认可写
		this.contentArea.setEditable(true);
		//自�?�应
		this.contentArea.prefWidthProperty().bind(this.widthProperty());
		this.contentArea.prefHeightProperty().bind(this.heightProperty());

		//创建菜单�?
		this.menuBar = new FileMenuBar();
		//设置宽度自�?�应
		this.menuBar.prefWidthProperty().bind(this.widthProperty());
		//设置背景�?
		this.menuBar.setStyle("-fx-background-color:#EFEFEF; -fx-border-width:1; -fx-border-color:#D0D0D0;");

		//添加
		root.getChildren().addAll(menuBar, contentArea);
		//设置名称
		this.setTitle(fileName+" - 文本编辑器");
		Scene scene = new Scene(root, STAGE_WIDTH, STAGE_HEIGHT, Color.WHITE);
		this.setScene(scene);
	}

	//自定义的菜单�?
	class FileMenuBar extends MenuBar {

		private Menu fileOper;	//文件操作菜单
		private MenuItem save;	//保存菜单�?
		private MenuItem saveExit;	//保存并�??出菜单项
		private MenuItem exit;	//�?出菜单项
		private Menu textFormat;	//文本格式菜单
		private RadioMenuItem autoWrap;	//自动换行菜单�?
		private Menu textFont;	//字体设置菜单
		private Menu fontType;	//字体
		private Menu fontWeight;	//字形
		private Menu fontSize;	//字体大小
		private String type = "宋体";	//默认宋体
		private FontWeight weight = FontWeight.NORMAL;	//默认标准字形
		private FontPosture posture = FontPosture.REGULAR;
		private Double size = 14.0;	//默认字体大小14
		private FileSystem fileSystem;

		//构�?�函�?
		public FileMenuBar() {
			//初始�?
			this.initMenuBar();
		}

		//初始化菜单栏
		private void initMenuBar() {

			//获取文件系统对象
			fileSystem = FileSystem.getInstance();
			//文件操作菜单
			this.fileOper = new Menu("文件");	//菜单
			this.save = new MenuItem("保存");	//菜单�?
			this.saveExit = new MenuItem("保存并退出");
			this.exit = new MenuItem("退出");
			//添加菜单项到菜单
			this.fileOper.getItems().addAll(save, saveExit, new SeparatorMenuItem(), exit);

			//格式菜单
			this.textFormat = new Menu("格式");
			//自动换行菜单�?
			this.autoWrap = new RadioMenuItem("自动换行");
			//默认选中
			this.autoWrap.setSelected(true);

			//字体菜单
			this.textFont = new Menu("字体");

			//字体菜单
			this.fontType = new Menu("字体");
			//字体�?
			ToggleGroup typeGroup = new ToggleGroup();
			//几种常用的字�?
			RadioMenuItem song = new RadioMenuItem("宋体");
			song.setUserData("宋体");
			song.setToggleGroup(typeGroup);
			song.setSelected(true);
			RadioMenuItem arial = new RadioMenuItem("微软雅黑");
			arial.setUserData("微软雅黑");
			arial.setToggleGroup(typeGroup);
			//添加字体
			this.fontType.getItems().addAll(song, arial);

			//字体字形菜单
			this.fontWeight = new Menu("字形");
			//字形�?
			ToggleGroup weightGroup = new ToggleGroup();
			//常用字形
			RadioMenuItem normal = new RadioMenuItem("常规");
			normal.setToggleGroup(weightGroup);
			normal.setUserData("normal");
			normal.setSelected(true);
			RadioMenuItem italic = new RadioMenuItem("斜体");
			italic.setToggleGroup(weightGroup);
			italic.setUserData("italic");
			RadioMenuItem blod = new RadioMenuItem("粗体");
			blod.setToggleGroup(weightGroup);
			blod.setUserData("blod");
			RadioMenuItem blodItalic = new RadioMenuItem("粗斜体");
			blodItalic.setToggleGroup(weightGroup);
			blodItalic.setUserData("blodItalic");
			//添加常用字形
			this.fontWeight.getItems().addAll(normal, italic, blod, blodItalic);

			//字体大小菜单
			this.fontSize = new Menu("大小");
			//字体大小�?
			ToggleGroup sizeGroup = new ToggleGroup();
			//常用的大�?
			RadioMenuItem size10 = new RadioMenuItem("10");
			size10.setToggleGroup(sizeGroup);
			size10.setUserData(10.0);
			RadioMenuItem size12 = new RadioMenuItem("12"); 
			size12.setToggleGroup(sizeGroup);
			size12.setUserData(12.0);
			RadioMenuItem size14 = new RadioMenuItem("14");
			size14.setToggleGroup(sizeGroup);
			size14.setUserData(14.0);
			size14.setSelected(true);
			RadioMenuItem size16 = new RadioMenuItem("16");
			size16.setToggleGroup(sizeGroup);
			size16.setUserData(16.0);
			RadioMenuItem size18 = new RadioMenuItem("18");
			size18.setToggleGroup(sizeGroup);
			size18.setUserData(18.0);
			//添加字体大小
			this.fontSize.getItems().addAll(size10, size12, size14, size16, size18);

			//添加
			this.textFont.getItems().addAll(fontType, fontWeight, fontSize);
			this.textFormat.getItems().addAll(autoWrap, textFont);

			//添加菜单到菜单栏
			this.getMenus().addAll(fileOper, textFormat);
			//为文本区域设置默认样�?
			contentArea.setFont(Font.font(type, weight, posture, size));

			//设置菜单事件
			//根据选中状�?�的不同设置是否自动换行
			this.autoWrap.selectedProperty().addListener(e->{
				if (this.autoWrap.isSelected()) {
					//自动换行
					contentArea.setWrapText(true);
				} else {
					//取消自动换行
					contentArea.setWrapText(false);
				}
			});

			//设置字体选择事件
			typeGroup.selectedToggleProperty().addListener(e->{
				if (typeGroup.getSelectedToggle() != null) {
					//更新字体
					this.type = (String) typeGroup.getSelectedToggle().getUserData();
					//设置文本编辑区域样式
					contentArea.setFont(Font.font(type, weight, posture, size));
				}
			});

			//设置字形切换事件
			weightGroup.selectedToggleProperty().addListener(e->{
				if (weightGroup.getSelectedToggle() != null) {
					String value = (String) weightGroup.getSelectedToggle().getUserData();
					if ("normal".equals(value)) {
						this.weight = FontWeight.NORMAL;
					} else if ("blod".equals(value)) {
						this.weight = FontWeight.EXTRA_BOLD;
					} else if ("italic".equals(value)) {
						this.posture = FontPosture.ITALIC;
					} else if ("blodItalic".equals(value)) {
						this.posture = FontPosture.ITALIC;
						this.weight = FontWeight.BOLD;
					}
					//设置文本编辑区域样式
					contentArea.setFont(Font.font(type, weight, posture, size));
				}
			});

			//设置字体大小切换事件
			sizeGroup.selectedToggleProperty().addListener(e->{
				if (sizeGroup.getSelectedToggle() != null) {
					//更新字体大小
					this.size = (Double) sizeGroup.getSelectedToggle().getUserData();
					//设置文本编辑区域样式
					contentArea.setFont(Font.font(type, weight, posture, size));
				}
			});

			//为文本编辑器菜单栏中保存菜单项添加点击事件
			addSaveActionEvent();
			//为文本编辑器菜单栏中保存并退出菜单项添加点击事件
			addSaveAndExitEvent();
			//为文本编辑器菜单栏中退出菜单项添加点击事件
			addExitEvent();

		}
		/**
		 * 描述： 为文本编辑器菜单栏中保存菜单项添加点击事件
		 */
		private void addSaveActionEvent() {

			this.save.setOnAction(e->{

				String buffer = contentArea.getText();
				//规范文件路径
				String path = StringMethod.deleRootStr(pathName);
				//将文本编辑器中的内容保存到磁盘中
				fileSystem.storeIntoFile(path, buffer);
				//渲染主界面
				MainFrame.paintMainFrame();
			});
		}

		/**
		 * 描述： 为文本编辑器菜单栏中保存并退出菜单项添加点击事件
		 * @return
		 */
		private void addSaveAndExitEvent() {

			this.saveExit.setOnAction(e->{

				String buffer = contentArea.getText();
				//规范文件路径
				String path = StringMethod.deleRootStr(pathName);
				//将文本编辑器中的内容保存到磁盘中
				fileSystem.storeIntoFile(path, buffer);
				//渲染主界面
				MainFrame.paintMainFrame();
				//关闭文本编辑器
				closeStage();
			});
		}

		/**
		 * 描述： 为文本编辑器菜单栏中保存并退出菜单项添加点击事件
		 * @return
		 */
		private void addExitEvent() {

			this.exit.setOnAction(e->{

				closeStage();
			});
		}

		//获取菜单
		public Menu getFileOper() {
			return fileOper;
		}

		public Menu getTextFormat() {
			return textFormat;
		}

		public Menu getTextFont() {
			return textFont;
		}

	}

	//获取文本编辑器中的文本域对象
	public TextArea getContentArea() {

		return this.contentArea;
	}
}

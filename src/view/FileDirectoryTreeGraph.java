package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.FolderNode;
import model.MyFile;
import service.FileSystem;
import util.StringMethod;
import view.FileDirectoryEditGraph.FileDirectoryItem;

/**
 * 此类为文件目录树视图
 *
 */

public class FileDirectoryTreeGraph extends TreeView<String> {

	private  static MyTreeItem selectedItem;	//当前选中的结�?
	//图标地址
	private static String emptyFolderImageSrc = "/image/emptyFolder.png";
	private static String folderImageSrc = "/image/folder.png";
	private static String fileImageSrc = "/image/file.png";
	private static String rootImageSrc = "/image/root.png";
	//视图大小
	private static final int VIEW_WIDTH = 200;
	private static final int VIEW_HEIGHT = 200;
	private static FileDirectoryTreeGraph instance;
	//文件结点的右键菜单
	private MyContextMenu rootMenu;
	//文件夹右键菜单
	private MyContextMenu folderMenu;
	//文件管理系统
	private static FileSystem fileSystem = FileSystem.getInstance();

	private FileDirectoryTreeGraph() {
		//初始化目录树
		this.initTreeGraph();
	}

	//初始化文件目录树
	private void initTreeGraph () {
		//添加�?个系统盘作为根结�?
		MyTreeItem rootItem = new MyTreeItem(MyFile.SYSTEM_VALUE, null);
		//添加此结�?
		this.setRoot(rootItem);
		//展开根结�?
		rootItem.setExpanded(true);

		//文件结点的右键菜�?
		rootMenu = new MyContextMenu(MyFile.SYSTEM_VALUE);
		//文件夹右键菜�?
		folderMenu = new MyContextMenu(MyFile.FOLDER_VALUE);

		//设置大小
		this.setPrefSize(VIEW_WIDTH, VIEW_HEIGHT);
		//设置选中模式为单个�?�中
		this.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		//设置右键菜单
		this.getSelectionModel().selectedItemProperty().addListener(e->{
			//获取选中的结�?
			selectedItem = (MyTreeItem) this.getSelectionModel().getSelectedItem();
			//获取结点类型
			int attribute = selectedItem.getAttribute();
			//根据结点的不同，设置不同的右键菜�?
			if (attribute == MyFile.FOLDER_VALUE) {
				//设置文件夹右菜单
				this.setContextMenu(folderMenu);
			} else {
				//设置系统目录右菜�?
				this.setContextMenu(rootMenu);
			}
		});

		//默认选中根结�?
		this.getSelectionModel().select(rootItem);
	}

	//获取实例
	public static FileDirectoryTreeGraph getInstance() {
		if (instance == null) {
			instance = new FileDirectoryTreeGraph();
		}
		return instance;
	}

	//获取根结点菜单项
	public MyContextMenu getRootMenu() {
		return this.rootMenu;
	}

	//获取文件夹菜单项
	public MyContextMenu getFolderMenu() {
		return this.folderMenu;
	}

	//添加子结点
	public void addChildItem(int attribute) { //FIXME
		//获取要添加子结点的结点
		selectedItem = (MyTreeItem) this.getSelectionModel().getSelectedItem();
		//创建子结点
		MyTreeItem childItem = new MyTreeItem(attribute, selectedItem);
		//添加到子结点集合
		selectedItem.addChild(childItem);
		//如果是文件夹，则添加到目录树中，并且设置为中
		if (attribute == MyFile.FOLDER_VALUE || attribute == MyFile.SYSTEM_VALUE) {
			//添加
			selectedItem.getChildren().add(childItem);
			//展开当前结点
			selectedItem.setExpanded(true);
			//将添加的子结点置为�?�中的结�?
			this.getSelectionModel().select(childItem);
		}
	}

	//移除子结点
	public void removeChildItem(MyTreeItem selectedItem) {
		//获取父结点
		MyTreeItem parentItem = (MyTreeItem) selectedItem.getParent();
		//移除当前结点
		parentItem.getChildren().remove(selectedItem);
		//父结点�?�中
		this.getSelectionModel().select(parentItem);
	}



	/**
	 * 描述： 初始化当前路径中新建立的一个子节点
	 * 返回值：新建立字节中的文件目录项实例，其中包含该子节点的相关属性和信息
	 * @param childItem  新建立的子节点
	 */ 
	public  static FolderNode initChildItem(int attribute, String path) {
		
		//去除路径中的'ROOT:'子串
		String pathName = StringMethod.deleRootStr(path);
		//根目录系统自带，无需初始化
		if("\\".equals(pathName))
			return null;
		//子节点中的文件目录项信息
		FolderNode childFolderNode  = fileSystem.createFile(pathName);
		return childFolderNode;
	} 

	//根据传入的参数创建文件夹结点或新建节点
	static class MyTreeItem extends TreeItem<String> {

		private int attribute;	//文件属属性
		private FolderNode folderNode;  //目录登记项，记录该文件的信息
		private List<MyTreeItem> childList;	 //子结点集合
		private MyTreeItem parentItem;	//父结点
		private ImageView emptyIcon;	//空文件夹图标
		private ImageView normalIcon;	//非空文件夹图标
		private String path;	//当前路径
		private FileDirectoryItem fileItem;	//绑定的文件子项
//FIXME 
		public MyTreeItem(int attribute, MyTreeItem parentItem) {

			//初始化文件目录登记项
			folderNode = new FolderNode();
			//接收属性
			this.attribute = attribute;
			//接收父结点
			this.parentItem = parentItem;
			//初始化集合
			this.childList = new ArrayList<MyTreeItem>();
			//设置图标和名字
			if (attribute == MyFile.FOLDER_VALUE) {
				//设置文件夹名字
				emptyIcon = new ImageView(new Image(getClass().getResourceAsStream(emptyFolderImageSrc)));
				this.setGraphic(emptyIcon);
				this.setValue("新建文件夹");
				normalIcon = new ImageView(new Image(getClass().getResourceAsStream(folderImageSrc)));
			} else if (attribute == MyFile.FILE_VALUE) {
				//设置文件名
				ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(fileImageSrc)));
				this.setGraphic(icon);
				this.setValue("新建文件.txt");
			} else {
				//系统文件目录
				ImageView icon = new ImageView(new Image(getClass().getResourceAsStream(rootImageSrc)));
				this.setGraphic(icon);
				this.setValue("系统文件");
			}
			//设置路径
			this.path = getItemPath();
			String fileName = getFileName(attribute);
			this.setValue(fileName);
			this.path = getItemPath();
			//绑定路径更新
			this.valueProperty().addListener(e->{
				this.path = getItemPath();
			});
			//将新文件名登记到目录项中
			folderNode = initChildItem(attribute, this.path);
			
		}
		
		/**
		 * 设置文件子项
		 */
		public void setFileItem(FileDirectoryItem item) {
			this.fileItem = item;
		}
		
		/**
		 * 获取文件子项
		 * @return 
		 */
		public FileDirectoryItem getFileItem() {
			return fileItem;
		}

		//获取文件属性
		public int getAttribute() {
			return this.attribute;
		}
		
		//获取子结点集合
		public List<MyTreeItem> getChildList() {
			return this.childList;
		}
		
		/**
		 * 添加子结点到集合
		 * @param child
		 */
		public void addChild(MyTreeItem child) {
			this.childList.add(child);
			//更新图标
			if (this.attribute == MyFile.FOLDER_VALUE) {
				this.setGraphic(normalIcon);
				if (this.fileItem != null) {
					this.fileItem.setNormalIcon();
				}
			}
		}
		
		/**
		 * 添加子结点到集合
		 * @param child
		 */
		public void removeChild(MyTreeItem child) {
			this.childList.remove(child);
			//更新图标
			if (this.attribute == MyFile.FOLDER_VALUE && this.childList.isEmpty()) {
				this.setGraphic(emptyIcon);
				if (this.fileItem != null) {
					this.fileItem.setEmptyIcon();
				}
			}
		}
		
		//获取父结点
		public MyTreeItem getParentItem() {
			return this.parentItem;
		}

		//获取该节点的文件目录项
		public FolderNode getFolderNode() {
			return folderNode;
		}
		//设置该节点的文件目录项
		public void setFolderNode(FolderNode folderNode) {
			this.folderNode = folderNode;
		}

		//向上获取路径
		private String getItemPath() {
			//路径集合
			List<String> pathList = new ArrayList<String>();
			//当前子项
			MyTreeItem temp = this;
			//�?上获取路�?
			while (temp.getParentItem() != null) {
				pathList.add(temp.getValue());
				temp = (MyTreeItem) temp.getParentItem();
			} 
			//获取完整路径
			StringBuilder path = new StringBuilder("Root:\\");
			if (pathList != null && pathList.size() > 0) {
				for(int i = pathList.size()-1; i >= 0; i--) {
					path.append(pathList.get(i));
					if (i != 0) {
						path.append("\\");
					}
				}
			}
			//返回路径
			return path.toString();
		}
		
		//获取当前路径
		public String getPath() {
			return this.path;
		}
		
		/**
		 * 描述： 返回一个文件名给新建的文件或文件夹
		 * @param 文件属性
		 * @return 一个文件或文件夹名
		 */
		public String getFileName(int attribute) {
			
			String pathName = this.getPath();
			boolean flag = true;
			String fileName = "新建文件";
			if(attribute == MyFile.FILE_VALUE) 
			{
				fileName = "新建文件.txt";
				for(int i = 1; i < 8; i++) 
				{
					flag = fileSystem.checkRename(pathName, fileName);
					if(!flag)
					{
						break;
					}
					fileName = "新建文件(" + i + ").txt";
				}
			} else 
			{
				fileName = "新建文件夹";
				for(int i = 1; i < 8; i++) 
				{
					flag = fileSystem.checkRename(pathName, fileName);
					if(!flag)
					{
						break;
					}
					fileName = "新建文件夹(" + i + ")";
				}
			}System.out.println("______" + fileName);
			return fileName;
		}

	}

	//根据传入的文件类型创建不同的右键菜单
	class MyContextMenu extends ContextMenu {

		//打开菜单项
		private MenuItem open = new MenuItem("打开");
		//删除菜单项
		private MenuItem delete = new MenuItem("删除");
		//重命名菜单项
		private MenuItem rename = new MenuItem("重命名");
		//新建文件夹菜单项
		private MenuItem addFolder = new MenuItem("新建文件夹");
		//属性菜单项
		private MenuItem attribute = new MenuItem("属性");

		//构造函数
		public MyContextMenu(int attribute) { 
			if (attribute == MyFile.FOLDER_VALUE) {
				//文件夹
				this.createFolderMenu();
			} else { 
				//系统目录
				this.createRootMenu();
			}
		}

		//创建系统盘菜单
		public void createRootMenu() {
			//打开菜单项
			open = new MenuItem("打开");
			//新建文件夹菜单项
			addFolder = new MenuItem("新建文件夹");
			//添加菜单项到菜单项
			this.getItems().addAll(open, addFolder);
		}

		//创建文件夹菜单
		public void createFolderMenu() {
			//打开菜单项
			open = new MenuItem("打开");
			//删除菜单项
			delete = new MenuItem("删除");
			//重命名菜单项
			rename = new MenuItem("重命名");
			//新建文件夹菜单项
			addFolder = new MenuItem("新建文件夹");
			//属性菜单项
			attribute = new MenuItem("属性");
			//添加菜单项到菜单项
			this.getItems().addAll(open, delete, rename, addFolder, attribute);
		}

		//获取菜单�?
		public MenuItem getAddFolder() {
			return addFolder;
		}

		public MenuItem getOpen() {
			return open;
		}

		public MenuItem getDelete() {
			return delete;
		}

		public MenuItem getRename() {
			return rename;
		}

		public MenuItem getAttribute() {
			return attribute;
		}

	}

}
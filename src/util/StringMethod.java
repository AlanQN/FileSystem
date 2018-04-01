package util;

public class StringMethod {
	
	/**
	 * 判断字符串对象str是否为空对象或者其内容为0
	 * @return 若是，则返回true, 否则返回false;
	 */
	public static boolean isEmpty(String str) {
		
		if(str == "" || str == null) {
			
			return true; 
		}
		return false; 
	}
	
	/**
	 * 将将字符串中首个子串'ROOT:'去掉
	 * @param pathName 字符串路径
	 * @return 去除'ROOT:'后的子串
	 */
	public static String deleRootStr(String pathName) {
		
		StringBuilder childItemPath = new StringBuilder(pathName);
		int n = childItemPath.indexOf(":");
		childItemPath.delete(0, n + 1) ;
		return childItemPath.toString();
	}
	
	/**
	 * 描述： 根据文件路径获取文件名
	 * @param pathName 文件所在路径
	 * @return 文件名
	 */
	public static String getFileName(String pathName) {
		
		//获取文件名
		String[] pathArray = pathName.split("\\\\");
		String fileName = new String(pathArray[pathArray.length - 1]);
		return fileName;
	}
	
	/**
	 * 描述： 根据文件路径，获取文件所在的工作目录
	 * 
	 */
	public static String getWorkPath(String pathName) {
		
		int index = pathName.lastIndexOf("\\");
		System.out.println(index);
		StringBuilder path = new StringBuilder(pathName);
		path.delete(index+1, pathName.length());
		return path.toString();
	}
	
}

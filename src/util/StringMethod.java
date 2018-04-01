package util;

public class StringMethod {
	
	/**
	 * �ж��ַ�������str�Ƿ�Ϊ�ն������������Ϊ0
	 * @return ���ǣ��򷵻�true, ���򷵻�false;
	 */
	public static boolean isEmpty(String str) {
		
		if(str == "" || str == null) {
			
			return true; 
		}
		return false; 
	}
	
	/**
	 * �����ַ������׸��Ӵ�'ROOT:'ȥ��
	 * @param pathName �ַ���·��
	 * @return ȥ��'ROOT:'����Ӵ�
	 */
	public static String deleRootStr(String pathName) {
		
		StringBuilder childItemPath = new StringBuilder(pathName);
		int n = childItemPath.indexOf(":");
		childItemPath.delete(0, n + 1) ;
		return childItemPath.toString();
	}
	
	/**
	 * ������ �����ļ�·����ȡ�ļ���
	 * @param pathName �ļ�����·��
	 * @return �ļ���
	 */
	public static String getFileName(String pathName) {
		
		//��ȡ�ļ���
		String[] pathArray = pathName.split("\\\\");
		String fileName = new String(pathArray[pathArray.length - 1]);
		return fileName;
	}
	
	/**
	 * ������ �����ļ�·������ȡ�ļ����ڵĹ���Ŀ¼
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

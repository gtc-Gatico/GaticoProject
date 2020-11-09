package cn.com.gatico;

import java.io.File;

public class fileUpload {
	static boolean flag = false;
	static File file;
	static FileClient fc = new FileClient();

	public static void main(String[] args) throws Exception {
		fileUpload fileUpload = new fileUpload();
		// fc.setAddress("119.29.184.148");//大哥
		// fc.setPort(9999);
		// fc.setAddress("gatico.com.cn");//我的
		if (args.length <= 0) {
			System.out.println("参数不正确。");
			return;
		}
		String address = args[0];
		String port = args[1];
		String path = args[2];
		fc.setAddress(address);
		fc.setPort(Integer.valueOf(port));
		File file = new File(path);
		fileUpload.upload(file);

		// flag = fc.uploadFile(file);
		/*
		 * File[] filelist=new File("G:\\img").listFiles(); for(File file1:filelist) {
		 * fc.uploadFile(file1); }
		 */
	}

	public void upload(File file) {
		flag = fc.uploadFile(file);
		System.out.println("客户端:文件:" + file.getAbsolutePath() + "  上传" + (flag ? "成功" : "失败") + ".");
	}
}

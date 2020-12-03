package com.guigu.random;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
 
public class RandomAccessFileTest {
	/**
	 * 文件下载的路径
	 */
	private static final String PATH = "http://localhost:8080/test.exe";
	/**
	 * 下载的线程个数
	 */
	private static final int COUNT = 3;
	/**
	 * 创建一个文件，将远程文件写入该文件
	 */
	private File f = new File("C:\\Users\\Administrator\\Desktop\\test.exe");
	
	public void download() throws Exception{
		URL url = new URL(PATH);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//使用get请求
		conn.setRequestMethod("GET");
		//设置连接超时时间为5秒
		conn.setConnectTimeout(5000);
		//得到服务器返回的响应码
		int code = conn.getResponseCode();
		//响应码为200，代表请求资源成功（前提是请求整个资源，而不是资源的某一部分）
		if(code == 200){
			//得到文件的总大小
			int length = conn.getContentLength();
			//新建一个随机访问的文件
			RandomAccessFile raf = new RandomAccessFile(f, "rw");
			//第一个参数为文件类型，第二个参数为模式，可以为"r","rw","rws","rwd"
			raf = new RandomAccessFile(f, "rw");
			//设置随机文件的大小为服务器端文件的大小
			raf.setLength(length);
			System.out.println("文件总大小为：" + length);
			//计算每个线程下载的块的大小
			int blockSize = length / COUNT;
			for(int i = 0; i < COUNT; i++){
				//每个线程的起始下载点
				int startPos = blockSize * i;
				//每个线程的结束下载点
				int endPos = blockSize * (i + 1) - 1;
				//如果是最后一条线程，将其下载的终止点设为文件的终点
				if(i == COUNT){
					endPos = length;
				}
				System.out.println("线程" + i + "下载的部分为：" + startPos +"---" + endPos);
				//开启线程分别下载不同的部分
				new DownThread(i, startPos, endPos).start();
			}
		}
	}
	
	/**
	 * 下载文件的线程
	 * @author Administrator
	 *
	 */
	class DownThread extends Thread{
		//线程ID
		private int threadId;
		//线程下载的起始点
		private int startPos;
		//线程下载的结束点
		private int endPos;
		
		public DownThread(int threadId, int startPos, int endPos) {
			this.threadId = threadId;
			this.startPos = startPos;
			this.endPos = endPos;
		}
 
		@Override
		public void run(){
			try {
				URL url = new URL(PATH);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestMethod("GET");
				//设置头部的参数，表示请求服务器资源的某一部分
				conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
				//设置了上面的头信息后，响应码为206代表请求资源成功，而不再是200
				int code = conn.getResponseCode();
				if(code == 206){
					InputStream is = conn.getInputStream();
					int hasRead = 0;
					byte[] buf = new byte[1024];
					//这里要注意新创建一个RandomAccessFile对象，而不能重复使用download方法中创建的
					RandomAccessFile raf = new RandomAccessFile(f, "rw");
					//将写文件的指针指向下载的起始点
					raf.seek(startPos);
					while((hasRead = is.read(buf)) > 0) {
						raf.write(buf, 0, hasRead);
					}
					is.close();
					System.out.println("线程" + threadId + "下载完毕...");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			new RandomAccessFileTest().download();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
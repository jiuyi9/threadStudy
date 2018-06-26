package thread;
public class MultiThreadShareData {

	public static void main(String[] args) {
		//共享数据，把同步操作封装在一个对象中，调用对象的同步方法实现同步
		execute0();
		//若每个线程执行代码相同，则可以让线程操作同一个runnable对象，runnable放入共享数据
		execute1();
		//若每个线程执行代码不同，则可以建多个runnable对象,每个runnable执行一种逻辑，runnbale传入共享数据
		execute2();
		//使用内部类,内部类调用外部类的同步方法，成员变量要定义成static保证只有一份数据，或者在构造方法中传入
		execute3();
	}

	private static void execute3() {
		new Thread(new ShareData3().new MyRunnable11()).start();
		new Thread(new ShareData3().new MyRunnable11()).start();
		
		int i = 0 ;
		new Thread(new ShareData4(i).new MyRunnable11()).start();
		new Thread(new ShareData4(i).new MyRunnable11()).start();
	
	}

	private static void execute2() {
		ShareData0 data1 = new ShareData0();
		new Thread(new MyRunnable1(data1)).start();
		new Thread(new MyRunnable2(data1)).start();
		
	}

	private static void execute1() {
		ShareData1 data1 = new ShareData1();
		new Thread(data1).start();
		new Thread(data1).start();
	}

	private static void execute0() {
		ShareData0 data0 = new ShareData0();
		new Thread(){
			@Override
			public void run() {
				data0.inc();
			}
		}.start();
		
		new Thread(){
			@Override
			public void run() {
				data0.dec();
			}
		}.start();
	}
	
	
}

class ShareData0 {
	private int i = 0 ;
	public synchronized void inc(){
		i++;
	}
	public synchronized void dec(){
		i--;
	}
}

class ShareData1 implements Runnable{
	
	private int count = 100;
	@Override
	public void run() {
		while(true){
			count--;
		}
		
	}
	
}


class MyRunnable1 implements Runnable{
	private ShareData0 shareData ;
	public MyRunnable1 (ShareData0 data){
		shareData = data ;
	}
	@Override
	public void run() {
		shareData.inc();
		
	} 
	
}
class MyRunnable2 implements Runnable{
	private ShareData0 shareData ;
	public MyRunnable2 (ShareData0 data){
		shareData = data ;
	}
	@Override
	public void run() {
		shareData.dec();
		
	} 
	
}

class ShareData3 {
	private static int i = 0 ;
	public synchronized void inc(){
		i++;
	}
	public synchronized void dec(){
		i--;
	}
	
	class MyRunnable11 implements Runnable{

		@Override
		public void run() {
			inc();
		}
		
	}
	
	class MyRunnable22 implements Runnable{

		@Override
		public void run() {
			dec();
		}
		
	}
}

class ShareData4 {
	
	private  int i ;
	
	public ShareData4(int i){
		this.i = i ;
	}
	public synchronized void inc(){
		i++;
	}
	public synchronized void dec(){
		i--;
	}
	
	class MyRunnable11 implements Runnable{

		@Override
		public void run() {
			inc();
		}
		
	}
	
	class MyRunnable22 implements Runnable{

		@Override
		public void run() {
			dec();
		}
		
	}
}


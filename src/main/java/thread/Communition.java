package thread;

/**
 * sub 先运行10次，然后main运行10次，交替循环50次
 * 设计原则：将同步代码和资源放在一个对象中，让这个对象管理共享互斥的代码和共享资源
 * @author zhuxutao
 *
 */
public class Communition {
	public static void main(String[] args) {
		Bussiness buss = new Bussiness();

		new Thread(new Runnable(){
			@Override
			public void run() {
				for(int i = 1 ; i<=50 ; i++){
					buss.sub(i);
				}
			}
			
		}).start();
		
		for(int i = 1 ; i<=50 ; i++){
			buss.main(i);
		}
	}
	
	
}
class Bussiness{
	private boolean flag = false ;
	public synchronized void main(int n){
		while(!flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i = 1 ; i <= 10 ; i++){
			System.out.println("main thread sequence of"+ i+"loop is "+n);
		}
		flag = false ;
		this.notify();
	}
	public synchronized void sub(int n){
		while(flag){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i = 1 ; i <= 10 ; i++){
			System.out.println("sub thread sequence of "+ i +"loop is "+n);
		}
		flag = true ;
		this.notify();
	}
}

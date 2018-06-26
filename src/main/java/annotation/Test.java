
package annotation;

@MyAnnotion(color = "")
public class Test {
	
	public static void main(String[] args) {
		if(Use.class.isAnnotationPresent(MyAnnotion.class)){
			MyAnnotion a = (MyAnnotion) Use.class.getAnnotation(MyAnnotion.class);
			System.out.println(a.color());
		}
	}
}

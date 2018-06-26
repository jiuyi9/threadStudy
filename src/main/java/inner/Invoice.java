package inner;

import java.util.ArrayList;

public class Invoice {
	
	//静态内部类和外部类一样可以直接使用， 但私有化的只能通过外部类调用
	private static class Item{
		String description ;
		int quantity ;
		double unitPrice ;
		
		double price(){
			return quantity*unitPrice ;
		}
	}
	private ArrayList<Item> items = new ArrayList<>();
	
	public void addItem(String description , int quantity , double unitPrice){
		Item newItem = new Item();
		newItem.description = description ;
		newItem.quantity = quantity ;
		newItem.unitPrice = unitPrice ;
		items.add(newItem);
	}
	
	public static void main(String[] args) {
		Invoice in = new Invoice();
		in.addItem("", 1, 1L);
		in.addItem("", 1, 1L);
		in.addItem("", 1, 1L);
		in.items.size();
	}
}

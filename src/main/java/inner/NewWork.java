package inner;

import java.util.ArrayList;

public class NewWork {
	
	public class Member{
		private String name ;
		private ArrayList<Member> friends ;
		
		public Member(String name){
			this.name = name ;
			friends = new ArrayList<>();
		}
	}
	
	private ArrayList<Member> members ;
	
	
	public Member enroll(String name){
		Member newMember = new Member(name);
		members.add(newMember);
		return newMember ;
	}
	
}

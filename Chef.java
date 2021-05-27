import java.util.Random;
import java.util.Vector;

public class Chef extends Person {

	
	Random rand = new Random();
	private String username;
	private int salary;
	private String status;
	
	public void cooking(Vector<Menu> vecMenu, Vector<Menu> queue) {
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				boolean flag = true;
				while(flag) {
					try {
						int num = rand.nextInt(10);
						queue.add(vecMenu.get(num));
						if(status.equals("Professional")) Thread.sleep(1000);
						else if(status.equals("Not Professional")) Thread.sleep(2000);
					} catch (Exception e) {
					}
				}
			}
		});
		t1.start();
	}
	
	public Chef(String id, String name, int age, String gender, String username, int salary, String status) {
		super(id, name, age, gender);
		this.username = username;
		this.salary = salary;
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Chef() {
		// TODO Auto-generated constructor stub
	}

}

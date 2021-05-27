import java.util.Random;
import java.util.Scanner;
import java.util.UUID;
import java.util.Vector;

public class Main {

	Scanner scan = new Scanner(System.in);
	Random rand = new Random();
	int money=10000;
	int seats=0;
	Chef ch;
	Customer cu;
	Menu m;
	Vector<Chef> vecChef = new Vector<Chef>();
	Vector<Menu> vecMenu = new Vector<Menu>();
	Vector<Menu> queue = new Vector<Menu>();
	Vector<Customer> vecCust = new Vector<Customer>();
	Thread t2 = new Thread(new Runnable() {
		public void run() {
			boolean flag = true;
			while(flag) {
				try {
					Thread.sleep(120000);
					money-=3000;			
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	});
	
	Thread t3 = new Thread(new Runnable() {
		public void run() {
			boolean flag=true;
			while(flag) {
				while(seats<=5) {
					String id=generateID();
					String name="Customer"+" "+rand.nextInt(100);
					int age=rand.nextInt(97)+4;
					String gender=null;
					int statGender = rand.nextInt(2);
					if(statGender==0) gender = "Male";
					else if(statGender==1) gender = "Female";
					cu = new Customer(id, name, age, gender);
					vecCust.add(cu);
					int index = vecCust.indexOf(cu);
					custActivities(index);
					seats++;
					try {
						Thread.sleep(15000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}				
			}
		}
	});
	public Main() {
		initialize();
		t3.start();
		int choose;
		do { 
			cls();
			mainMenu();
			choose = scan.nextInt();scan.nextLine();
			switch(choose) {
			case 1:
				addChef();
				break;
			case 2:
				danbamInfo();
				break;
			case 3:
				custIn();
				break;
			default:
				break;
			}
		}while(choose != 4);
		
	}
	
	private void cls() {
		for(int i=0;i<40;i++) {
			System.out.println("");
		}
	}
	
	private void pause() {
		System.out.println("Press enter to continue");
		scan.nextLine();
	}
	
	private void mainMenu() {
		System.out.println("Danbam");
		System.out.println("");
		System.out.println("Total money : "+money);
		System.out.println("");
		System.out.println("1. Add danbam's chef");
		System.out.println("2. View danbam's information");
		System.out.println("3. View customers in danbam");
		System.out.println("4. Quit");
		System.out.print(">> ");
	}
	
	private void infoMenu() {
		System.out.println("1. View all chefs");
		System.out.println("2. View customer with history order");
		System.out.println("3. Exit");
		System.out.print(">> ");
	}

	private String generateID() {
		String id=""+UUID.randomUUID();
		return id;
	}
	
	private int generatePrice() {
		int price = rand.nextInt(501)+500;
		return price;
	}
	
	private void initialize() {
		ch =new Chef(generateID(),"Park Sae Royi",33,"Male","saeroyi",3000,"Professional");
		ch.cooking(vecMenu, queue);
		t2.start();
		vecChef.add(ch);
		m=new Menu(generateID(), "Kimchi Jjigae", generatePrice());
		vecMenu.add(m);
		m=new Menu(generateID(), "Jjinmandu", generatePrice());
		vecMenu.add(m);
		m=new Menu(generateID(), "Daeji Bulgogi", generatePrice());
		vecMenu.add(m);
		m=new Menu(generateID(), "Gogigui", generatePrice());
		vecMenu.add(m);
		m=new Menu(generateID(), "Haejangguk", generatePrice());
		vecMenu.add(m);
		m=new Menu(generateID(), "Sundubu Jjigae", generatePrice());
		vecMenu.add(m);
		m=new Menu(generateID(), "Saengseon Jjigae", generatePrice());
		vecMenu.add(m);
		m=new Menu(generateID(), "Nakji Bokkeum", generatePrice());
		vecMenu.add(m);
		m=new Menu(generateID(), "Seolleongtang", generatePrice());
		vecMenu.add(m);
		m=new Menu(generateID(), "Dolsot Bibimbap", generatePrice());
		vecMenu.add(m);
	}
	
	private int errorNameCheck(String name) {
		String[] arr = name.split(" ");
		if(arr.length<3) return 1;
		else return 0;
	}
	
	private int errorUsernameCheck(String username,String name) {
		String temp = name;
		temp.replaceAll(" ", "");
		if(!temp.toLowerCase().contains(username.toLowerCase())) return 1;
		return 0;
	}
	
	private void addChef() {
		String name;
		String username;
		String gender;
		int age=0;
		int salary = 3000;
		int stat=0;
		String status=null;
		String id = generateID();
		int error=0;
		do {
			error=0;
			System.out.print("Chef's name [must contain 3 words]>> ");
			name=scan.nextLine();
			error=errorNameCheck(name);
		}while(error==1);
		do {
			error=0;
			System.out.print("Chef's username[must be unique]>> ");
			username=scan.nextLine();
			error=errorUsernameCheck(username,name);
		}while(error==1);
		do {
			System.out.print("Chef's gender [Female | Male](Case Sensitive)>> ");
			gender=scan.nextLine();
		}while(!gender.equals("Male")&&!gender.equals("Female"));
		do {
			System.out.println("Chef's age [17 - 40]>> ");
			age=scan.nextInt();scan.nextLine();
		}while(age<17||age>40);
		stat = rand.nextInt(2);
		if(stat==0) {
			status="Not Professional";
		}else if(stat==1) {
			status="Professional";
		}
		ch = new Chef(id, name, age, gender, username, salary, status);
		ch.cooking(vecMenu, queue);
		t2.start();
		vecChef.add(ch);
		System.out.println("Chef has been successfully added!");
		pause();
	}
	
	private void viewChef() {
		for (Chef chef : vecChef) {
			System.out.println("ID : "+chef.getId());
			System.out.println("Name : "+chef.getName());
			System.out.println("Username : "+chef.getUsername());
			System.out.println("Age : "+chef.getAge());
			System.out.println("Gender : "+chef.getGender());
			System.out.println();
		}
		pause();
	}
	
	private void danbamInfo() {
		int choose;
		do {
			cls();
			infoMenu();
			choose = scan.nextInt();scan.nextLine();
			switch(choose) {
			case 1:
				viewChef();
				break;
			case 2:
				custDetails();
				break;
			default:
				break;
			}
		}while(choose!=3);
	}
	
	private void custActivities(int index) {
		Thread t4 = new Thread(new Runnable() {
			public void run() {
				int exit;
				do {
					try {
						int random = rand.nextInt(queue.size());
						vecCust.get(index).getVecPesanan().add(queue.get(random));
						queue.remove(random);
						Thread.sleep(4000);
					} catch (Exception e) {
						
					}
					exit = rand.nextInt(10);
					if(exit==0) {
						seats-=1;
						int total=0;
						for (Menu menu : vecCust.get(index).getVecPesanan()) {
							total+=menu.getPrice();
						}
						money+=total;
					}
				}while(exit!=0);
			}
		});
		t4.start();
	}

	private void custIn() {
		System.out.println("Cust in danbam now");
		if(vecCust.size()<5) {
			for (Customer cust : vecCust) {
				System.out.println(cust.getName());
			}
			pause();
		}else {
			for(int i=vecCust.size()-1;i>=vecCust.size()-6;i--) {
				System.out.println(vecCust.get(i).getName());
			}
		}
	}
	
	private void custDetails() {
		System.out.println("Customer list");
		for (Customer cu : vecCust) {
			System.out.println("Name : "+cu.getName());
			System.out.println("Age : "+cu.getAge());
			System.out.println("Gender : "+cu.getGender());
			System.out.println("");
			int total =0;
			System.out.println("Menu details");
			for (Menu me : cu.getVecPesanan()) {
				System.out.println(me.getId()+" - "+me.getName());
				total+=me.getPrice();
			}
			System.out.println("");
			System.out.println("Total Price: W"+total);
			System.out.println("");
			System.out.println("----------------------------");
		}
		pause();
	}
	
 	public static void main(String[] args) {
		new Main();

	}

}

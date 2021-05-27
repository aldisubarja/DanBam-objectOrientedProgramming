import java.util.Vector;

public class Customer extends Person{

	private Vector<Menu> vecPesanan = new Vector<Menu>();
	
	public Customer(String id, String name, int age, String gender) {
		super(id, name, age, gender);
	}
	
	

	public Vector<Menu> getVecPesanan() {
		return vecPesanan;
	}

	public void setVecPesanan(Vector<Menu> vecPesanan) {
		this.vecPesanan = vecPesanan;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

}


import java.util.ArrayList;

public class Primes {
	private ArrayList<Integer> data;
	private Boolean[] booleanData;

	public Primes() {
		data = new ArrayList<Integer>();
		booleanData = new Boolean[(int) 10e6 + 1];
	}

	public void Seive(int n) {
		if (n <= 1 || n > (int) (10e6))
			return;
		for (int i = 2; i <= n; i++)
			booleanData[i] = false;
		for (int i = 2; i * i <= n; i++) {
			if (!booleanData[i]) {
				for (int j = i * i; j <= n; j += i) {
					booleanData[j] = true;
				}
				data.add(i);
			}
		}
	}

	public int primeNumberGen() {
		if (data.size() == 0)
			Seive((int) 10e6);
		int random = (int) Math.round((data.size()) * Math.random());
		// System.out.println("Your Random number is "+primes.get(random));
		return data.get(random);
	}

	public static void main(String[] args) {
		Primes p = new Primes();
		System.out.println("Random Prime = " + p.primeNumberGen());
	}
}
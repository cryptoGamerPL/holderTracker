import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class WalletTrackerIcons {
	public static void main(String[] args) {
		
			ArrayList<Wallet> holdersD = readData("c:\\Downloads\\HERO 01.08.21.csv");
			ArrayList<Wallet> holdersD1 = readData("c:\\Downloads\\HERO 02.08.21.csv");
			
			ArrayList<Integer> d = getData(holdersD);
			ArrayList<Integer> d1 = getData(holdersD1);
			
			System.out.println("Holders daily change 01.08.2021 - 02.08.2021");
			printResult(d, d1);

		
	}

	public static ArrayList<Wallet> readData(String path) {
		ArrayList<Wallet> holders = new ArrayList<Wallet>();
		int lineNumber = -1;
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line;

			while ((line = br.readLine()) != null) {
				lineNumber++;
				if (lineNumber == 0) {
					continue;
				}
				StringTokenizer st = new StringTokenizer(line, ",");
				int counter = -1;
				Wallet wallet = new Wallet();
				while (st.hasMoreTokens()) {
					String current = st.nextToken().replaceAll("\"", "");
					counter++;
					switch (counter) {
					case 0:
						wallet.setAddress(current);
						break;
					case 1:
						if (current.contains(".")) {
							current = current.substring(0, current.indexOf("."));
						}
						if (current.contains("E")) {
							current = "0";
						}
						wallet.setAmount(Long.parseLong(current));
						break;
					case 2:
						break;
					default:
						break;
					}

				}
				holders.add(wallet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return holders;

	}
	
	public static void printResult (ArrayList<Integer> r1, ArrayList<Integer> r2) {
		
		
		System.out.println("Holders [TOTAL]      : " + r2.get(0) + " ("+getDiff(r1.get(0), r2.get(0))+")");
		System.out.println("🐳 Whale 10M+        : " + r2.get(1) + " ("+getDiff(r1.get(1), r2.get(1))+")");
		System.out.println("🦈 Shark 5M-10M      : " + r2.get(2) + " ("+getDiff(r1.get(2), r2.get(2))+")");
		System.out.println("🐊 Crocodile 1M-5M   : " + r2.get(3) + " ("+getDiff(r1.get(3), r2.get(3))+")");
		System.out.println("🐬 Dolphin 500K-1M   : " + r2.get(4) + " ("+getDiff(r1.get(4), r2.get(4))+")");
		System.out.println("🐙 Octopus 100K-500K : " + r2.get(5) + " ("+getDiff(r1.get(5), r2.get(5))+")");
		System.out.println("🐍 Seasnake 50K-100K : " + r2.get(6) + " ("+getDiff(r1.get(6), r2.get(6))+")");
		System.out.println("🐢 Turtle 10K-50K    : " + r2.get(7) + " ("+getDiff(r1.get(7), r2.get(7))+")");
		System.out.println("🐟 Piranha 5K-10K    : " + r2.get(8) + " ("+getDiff(r1.get(8), r2.get(8))+")");
		System.out.println("🦞 Lobster 1K-5K     : " + r2.get(9) + " ("+getDiff(r1.get(9), r2.get(9))+")");;
		System.out.println("🦀 Crab 500-1K       : " + r2.get(10) + " ("+getDiff(r1.get(10), r2.get(10))+")");;
		System.out.println("🦐 Shrimp 100-500    : " + r2.get(11) + " ("+getDiff(r1.get(11), r2.get(11))+")");
		System.out.println("🐠 Plankton 1-100    : " + r2.get(12) + " ("+getDiff(r1.get(12), r2.get(12))+")");
	}
	
	public static String getDiff(int d, int d1) {
		return (d1-d)>0?"+"+(d1-d):""+(d1-d);
	}

	public static ArrayList<Integer> getData(ArrayList<Wallet> holders) {
		
		ArrayList<Integer> counters = new ArrayList<Integer>();
		
		int amountOver10M = 0;
		int amountOver5M = 0;
		int amountOver1M = 0;
		int amountOver500K = 0;
		int amountOver100K = 0;
		int amountOver50K = 0;
		int amountOver10K = 0;
		int amountOver5K = 0;
		int amountOver1K = 0;
		int amountOver500 = 0;
		int amountOver100 = 0;
		int amountBelow100 = 0;
		for (Wallet wallet : holders) {

			if (wallet.getAmount() >= 10000000) {
				amountOver10M++;
			}
			else if (wallet.getAmount() >= 5000000) {
				amountOver5M++;
			}
			else if (wallet.getAmount() >= 1000000) {
				amountOver1M++;
			}
			else if (wallet.getAmount() >= 500000) {
				amountOver500K++;
			}
			else if (wallet.getAmount() >= 100000) {
				amountOver100K++;
			}
			else if (wallet.getAmount() >= 50000) {
				amountOver50K++;
			}
			else if (wallet.getAmount() >= 10000) {
				amountOver10K++;
			}
			else if (wallet.getAmount() >= 5000) {
				amountOver5K++;
			}
			else if (wallet.getAmount() >= 1000) {
				amountOver1K++;
			}
			else if (wallet.getAmount() >= 500) {
				amountOver500++;
			}
			else if (wallet.getAmount() >= 100) {
				amountOver100++;
			}
			else {
				amountBelow100++;
			}

		}
		
		counters.add(holders.size());
		counters.add(amountOver10M);
		counters.add(amountOver5M);
		counters.add(amountOver1M);
		counters.add(amountOver500K);
		counters.add(amountOver100K);
		counters.add(amountOver50K);
		counters.add(amountOver10K);
		counters.add(amountOver5K);
		counters.add(amountOver1K);
		counters.add(amountOver500);
		counters.add(amountOver100);
		counters.add(amountBelow100);
		
		return counters;

	}
}

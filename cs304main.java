
public class cs304main {
	
	public static void main(String args[])
	{
		dbConnection c = new dbConnection();
		//swing gui = new swing(c.getConnection());
		Search s = new Search(c.getConnection());
		s.searchTitle("ok computer", 3);
		//System.out.println(s.results(1).printItem());
		System.out.println(s.printResults());
	} 

}

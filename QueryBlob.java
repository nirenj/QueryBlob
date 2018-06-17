import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 * @author Nirenj George
 *
 */
public class QueryBlob
{
	public static void main(String[] args)
	{
		String url = "jdbc:db2://10.10.10.1:60000/ABCDB"; //DB url
		String usr = "abcd"; //DB user
		String ps = "abcd";  //DB password
		
		String query = "select * from table where status='P' with UR"; //query
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try
		{
			Class.forName("com.ibm.db2.jcc.DB2Driver");

			conn = DriverManager.getConnection(url, usr, ps);
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while (rset.next())
			{
				String uuid = rset.getString("Column1"); //column 1
				byte[] b = rset.getBytes("Column2");	 //column 2
				
				if (b != null)
				{
					System.out.print(uuid + "#");
					for (byte bs : b)
					{
						System.out.print(bs + "|");
					}
					System.out.println();
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();

		}
		finally
		{
			try
			{
				if (rset != null) rset.close();
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}

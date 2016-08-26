package bankmanagement;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
public class DataAccess {
	private Connection conn;
	private Statement stm;
	
	private String connStr = "jdbc:oracle:thin:hr/123456@localhost:1521:XE";
	private String dbusername = "hr";
	private String dbpassword = "123456";
	
	public DataAccess()
	{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			this.conn = DriverManager.getConnection(connStr, dbusername, dbpassword);
			this.stm = this.conn.createStatement();
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public ResultSet getResultSet(String sql)
	{
		try{
			return this.stm.executeQuery(sql);
		} catch(Exception ex)
		{
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean executeSQL(String sql)
	{
		try{
			return this.stm.execute(sql);
		} catch(Exception ex)
		{
			ex.printStackTrace();
			return false;
		}
	}
	
	@Override
	protected void finalize()
	{
		try{
			this.stm.close();
			this.conn.close();
		} catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
	}
        public void FillTable(JTable table,String sql){
        try
        {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","HR","123456");
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(sql);

        while(table.getRowCount() > 0) 
        {
            ((DefaultTableModel) table.getModel()).removeRow(0);
        }
        int columns = rs.getMetaData().getColumnCount();
        while(rs.next())
        {  
            Object[] row = new Object[columns];
            for (int i = 1; i <= columns; i++)
            {  
                row[i - 1] = rs.getObject(i);
            }
            ((DefaultTableModel) table.getModel()).insertRow(rs.getRow()-1,row);
        }

        rs.close();
        stat.close();
        con.close();
        }
        catch(Exception e)
        {
            System.out.println("Error in the table");
        }
    }
        
}

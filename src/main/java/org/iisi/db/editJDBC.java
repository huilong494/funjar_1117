package org.iisi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.iisi.bean.edit;

public class editJDBC extends JDBCCore {
	public List<edit> edit(String eid) throws Exception {
		ArrayList<edit> showed = new ArrayList<edit>();

		try {
			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select pid,applytime,reply from pse_main where eid=? and status=2 order by pid desc");
			pstmt.setString(1, eid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int i = 0;
				String rspid = rs.getString(1);
				String rstime = rs.getString(2);
				String rsreply = rs.getString(3);

				edit edl = new edit(rspid, rstime, rsreply);
				showed.add(i, edl);//ArrayList<edit> showed = new ArrayList<edit>();
				i++;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
throw e;
		}
		return showed;//回傳sditServlet1
	}
}

/*
 * public String getName(String Name) { String eName =""; try { Connection conn;
 * conn = makeConnection(); PreparedStatement st = conn
 * .prepareStatement("Select Name from EMPLOYEE where Name ='"+Name+"'"); //
 * st.setString(1, Name); ResultSet rs = st.executeQuery(); while(rs.next()) {
 * eName=rs.getString("Name"); }
 * 
 * } catch (Exception e) { eName = "???"; //發生錯誤 }
 * 
 * return eName; }
 * 
 * 
 * }
 */
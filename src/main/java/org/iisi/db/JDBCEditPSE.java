package org.iisi.db;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.iisi.bean.Holiday;


public class JDBCEditPSE extends JDBCCore {

	public int ePSE(int Pid, String ad) {
		
		int r=1;
		try {

			
			Connection conn;
			conn = makeConnection();
						
			PreparedStatement st = conn
					.prepareStatement("update PSE.PSE_MAIN set STATUS=3,APPLYTIME = STR_TO_DATE(?,'YYYY/MM/DD HH24/MI') where PID=?");
			st.setString(1, ad);
			st.setInt(2, Pid);
			st.executeUpdate();

			st.close();
			
			conn.close();

		} catch (Exception e) {
	     r=5;
		}
		return r;
	}
	
	public int eSubPSE(int Pid,int Pcid, String pk,String ds, String de,
			String ts, String te, String total, String ps) {
		int r = 0;
		
		try {

			
			Connection conn;
			conn = makeConnection();
			
			PreparedStatement st = conn
					.prepareStatement("update PSE.PSE_SUB set STARTDATETIME = STR_TO_DATE(?,'%Y-%m-%d%H:%i'),ENDDATETIME = STR_TO_DATE(?,'%Y-%m-%d%H:%i'),PCTOTAL = ?, KID=?, PS=?  where PID=? and PCID=?");
			//pid/pcid/st/et/pctotal/kid/ps
			
			
			st.setString(1, ds+ts);
			
			st.setString(2, de+te);
			
			st.setDouble(3, Double.parseDouble(total));
			st.setInt(4, Integer.parseInt(pk));
			st.setString(5, ps);
			st.setInt(6, Pid);
			st.setInt(7, Pcid);
			st.executeUpdate();
				
			st.close();
			conn.close();

		} catch (Exception e) {
		r=5;
		}
		return r;
	}
	
	
public List<Holiday> getHolidays(){
		
		ArrayList<Holiday> holiday = new ArrayList<Holiday>();
		try {

			Connection conn;
			conn = makeConnection();
			PreparedStatement pstmt = conn
					.prepareStatement("select date_format(H_Date,'%Y-%m-%d'),H_NAME from holiday ");
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int i = 0;
				String H_date = rs.getString(1);
				String H_name = rs.getString(2);
				

				Holiday dl = new Holiday(H_date, H_name);
				holiday.add(i, dl);
				i++;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
		}
		return holiday;
	}

public int checkpse(int j, String sdate, String stime, String edate,String etime) {
	int count = 0;
	try {
		Connection conn;
		conn = makeConnection();
		PreparedStatement pstmt = conn
				.prepareStatement("select count(A.PID) from pse_sub a,pse_main b where a.pid=b.pid and b.status=? and( a.startdatetime between STR_TO_DATE(?,'%Y-%m-%d %H:%i') and STR_TO_DATE(?,'%Y-%m-%d %H:%i') or a.enddatetime between  STR_TO_DATE(?,'%Y-%m-%d %H:%i') and STR_TO_DATE(?,'%Y-%m-%d%H:%i'))");
		pstmt.setInt(1, j);
		pstmt.setString(2, sdate + " " +stime);
		pstmt.setString(3, edate + " "+ etime);
		pstmt.setString(4, sdate + " "+ stime);
		pstmt.setString(5, edate + " "+ etime);
		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			count=rs.getInt(1);
			
		} else {
			count = 99999;
		}
		pstmt.close();
		conn.close();

	} catch (Exception e) {
		count = 5; // 發生錯誤
		return count;

	}

	return count;
}

}

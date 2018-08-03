package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

// ����Ŭ�� ���� -> select, insert, update, delete �۾� ����
public class MemberDAO {
	
	String id = "scott";		// DB ���� ���̵�
	String pass = "tiger";		// DB ���� �н�����
	String url = "jdbc:oracle:thin:@localhost:1521:XE";	// ����Ŭ ���� ���
	
	Connection con;				// ����Ŭ�� ������ �� �ְ� �����ϴ� ��ü
	PreparedStatement pstmt;	// ����Ŭ���� ������ ��������ִ� ��ü
	ResultSet rs;				// ����Ŭ���� select ���� ����� ���Ϲ޾� �����ϴ� ��ü
	
	// 1. ����Ŭ�� �����ϴ� �޼���
	public void getCon() {
		
		try {
			// ����Ŭ ������ ���̽� ��� ����(����Ŭ ����̹� �ε�)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// ����Ŭ�� ����
			con = DriverManager.getConnection(url, id, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 2. ����Ŭ�� ȸ�� �Ѹ��� insert �ϴ� �޼���
	public void insertMember(MemberBean mbean) {
		try {
			// ����Ŭ�� ����
			getCon();
			
			// ���� �غ� (PreparedStatment ��ü ���)
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			
			// PreparedStatment ��ü�� �̿��Ͽ� JSP���� ������ ����ϵ��� ����
			pstmt = con.prepareStatement(sql);
			
			// �������� "?"�� ������ �°� ������ ����
			pstmt.setString(1, mbean.getId());
			pstmt.setString(2, mbean.getPass1());
			pstmt.setString(3, mbean.getEmail());
			pstmt.setString(4, mbean.getTel());
			pstmt.setString(5, mbean.getHobby());
			pstmt.setString(6, mbean.getJob());
			pstmt.setString(7, mbean.getAge());
			pstmt.setString(8, mbean.getInfo());
			
			// ���� ����
			pstmt.executeUpdate();	// insert, update, delete �� ����ϴ� �޼���
			
			// �ڿ� �ݳ�
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 3. ��� ȸ�������� ��ȸ�Ͽ� �����ϴ� �޼���
	public Vector<MemberBean> SelectAllMember() {
		Vector<MemberBean> vec = new Vector<MemberBean>();
		
		try {
			// ����Ŭ�� ����
			getCon();
			
			// ���� �غ�
			String sql = "select * from member";
			
			// PreparedStatment ��ü�� �̿��Ͽ� JSP���� ������ ����ϵ��� ����
			pstmt = con.prepareStatement(sql);
			
			// ����Ŭ���� ������ ���� -> ���� ����� ���Ϲ޾� ResultSet ��ü�� ����
			//	(���̺� �˻� ����� �޾Ƽ� ResultSet ��ü�� ����)
			rs = pstmt.executeQuery();	// select �� ����ϴ� �޼���
			
			// �ݺ����� �̿��Ͽ� ResultSet ��ü�� ����� �Ǽ���ŭ MemberBean ��ü�� ���� �� ����
			while(rs.next()) {
				MemberBean bean = new MemberBean();
				
				bean.setId(rs.getString(1));
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
				
				vec.add(bean);
			}
		
			// �ڿ� �ݳ�
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vec;
	}
	
	// 4. ȸ�� �Ѹ��� ������ ��ȸ�Ͽ� �����ϴ� �޼���
	public MemberBean SelectOneMember(String id) {
		
		MemberBean bean = new MemberBean();
		
		try {
			// Ŀ�ؼ� ����
			getCon();
			
			// ���� �غ�
			String sql = "select * from member where id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			// ���� ����
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				bean.setId(rs.getString(1));
				bean.setPass1(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setTel(rs.getString(4));
				bean.setHobby(rs.getString(5));
				bean.setJob(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setInfo(rs.getString(8));
			}
			
			// �ڿ��ݳ�
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
		
	}
	
	// 5. ȸ�� �Ѹ��� ��й�ȣ�� ��ȸ�Ͽ� �����ϴ� �޼���
	public String getPass(String id) {
		
		String pass = "";
		
		try {
			getCon();
			
			String sql = "select pass1 from member where id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				pass = rs.getString(1);
			}
			
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pass;	
	}
	
	// 6. ���̺� �Ѹ��� ȸ�� ������ Update �ϴ� �޼���
	public void updateMember (MemberBean bean) {
		
		try {
			getCon();
			
			String sql = "update member set email=?, tel=?, hobby=?, job=?, age=?, info=? where id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getEmail());
			pstmt.setString(2, bean.getTel());
			pstmt.setString(3, bean.getHobby());
			pstmt.setString(4, bean.getJob());
			pstmt.setString(5, bean.getAge());
			pstmt.setString(6, bean.getInfo());
			pstmt.setString(7, bean.getId());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 7. ���̺� �Ѹ��� ȸ�� ������ Delete �ϴ� �޼���
		public void deleteMember (String id) {
			
			try {
				// ����Ŭ�� ����
				getCon();
				
				// ���� �غ�
				String sql = "delete from member where id=?";
				
				// ���� ���� ��ü ����
				pstmt = con.prepareStatement(sql);
				
				// ? ���� (���� ���� �ϼ�)
				pstmt.setString(1, id);
				
				// ���� ����
				pstmt.executeUpdate();
				
				// �ڿ� �ݳ�
				pstmt.close();
				rs.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	
}





















package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

// 오라클에 연결 -> select, insert, update, delete 작업 실행
public class MemberDAO {
	
	String id = "scott";		// DB 접속 아이디
	String pass = "tiger";		// DB 접속 패스워드
	String url = "jdbc:oracle:thin:@localhost:1521:XE";	// 오라클 접속 경로
	
	Connection con;				// 오라클에 접속할 수 있게 설정하는 객체
	PreparedStatement pstmt;	// 오라클에서 쿼리를 실행시켜주는 객체
	ResultSet rs;				// 오라클에서 select 쿼리 결과를 리턴받아 저장하는 객체
	
	// 1. 오라클에 연결하는 메서드
	public void getCon() {
		
		try {
			// 오라클 데이터 베이스 사용 선언(오라클 드라이버 로드)
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 오라클에 접속
			con = DriverManager.getConnection(url, id, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 2. 오라클에 회원 한명을 insert 하는 메서드
	public void insertMember(MemberBean mbean) {
		try {
			// 오라클에 접속
			getCon();
			
			// 쿼리 준비 (PreparedStatment 객체 사용)
			String sql = "insert into member values(?,?,?,?,?,?,?,?)";
			
			// PreparedStatment 객체를 이용하여 JSP에서 쿼리를 사용하도록 설정
			pstmt = con.prepareStatement(sql);
			
			// 쿼리에서 "?"의 순서에 맞게 데이터 매핑
			pstmt.setString(1, mbean.getId());
			pstmt.setString(2, mbean.getPass1());
			pstmt.setString(3, mbean.getEmail());
			pstmt.setString(4, mbean.getTel());
			pstmt.setString(5, mbean.getHobby());
			pstmt.setString(6, mbean.getJob());
			pstmt.setString(7, mbean.getAge());
			pstmt.setString(8, mbean.getInfo());
			
			// 쿼리 실행
			pstmt.executeUpdate();	// insert, update, delete 시 사용하는 메서드
			
			// 자원 반납
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 3. 모든 회원정보를 조회하여 리턴하는 메서드
	public Vector<MemberBean> SelectAllMember() {
		Vector<MemberBean> vec = new Vector<MemberBean>();
		
		try {
			// 오라클에 접속
			getCon();
			
			// 쿼리 준비
			String sql = "select * from member";
			
			// PreparedStatment 객체를 이용하여 JSP에서 쿼리를 사용하도록 설정
			pstmt = con.prepareStatement(sql);
			
			// 오라클에서 쿼리를 실행 -> 실행 결과를 리턴받아 ResultSet 객체에 저장
			//	(테이블 검색 결과를 받아서 ResultSet 객체에 저장)
			rs = pstmt.executeQuery();	// select 시 사용하는 메서드
			
			// 반복문을 이용하여 ResultSet 객체에 저장된 건수만큼 MemberBean 객체를 생성 및 셋팅
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
		
			// 자원 반납
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vec;
	}
	
	// 4. 회원 한명의 정보를 조회하여 리턴하는 메서드
	public MemberBean SelectOneMember(String id) {
		
		MemberBean bean = new MemberBean();
		
		try {
			// 커넥션 연결
			getCon();
			
			// 쿼리 준비
			String sql = "select * from member where id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			// 쿼리 실행
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
			
			// 자원반납
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
		
	}
	
	// 5. 회원 한명의 비밀번호를 조회하여 리턴하는 메서드
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
	
	// 6. 테이블에 한명의 회원 정보를 Update 하는 메서드
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
	
	// 7. 테이블에 한명의 회원 정보를 Delete 하는 메서드
		public void deleteMember (String id) {
			
			try {
				// 오라클에 접속
				getCon();
				
				// 쿼리 준비
				String sql = "delete from member where id=?";
				
				// 쿼리 실행 객체 선언
				pstmt = con.prepareStatement(sql);
				
				// ? 셋팅 (쿼리 구조 완성)
				pstmt.setString(1, id);
				
				// 쿼리 실행
				pstmt.executeUpdate();
				
				// 자원 반납
				pstmt.close();
				rs.close();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	
}





















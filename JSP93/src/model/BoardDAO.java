package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	// DB 연결
	public void getCon() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			con = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 전체 글의 개수를 리턴
	public int getAllCount() {
		// 게시글 전체 개수를 저장할 변수
		int count = 0;
		
		getCon();
		
		try {
			// 쿼리준비
			String sql = "select count(*) from board";
			
			// 쿼리 실행 객체 선언
			pstmt = con.prepareStatement(sql);
			
			// 쿼리 실행 및 결과를 리턴
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			// 자원반납
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	// 화면에 출력할 게시글 개수 만큼만 리턴
	public Vector<BoardBean> getAllBoard(int strat, int end) {
		Vector<BoardBean> vec = new Vector<>();
		
		getCon();
		
		try {
			// 쿼리 준비
			String sql = "select * from (select a.*, ROWNUM AS rnum from (select * from board order by ref DESC, re_level ASC) a) where rnum between ? and ?";
			
			//쿼리 실행 객체 선언 및 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, strat);
			pstmt.setInt(2, end);
			
			// 쿼리실행 후 결과 받음
			rs = pstmt.executeQuery();
			
			// 받은 데이터 갯수만큼 반복하며 추출 및 처리
			while(rs.next()) {
				BoardBean bean = new BoardBean();
				
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
				bean.setDel_flag(rs.getString(12));	// 삭제여부
				
				vec.add(bean);
			}
			
			// 자원반납
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return vec;
	}

	// 글쓰기
	public void insertBoard(BoardBean bean) {
		getCon();
		
		int ref = 0;
		int re_step = 1;
		int re_level = 1;
		
		try {
			String refsql = "select NVL(max(ref),0) from board";
			pstmt = con.prepareStatement(refsql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				ref = rs.getInt(1) + 1;	// 새글쓰기 : 글그룹 최대값 + 1
			}
			
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,SYSDATE,?,?,?,0,?,'N')";	// 마지막 'N' : 삭제여부
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step);
			pstmt.setInt(7, re_level);
			pstmt.setString(8, bean.getContent());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 조회수를 증가하면서 하나의 게시글을 리턴
	public BoardBean getOneBoard(int num) {
		BoardBean bean = new BoardBean();
		
		getCon();
		
		try {
			// 조회수 증가
			String readsql = "update board set readcount = readcount + 1 where num = ?";
			pstmt = con.prepareStatement(readsql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			String sql = "select * from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
			}
			
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	// 답글저장
	public void ReWriteBoard(BoardBean bean) {
		int ref = bean.getRef();
		int re_step = bean.getRe_step();
		int re_level = bean.getRe_level();
		
		getCon();
		
		try {
			// 1. 부모글과 같은 그룹의 글을 대상으로 -> 부모글보다 큰 re_level의 값을 모두 1씩 증가
			String levelsql = "update board set re_level = re_level + 1 where ref = ? and re_level > ?";
			
			pstmt = con.prepareStatement(levelsql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, re_level);
			pstmt.executeUpdate();
			
			// 2. 답글 저장
			String sql = "insert into board values(board_seq.NEXTVAL,?,?,?,?,sysdate,?,?,?,0,?,'N')"; // 마지막 'N' : 삭제여부
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getWriter());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getSubject());
			pstmt.setString(4, bean.getPassword());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, re_step + 1);
			pstmt.setInt(7, re_level + 1);
			pstmt.setString(8, bean.getContent());
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	// 조회수 증가없이 하나의 게시글을 리턴
	public BoardBean getOneUpdateBoard(int num) {
		BoardBean bean = new BoardBean();
		
		getCon();
		
		try {
			String sql = "select * from board where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bean.setNum(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setSubject(rs.getString(4));
				bean.setPassword(rs.getString(5));
				bean.setReg_date(rs.getDate(6).toString());
				bean.setRef(rs.getInt(7));
				bean.setRe_step(rs.getInt(8));
				bean.setRe_level(rs.getInt(9));
				bean.setReadcount(rs.getInt(10));
				bean.setContent(rs.getString(11));
			}
			
			pstmt.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return bean;
	}
	
	// 해당 게시글의 비밀번호를 리턴
	public String getPass(int num) {
		String pass = "";
		
		getCon();
		
		try {
			String sql = "select password from board where num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
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
	
	// 해당 게시글 한개를 수정
	public void updateBoard(int num, String subject, String content) {
		getCon();
		
		try {
			String sql = "update board set subject = ?, content = ? where num = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, num);
			
			pstmt.executeUpdate();
			
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 해당 게시글 한개를 삭제
	public void deleteBoard(int num) {
		getCon();
		
		try {
			// 쿼리준비
			// String sql = "delete from board where num = ?";
			String sql = "update board set del_flag = 'Y' where num = ?"; // 글 삭제시 업데이트로 처리
			
			// 쿼리실행객체 선언 및 셋팅
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			// 쿼리실행
			pstmt.executeUpdate();
			
			// 자원반납
			pstmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}















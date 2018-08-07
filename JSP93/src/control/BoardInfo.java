package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardDAO;

@WebServlet("/BoardInfo.do")
public class BoardInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한글처리
		request.setCharacterEncoding("utf-8");
		
		// 글번호(PK) 받음
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DB에서 선택된 하나의 게시글에 대한 정보를 얻어옴
		BoardDAO bdao = new BoardDAO();
		BoardBean bean = bdao.getOneBoard(num);
		
		// 얻어온 bean 객체를 request 객체에 저장
		request.setAttribute("bean", bean);
		
		// 뷰(JSP) 쪽으로 데이터 넘겨줌
		RequestDispatcher rd = request.getRequestDispatcher("C01BoardInfo.jsp");
		rd.forward(request, response);
	}

}

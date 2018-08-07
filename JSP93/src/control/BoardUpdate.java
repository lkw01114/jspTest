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

@WebServlet("/BoardUpdate.do")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// C01BoardInfo.jsp 에서 넘어온 글번호를 받음
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DAO의 메서드를 통해 글상세 정보를 리턴 받음 -> BoardBean 객체에 저장
		BoardDAO bdao = new BoardDAO();
		BoardBean bean = bdao.getOneUpdateBoard(num);
		
		// 수정하기 페이지로 빈객체 넘기면서 포워딩
		request.setAttribute("bean", bean);
		
		RequestDispatcher rd = request.getRequestDispatcher("E01BoardUpdateForm.jsp");
		rd.forward(request, response);
	}

}

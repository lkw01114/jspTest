package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;

@WebServlet("/BoardDeleteProc.do")
public class BoardDeleteProc extends HttpServlet {
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
		
		// F01BoardDeleteForm.jsp 에서 넘어온 데이터를 받음
		int num = Integer.parseInt(request.getParameter("num"));
		String password = request.getParameter("password");
		
		// DB에서 해당 글의 패스워드 받아옴
		BoardDAO bdao = new BoardDAO();
		String passdb = bdao.getPass(num);
		
		// 패스워드 일치 여부 확인 및 데이터 삭제 처리
		if(passdb.equals(password)) {
			// 해당글 삭제 처리
			BoardDAO bdao2 = new BoardDAO();
			bdao2.deleteBoard(num);
			
			// 삭제 종료후 전체글보기 페이지로 이동
			request.setAttribute("msg", "DELETE_OK");
			RequestDispatcher rd = request.getRequestDispatcher("BoardList.do");
			rd.forward(request, response);
		} else {
			// 비밀번호가 틀리면 에러페이지로 이동
			request.setAttribute("msg", "DELETE_PASS_ERR");
			RequestDispatcher rd = request.getRequestDispatcher("R01Error.jsp");
			rd.forward(request, response);
		}
	}

}

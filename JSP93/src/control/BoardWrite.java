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

@WebServlet("/BoardWrite.do")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �ѱ�ó��
		request.setCharacterEncoding("utf-8");
		
		// BoardBean ��ü�� �̿��Ͽ� ����ڰ� �Է��� �����͸� ����
		BoardBean bean = new BoardBean();
		bean.setWriter(request.getParameter("writer"));
		bean.setSubject(request.getParameter("subject"));
		bean.setEmail(request.getParameter("email"));
		bean.setPassword(request.getParameter("password"));
		bean.setContent(request.getParameter("content"));
		
		// ����� �Է°��� DAO�� �Ѱܼ� insert ó��
		BoardDAO bdao = new BoardDAO();
		bdao.insertBoard(bean);
		
		// �����Ͱ� insert �� �� -> �ڵ����� ��ü�ۺ���� �̵�
		RequestDispatcher rd = request.getRequestDispatcher("BoardList.do");
		rd.forward(request, response);
	}

}








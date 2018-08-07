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

@WebServlet("/BoardDelete.do")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		// C01BoardInfo.jsp ���� �Ѿ�� �� ��ȣ�� ����
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DAO�� �޼��带 ���� �ۻ� ������ ���� ���� -> BoardBean ��ü�� �ۻ� ���� ����
		BoardDAO bdao = new BoardDAO();
		BoardBean bean = bdao.getOneUpdateBoard(num);
		
		// �����ϱ� �������� BoardBean ��ü �ѱ�鼭 ������
		request.setAttribute("bean", bean);
		
		RequestDispatcher rd = request.getRequestDispatcher("F01BoardDeleteForm.jsp");
		rd.forward(request, response);
	}

}

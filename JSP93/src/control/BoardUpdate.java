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
		
		// C01BoardInfo.jsp ���� �Ѿ�� �۹�ȣ�� ����
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DAO�� �޼��带 ���� �ۻ� ������ ���� ���� -> BoardBean ��ü�� ����
		BoardDAO bdao = new BoardDAO();
		BoardBean bean = bdao.getOneUpdateBoard(num);
		
		// �����ϱ� �������� ��ü �ѱ�鼭 ������
		request.setAttribute("bean", bean);
		
		RequestDispatcher rd = request.getRequestDispatcher("E01BoardUpdateForm.jsp");
		rd.forward(request, response);
	}

}

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
		// �ѱ�ó��
		request.setCharacterEncoding("utf-8");
		
		// �۹�ȣ(PK) ����
		int num = Integer.parseInt(request.getParameter("num"));
		
		// DB���� ���õ� �ϳ��� �Խñۿ� ���� ������ ����
		BoardDAO bdao = new BoardDAO();
		BoardBean bean = bdao.getOneBoard(num);
		
		// ���� bean ��ü�� request ��ü�� ����
		request.setAttribute("bean", bean);
		
		// ��(JSP) ������ ������ �Ѱ���
		RequestDispatcher rd = request.getRequestDispatcher("C01BoardInfo.jsp");
		rd.forward(request, response);
	}

}

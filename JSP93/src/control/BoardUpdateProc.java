package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardDAO;

@WebServlet("/BoardUpdateProc.do")
public class BoardUpdateProc extends HttpServlet {
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
		
		// E01BoardUpdateForm.jsp ���� �Ѿ�� �����͸� ����
		int num = Integer.parseInt(request.getParameter("num"));
		String password = request.getParameter("password");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		// DB���� �ش� ���� �н����带 �޾ƿ�
		BoardDAO bdao = new BoardDAO();
		String passdb = bdao.getPass(num);
		
		// �н����� ��ġ ���� Ȯ�� �� ������ ���� ó��
		if(passdb.equals(password)) {
			// �ش�� ������Ʈ ó��
			BoardDAO bdao2 = new BoardDAO();
			bdao2.updateBoard(num, subject, content);
			
			// ������Ʈ ������ ��ü�ۺ��� �������� �̵�
			request.setAttribute("msg", "UPDATE_OK");
			RequestDispatcher rd = request.getRequestDispatcher("BoardList.do");
			rd.forward(request, response);
		} else {
			// ��й�ȣ�� Ʋ���� ������������ �̵�
			request.setAttribute("msg", "UPDATE_PASS_ERR");
			RequestDispatcher rd = request.getRequestDispatcher("R01Error.jsp");
			rd.forward(request, response);
		}
	}

}

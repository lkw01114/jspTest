package control;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BoardBean;
import model.BoardDAO;

@WebServlet("/BoardList.do")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request, response);
	}
	
	protected void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	////////// ī���͸� - Start
		
		//////////////////////////////////////////////////////
		// ȭ�鿡 ��µ� �Խñ��� ������ �����ϴ� ����
		int pageSize = 5;
		//////////////////////////////////////////////////////
		
		// ����ڰ� Ŭ���� �������� ��ȣ�� �����ϴ� ����
		String pageNum = request.getParameter("pageNum");
		// ����, ó�� BoardList.do�� ȣ���ϰų� �Ǵ� ����/���� �� �ٸ����������� �����ϴ� ���
		//	-> pageNum ���� -> ��ó�� �ʿ�
		if(pageNum == null) {
			pageNum = "1";
		}
		// ����ڰ� Ŭ���� �������� ��ȣ�� �����ϴ� ���� -> ����
		int currentPage = Integer.parseInt(pageNum);
		
		// ��ü ���� ������ ������ ���� : DB���� ��ȸ
		int count = 0;
		
		// �������� ǥ�õǴ� ù��° ���� �۹�ȣ(���� ���ٿ� �ִ� ���� �۹�ȣ)�� ������ ����
		int number = 0;
		
		// DB ���� �� ��ü���� ������ �޾ƿ�
		BoardDAO bdao = new BoardDAO();
		count = bdao.getAllCount();
		
		// ���� �������� ������ ���۱��� ����
		int startRow = (currentPage - 1) * pageSize + 1;
		// ���� �������� ������ ���������� ����
		int endRow = currentPage * pageSize;
		
		// ȭ�鿡 ��µ� �Խñ� ��ŭ�� ���� ����
		Vector<BoardBean> vec = bdao.getAllBoard(startRow, endRow);
		
		// �������� ǥ�õ� ù��° �۹�ȣ(���� ���ٿ� ��ġ�ϴ� �۹�ȣ)�� ����
		number = count - (currentPage - 1) * pageSize;
	////////ī���͸� - End
		
	//////// ȭ�鿡 ����� ����� �����Ͽ� request ��ü�� ���� �� A01BoardList.jsp ������ ������
		// 1. ���Ϳ� ��� �Խñ� ��ü��
		request.setAttribute("vec", vec);
		// 2. �������� ǥ���� ù��° �۹�ȣ
		request.setAttribute("number", number);
		// 3. ����� �Խñ��� ����
		request.setAttribute("pageSize", pageSize);
		// 4. ���̺� �ִ� ��ü �Խñ� ����
		request.setAttribute("count", count);
		// 5. ����ڰ� Ŭ���� �������� ��ȣ
		request.setAttribute("currentPage", currentPage);
	
	//////// ���� + ������ ��й�ȣ ���� �޽����� �ѱ�
		String msg = (String) request.getAttribute("msg");
		request.setAttribute("msg", msg);
		
		RequestDispatcher rd = request.getRequestDispatcher("A01BoardList.jsp");
		rd.forward(request, response);
	}

}


















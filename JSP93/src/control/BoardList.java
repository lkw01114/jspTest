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
	////////// 카운터링 - Start
		
		//////////////////////////////////////////////////////
		// 화면에 출력될 게시글의 개수를 지정하는 변수
		int pageSize = 5;
		//////////////////////////////////////////////////////
		
		// 사용자가 클릭한 페이지의 번호를 저장하는 변수
		String pageNum = request.getParameter("pageNum");
		// 만일, 처음 BoardList.do를 호출하거나 또는 수정/삭제 등 다른페이지에서 진입하는 경우
		//	-> pageNum 없음 -> 널처리 필요
		if(pageNum == null) {
			pageNum = "1";
		}
		// 사용자가 클릭한 페이지의 번호를 저장하는 변수 -> 정수
		int currentPage = Integer.parseInt(pageNum);
		
		// 전체 글의 개수를 저장할 변수 : DB에서 조회
		int count = 0;
		
		// 페이지에 표시되는 첫번째 글의 글번호(가장 윗줄에 있는 글의 글번호)를 저장할 변수
		int number = 0;
		
		// DB 연결 및 전체글의 개수를 받아옴
		BoardDAO bdao = new BoardDAO();
		count = bdao.getAllCount();
		
		// 현재 페이지에 보여줄 시작글을 설정
		int startRow = (currentPage - 1) * pageSize + 1;
		// 현재 페이지에 보여줄 마지막글을 설정
		int endRow = currentPage * pageSize;
		
		// 화면에 출력될 게시글 만큼만 리턴 받음
		Vector<BoardBean> vec = bdao.getAllBoard(startRow, endRow);
		
		// 페이지에 표시될 첫번째 글번호(가장 윗줄에 위치하는 글번호)를 지정
		number = count - (currentPage - 1) * pageSize;
	////////카운터링 - End
		
	//////// 화면에 출력할 내용들 관련하여 request 객체에 저장 및 A01BoardList.jsp 쪽으로 포워딩
		// 1. 벡터에 담긴 게시글 객체들
		request.setAttribute("vec", vec);
		// 2. 페이지에 표시할 첫번째 글번호
		request.setAttribute("number", number);
		// 3. 출력할 게시글의 개수
		request.setAttribute("pageSize", pageSize);
		// 4. 테이블에 있는 전체 게시글 개수
		request.setAttribute("count", count);
		// 5. 사용자가 클릭한 페이지의 번호
		request.setAttribute("currentPage", currentPage);
	
	//////// 수정 + 삭제시 비밀번호 관련 메시지를 넘김
		String msg = (String) request.getAttribute("msg");
		request.setAttribute("msg", msg);
		
		RequestDispatcher rd = request.getRequestDispatcher("A01BoardList.jsp");
		rd.forward(request, response);
	}

}


















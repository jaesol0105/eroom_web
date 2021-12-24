package kpu.web.club.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kpu.web.club.domain.EroomVO;
import kpu.web.club.domain.UserVO;
import kpu.web.club.persistence.EroomDAO;
import kpu.web.club.persistence.UserDAO;

/**
 * Servlet implementation class EroomServlet
 */
// @WebServlet("/EroomServlet")
public class EroomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EroomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String cmdReq = "";
		cmdReq = request.getParameter("cmd");
		
		String message ="";
		
		if (cmdReq.equals("mypage")) {
			UserDAO dao = new UserDAO();
			
			HttpSession session = request.getSession(false);
			String user_id = (String)session.getAttribute("cookie");

			// 로그인되있는 유저 정보
			UserVO user = dao.read(user_id);
			request.setAttribute("user", user);
			RequestDispatcher view = request.getRequestDispatcher("my_page.jsp");
			view.forward(request, response);
		}
		
		else if (cmdReq.equals("login_required")) {
			String location = "/eroom_web/login.jsp?login=login_required";			
			response.sendRedirect(location);	
		}
		
		else if (cmdReq.equals("logout")) {
			// 로그아웃. 세션 해제
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			response.sendRedirect("main_page.jsp");	
		}
		
		else if (cmdReq.equals("reserve")) {
			RequestDispatcher view = request.getRequestDispatcher("reservation.jsp");
			view.forward(request, response);
		}
		
		else if (cmdReq.equals("reserve_cancel")) {
			HttpSession session = request.getSession(false);
			String user_id = (String)session.getAttribute("cookie");
			
			EroomDAO dao = new EroomDAO();
			// 유저가 예약중인 좌석이 있는지 확인 (없을경우 0 반환)
			int reservation = dao.read(user_id);
			
			if (reservation == 0) {
				message = "현재 예약 중인 좌석이 없습니다.";
			}
			else {
				message = "현재 예약 중인 좌석은";
			}
			request.setAttribute("msg",message);
			request.setAttribute("rsv",reservation);
			RequestDispatcher view = request.getRequestDispatcher("my_reservation.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		
		String cmdReq = "";
		cmdReq = request.getParameter("cmd");
		
		String message = "";
		
		if ( cmdReq.equals("join")) {
			UserVO userVO = new UserVO();
			
			userVO.setId(request.getParameter("id"));
			userVO.setPasswd(request.getParameter("passwd"));
			userVO.setUsername(request.getParameter("username"));
			userVO.setMobile(request.getParameter("mobile"));
			userVO.setEmail(request.getParameter("email"));
			
			UserDAO userDAO = new UserDAO();
			
			// 회원가입 수행
			if (userDAO.add(userVO)) {
				message = "회원가입 완료.";
				request.setAttribute("register_id", userVO.getId());
			}
			else {
				message = "회원가입 실패.";
				request.setAttribute("register_id", null);
			}

			request.setAttribute("msg", message);
			request.setAttribute("cmd", cmdReq);

			RequestDispatcher view = request.getRequestDispatcher("result.jsp");
			view.forward(request, response);
			}
		
		
		else if ( cmdReq.equals("login")) {
			UserDAO dao = new UserDAO();
			UserVO user = dao.read(request.getParameter("id"));
			
			if (user.getId() == null) {
				message = "fail";
			}
			// 아이디 비밀번호 확인
			else {
				if (request.getParameter("id").equals(user.getId())) {
					if(request.getParameter("passwd").equals(user.getPasswd())) {
						// 세션 할당.
						HttpSession session = request.getSession(true);
						session.setAttribute("cookie", user.getId());
						session.setAttribute("welcome_user", user.getUsername());
						message = "success";
					}
					else
						message = "fail";
				}
			}
			// alert로 로그인 성공/실패 메세지 
			String location = "/eroom_web/main_page.jsp?login="+message;			
			response.sendRedirect(location);	
		}
		
		else if ( cmdReq.equals("update")) {
			UserVO userVO = new UserVO();
			
			userVO.setId(request.getParameter("id"));
			userVO.setPasswd(request.getParameter("passwd"));
			userVO.setUsername(request.getParameter("username"));
			userVO.setMobile(request.getParameter("mobile"));
			userVO.setEmail(request.getParameter("email"));
			
			UserDAO userDAO = new UserDAO();
			
			// 회원정보 수정
			if (userDAO.update(userVO)) message = "회원정보 수정이 완료되었습니다.";
			else message = "회원정보 수정에 실패하였습니다.";

			request.setAttribute("msg", message);
			request.setAttribute("cmd", cmdReq);

			RequestDispatcher view = request.getRequestDispatcher("result.jsp");
			view.forward(request, response);
		}
		
		else if ( cmdReq.equals("delete")) {
			UserVO userVO = new UserVO();
			
			userVO.setId(request.getParameter("id"));
			userVO.setPasswd(request.getParameter("passwd"));
			userVO.setUsername(request.getParameter("username"));
			userVO.setMobile(request.getParameter("mobile"));
			userVO.setEmail(request.getParameter("email"));
			
			UserDAO userDAO = new UserDAO();
			
			String state = "fail";
			
			// 회원 탈퇴 수행
			if (userDAO.delete(userVO)) {
				message = "회원 탈퇴가 완료되었습니다.";
				state = "success";
				// 세션 해제.
				HttpSession session = request.getSession(false);
				if (session != null) {
					session.invalidate();
				}
				// 예약중인 좌석 해제.
				EroomDAO eroomDAO = new EroomDAO();
				eroomDAO.cancel(userVO.getId());
			}
			// 예약중인 좌석이 있을경우 참조 무결성에 의해 delete에 실패한다.
			else
				message = "회원 탈퇴에 실패하였습니다.";

			request.setAttribute("msg", message);
			request.setAttribute("cmd", cmdReq);
			request.setAttribute("state", state);

			RequestDispatcher view = request.getRequestDispatcher("result.jsp");
			view.forward(request, response);
		}
		
		else if (cmdReq.equals("do_reserve")) {
			EroomVO eroomVO = new EroomVO();
			// 좌석 번호
			String seat_no = request.getParameter("seat_no");
			eroomVO.setSeat_no(seat_no);
			// state
			eroomVO.setState("1");
			// 로그인되있는 아이디
			HttpSession session = request.getSession(false);
			String user_id = (String)session.getAttribute("cookie");
			if (user_id == null) {
				message = "좌석 예약에 실패하였습니다.";
				seat_no = null;
				request.setAttribute("msg", message);
				request.setAttribute("cmd", cmdReq);
				request.setAttribute("seat_no", seat_no);
				RequestDispatcher view = request.getRequestDispatcher("result.jsp");
				view.forward(request, response);
			}
			else {
				eroomVO.setId(user_id);
				
				EroomDAO dao = new EroomDAO();
				
				// 유저가 예약중인 좌석이 있는지 확인 (없을경우 0 반환, 있을경우 예약중인 좌석의 번호 반환)
				if (dao.read(user_id) == 0) {
					// 예약 수행
					if (dao.reserve(eroomVO)) {
						message = "좌석 예약이 완료되었습니다.";
					}
					else {
						message = "좌석 예약에 실패하였습니다.";
						seat_no = null;
					}
				}
				else {
					message = "이미 해당 아이디로<br>예약된 좌석이 있습니다.";
					seat_no = "exist";
				}

				request.setAttribute("msg", message);
				request.setAttribute("cmd", cmdReq);
				request.setAttribute("seat_no", seat_no);

				RequestDispatcher view = request.getRequestDispatcher("result.jsp");
				view.forward(request, response);			
			}
		}
		else if (cmdReq.equals("do_reserve_cancel")) {
			EroomDAO dao = new EroomDAO();
			
			HttpSession session = request.getSession(false);
			String user_id = (String)session.getAttribute("cookie");
			if (user_id == null) {
				message = "예약 취소 실패.";
				request.setAttribute("msg", message);
				request.setAttribute("cmd", cmdReq);
				RequestDispatcher view = request.getRequestDispatcher("result.jsp");
				view.forward(request, response);
			}
			
			// 예약 취소 수행
			if (dao.cancel(user_id)) message = "예약 취소가 완료되었습니다.";
			else message = "예약 취소 실패.";

			request.setAttribute("msg", message);
			request.setAttribute("cmd", cmdReq);

			RequestDispatcher view = request.getRequestDispatcher("result.jsp");
			view.forward(request, response);
			
		}
	}

}

package testAjax.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import testAjax.ajax.model.vo.User;

/**
 * Servlet implementation class TestServlet8
 */
@WebServlet("/test8.do")
public class TestServlet8 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet8() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TestServlet5에서 복사해오자
		ArrayList<User> userList = new ArrayList<>();
		
		userList.add(new User(1,"김동원","한국"));
		userList.add(new User(2,"강광산","미국"));
		userList.add(new User(3,"오영은","중국"));
		userList.add(new User(4,"서정완","러시아"));
		userList.add(new User(5,"김민환","아프리카"));
		userList.add(new User(6,"김경남","쿠웨이트"));
		userList.add(new User(7,"박재명","파키스탄"));
		// DB를 통해 회원 데이터를 select해서 userList에 담았다고 가정
		
		String userName = request.getParameter("userName");
		String[] names = userName.split(",");
		
		// 검색이 일치하는 회원을 담을 변수
		User user = null;
		
		// user한명을 담을 JSONObject 객체
		JSONObject userObj = null;
		
		// 클라이언트로 마지막에 감싸서 보낼 맵 형태의 객체
		JSONObject userMap = new JSONObject();
		
		
		for(String name : names) {
			for(int i=0; i<userList.size(); i++) {
				if(userList.get(i).getUserName().equals(name)) {
					user = userList.get(i);
					
					userObj = new JSONObject();
					userObj.put("userNo", user.getUserNo());
					userObj.put("userName", user.getUserName());
					userObj.put("userNation", user.getUserNation());
					
					// json 배열이 아니라 JSON객체를 이용하여
					// key값은 "user뒤에 이름", value는 해당 회원 정보 JSON객체로 담자
					
					
					userMap.put("user"+name, userObj);
					
				}
			}
		}
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(userMap);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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
 * Servlet implementation class TestServlet5
 */
@WebServlet("/test5.do")
public class TestServlet5 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet5() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<User> userList = new ArrayList<>();

		userList.add(new User(1,"김동원","한국"));
		userList.add(new User(2,"강광산","미국"));
		userList.add(new User(3,"오영은","중국"));
		userList.add(new User(4,"서정완","러시아"));
		userList.add(new User(5,"김민환","아프리카"));
		userList.add(new User(6,"김경남","쿠웨이트"));
		userList.add(new User(7,"박재명","파키스탄"));
		// DB를 통해 회원 데이터를 select해서 userList에 담았다고 가정
		
		int userNo = Integer.valueOf(request.getParameter("userNo1"));
		
		User user = null; // 일치하는 회원을 담아서 화면단에 객체
		for(int i=0; i<userList.size(); i++) {
			if(userList.get(i).getUserNo() == userNo) {
				user = userList.get(i);
			}
		}
		

		/* 
		 * JSON(Javascript Object Notation : 자바스크립트 객체 표기법)
		 *  - JSON을 사용하여 모든 데이터형을 서버와 주고 받을 수 있다.
		 *  - '{'로 시작하여'}'로 끝나고 그 속에 데이터를 'key:value' 쌍으로 구성된다.
		 */		
		
		// JSON 사용하려면 라이브러리 다운 받자(mvnrepository에서 다운한다.)
		JSONObject userObj = null; // JSON객체를 초기화.
		
		if(user!=null) { // 회원 검색이 되었을 때
			userObj = new JSONObject();
			
			// key값, value값을 매핑해서 넣는다.
			userObj.put("userNo", user.getUserNo());
			userObj.put("userName", user.getUserName());
			userObj.put("userNation", user.getUserNation());
		}
		
		response.setContentType("application/json; charset=utf-8"); // application/json은 꼭 적어주자!!
		PrintWriter out = response.getWriter();
		out.println(userObj);
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

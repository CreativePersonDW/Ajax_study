package testAjax.ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import testAjax.ajax.model.vo.User;

/**
 * Servlet implementation class TestServlet6
 */
@WebServlet("/test6.do")
public class TestServlet6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet6() {
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
		
		int userNo = Integer.valueOf(request.getParameter("userNo1"));
		
		User user = null;
		for(int i=0 ; i<userList.size() ; i++) {
			if(userList.get(i).getUserNo() == userNo) {
				user = userList.get(i);
			}
		}
		
		JSONObject userObj = null;				// Map과 비슷(key, value) : put -> 화면단에선 속성
		JSONArray userArray = new JSONArray();  // ArrayList와 비슷 : add -> 화면단에선 배열
		
		if(user != null) {
			userObj = new JSONObject();
			
			userObj.put("userNo", user.getUserNo());
			userObj.put("userName", user.getUserName());
			userObj.put("userNation", user.getUserNation());
			
			userArray.add(userObj);
		}else {		// 검색된 회원이 없다면
			for(User userInfo : userList) {
				userObj = new JSONObject();
				
				userObj.put("userNo", userInfo.getUserNo());
				userObj.put("userName", userInfo.getUserName());
				userObj.put("userNation", userInfo.getUserNation());
				
				userArray.add(userObj);
			}
		}
		
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.print(userArray);
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

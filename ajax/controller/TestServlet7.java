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
 * Servlet implementation class TestServlet7
 */
@WebServlet("/test7.do")
public class TestServlet7 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet7() {
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
		
		
		// 문자열이 ','를 기준으로 회원번호를 담고 있음
		String userNo = request.getParameter("userNo");
		String[] userNoArr = userNo.split(",");
		
		JSONArray userArray = new JSONArray();
		
		for(String id : userNoArr) {
			for(int i=0; i<userList.size(); i++) {
				if(Integer.valueOf(id) == userList.get(i).getUserNo()) {
					User user = userList.get(i);
					
					JSONObject userObj = new JSONObject();
					userObj.put("userNo", user.getUserNo());
					userObj.put("userName", user.getUserName());
					userObj.put("userNation", user.getUserNation());
					
					userArray.add(userObj);					
				}
			}
		}
	
	
		// TestServlet6에서는 userArray를 바로 보냈지만,
		// 여기서는 userArray를 JSONObject에 key값과 매칭해서 보내겠다.
		JSONObject result = new JSONObject();
		result.put("list", userArray); // 우리의 JSONArray를 한번 더 JSONObject로 감쌈
		
		response.setContentType("application/json; charset=utf-8");
	
		PrintWriter out = response.getWriter();
		out.print(result);
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

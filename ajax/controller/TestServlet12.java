package testAjax.ajax.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import testAjax.ajax.model.vo.User;

/**
 * Servlet implementation class TestServlet12
 */
@WebServlet("/test12.do")
public class TestServlet12 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet12() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TestServlet5에서 복사해오자
		Map<String, User> userMap = new HashMap<>();

		userMap.put("김동원",new User(1,"김동원","한국"));
		userMap.put("강광산",new User(2,"강광산","미국"));
		userMap.put("오영은",new User(3,"오영은","중국"));
		userMap.put("서정완",new User(4,"서정완","러시아"));
		userMap.put("김민환",new User(5,"김민환","아프리카"));
		userMap.put("김경남",new User(6,"김경남","쿠웨이트"));
		userMap.put("박재명",new User(7,"박재명","파키스탄"));
		// DB를 통해 회원 데이터를 select해서 userList에 담았다고 가정
		
		response.setContentType("application/json; charset=utf-8");
		new Gson().toJson(userMap, response.getWriter());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

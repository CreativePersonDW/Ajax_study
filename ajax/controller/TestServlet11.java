package testAjax.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import testAjax.ajax.model.vo.User;

/**
 * Servlet implementation class TestServlet11
 */
@WebServlet("/test11.do")
public class TestServlet11 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet11() {
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
		
		response.setContentType("application/json; charset=utf-8");
		
		
		/*
		 * Gson : Google JSON을 줄인말
		 * 자바의 List와 Map을 아주 쉽게 json객체로 전송할 수 있다.
		 * 두 객체를 달리 처리하는 것이 아니라, gson객체의 toJson메소드로 쉽게
		 * json객체로 변환
		 * 
		 * toJson메소드의 첫번째 인자는 Object타입이다.
		 * --> 따라서 컬렉션 뿐만 아니라, 어떠한 객체도 처리할 수 있다.
		 * 두번째 인자는 스트림 객체를 쓰는 부분이다.
		 * 
		 * 날짜의 포맷을 처리하기 위해서 GsonBuilder도 제공한다.
		 */
		
		new Gson().toJson(userList,response.getWriter());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

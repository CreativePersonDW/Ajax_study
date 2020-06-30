<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>

	<!-- 
		*Ajax의 주요 속성	
		- url : 데이터를 전송할 URL경로
		- data : 요청 parameter 설정(back단으로 보내는 값)
		- dataType : 서버에서 response로 오는 데이터의 데이터 형 설정
		- error : ajax통신에 실패 했을 때 호출되는 이벤트 핸들러 함수
		- success : ajax 통신에 성공했을 때 호출 될 이벤트 핸들러 함수
		- complete : 요청이 완료 후 실행되는 함수(성공, 실패 상관없이 요청 처리 이후 무조건 실행)
	 -->
	
	<h1 align="center">JQuery를 이용한 Ajax 테스트</h1>
	
		<label>이름 : </label> <input type="text" id="myName">
		<button id="nameBtn">이름 보내기</button>
		<hr>
		
		<script>
			$(function(){
				$("#nameBtn").click(function(){
					var name = $("#myName").val();
					
					$.ajax({
						url:"test1.do", // TestServlet 만들자
						data:{name1:name}, // name = myName의 value값
						type:"get",
						success:function(data){ // 여기 data는 back단에서 String으로 넘어온 값이다. 위에 data:{name1:name}이 아님.
							console.log("성공");
						},
						error:function(data){
							console.log("실패");
							
						},
						complete:function(data){
							console.log("무조건 호출되는 함수");
						}
					}) // {}안에 객체가 매개변수로 들러온다. 
				
				})
			})
		</script>

		<h1>2. 버튼 선택 시 서버에서 보낸 값 사용자가 수신확인</h1>
		<button id="getServerTextBtn">서버에서 보낸 값 확인</button>
		<p id="p1" style="width:300px; height:50px; border:1px solid red;"></p>
		<hr>
		
		<script>
			$(function(){
				$("#getServerTextBtn").click(function(){
					$.ajax({
						url:"test2.do", // TestServlet2 만들자
						type:"get",
						success:function(data){
							// 서버에서 전송한 값은 data에 담긴다.
							// alert(data);
							$("#p1").text(data);
						},
						error:function(data){
							console.log("실패");
						}
					})
				})
			})
		</script>

		<h1>3. 서버로 기본값 전송 하고, 결과로 문자열을 받아 처리하는 방법</h1>
		<h3>두개의 값을 더한 결과를 받아오자</h3>
		<label>첫번째 숫자 : </label><input type="text" id="firstNum"><br>
		<label>두번째 숫자 : </label><input type="text" id="secondNum"><br>
		<button id="plusBtn">결과 확인</button>
		<p id="p2" style="width:300px; height:50px; border:1px solid red;"></p>
		<hr>
		<script>
		$(function(){
			$("#plusBtn").click(function(){
				var firstNum = $("#firstNum").val();
				var secondNum = $("#secondNum").val();
			
				$.ajax({
					url:"test3.do", // TestServlet3 만들자.
					type:"get",
					data:{firstNum1:firstNum,secondNum1:secondNum},
					success:function(data){
						$("#p2").text(data);
					},
					error:function(data){
						alert("빈칸없이 숫자를 입력해주세요.");
					}
				})
			})
		})
		</script>
		
		<h1>4. 서버로 Object 형태의 데이터 전송, 서버에서 처리 후 서버 console 출력</h1>
		<label>학생 1 : </label><input type="text" id="student1"><br>
		<label>학생 2 : </label><input type="text" id="student2"><br>
		<label>학생 3 : </label><input type="text" id="student3"><br>
		<button id="studentTest">4번 결과 확인</button>
		<hr>
		
		<script>
		$(function(){
			$("#studentTest").click(function(){
				var student1 = $("#student1").val();
				var student2 = $("#student2").val();
				var student3 = $("#student3").val();
				
				var students = {student11:student1,
								student22:student2,
								student33:student3};
				
				$.ajax({
					url:"test4.do", // TestServlet4 만들자.
					type:"get",
					data:students,
					success:function(data){
						console.log(data);
					},
					error:function(data){
						alert("실패");
					}
				
					
				})
				
			})
		})
		</script>
		
		
		<h1>5. 서버로 기본형 데이터 전송, 서버에서 객체 반환</h1>
		<h3>유저 번호 보내서 해당 유저 정보 가져오기</h3>
		유저 정보(번호) : <input type="text" id="userNo"><br>
		<button id="getUserInfoBtn">정보 가져오기</button>
		<textarea rows="5" cols="40" style="border:1px solid red;" id="p3"></textarea>
		<hr>
		
		<script>
		$(function(){
			$("#getUserInfoBtn").click(function(){
				var userNo = $("#userNo").val();
				
				$.ajax({
					url:"test5.do", // TestServlet5 만들기.
					type:"get",
					data:{userNo1:userNo},
					success:function(data){
						var result = "";
						
						if(data!=null){
							result = data.userNo + "," + data.userName + "," + data.userNation;
						}
						$("#p3").val(result);
					},
					error:function(data){
						console.log("실패");
					}
				})
				
				
			})
		})
		</script>
		
		<h1>6. 서버로 기본값 전송, 서버에서 리스트 객체 반환</h1>
		<h3>유저 번호 요청 --> 해당 유저가 있는 경우 유저 정보, 없는 경우 전체 가져오기</h3>		
		유저정보(번호) : <input type="text" id="userNo2"><br>
		<button id="getUserInfoBtn2">정보 가져오기</button>
		<textarea rows="5" cols="40" style="border:1px solid red;" id="p4"></textarea>
		<hr>
		
		<script>
				$(function(){
					$("#getUserInfoBtn2").click(function(){
						var userNo = $("#userNo2").val();
						
						$.ajax({
							url:"test6.do",
							type:"get",
							data:{userNo1:userNo},
							success:function(data){
								//console.log(data[0].userName);	// 김동원
								var result = "";
								for(var i in data){
									var user = data[i];
									
									result += user.userNo + ","
											+ user.userName + ","
											+ user.userNation + "\n";
								}
								$("#p4").val(result);
							},
							error:function(data){
								console.log("실패");
							}
						})
					})
				})
			</script>
		
		
			
</body>
</html>
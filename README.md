# Ajax_study

```
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
```



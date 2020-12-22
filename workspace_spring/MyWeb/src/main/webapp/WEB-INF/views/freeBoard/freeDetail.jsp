﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


    <section>
        <div class="container">
            <div class="row">
                <div class="col-xs-12 col-md-9 write-wrap">
                        <div class="titlebox">
                            <p>상세보기</p>
                        </div>
                        
                        <form action="#">
                            <div>
                                <label>DATE</label>
                                <p><fmt:formatDate value="${vo.regdate }" pattern="yyyy-MM-dd"/> </p>
                            </div>   
                            <div class="form-group">
                                <label>번호</label>
                                <input class="form-control" name='bno' value="${vo.bno }" readonly>
                            </div>
                            <div class="form-group">
                                <label>작성자</label>
                                <input class="form-control" name='writer' value="${vo.writer }" readonly>
                            </div>    
                            <div class="form-group">
                                <label>제목</label>
                                <input class="form-control" name='title' value="${vo.title }" readonly>
                            </div>

                            <div class="form-group">
                                <label>내용</label>
                                <textarea class="form-control" rows="10" name='content' readonly>${vo.content }</textarea>
                            </div>

                            <button type="button" class="btn btn-primary" onclick="location.href='freeModify?bno=${vo.bno }'">변경</button>
                            <button type="button" class="btn btn-dark" onclick="location.href='freeList'">목록</button>
                    </form>
                </div>
            </div>
        </div>
        </section>
        
        <section style="margin-top: 80px;">
            <div class="container">
                <div class="row">
                    <div class="col-xs-12 col-md-9 write-wrap">
                        <form class="reply-wrap">
                            <div class="reply-image">
                                <img src="../resources/img/profile.png">
                            </div>
                            <!--form-control은 부트스트랩의 클래스입니다-->
	                    <div class="reply-content">
	                        <textarea class="form-control" rows="3" id="reply"></textarea>
	                        <div class="reply-group">
	                              <div class="reply-input">
	                              <input type="text" class="form-control" id="replyId" placeholder="이름" >
	                              <input type="password" class="form-control" id="replyPw" placeholder="비밀번호">
	                              </div>
	                              
	                              <button type="button" class="right btn btn-info" id="replyRegist">등록하기</button>
	                        </div>
	
	                    </div>
                        </form>

                        <!--여기에접근 반복-->
                        <div id="replyList">
                        <!-- 
                        <div class='reply-wrap'>
                            <div class='reply-image'>
                                <img src='../resources/img/profile.png'>
                            </div>
                            <div class='reply-content'>
                                <div class='reply-group'>
                                    <strong class='left'>honggildong</strong> 
                                    <small class='left'>2019/12/10</small>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-pencil'></span>수정</a>
                                    <a href='#' class='right'><span class='glyphicon glyphicon-remove'></span>삭제</a>
                                </div>
                                <p class='clearfix'>여기는 댓글영역</p>
                            </div>
                        </div> 
                        -->
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        
        
        
        
        
        
	<!-- 
	모달
	선택자.modal("show"); //open
	선택자.modal("hide"); //hide 
	-->
	<div class="modal fade" id="replyModal" role="dialog">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn btn-default pull-right" data-dismiss="modal">닫기</button>
					<h4 class="modal-title">댓글수정</h4>
				</div>
				<div class="modal-body">
					<!-- 수정폼 id값을 확인하세요-->
					<div class="reply-content">
					<textarea class="form-control" rows="4" id="modalReply" placeholder="내용입력"></textarea>
					<div class="reply-group">
						<div class="reply-input">
						    <input type="hidden" id="modalRno">
							<input type="password" class="form-control" placeholder="비밀번호" id="modalPw">
						</div>
						<button class="right btn btn-info" id="modalModBtn">수정하기</button>
						<button class="right btn btn-info" id="modalDelBtn">삭제하기</button>
					</div>
					</div>
					<!-- 수정폼끝 -->
				</div>
			</div>
		</div>
	</div>
	

	<script>
		/* 
		$("#replyModal").modal("show"); //모달창 보이게
		$("#modalDelBtn").click(function() {
			$("#replyModal").modal("hide");
		}) 
		*/
	
		//1. 제이쿼리 라이브러리 확인
		//2. 로딩이 끝난 직후 ready함수 안에 작성
		$(document).ready(function(){
			
			//댓글등록 이벤트
			$("#replyRegist").click(add);
			
			//등록함수
			function add() {
				var bno = "${vo.bno}"; //문자열의 형태로 화면에서 넘어온 bno번호
				var reply = $("#reply").val(); //내용
				var replyId = $("#replyId").val();//아이디	
				var replyPw = $("#replyPw").val();//비밀번호
				
				/* console.log(bno, reply, replyId, replyPw); */
				
				if(reply === ''|| replyId ===''|| replyPw === ''){
					alert("이름, 비밀번호, 내용을 입력하세요");
					return; //함수종료
				}
				
				$.ajax({
					type: "POST", //요청방식
					url: "../reply/replyRegist", //요청주소
					data: JSON.stringify({"bno":bno, "reply":reply, "replyId":replyId, "replyPw":replyPw }),
					contentType: "application/json; charset=utf-8",
					success: function (data) {
						
						//console.log("성공여부 : " + data)
						if(data == 1){//등록성공
							$("#reply").val("");
							$("#replyId").val("");
							$("#replyPw").val("");
							alert("댓글 등록에 성공했습니다");
							getList(); //목록요청 호출
						}else{
							alert("댓글 등록에 실패했습니다. 잠시후에 다시 시도하세요");
							
						}
					},
					error: function (arror) {//에러발생시 실행시킬 콜백함수
						alert(error, "등록실패입니다. 관리자에게 문의하세요");
					} 
				})
				
			} //end regist
			
			//페이지넘버선언
			var pageNum = 1;
			
			//목록요청
			getList(1); //상세화면 진입시에 리스트 목록을 가져옵니다.
			function getList(pageNum) {
				
				//select구문에서 필요한 값은 ? bno
				var bno = "${vo.bno}";
				
				//$.ajax()  vs $.getJSON()
				//$.ajax() -- get, post, put, delete 공용적으로 처리하는 제이쿼리 기능
				//$.getJSON(요청주소, 콜백함수) -- 단순히 get방식의 데이터만 얻어올 때 사용하는 기능
				$.getJSON(
					"../reply/getList/" + bno + "/" + pageNum,
					function(data) {
						
						if(data.length <= 0){ //댓글이 없는 경우 함수종료
							return; //함수종료
						}
						
						var strAdd = ""; //화면에 그려넣을 태그를 문자열의 형태로 추가
						for(var i = 0; i < data.length; i ++){
							strAdd += "<div class='reply-wrap'>"
							strAdd += "<div class='reply-image'>"
							strAdd += "<img src='../resources/img/profile.png'>"
							strAdd += "</div>"
							
							strAdd += "<div class='reply-content'>"
							strAdd += "<div class='reply-group'>"
							strAdd += "<strong class='left'>" + data[i].replyId + "</strong>" 
							strAdd += "<small class='left'>" + timeStamp(data[i].replydate) + "</small>"
							strAdd += "<a href='"+ data[i].rno +"' class='right replyModify'><span class='glyphicon glyphicon-pencil'></span>수정</a>"
							strAdd += "<a href='"+ data[i].rno +"' class='right replyDelete'><span class='glyphicon glyphicon-remove'></span>삭제</a>"
							strAdd += "</div>"
							strAdd += "<p class='clearfix'>"+ data[i].reply +"</p>"
							strAdd += "</div>"
							strAdd += "</div>"
						}
						
						$("#replyList").html(strAdd); //repltList의 자식으로 추가
					}
				);
					
			}//end getList
			
			//수정삭제 모달창 핸들러
			/* 
			에이젝스가 순서를 보장하지 않기 때문에, 실제 이벤트 선언이 먼저 실행이 됩니다.
			그렇다면 화면에 댓글관련 창은 아무것도 없는 형태이므로, 일반클릭이벤트는 동작하지 않습니다.
			이 때, 이미 존재하는 태그 replyList(부모)에 이벤트를 등록하고 이벤트를 전파시켜서 사용하는 위임방식을 반드시 써야합니다.
			*/
			$("#replyList").on("click", "a", function(){
				event.preventDefault();//고유이벤트 중단
				
				//1. 수정버튼 vs 삭제버튼
				//현재클릭한 a태그 href안에 있는 rno번호를 -> 모달창의 hidden태그로 옮겨보세요.
				//제이쿼리 hasClass() 함수를 이용해서 처리..
				
				//제이쿼리 this가 JS의 event.target과 유사합니다.
				//var rno = $(this).attr("href")
				//$("#modalRno").val(rno)
				
				$("#modalRno").val($(this).attr("href"));

				if($(this).hasClass('replyModify')){ //event.target.classList.contains("replyModify")
					//수정을 눌렀을 때 수정창 형식으로 변경
					$(".modal-title").html("댓글수정");
					$("#modalReply").css("display", "inline"); //textarea보임
					$("#modalModBtn").css("display", "inline"); //수정버튼 보임
					$("#modalDelBtn").css("display", "none");//삭제버튼 숨김 
					
					
					$("#replyModal").modal("show");
				
				}else{
					//삭제를 눌렀을 때 삭제창 형식으로 변경
					$(".modal-title").html("댓글삭제");
					$("#modalReply").css("display", "none"); //textarea숨김
					$("#modalModBtn").css("display", "none"); //수정버튼 숨김
					$("#modalDelBtn").css("display", "inline");//삭제버튼 보임 
					
					$("#replyModal").modal("show");
					
				}
			});
			
			
			
			//수정이벤트
			$("#modalModBtn").click(function(){
				/* 
				1. 모달창에 rno, reply, replyPw 값을 얻습니다
				2. ajax함수를 이용해서 POST방식으로 reply/update 요총. 필요한값은 JSON형식으로 처리
				3. 서버에서는 요청을 받아서 비밀번호를 확인하고, 비밀번호가 맞다면 업데이트를 진행하면 됩니다.
				4. 만약 비밀번호가 틀렸다면 0을 반환해서 '비밀번호가 틀렸습니다.' 경고창을 띄우세요
				5. 업데이트가 성공적으로 진행됬다면 modal창 값을 공백으로 초기화 시키세요
				*/
				var rno = $("#modalRno").val()
				var reply = $("#modalReply").val()
				var modalPw = $("#modalPw").val()
				
				if(rno === "" || reply === "" || modalPw === ""){
					alert("내용, 비밀번호를 입력해주세요");
					return; //종료
				}
				

				$.ajax({
					type : "POST",
					url : "../reply/update",
					data: JSON.stringify({"rno":rno, "reply":reply, "replyPw":modalPw }),
					contentType: "application/json; charset=utf-8",
					success: function (data) {
						
						
						//console.log("성공여부 : " + data)
						if(data == 1){//업데이트 성공
							alert("댓글 수정에 성공했습니다");
							$("#modalReply").val(""); //수정창 비움
							$("#modalPw").val(""); //비밀번호 창 비움
							$("#replyModal").modal("hide"); //모달창내리기
							getList(); //목록요청 호출
						}else{
							$("#modalPw").val(""); //비밀번호 창 비움
							alert("비밀번호가 틀렸습니다");
							
						}
					},
					error: function (arror) {//에러발생시 실행시킬 콜백함수
						alert(error, "등록실패입니다. 관리자에게 문의하세요");
					} 
				})
				
				
				
			});//end update
			
			//삭제이벤트
			$("#modalDelBtn").click(function(){
				/* 
				1. 모달창에 rno, replyPw값을 얻습니다
				2. ajax함수를 이용해서 POST방식으로 reply/delete 요청, 필요한 값은 JSON형식으로 처리
				3. 서버에서 요청을 받아서 비밀번호를 확인하고, 비밀번호가 일치한다면 삭제를 진행하면됩니다.
				4. 비밀번호가 틀렸다면, 0을 반환해서 "경고창을 띄우면 됩니다."
				*/
				
				var rno = $("#modalRno").val();
				var replyPw = $("#modalPw").val();
				
				if(replyPw === ""){
					alert("비밀번호를 입력해주세요");
					return; //종료
				}
				
				$.ajax({
					type : "POST",
					url : "../reply/delete",
					data : JSON.stringify({"rno":rno, "replyPw":replyPw}),
					contentType : "application/json; charset=utf-8",
					success : function(data){
						if(data === 1){//업데이트 성공
							alert("댓글 삭제가 성공했습니다");
							$("#modalPw").val("");
							$("#replyModal").modal("hide"); //모달창내리기
							getList(); //목록요청 호출
							
						}else if(data === 0){
							alert("삭제시 에러가 발생했습니다. 관리자에게 문의하세요");
						}else if(data === -1){
							$("#modalPw").val(""); //비밀번호 창 비움
							alert("비밀번호가 틀렸습니다");
							
						}
					},
					error : function(error){
						alert(error, "등록실패입니다. 관리자에게 문의하세요");
					}
					
				})
			});
			
			
			
			
			
			
			
			
			//javascript에서 밀리초를 전달받는 함수
			function timeStamp(millis) {
				//1기준 분으로 방금전 or xx시간
				//1일 기준으로 날짜출력
				var date = new Date(); //오늘날짜
				var gap = date.getTime() - millis;
				var str = "";

				if(gap < 1000 * 60 * 60 * 24){ //1일 이하인 경우
		            if(gap < 1000 * 60 * 60){ //1시간 이하인 경우
		                str = "방금 전";
		            }else { //1시간 이상인 경우
		            	str = parseInt( gap / (1000 * 60 * 60) ) + "시간 전"; //xx시간 전
		            }

		        }else{ //1일 이상인 경우

		            var now = new Date(millis); //밀리초를 통한 날짜
		            var year = now.getFullYear(); //해당 년
		            var month = now.getMonth() + 1; //해당 월
		            var day = now.getDate(); //해당 일
		            var hour = now.getHours(); // 해당시간
		            var miniute = now.getMinutes(); //해당 분
		            var second = now.getSeconds(); //해당 초
		            
		            var time = (hour < 10 ? "0" + hour : hour) + ":" + 
                    (miniute < 10 ? "0" + miniute : miniute) + ":" +
                    (second < 10 ? "0" + second : second);

		            str = year + "-" + month + "-" + day + " " + time;

		        }
				return str;
			}
			
			
			
		});
	</script>

	

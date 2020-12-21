package com.team404.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.FreeBoardVO;

@RestController //@RequestMapping + @ResponseBody이 합쳐진 형태의 어노테이션
public class RestBasicController {
	
	//1. RestController는 기본적으로 return값이 리졸버 뷰로 전달되는게 아니라, 요청된 주소로 반환됩니다 --- 비동기식
	//produces는 타입을 지정해주면, 해당 타입으로 사용자에게 응답하겠다는 뜻입니다.
	
	//@GetMapping = @RequestMapping(method = RequestMethod.GET) 와 동일
	//소비자가 요청한 값에 대한 응답데이터의 반환(return) 타입선언을 produces로 지정해줍니다. --- client측 Response Headers에서 확인가능 !
	//produces의 디폴트 : JSON
	@GetMapping(value = "/getText", produces = "text/plain; charset=utf-8") 
	public String getText() {
		
		System.out.println("REST API실행됨");
		
		//REST API확인 -- 구글 확장프로그램(Yet Another REST Client) 필요 !
		//Yet Another REST Client --- 임시 클라이언트 , 내컴퓨터 --- 서버
		return "안녕하세요";
	}
	
	//객체반환(produces는 객체를 보낼 때 기본형으로 json형식을 가집니다.)
	//jackson-databind라이브러리를 통해 객체를 json형태로 변환 --- 반드시필요 !
	@GetMapping(value = "/getObject", produces = "application/json;charset=UTF-8")
	public FreeBoardVO getObject() {
		
		return new FreeBoardVO(10, "헬로", "테스트", "테스트", null, null);
	}
	
	//값을 받고 객체형태로 반환
	//get방식의 requestParam으로 필수값을 지정할 수 있습니다.
	//http://localhost:8282/myweb/getCollection?key=블라블라&bno=1
	@GetMapping("/getCollection")
	public ArrayList<FreeBoardVO> getCollection(@RequestParam("key") String key,
												@RequestParam("bno") int bno) {
		
		System.out.println("브라우저에서 넘어온 값 : " + key);
		System.out.println("브라우저에서 넘어온 값 : " + bno);
		
		ArrayList<FreeBoardVO> list = new ArrayList<>();
		
		for(int i = 1; i <= 10; i++) {
			FreeBoardVO vo = new FreeBoardVO();
			vo.setBno(i);
			vo.setTitle("테스트" + i);
			vo.setContent("테스트" + i);
			
			list.add(vo);
		}
		
		return list;
	}

	// 값/값/값 의 url형태로 받고 Map을 반환 --- 반환은 배열, 리스트도 상관없음 !
	//@PathVariable : url주소에 첨부된 값을 추출 --- 동기, 비동기 가능
	//http://localhost:8282/myweb/getPath/값/값/값
	@GetMapping("/getPath/{sort}/{rank}/{page}")
	public HashMap<String, Object> getPath(@PathVariable("sort") String sort,
										   @PathVariable("rank") String rank,
										   @PathVariable("page") String page){
		System.out.println(sort);
		System.out.println(rank);
		System.out.println(page);
		
		//처리...
		HashMap<String, Object> map = new HashMap<>();
		FreeBoardVO vo = new FreeBoardVO(1, "테스트", "테스트", "테스트", null, null);
		map.put("info", vo);
		
		return map;
	}
	
	
	/*
	 - POST형식
	 1. content-type에 넘어가는 응답형태(json...) 필수기재
	 2. @RequestBody를 통해 요청 측 Body안의  값을 연결
	 3. Accept : 클라이언트가 서버에 데이터를 보냈을 때 이에 대한 응답을 받을 때 받을 수 있는 형식(ex - 클라이언트가 서버쪽에 나는 json만 해석할 수 있으니까 json형태로 반환해줘)
	 4. Content-Type : 서버-클라이언트(클라이언트-서버)에 보내는 데이터의 형식
	 
	 --------------------------------------------------------------
	"Accept": "application/json, text/plain, ＊/＊"   
	 결과를 받을 때 해당 타입으로 받겠다고 서버에서 판단(미리 정해진 타입이 있을 시 무시?)
	 
	 "Content-Type": "application/json;charset=utf-8"
	 결과를 해당 타입으로 보냅니다~
	 
	 */
	
	//@PostMapping = @RequestMapping(method = RequestMethod.POST) 와 동일
	//POST형식의 JSON형식으로 값을받음, 객체로 반환
	//1. 화면에서 JSON형식으로 넘어오는 데이터를 @RequestBody어노테이션으로 맵핑
	//2. 화면에서는 데이터를 보낼때 Content-Type을 선언해서 데이터의 유형을 알려줘야합니다
	
	//크로스도메인 정책 - 서버가 다른경우, 스프링은 기본적으로 요청을 받아주지 않는데, 이러한 요청을 허용시키는 옵션입니다.
	//@CrossOrigin(origins = "*") //* --- 허용어떤서버든 허용(CROS정책 무시)
	@CrossOrigin(origins = "http://127.0.0.1:5502") //origins안의 서버만 허용
	@PostMapping("/getJson")
	public ArrayList<FreeBoardVO> jetJson(@RequestBody FreeBoardVO vo) {
		System.out.println(vo.toString());
		
		//처리...
		ArrayList<FreeBoardVO> list = new ArrayList<>();
		FreeBoardVO fvo = new FreeBoardVO();
		fvo.setTitle("결과입니다");
		list.add(fvo);
		
		return list;
	}
	
	
	
}

package com.study.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.prod.vo.ProdVO;

public class ProductList {
	private static Map<String, ProdVO> productMap = new HashMap<>();
	static{
		ProdVO vo = new ProdVO("CPU001", "AMD 라이젠 5 3500 (마티스)", "CPU", 140200, "", "/image/prod/CPU001.jpg" , "2019.11.01");
		vo.setProdDetail("완전히 새로워진 3세대 라이젠 프로세서<br>전 세계의 엘리트 게이머를 위한,<br>6개의 코어가 탑재된 세상에서 가장 발전된 프로세서");
		System.out.println(vo.hashCode());
		productMap.put("CPU001", vo );
		
		vo = new ProdVO("CPU002", "인텔 코어i7-9세대 9700K", "CPU", 420000, "", "/image/prod/CPU002.jpg" , "2018.10.01");
		vo.setProdDetail("STIM으로 극대화된 오버클러킹 성능<br> 인텔 300 시리즈 칩셋호환 <br>인텔 터보 부스터 기술 2.0 <br>"
				        + "다양한 플랫폼 통합지원<br> 향상된 미디어&디스플레이 기능<br> 인텔 스마트 사운드 기술<br> 차세대 인텔 옵테인 메모리 지원  ");
		System.out.println(vo.hashCode());
		productMap.put("CPU002", vo );
		
		vo = new ProdVO("CPU003", "A인텔 코어i5-9세대 9600KF", "CPU", 203000, "", "/image/prod/CPU003.jpg" , "2019.03.01");
		vo.setProdDetail("진정한 멀티코어로<br>쾌적한 게임과 스트리밍 진행<br>9세대 인텔코어 i5-9600KF 프로세서는 6코어 6스레드로 효과적인 작업이 가능합니다.");
		System.out.println(vo.hashCode());
		productMap.put("CPU003",  vo);
		
		productMap.put("MEM001", new ProdVO("MEM001", "삼성전자 DDR4 8G PC4-21300", "MEM", 40000, 
				                            "최신 인텔, AMD 프로세서 호환<br>쾌적한 영상이미지 소프트웨어 사용환경<br> 향상된 성능과 안전성"
				                           , "/image/prod/MEM001.jpg" , "2018.03.01") );
		productMap.put("MEM002", new ProdVO("MEM002", "GeIL DDR4 8G PC4-21300 CL19 PRISTINE", "MEM", 34500, 
				                            "깔끔하고 단정한 이미지와 고급스럽고 세련된 느낌의 블랙 PCB<br>극한의 환경에서 진행되는 품질테스트를 통과한 DDR4 메모리입니다."
				                           , "/image/prod/MEM002.jpg" , "2018.05.01") );
		productMap.put("MEM003", new ProdVO("MEM003", "ESSENCORE KLEVV DDR4 8G PC4-21300 CL19", "MEM", 35000, 
				                             "에센코어는 반도체 메모리 분야의 축적된 노하루를 바탕으로<br>고객의 비즈니스를 위한 최적의 솔루션을 제공하고자 합니다."
				                             , "/image/prod/MEM003.jpg" , "2019.08.01") );
	}
	
	public static List<ProdVO> getProductList() {
		return  new ArrayList<ProdVO>(productMap.values());
	}
	
	public static ProdVO getProduct(String prodId) {
		if(productMap.containsKey(prodId)) {
			return productMap.get(prodId);
		}else {
			return null;
		}
	}
	
}




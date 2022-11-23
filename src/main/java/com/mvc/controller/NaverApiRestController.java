package com.mvc.controller;

import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin("*")
public class NaverApiRestController {
	
	@GetMapping("/naver")
	public String naver() {
		String query = "서울 아파트";
		ByteBuffer buffer = StandardCharsets.UTF_8.encode(query);
		String encode = StandardCharsets.UTF_8.decode(buffer).toString();
		
		URI uri = UriComponentsBuilder
				.fromUriString("https://openapi.naver.com")
				.path("/v1/search/news.json")
				.queryParam("query", encode)
				.queryParam("diplay", 10)
				.queryParam("start", 1)
				.queryParam("sort", "date")
				.encode()
				.build()
				.toUri();
		
		RestTemplate restTemplate = new RestTemplate();
		
		//헤더를 넣기 위함
		RequestEntity<Void> req = RequestEntity
				.get(uri)
				.header("X-Naver-Client-Id", "OMHo5rQ7hEnYd1L2fuNT")
				.header("X-Naver-Client-Secret", "Tz66dqeDBa")
				.build();
		
		
		
		ResponseEntity<String> result = restTemplate.exchange(req, String.class);
	
		return result.getBody();
	}
	
}

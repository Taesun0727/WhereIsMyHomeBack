package com.mvc.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mvc.mapper.UserMapper;
import com.mvc.vo.User;

@Service
public class kakaologinServiceImpl implements kakaologinService {
	
	@Autowired
	private UserMapper mapper;
	
    @Override
    public String getAccessToken(String auth_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            String sb = "grant_type=authorization_code" +
                    "&client_id=9903ba916813929e1cf77e63b54c47c2" +
                    "&redirect_uri=http://localhost/kakaologin" +
                    "&code=" + auth_code +
                    "&client_secret=MMhTMZ2sTL4aqWp7etQk36paOvVzu4i2";
            bw.write(sb);
            bw.flush();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }
    
    @Override
    public void getUserInfo(String access_Token) {

        User userInfo = new User();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
            
            //String id = element.getAsJsonObject().get("id").getAsString();
            String email = null;
            
            //추가
            JsonObject profile = kakao_account.getAsJsonObject().get("profile").getAsJsonObject();
            String nickname = profile.get("nickname").getAsString();
            
            if (kakao_account.getAsJsonObject().get("email") != null) {
                email = kakao_account.getAsJsonObject().get("email").getAsString();
                
                //추가
//                userInfo.put("nickname", nickname);
//                userInfo.put("access_Token", access_Token);
//                userInfo.put("email", email);
                userInfo.setUserinfo_id(email);
                userInfo.setUserinfo_nick(nickname);
                userInfo.setUserinfo_token(access_Token);
            }
            
//            System.out.println(userInfo.get(2));

        } catch (IOException e) {
            e.printStackTrace();
        }
        

       mapper.KakaoUserInfo(userInfo);
    }
}

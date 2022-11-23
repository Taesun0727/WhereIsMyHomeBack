package com.mvc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class kakaologinServiceImpl implements kakaologinService {

	@Override
	public HashMap<String, String> getUserInfo(String access_Token) {
		HashMap<String, String> userInfo = new HashMap<>();
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

            String id = element.getAsJsonObject().get("id").getAsString();
//          String access_token = element.getAsJsonObject().get("access-token").getAsString();
            String email = null;
            String nickname = null;
            if (kakao_account.getAsJsonObject().get("email") != null) {
                email = kakao_account.getAsJsonObject().get("email").getAsString();
                nickname = element.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").toString().replace("\"", "");
                userInfo.put("id", id);
//                userInfo.put("access-token", access_token);
                userInfo.put("email", email);
                userInfo.put("nickname", nickname);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userInfo;
	}

}

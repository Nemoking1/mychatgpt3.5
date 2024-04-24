package com.qyt.chatgpt;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class OpenAIAPI {
    /**
     * 聊天端点
     */
    String chatEndpoint = "https://gateway.ai.cloudflare.com/v1/d4777cc628d2c868dea7325104836c9f/qyt/openai/chat/completions";
    /**
     * api密匙
     */
    String apiKey = "Bearer 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";//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";

    /**
     * 发送消息
     *
     * @param txt 内容
     * @return {@link String}
     */
    public static String chat(String txt) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>(){{
            put("role", "user");
            put("content", txt);
        }});
        paramMap.put("messages", dataList);
        JSONObject message = null;
        try {
            String body = HttpRequest.post(chatEndpoint)
                    .header("Authorization", apiKey)
                    .header("Content-Type", "application/json")
                    .body(JSONUtil.toJsonStr(paramMap))
                    .execute()
                    .body();

            JSONObject jsonObject = JSONUtil.parseObj(fixIncompleteEntity(body));
            JSONArray choices = jsonObject.getJSONArray("choices");
            JSONObject result = choices.get(0, JSONObject.class, Boolean.TRUE);
            message = result.getJSONObject("message");
            return message.getStr("content");
        } catch (Exception e) {
            e.printStackTrace();
            return "出现了异常";
        }
    }
    private String fixIncompleteEntity(String xmlData) {
        // 找到所有不完整的实体引用符号 "&"
        Pattern pattern = Pattern.compile("&(?![a-zA-Z]+;)");
        Matcher matcher = pattern.matcher(xmlData);

        // 逐个修复不完整的实体引用符号 "&"
        StringBuffer fixedXml = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(fixedXml, "&amp;");
        }
        matcher.appendTail(fixedXml);

        return fixedXml.toString();
    }
}


package com.jl.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.properties.QiniuProperties;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ytx.org.apache.http.HttpResponse;
import ytx.org.apache.http.client.HttpClient;
import ytx.org.apache.http.client.methods.HttpPost;
import ytx.org.apache.http.entity.StringEntity;
import ytx.org.apache.http.impl.client.DefaultHttpClient;
import ytx.org.apache.http.params.CoreConnectionPNames;
import ytx.org.apache.http.util.EntityUtils;
import java.util.Map;

/**
 * Created by fannairu on 2016/8/7.
 */
@Component
public class Qiniu {
    @Autowired
    private QiniuProperties qiniuProperties;
    private Logger logger = Logger.getLogger(Qiniu.class);

    public String upload(byte[] content) throws QiniuException {
        Auth auth = Auth.create(qiniuProperties.getAccesskey(), qiniuProperties.getSecretkey());
        String key = System.currentTimeMillis() + ".png";
        UploadManager uploadManager = new UploadManager();
        uploadManager.put(content, key, auth.uploadToken(qiniuProperties.getBucket()));
        return qiniuProperties.getCdn() + key;
    }

    public String base64Upload(String content) throws Exception {
        String url = "http://upload.qiniu.com/putb64/-1";
        Auth auth = Auth.create(qiniuProperties.getAccesskey(), qiniuProperties.getSecretkey());
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-Type", "application/octet-stream");
        post.addHeader("Authorization", "UpToken " + auth.uploadToken(qiniuProperties.getBucket()));
        post.setEntity(new StringEntity(formatBase64(content)));
        HttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, new Integer(3000));
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, new Integer(20000));
        HttpResponse res = client.execute(post);
        String responseBody = EntityUtils.toString(res.getEntity(), "UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        Map reMap = objectMapper.readValue(responseBody, Map.class);
        if (reMap.get("key") == null) {
            logger.error(responseBody);
            throw new Exception(responseBody);
        }
        return qiniuProperties.getCdn() + reMap.get("key");
    }

    private String formatBase64(String content) {
        if (content.contains("base64")) {
            int index = content.indexOf(',');
            if (index > 0) {
                return content.substring(index + 1);
            }
        }
        return content;
    }
}

package com.jl.controller;

import com.jl.utils.Qiniu;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fannairu on 2016/8/7.
 */
@Component
@Path("image")
@Produces("application/json")
public class ImageController {
    @Autowired
    private Qiniu qiniu;

    @POST
    @Path("upload")
    public Map upload(ImageBean imageBean) throws Exception {
        Map reMap = new HashMap();
        reMap.put("image", qiniu.base64Upload(imageBean.getImage()));
        return reMap;
    }
}

class ImageBean {
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
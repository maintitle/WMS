package com.tz.warehouse.sys.component;

import com.obs.services.ObsClient;
import com.obs.services.model.PostSignatureRequest;
import com.obs.services.model.PostSignatureResponse;
import com.tz.warehouse.sys.common.utils.R;
import com.tz.warehouse.sys.dto.ObsPolicyResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by TangZhen on 2022/10/7
 */
@Component
public class ObsUtil {


    @Value("${huawei.obs.policy.expire}")
    private int HUAWEI_OBS_EXPIRE;
    @Value("${huawei.obs.maxSize}")
    private int HUAWEI_OBS_MAX_SIZE;
    @Value("${huawei.obs.callback}")
    private String HUAWEI_OBS_CALLBACK;
    @Value("${huawei.obs.bucketName}")
    private String HUAWEI_OBS_BUCKET_NAME;
    @Value("${huawei.obs.endpoint}")
    private String HUAWEI_OBS_ENDPOINT;
    @Value("${huawei.obs.dir.prefix}")
    private String HUAWEI_OBS_DIR_PREFIX;
    @Value("${huawei.obs.sk}")
    private String HUAWEI_OBS_SK;
    @Value("${huawei.obs.ak}")
    private String HUAWEI_OBS_AK;

    public ObsPolicyResult policy(Long id) {
        ObsPolicyResult result = new ObsPolicyResult();
        final ObsClient obsClient = new ObsClient(HUAWEI_OBS_AK, HUAWEI_OBS_SK, HUAWEI_OBS_ENDPOINT);
        String action = "https://" + HUAWEI_OBS_BUCKET_NAME + "." + HUAWEI_OBS_ENDPOINT;
        // 存储目录
        String dir = "";
        if (id != null) {
            dir = HUAWEI_OBS_DIR_PREFIX + "headportrait/" + id + "/";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            dir = HUAWEI_OBS_DIR_PREFIX + sdf.format(new Date()) + "/";
        }
        PostSignatureRequest request = new PostSignatureRequest();
        // 设置表单参数
        ArrayList<String> conditions = new ArrayList<>();
        //设置接收表内容为image开头
        conditions.add("[\"starts-with\",\"$content-type\",\"image/\"]");
        //匹配任意key
        conditions.add("[\"starts-with\",\"$key\",\"\"]");
        //设置共读
        conditions.add("{\"x-obs-acl\": \"public-read\"}");
        //设置大小为0到5m
        conditions.add("[\"content-length-range\", 0, 5242880]");
        conditions.add("{\"bucket\":\"" + HUAWEI_OBS_BUCKET_NAME + "\"}");
        request.setConditions(conditions);
        // 设置表单上传请求有效期，单位：秒
        request.setExpires(HUAWEI_OBS_EXPIRE);

        // 获取表单上传请求参数
        PostSignatureResponse response = obsClient.createPostSignature(request);
        result.setPolicy(response.getPolicy());
        result.setDir(dir);
        result.setSignature(response.getSignature());
        result.setAccessKeyId(HUAWEI_OBS_AK);
        result.setHost(action);
        return result;
    }

    /**
     * 删除对象
     *
     * @return
     */
    public R delete(Map<String, String> params) {
        String url = params.get("url");
        ObsClient obsClient = new ObsClient(HUAWEI_OBS_AK, HUAWEI_OBS_SK, HUAWEI_OBS_ENDPOINT);
        String newUrl = url.replace("https://" + HUAWEI_OBS_BUCKET_NAME + "." + HUAWEI_OBS_ENDPOINT + "/", "");
        obsClient.deleteObject(HUAWEI_OBS_BUCKET_NAME, newUrl);
        if (obsClient.doesObjectExist(HUAWEI_OBS_BUCKET_NAME, newUrl)) {//判断是否删除成功
            return R.error();
        } else {
            return R.ok();
        }
    }
}

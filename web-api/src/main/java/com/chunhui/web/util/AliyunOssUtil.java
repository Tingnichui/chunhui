//package com.chunhui.web.util;
//
//import cn.hutool.core.date.DateUtil;
//import com.aliyun.oss.ClientException;
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.auth.sts.AssumeRoleRequest;
//import com.aliyuncs.auth.sts.AssumeRoleResponse;
//import com.aliyuncs.http.MethodType;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.profile.IClientProfile;
//import com.tingnichui.common.pojo.Result;
//import com.tingnichui.common.util.ResultGenerator;
//import com.tingnichui.security.util.SecurityUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.codec.binary.Base64;
//import org.springframework.stereotype.Component;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Component
//public class AliyunOssUtil {
//    public Result<Map<String, String>> getSts() {
//        // STS接入地址，例如sts.cn-hangzhou.aliyuncs.com。
//        String endpoint = "sts.cn-nanjing.aliyuncs.com";
//        // 填写步骤1生成的访问密钥AccessKey ID和AccessKey Secret。
//        String AccessKeyId = "LTAI5t6gPr3kYVKEErceE7kP";
//        String accessKeySecret = "inyJ4Smz2VLYMIB5TSR3yc8AoqSTci";
//        // 填写步骤3获取的角色ARN。
//        String roleArn = "acs:ram::1946319202375959:role/tingnichui";
//        // 自定义角色会话名称，用来区分不同的令牌，例如可填写为SessionTest。
//        String roleSessionName = "tingnichui";
//        // 以下Policy用于限制仅允许使用临时访问凭证向目标存储空间examplebucket上传文件。
//        // 临时访问凭证最后获得的权限是步骤4设置的角色权限和该Policy设置权限的交集，即仅允许将文件上传至目标存储空间examplebucket下的exampledir目录。
//        String policy = "{\"Version\":\"1\",\"Statement\":[{\"Action\":[\"oss:PutObject\"],\"Resource\":[\"acs:oss:*:*:chunhui-a/*\"],\"Effect\":\"Allow\"}]}";
//        try {
//            String regionId = "";
//            DefaultProfile.addEndpoint("", regionId, "Sts", endpoint);
//            IClientProfile profile = DefaultProfile.getProfile(regionId, AccessKeyId, accessKeySecret);
//            DefaultAcsClient client = new DefaultAcsClient(profile);
//            final AssumeRoleRequest request = new AssumeRoleRequest();
//            request.setMethod(MethodType.POST);
//            request.setRoleArn(roleArn);
//            request.setRoleSessionName(roleSessionName);
//            request.setPolicy(policy);
//            request.setDurationSeconds(20 * 60L);
//            final AssumeRoleResponse response = client.getAcsResponse(request);
//
//            AssumeRoleResponse.Credentials credentials = response.getCredentials();
//            String securityToken = credentials.getSecurityToken();
//            String accessKeyId = credentials.getAccessKeyId();
//            String accessKeySecret1 = credentials.getAccessKeySecret();
//
//            String dir = "tingnichui/images/" + SecurityUtils.getSysUser().getUserId() + "/";
//            final String postPolicy = "{\"expiration\":\"" + DateUtil.offsetMinute(new Date(), 30).toLocalDateTime() + "Z\"," +
//                    "\"conditions\":[" +
//                    "{\"bucket\":\"chunhui-a\"}," +
//                    "[\"starts-with\",\"$key\",\"" + dir + "\"]" +
//                    "]}";
//            String encodePolicy = Base64.encodeBase64String(postPolicy.getBytes(StandardCharsets.UTF_8));
//            String signaturecom = com.aliyun.oss.common.auth.ServiceSignature.create().computeSignature(accessKeySecret1, encodePolicy);
//
//            Map<String, String> map = new HashMap<>();
//            map.put("accessKeyId", accessKeyId);
//            map.put("Signature", signaturecom);
//            map.put("token", securityToken);
//            map.put("policy", encodePolicy);
//            map.put("url", "https://chunhui-a.oss-cn-nanjing.aliyuncs.com/");
//            map.put("dir", dir);
//            return ResultGenerator.success(map);
//        } catch (ClientException | com.aliyuncs.exceptions.ClientException e) {
//            System.err.println("Error message: " + e.getMessage());
//        }
//        return ResultGenerator.fail("获取失败");
//    }
//
//}

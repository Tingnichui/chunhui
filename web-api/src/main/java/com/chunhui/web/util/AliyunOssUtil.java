package com.chunhui.web.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PolicyConditions;
import com.chunhui.web.pojo.vo.AliyunOssUploadAccess;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Slf4j
@Component
public class AliyunOssUtil {

    public static final String bucketName = "^[a-z0-9](?:[a-z0-9]|-(?=[a-z0-9])){1,61}[a-z0-9]$";
    public static final String objectName = "^(?![/\\\\])[^\\p{C}]{1,1023}$\n";

    @Value("${aliyun.oss.bucket}")
    private String bucket;

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKey.id}")
    private String accessKeyId;

    @Value("${aliyun.oss.accessKey.secret}")
    private String accessKeySecret;


    private OSS getOssClient() {
        CredentialsProvider credentialsProvider = new DefaultCredentialProvider(accessKeyId, accessKeySecret);
        return new OSSClientBuilder().build(endpoint, credentialsProvider);
    }

    /**
     * 创建桶
     */
    public void createBucket(String bucketName) {
        OSS ossClient = getOssClient();
        Bucket bucket = ossClient.createBucket(bucketName);
        ossClient.shutdown();
    }


    /**
     * 删除文件
     */
    public void delete(String bucketName, String objectName) {
        OSS ossClient = getOssClient();
        ossClient.deleteObject(bucketName, objectName);
        ossClient.shutdown();
    }


    /**
     * 上传文件
     *
     * @param bucketName 填写Bucket名称，例如examplebucket。
     * @param objectName 填写Object完整路径，例如exampledir/exampleobject.txt。Object完整路径中不能包含Bucket名称。
     */
    public void upload(String bucketName, String objectName, FileInputStream inputStream) {
        OSS ossClient = getOssClient();
        ossClient.putObject(bucketName, objectName, inputStream);
        ossClient.shutdown();
    }

    public void download(String bucketName, String objectName) throws Exception {
        OSS ossClient = getOssClient();
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();
        if (content != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                System.out.println("\n" + line);
            }
            // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
            content.close();
        }
        ossClient.shutdown();
    }


    /**
     * @param dir 设置上传到OSS文件的前缀，可置空此项。置空后，文件将上传至Bucket的根目录下。
     * @return
     */
    public AliyunOssUploadAccess getUploadAccess(String dir, String fileName) {
        // 填写Host名称，格式为https://bucketname.endpoint。
        String host = "https://" + bucket + "." + endpoint;

        // 创建OSSClient实例。
        OSS ossClient = getOssClient();

        PolicyConditions policyConds = new PolicyConditions();
        // 设置上传大小 此处设置为1G PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1 * 1024 * 1024 * 1024);
        // 设置上传目录
        String key = dir + (StringUtils.isBlank(fileName) ? "" : fileName);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, key);

        // 设置过期时间
        DateTime expireEndTime = DateUtil.offsetMinute(new Date(), 1);
        String postPolicy = ossClient.generatePostPolicy(expireEndTime, policyConds);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        AliyunOssUploadAccess access = new AliyunOssUploadAccess();
        access.setAccessid(accessKeyId);
        access.setPolicy(encodedPolicy);
        access.setSignature(postSignature);
        access.setDir(dir);
        access.setFilePath(key);
        access.setHost(host);
        access.setExpire(String.valueOf(expireEndTime.getTime() / 1000));
        ossClient.shutdown();
        return access;

    }

}

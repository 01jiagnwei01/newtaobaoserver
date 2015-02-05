package com.gxkj.taobaoservice.ali.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSErrorCode;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.model.AccessControlList;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.gxkj.common.util.SystemGlobals;
/**
 * 个人中心-安全中心-查找ACCESS_ID和ACCESS_KEY
 * @author Administrator
 *
 */
public class ClientUtils {
	private static OSSClient oSSClient = null;
	private static   String ACCESS_ID = "yFHjBg4HXVwBSyMkTrxQWcuinAi2o7";// "<your access key id>";
    private static   String ACCESS_KEY = "";//<your access key secret>
    //北京节点外网地址：oss-cn-beijing.aliyuncs.com
    //北京节点内网地址：oss-cn-beijing-internal.aliyuncs.com
    private static final String OSS_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com/";
	
	public static  OSSClient getOSSClient( ){
		if(oSSClient != null) return oSSClient;
		ACCESS_ID = SystemGlobals.getPreference("taobaoservice.ali.Access.Key.ID","arzNQ1bmmyOSjHtt");
		ACCESS_KEY = SystemGlobals.getPreference("taobaoservice.ali.Access.Key.Secret","yFHjBg4HXVwBSyMkTrxQWcuinAi2o7");
		oSSClient = new OSSClient(OSS_ENDPOINT, ACCESS_ID, ACCESS_KEY);
		return oSSClient;
	}
	
	public void actTest(String bucketName){
		OSSClient client =  ClientUtils.getOSSClient();
		
		client.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite); 
		AccessControlList accessControlList = client.getBucketAcl(bucketName);
		//可以打印出来看结果,也可以从控制台确认
		System.out.println(accessControlList.toString());
	}
	public  void listContentInBucket(String bucketName){
		OSSClient client =  ClientUtils.getOSSClient();
		 ObjectListing ObjectListing = client.listObjects(bucketName);
	        List<OSSObjectSummary> listDeletes = ObjectListing
	                .getObjectSummaries();
	        for (int i = 0; i < listDeletes.size(); i++) {
	            String objectName = listDeletes.get(i).getKey();
	            System.out.println(objectName);
	            // 如果不为空，先删除bucket下的文件
	            //client.deleteObject(bucketName, objectName);
	        }
	}
	public static void main(String[] args) throws OSSException, ClientException, FileNotFoundException {
		String bucketName = "001taobaoservice";
		//new ClientUtils().actTest();
		//new ClientUtils().listContentInBucket(bucketName);
		
		OSSClient client =  ClientUtils.getOSSClient();
		ClientUtils.ensureBucket(client,bucketName);
		
        //设置bucket的访问权限，public-read-write权限
       // client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
		 String uploadFilePath = "c:/0.jpg";
		 //上传
		  ClientUtils.uploadFile(client, bucketName, "img/0.jpg", uploadFilePath);
		 //ClientUtils.browerBucket(client, bucketName);
		
	}
	/**
	 * 上传文件
	 * @param bucketName
	 * @param saveName
	 * @param pic
	 * @return
	 * @throws OSSException
	 * @throws ClientException
	 * @throws IOException
	 */
	public static String uploadFile( String bucketName, String saveName, MultipartFile pic) throws OSSException, ClientException, IOException{
		OSSClient client =  ClientUtils.getOSSClient();
		ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(pic.getSize());
        // 可以在metadata中标记文件类型
        objectMeta.setContentType(pic.getContentType());
        PutObjectResult result =client.putObject(bucketName, saveName, pic.getInputStream(), objectMeta);
        return result.getETag();

	}
	/**
	 * 删除某个文件
	 * @param bucketName
	 * @param fileName
	 */
	public static void deleteFile(String bucketName, String fileName){
			OSSClient client =  ClientUtils.getOSSClient();
			client.deleteObject(bucketName, fileName);
	}
	  // 上传文件
    private static void uploadFile(OSSClient client, String bucketName, String key, String filename)
            throws OSSException, ClientException, FileNotFoundException {
        File file = new File(filename);

        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.length());
        // 可以在metadata中标记文件类型
        objectMeta.setContentType("image/jpeg");

        InputStream input = new FileInputStream(file);
        PutObjectResult result =client.putObject(bucketName, key, input, objectMeta);
        System.out.println(result.getETag());
        
    }
	// 创建Bucket.
	public static void ensureBucket(OSSClient client, String bucketName)
            throws OSSException, ClientException{
        try {
            // 创建bucket
            client.createBucket(bucketName);
        } catch (ServiceException e) {
            if (!OSSErrorCode.BUCKES_ALREADY_EXISTS.equals(e.getErrorCode())) {
                // 如果Bucket已经存在，则忽略
                throw e;
            }
        }
    }
	
	//浏览Bucket下的文件
    private static void browerBucket(OSSClient client, String bucketName)
            throws OSSException, ClientException {
    	//构造 ListObjectsRequest请求
    	ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
    	//Delimiter:用于对 Object名字进行分组的符。所有名字包含指定前缀且第一次出现Delimiter字符之间的Object作为一组元素：CommonPrefixes 。
    	//Marker:设定结果从Marker之后按字母排序的第一个开始返回。
    	listObjectsRequest.setDelimiter("/");
    	listObjectsRequest.setMarker("123");
    	//list Objects 
    	ObjectListing listing = client.listObjects(listObjectsRequest);
    	//遍历所有的对象
    	System.out.println("objects");
    	  List<OSSObjectSummary> listDeletes = listing
                  .getObjectSummaries();
    	for(OSSObjectSummary oSSObjectSummary:listDeletes){
    		 System.out.println(oSSObjectSummary.getKey());
    	}
    		
    	 //遍历所有的CommonPrefix
    	System.out.println("CommonPrefix");
    	List<String> commonPrefixs = listing.getCommonPrefixes();
        for(String commonPrefix:commonPrefixs){
        	System.out.println(commonPrefix);
        }
    }
         
    
}

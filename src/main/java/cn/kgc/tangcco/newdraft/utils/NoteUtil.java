package cn.kgc.tangcco.newdraft.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * 短信验证
 * @author YaoShTime
 *
 */
public class NoteUtil {
	
	private String accessKeyId;
	private String secret;
	public NoteUtil(String accessKeyId, String secret ) {
		this.accessKeyId = accessKeyId;
		this.secret = secret;
	}
	
	public String getRandomNumber() {
		String randomNumber = "";
		randomNumber += (int)(Math.random() * 9) + 1;
		while(randomNumber.length() < 6) {
			randomNumber += (int)(Math.random() * 10);
		}
		return randomNumber;
	}
	
	public boolean send(String randomNumber, String phoneNumber, String signName, String TemplateCode) {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, secret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phoneNumber);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", TemplateCode);
        request.putQueryParameter("TemplateParam", "{code:" + randomNumber + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return true;
        } catch (ServerException e) {
        	e.printStackTrace();
        } catch (ClientException e) {
        	e.printStackTrace();
        }
        return false;
	}
	
}

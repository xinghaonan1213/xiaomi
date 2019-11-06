package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓


	//沙箱APPID
	public static final  String app_id = "2016101200670094";
	//沙箱私钥
	public static final  String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDFsgC5IrWvHcjyUXpwNkIxSsFq8xEVylaHbbtQ892SpSNt3ukjURN3YGPxY2+fybm+yZHqUnEOybrOF5CkgV3rRHgZUQD7bhTK8AKZvoIIPiliAfb6n4i77FC/EzKuFS1xFmi2n6GEKutaR71n4YeUradBPfVOsHCqPmu01pioenSHPUrtTXWLsWnZG4LI/0YniNHU7WW4YznAGtHIff+DHWKYhBeGI536qfFUNeN8GFZklbLzW1qFHY2mzQnSVXcieQYOgplJqaWT0UGysgklw4BP4rSotAidGKWBf0dvHSXW1b5Qiga5NkXo3NrTOx1snIn5faiyxKFX51iYjfL9AgMBAAECggEBAIPi6VqZDk3h9/G4a486k1QhBAHeYFKCJtG4YdNyrSmso4Mjx7K43tyv4FzkMk6OHC1sebyx/RyZddcRn3wvlcFjbx+si5Qw7wwnrAR3NHZdSexJVUQDdAuybnfaTeAW3rbn9TmIKw9qNpKG85O+wF1ur/r1Y5Z4XOEmPWJqG3Og95f3wpHYIZGbUwIKyZZAmzTeSZqFrCvSYJhZuKXC4jHvGhdRXBN6zFRoUhpCjZNJUl8OLM263hmxg9Jq9oNNGO/+R5i6+NfiePJ3tSMpYzN0cOT4QzTEyEZDLnBhpJeEg07UUv1A+z2e03sFA/Qw4hJnaIzAdqI3OwFeix93mJ0CgYEA/X9/if7byxChso7ebiqyhcCS/TKWwJ84VbLP6L47o+Csjtg8BtP+0yL4w69965wdBaG7FWHwW6fNRJz2GC9LZ4znkx1bKfSBnbE6TKghc5B1jFbIeQ3Px31Av60CTY7Zo+m/4uRP/Y9ffGcBvb1cMes3uYDtGHEwEhfnpx3gXU8CgYEAx6WCrp/KmA46uDmF271ZcXVHi2cjwJqJkxkW1sxHE9x4kVZ0sqwYjk2kvRbaMwm1DcMyPDTdcEdQhbknm2d4O6UZ3M/SgZEhZdD8GqgTTU5jGDEMjcW9p+uBhCGCKf54e0XBJkAPbf6D4xcKWrqa4rQHGDKTe6i47nQtmJUWT/MCgYEAwxBfFdL1UpbjtkDVzDQy11VORmOtOV/Lu7Yy4kewIPR8FBc0aZSr29BnWD0sSicA9hpzmYqZqA3BmHCntgh+n5n/gGbwhaJOMwfzcpVV2FVq/qiw7uW/4yPwtQ3uXbv0oXr3WyKQou62bUXeQyD5VrhHUwlwaISAlwuqCCCnS2UCgYBtMfyBEErZ0eaR/nxVTIJQSQohl/75CE041F8misJRfaPAMl5Pn0srAhKZjZc4lRWe+mVOgsee8pJ7IWMlX3lmfg3gNRb44O04aA6LceRkwvv2LlWkcUQjRO8o2r77wwAYxHU5O4505eEy4tY5+5hdaIP82yTJXOYRHiAWNWznuwKBgQDP7FL7LIGOk7HkWaqabLCxx4u+LJNT3QVdfXh+9Uvbn714ki2WJbRuYSerlQGpMJZJULMBOuPAZ07KpSYDM2vFyCE+2t+bWf4xss5lu/USlisPZ5W3rN2+qZxJQkUSLPMxF9MkEdEEcuoj8FxKMubhnTl8U/hTZoVcXwkKGTovqQ==";
	//支付宝公钥
	public static final  String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxbIAuSK1rx3I8lF6cDZCMUrBavMRFcpWh227UPPdkqUjbd7pI1ETd2Bj8WNvn8m5vsmR6lJxDsm6zheQpIFd60R4GVEA+24UyvACmb6CCD4pYgH2+p+Iu+xQvxMyrhUtcRZotp+hhCrrWke9Z+GHlK2nQT31TrBwqj5rtNaYqHp0hz1K7U11i7Fp2RuCyP9GJ4jR1O1luGM5wBrRyH3/gx1imIQXhiOd+qnxVDXjfBhWZJWy81tahR2Nps0J0lV3InkGDoKZSamlk9FBsrIJJcOAT+K0qLQInRilgX9Hbx0l1tW+UIoGuTZF6Nza0zsdbJyJ+X2ossShV+dYmI3y/QIDAQAB";
	//沙箱网关地址
	public static final  String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

//	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/xiaomi/pay/notify_url.jsp";

//	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/xiaomi/pay/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";

	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


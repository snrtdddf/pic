package com.k4meitu.pic.po;

import java.io.Serializable;

import com.k4meitu.pic.utils.UToStringBuilder;

public class ApiRequest implements Serializable
{
	private static final long serialVersionUID = -798013259026789803L;
	
	private String method; 		// 接口名称
	private String charset; 	// 请求格式(utf-8)
	private String sign; 		// 签名
	private String version; 	// 版本(1.0)
	private String timespan; 	// 时间(yyyy-MM-dd HH:mm:ss)
	private String ip;			// 客户端IP
	private String channel;		// 请求渠道
	
	
	public String getMethod()
	{
		return method;
	}
	public void setMethod(String method)
	{
		this.method = method;
	}
	public String getCharset()
	{
		return charset;
	}
	public void setCharset(String charset)
	{
		this.charset = charset;
	}
	public String getSign()
	{
		return sign;
	}
	public void setSign(String sign)
	{
		this.sign = sign;
	}
	public String getVersion()
	{
		return version;
	}
	public void setVersion(String version)
	{
		this.version = version;
	}
	public String getTimespan()
	{
		return timespan;
	}
	public void setTimespan(String timespan)
	{
		this.timespan = timespan;
	}
	public String getIp()
	{
		return ip;
	}
	public void setIp(String ip)
	{
		this.ip = ip;
	}
	public String getChannel()
	{
		return channel;
	}
	public void setChannel(String channel)
	{
		this.channel = channel;
	}
	
	@Override
	public String toString()
	{
		return UToStringBuilder.toString(this);
	}
	
}

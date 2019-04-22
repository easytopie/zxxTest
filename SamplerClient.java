package com.Samplers;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class SamplerClient extends AbstractJavaSamplerClient{
	private SampleResult rs;
	private String inNum;
	private String resultNum;


	@Override
	public void setupTest(JavaSamplerContext jsc) {
		rs=new SampleResult();
		inNum=jsc.getParameter("inNum","");
		resultNum=jsc.getParameter("resultNum","");
		
		if(inNum!=null&&inNum.length()>0){
			rs.setSamplerData(inNum);
		}
		
		if(resultNum!=null&&resultNum.length()>0){
			rs.setSamplerData(resultNum);
		}
	}
	
	@Override
	public Arguments getDefaultParameters() {
		Arguments arguments=new Arguments();
		arguments.addArgument("inNum","");
		arguments.addArgument("resultNum","66");
		return arguments;
	}


	@SuppressWarnings("deprecation")
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		boolean flag=false;
		rs.sampleStart();
		//判断输入的字符串是否是数字
		for(int i=inNum.length();--i>=0;){
			if(!Character.isDigit(inNum.charAt(i))){
				flag=false;
			}else
				flag=true;
		}
		rs.sampleEnd();
		
		if (flag) {
			Integer num=Integer.parseInt(inNum);
			Integer rsNum=Integer.parseInt(resultNum);
			
			if (num==rsNum) {
				rs.setDataEncoding("UTF-8");
				rs.setResponseData("恭喜你，答对了！\n答案是【"+resultNum+"】");
				rs.setSuccessful(true);
			}else if (num>rsNum) {
				rs.setDataEncoding("UTF-8");
				rs.setResponseData("好像大了点！\n您输入的值是【"+inNum+"】");
				rs.setSuccessful(false);			
			}else {
				rs.setDataEncoding("UTF-8");
				rs.setResponseData("好像小了点！\n您输入的值是【"+inNum+"】");
				rs.setSuccessful(false);
			}
					
		}else {
			rs.setDataEncoding("UTF-8");
			rs.setResponseData("请输入数字！\n【"+inNum+"】");
			rs.setSuccessful(false);
		}
		
		return rs;
	}
	
	@Override
	public void teardownTest(JavaSamplerContext context) {
	}


}

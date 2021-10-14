package com.abinge.boot.staging.freemarker;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.*;

public class PDFTemplateUtil {

	/**
	 * 通过模板导出pdf文件
	 * @param data 数据
	 * @param templateFileName 模板文件名
	 * @throws Exception
	 */
    public static ByteArrayOutputStream createPDF(Map<String,Object> data, String templateFileName) throws Exception {
        // 创建一个FreeMarker实例, 负责管理FreeMarker模板的Configuration实例
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // 指定FreeMarker模板文件的位置
        cfg.setClassForTemplateLoading(PDFTemplateUtil.class,"/templates");
        ITextRenderer renderer = new ITextRenderer();
        OutputStream out = new ByteArrayOutputStream();
        try {
            // 设置 css中 的字体样式（暂时仅支持宋体和黑体） 必须，不然中文不显示
            renderer.getFontResolver().addFont("/templates/font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 设置模板的编码格式
            cfg.setEncoding(Locale.CHINA, "UTF-8");
            // 获取模板文件
            Template template = cfg.getTemplate(templateFileName, "UTF-8");
            StringWriter writer = new StringWriter();

            // 将数据输出到html中
            template.process(data, writer);
            writer.flush();

            String html = writer.toString();
            // 把html代码传入渲染器中
            renderer.setDocumentFromString(html);

            // 解决图片的相对路径问题 ##必须在设置document后再设置图片路径，不然不起作用
            renderer.getSharedContext().setBaseURL("images/");
            renderer.layout();

            renderer.createPDF(out, false);
            renderer.finishPDF();
            out.flush();
            return (ByteArrayOutputStream)out;
        } finally {
        	if(out != null){
        		 out.close();
        	}
        }
    }



    /**
	 * 通过模板导出pdf文件
	 * @param data 数据
	 * @param templateFileName 模板文件名
	 * @throws Exception
	 */
    public static void createPDF2File(Map<String,Object> data, String templateFileName, String desFilePath) throws Exception {
        // 创建一个FreeMarker实例, 负责管理FreeMarker模板的Configuration实例
        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        // 指定FreeMarker模板文件的位置
        cfg.setClassForTemplateLoading(PDFTemplateUtil.class,"/templates");
        ITextRenderer renderer = new ITextRenderer();
        OutputStream out = new FileOutputStream(desFilePath);
        try {
            // 设置 css中 的字体样式（暂时仅支持宋体和黑体） 必须，不然中文不显示
            renderer.getFontResolver().addFont("/templates/font/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            // 设置模板的编码格式
            cfg.setEncoding(Locale.CHINA, "UTF-8");
            // 获取模板文件
            Template template = cfg.getTemplate(templateFileName, "UTF-8");
            StringWriter writer = new StringWriter();

            // 将数据输出到html中
            template.process(data, writer);
            writer.flush();

            String html = writer.toString();
            // 把html代码传入渲染器中
            renderer.setDocumentFromString(html);

            // 解决图片的相对路径问题 ##必须在设置document后再设置图片路径，不然不起作用
            renderer.getSharedContext().setBaseURL("images/");
            renderer.layout();

            renderer.createPDF(out, false);
            renderer.finishPDF();
            out.flush();
        } finally {
        	if(out != null){
        		 out.close();
        	}
        }
    }

    public static void main(String[] args) throws Exception {
        createApiPdf();
//        createTestPdf();

    }

    private static void createApiPdf() throws Exception {
        String apiJson = FileUtil.readString(new File("D:\\private\\edu\\abing-boot\\src\\main\\resources\\templates\\apiInfo.json"), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(apiJson);
        JSONObject data = jsonObject.getJSONObject("data");
        PDFTemplateUtil.createPDF2File(data, "api文档模板2.html", "D:\\private\\edu\\abing-boot\\src\\main\\resources\\templates\\apiInfo2.pdf");
    }

    private static void createTestPdf() throws Exception {
        Map<String,Object> data = new HashMap<>();
        data.put("curr", 1);
        data.put("one", 2);
        data.put("two", 1);
        data.put("three", 6);

        List<PDFDataTest> detailList = new ArrayList<>();
        detailList.add(new PDFDataTest(123456,"测试","测试","测试","测试"));
        detailList.add(new PDFDataTest(111111,"测试","测试","测试","测试"));
        detailList.add(new PDFDataTest(222222,"测试","测试","测试","测试"));
        data.put("detailList", detailList);

        PDFTemplateUtil.createPDF2File(data, "pdf测试模板.html","D:\\private\\edu\\abing-boot\\src\\main\\resources\\templates\\test.pdf");


    }
}

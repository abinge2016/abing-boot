package com.abinge.boot.staging.freemarker;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Map;

public class JavaToPdfHtmlFreeMarker {

    private static final String DEST = "D:\\private\\edu\\abing-boot\\src\\main\\resources\\templates\\apiInfo3.pdf";

    private static final String FTL = "/templates";

    private static final String HTML = "api文档模板2.html";

    private static final String FONT = "/templates/font/simsun.ttc";
    private static final String FONT_C = "/templates/font/calibri.ttf";
    private static final String FONT_S = "/templates/font/simsun.ttc";

    private static Configuration freemarkerCfg = null;

    static {
        freemarkerCfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        //freemarker的模板目录
        try {
            //freemarkerCfg.
            freemarkerCfg.setClassForTemplateLoading(JavaToPdfHtmlFreeMarker.class, FTL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createPdf(String content, String dest) throws Exception{
        ITextRenderer render = new ITextRenderer();

        //设置字体
        ITextFontResolver fontResolver = render.getFontResolver();
        fontResolver.addFont(FONT_S, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        fontResolver.addFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        fontResolver.addFont(FONT_C, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        // 解析html生成pdf
        render.setDocumentFromString(content);
        render.layout();
        render.createPDF(new FileOutputStream(dest));
    }

    /**
     * freemarker渲染html
     */
    public static String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
        Writer out = new StringWriter();
        try {
            // 获取模板,并设置编码方式
            Template template = freemarkerCfg.getTemplate(htmlTmp, "UTF-8");
            // 合并数据模型与模板
            template.process(data, out); //将合并后的数据和模板写入到流中，这里使用的字符流
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
//	        Map<String,Object> data = new HashMap();
//	        data.put("name","路飛.祖克");
        String apiJson = FileUtil.readString(new File("D:\\private\\edu\\abing-boot\\src\\main\\resources\\templates\\apiInfo.json"), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(apiJson);
        JSONObject data = jsonObject.getJSONObject("data");
        String content = JavaToPdfHtmlFreeMarker.freeMarkerRender(data, HTML);
        JavaToPdfHtmlFreeMarker.createPdf(content, DEST);
    }
}
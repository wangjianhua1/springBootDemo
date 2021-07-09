package com.example.demo.pdf;

import wiki.xsx.core.pdf.component.image.XEasyPdfImage;
import wiki.xsx.core.pdf.component.table.XEasyPdfCell;
import wiki.xsx.core.pdf.component.table.XEasyPdfRow;
import wiki.xsx.core.pdf.component.table.XEasyPdfTableStyle;
import wiki.xsx.core.pdf.component.text.XEasyPdfText;
import wiki.xsx.core.pdf.component.text.XEasyPdfTextStyle;
import wiki.xsx.core.pdf.footer.XEasyPdfDefaultFooter;
import wiki.xsx.core.pdf.handler.XEasyPdfHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 操作PDF的JAR主要分成两类，
 * 1.iText系列（iText 5、iText7收费、openpdf基于iText4分支）
 * 2.Apache PDFBox系列（x-easypdf）
 */
public class EasyPdf {

    public static void main(String[] args) throws IOException {
        String bgImagePath = "C:\\Users\\Upoint\\Pictures\\微信图片_20200615162028.gif";
        String opp = "F:\\test_easypdf.pdf";
        String fontPath = "E:\\BaiduNetdiskDownload\\bbcgw.m.jd.com\\bbcgw.m.jd.com\\jd-bbcServer-web\\target\\jd-bbcServer-web\\images_store\\方正正中黑简体.ttf";
        ArrayList<XEasyPdfRow> rowList = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            XEasyPdfCell cell1 = new XEasyPdfCell(40, 20).addContent(new XEasyPdfText("测试1"));
            XEasyPdfCell cell2 = new XEasyPdfCell(40, 20).addContent(new XEasyPdfText("测试2"));
            XEasyPdfCell cell3 = new XEasyPdfCell(40, 20).addContent(new XEasyPdfText("测试3"));
            XEasyPdfCell cell4 = new XEasyPdfCell(40, 20).addContent(new XEasyPdfText("测试4"));
            XEasyPdfCell cell5 = new XEasyPdfCell(40, 20).addContent(new XEasyPdfText("测试5"));
            XEasyPdfCell cell6 = new XEasyPdfCell(40, 20).addContent(new XEasyPdfText("测试5"));
            XEasyPdfCell cell7 = new XEasyPdfCell(40, 20).addContent(new XEasyPdfText("测试5"));
            XEasyPdfCell cell8 = new XEasyPdfCell(40, 20).addContent(new XEasyPdfText("测试5"));
            XEasyPdfCell cell9 = new XEasyPdfCell(40, 20).addContent(new XEasyPdfText("测试5"));
            XEasyPdfRow row = new XEasyPdfRow(cell1, cell2, cell3, cell4, cell5, cell6, cell7, cell8, cell9);
            row.setMarginLeft(10);
            rowList.add(row);
        }
        XEasyPdfHandler.Document.build().setFontPath(fontPath)
                .setGlobalBackgroundImage(new XEasyPdfImage(new File(bgImagePath)))
                .setGlobalHeader(XEasyPdfHandler.Header.build(new XEasyPdfText("这是页眉").setStyle(XEasyPdfTextStyle.CENTER).setMarginTop(10)))
                .setGlobalFooter(new XEasyPdfDefaultFooter(new XEasyPdfText("这是页脚")))
                .addPage(
                        XEasyPdfHandler.Page.build(
                                XEasyPdfHandler.Table.build(rowList).setStyle(XEasyPdfTableStyle.CENTER).setFontSize(14F)
                        )
                ).save(opp);
    }
}

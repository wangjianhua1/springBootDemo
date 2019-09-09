package com.example.demo.easypoi;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Watchable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.apache.tomcat.util.file.ConfigFileLoader.getInputStream;

public class POITest {
    String file=new String("C:\\Users\\Upoint\\Documents\\WeChat Files\\DoMyBest-jianhua\\FileStorage\\File\\2019-05\\百度百科分类-20190529-处理.xlsx");
    /**
     * 使用easyexcel解析03、07版本的Excel只是ExcelTypeEnum不同，其他使用完全相同，使用者无需知道底层解析的差异。
     * <p>
     * 无java模型直接把excel解析的每行结果以List<String>返回 在ExcelListener获取解析结果
     *
     * @throws IOException
     */
    @Test
    public void testExcel2003NoModel() throws IOException {
        InputStream inputStream = getInputStream(file);
        try {
            // 解析每行结果在listener中处理
            ExcelListener<Q_A> listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
            excelReader.read();
            List<Q_A> datas = listener.getDatas();
            System.out.println(datas.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testExcel2003WithReflectModel() throws IOException {
        InputStream inputStream = getInputStream(file);
        try {
            // 解析每行结果在listener中处理
            ExcelListener<Q_A> listener = new ExcelListener<>();

            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
            excelReader.read(new Sheet(1, 1, Q_A.class));
//            excelReader.read();
            List<Q_A> datas = listener.getDatas();
            System.out.println(datas.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testWorkBook() throws IOException, InvalidFormatException {
        XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(new File(file)));
        int count=0;
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            int lastRowNum = sheet.getLastRowNum();
            count+=lastRowNum;
        }
        System.out.println(count);
    }

    @GetMapping("/a.htm")
    public void cooperation(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletOutputStream out = response.getOutputStream();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        String fileName = new String(("UserInfo " + new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(), "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
        Sheet sheet1 = new Sheet(1, 0);
        sheet1.setSheetName("第一个sheet");

        writer.write0(new ArrayList<List<String>>(), sheet1);
        writer.finish();

        out.flush();
    }

}

package com.example.demo.easypoi;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * @ExcelProperty(index = 3)数字代表该字段与excel对应列号做映射，
 * 也可以采用 @ExcelProperty(value = {"一级表头","二级表头"})用于解决不确切知道excel第几列和该字段映射，位置不固定，但表头的内容知道的情况。
 */
public class LoanInfo extends BaseRowModel {
    @ExcelProperty(index = 0)
    private String bankLoanId;

    @ExcelProperty(index = 1)
    private Long customerId;

    @ExcelProperty(index = 2,format = "yyyy/MM/dd")
    private Date loanDate;

    @ExcelProperty(index = 3)
    private BigDecimal quota;

    @ExcelProperty(index = 4)
    private String bankInterestRate;

    @ExcelProperty(index = 5)
    private Integer loanTerm;

    @ExcelProperty(index = 6,format = "yyyy/MM/dd")
    private Date loanEndDate;

    @ExcelProperty(index = 7)
    private BigDecimal interestPerMonth;

    @ExcelProperty(value = {"一级表头","二级表头"})
    private BigDecimal sax;
}
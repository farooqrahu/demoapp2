package com.jamil.shop.springboot.Dto;

import org.springframework.web.multipart.MultipartFile;

public class BranchLogoDto {

    private Long id;

    private String imagePathOne;

    private String imagePathTwo;

    private String imageLocalPath;

    private Boolean is_deleted;

    private Long status;

   // private MultipartFile logo1;

    private Long branchId;

    private String invoiceFooterMessage;

    private String voucherFooterMessage;

    private String generalReportFooterMessage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImagePathOne() {
        return imagePathOne;
    }

    public void setImagePathOne(String imagePathOne) {
        this.imagePathOne = imagePathOne;
    }

    public String getImagePathTwo() {
        return imagePathTwo;
    }

    public void setImagePathTwo(String imagePathTwo) {
        this.imagePathTwo = imagePathTwo;
    }

    public String getImageLocalPath() {
        return imageLocalPath;
    }

    public void setImageLocalPath(String imageLocalPath) {
        this.imageLocalPath = imageLocalPath;
    }



    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getInvoiceFooterMessage() {
        return invoiceFooterMessage;
    }

    public void setInvoiceFooterMessage(String invoiceFooterMessage) {
        this.invoiceFooterMessage = invoiceFooterMessage;
    }

    public String getVoucherFooterMessage() {
        return voucherFooterMessage;
    }

    public void setVoucherFooterMessage(String voucherFooterMessage) {
        this.voucherFooterMessage = voucherFooterMessage;
    }

    public String getGeneralReportFooterMessage() {
        return generalReportFooterMessage;
    }

    public void setGeneralReportFooterMessage(String generalReportFooterMessage) {
        this.generalReportFooterMessage = generalReportFooterMessage;
    }

}

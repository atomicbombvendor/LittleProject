package com.company.InjectTest.SqlBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by eli9 on 9/5/2017.
 */
@Table(value = "CalcFinancialDataBalanceSheet_Test", primaryKey = {"ReportKey", "PerformanceId"})
public class CalcFinancialDataBalanceSheet{

    //region field
    private Long reportKey;
    private String performanceId;
    private Double oRBSZ00001;
    private Double oRBSZ00002;
    private Double oRBSZ00003;
    private Double oRBSZ00004;
    private Double oRBSZ00006;
    private Double oRBSZ00007;
    private Double oRBSZ00008;
    private Long createdTime;
    private Long lastUpdatedTime;
    //end region field

    @Column("ReportKey")
    @JsonProperty("reportKey")
    public Long getReportKey() {
        return reportKey;
    }

    public void setReportKey(Long reportKey) {
        this.reportKey = reportKey;
    }

    @Column("PerformanceId")
    @JsonProperty("shareClassId")
    public String getPerformanceId() {
        return performanceId;
    }

    public void setPerformanceId(String performanceId) {
        this.performanceId = performanceId;
    }

    @Column("ORBSZ00001")
    @JsonProperty("orbsz00001")
    public Double getoRBSZ00001() {
        return oRBSZ00001;
    }

    public void setoRBSZ00001(Double oRBSZ00001) {
        this.oRBSZ00001 = oRBSZ00001;
    }

    @Column("ORBSZ00002")
    @JsonProperty("orbsz00002")
    public Double getORBSZ00002() {
        return oRBSZ00002;
    }

    public void setORBSZ00002(Double oRBSZ00002) {
        this.oRBSZ00002 = oRBSZ00002;
    }

    @Column("ORBSZ00003")
    @JsonProperty("orbsz00003")
    public Double getORBSZ00003() {
        return oRBSZ00003;
    }

    public void setORBSZ00003(Double ORBSZ00003) {
        this.oRBSZ00003 = ORBSZ00003;
    }

    @Column("ORBSZ00004")
    @JsonProperty("orbsz00004")
    public Double getORBSZ00004() {
        return oRBSZ00004;
    }

    public void setORBSZ00004(Double ORBSZ00004) {
        this.oRBSZ00004 = ORBSZ00004;
    }

    @Column("ORBSZ00006")
    @JsonProperty("orbsz00006")
    public Double getORBSZ00006() {
        return oRBSZ00006;
    }

    public void setORBSZ00006(Double ORBSZ00006) {
        this.oRBSZ00006 = ORBSZ00006;
    }

    @Column("ORBSZ00007")
    @JsonProperty("orbsz00007")
    public Double getORBSZ00007() {
        return oRBSZ00007;
    }

    public void setORBSZ00007(Double ORBSZ00007) {
        this.oRBSZ00007 = ORBSZ00007;
    }

    @Column("ORBSZ00008")
    @JsonProperty("orbsz00008")
    public Double getORBSZ00008() {
        return oRBSZ00008;
    }

    public void setORBSZ00008(Double ORBSZ00008) {
        this.oRBSZ00008 = ORBSZ00008;
    }

    @Column("LastUpdatedTime")
    public String getUpdateTime() {
        return lastUpdatedTime.toString();
    }

    @Column("CreatedTime")
    public String getCreateDate() {
        return createdTime.toString();
    }
}

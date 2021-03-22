package com.wave.payroll.EmployeePay;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table
public class EmployeePay {

    @Id
    @SequenceGenerator(
            name = "employeepay_sequence",
            sequenceName = "employeepay_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "employeepay_sequence"
    )

    private Long id;
    private LocalDate date;
    private Long employeeID, reportID;
    private String jobGroup;
    private Float hoursWorked, payRate;
    private Float amountPaid;
    private LocalDate payPeriodStart, payPeriodEnd;
    @Column(name = "processedOn")
    private LocalDate processedOn;

    public EmployeePay() {
    }

    public EmployeePay(Long id, LocalDate date, Long employeeID, Long reportID, String jobGroup, Float hoursWorked, Float payRate, LocalDate processedOn) {
        this.id = id;
        this.date = date;
        this.employeeID = employeeID;
        this.reportID = reportID;
        this.jobGroup = jobGroup;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
        this.processedOn = processedOn;
    }

    public EmployeePay(LocalDate date, Long employeeID, Long reportID, String jobGroup, Float hoursWorked, Float payRate, LocalDate processedOn) {
        this.date = date;
        this.employeeID = employeeID;
        this.reportID = reportID;
        this.jobGroup = jobGroup;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
        this.processedOn = processedOn;
    }

    public EmployeePay(Long id, LocalDate date, Long employeeID, Long reportID, String jobGroup, Float hoursWorked, Float payRate, Float amountPaid, LocalDate payPeriodStart, LocalDate payPeriodEnd, LocalDate processedOn) {
        this.id = id;
        this.date = date;
        this.employeeID = employeeID;
        this.reportID = reportID;
        this.jobGroup = jobGroup;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
        this.amountPaid = amountPaid;
        this.payPeriodStart = payPeriodStart;
        this.payPeriodEnd = payPeriodEnd;
        this.processedOn = processedOn;
    }

    public EmployeePay(LocalDate date, Long employeeID, Long reportID, String jobGroup, Float hoursWorked, Float payRate, Float amountPaid, LocalDate payPeriodStart, LocalDate payPeriodEnd, LocalDate processedOn) {
        this.date = date;
        this.employeeID = employeeID;
        this.reportID = reportID;
        this.jobGroup = jobGroup;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
        this.amountPaid = amountPaid;
        this.payPeriodStart = payPeriodStart;
        this.payPeriodEnd = payPeriodEnd;
        this.processedOn = processedOn;
    }

    public EmployeePay(LocalDate date, Long employeeID, Long reportID, String jobGroup, Float hoursWorked) {
        this.date = date;
        this.employeeID = employeeID;
        this.reportID = reportID;
        this.jobGroup = jobGroup;
        this.hoursWorked = hoursWorked;
        this.processedOn = processedOn;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public Long getReportID() {
        return reportID;
    }

    public void setReportID(Long reportID) {
        this.reportID = reportID;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public Float getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Float hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public Float getPayRate() {
        return payRate;
    }

    public void setPayRate(Float payRate) {
        this.payRate = payRate;
    }

    public Float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getPayPeriodStart() {
        return payPeriodStart;
    }

    public void setPayPeriodStart(LocalDate payPeriodStart) { this.payPeriodStart = payPeriodStart;
    }

    public LocalDate getPayPeriodEnd() {
        return payPeriodEnd;
    }

    public void setPayPeriodEnd(LocalDate payPeriodEnd) {
        this.payPeriodEnd = payPeriodEnd;
    }

    public LocalDate getProcessedOn() {
        //return LocalDate.now();
        return processedOn;
    }

    public void setProcessedOn(LocalDate processedOn) {
        this.processedOn = processedOn;
    }

    public LocalDate calculatePayPeriodStart() {
        int day = getDate().getDayOfMonth();
        LocalDate start;
        if (day <= 15)
            start = this.date.withDayOfMonth(1);
        else
            start = LocalDate.of(date.getYear(), date.getMonth(),16);
        return start;
    }

    public LocalDate calculatePayPeriodEnd() {
        int day = getDate().getDayOfMonth();
        LocalDate end;
        if (day > 15)
            end = this.date.withDayOfMonth(this.date.lengthOfMonth());
        else
            end = LocalDate.of(date.getYear(), date.getMonth(),15);

        return end;
    }

    public Float calculatePayRate() {
        String jobGroupForPayRate = getJobGroup();
        Float pay;
        if (jobGroupForPayRate.equals("A"))
            pay = 20F;
        else
            pay = 30F;

        return pay;
    }

    public LocalDate calculateProcessedOnDate() {
        LocalDate todayDate;
        todayDate = LocalDate.now();
        return todayDate;
    }

    public Float calculateAmountPaid() {
        Float amount;
        amount = getPayRate()*getHoursWorked();
        return amount;
    }

    @PrePersist
    public void calculateSomeTables() {
        setPayPeriodStart(calculatePayPeriodStart());
        setPayPeriodEnd(calculatePayPeriodEnd());
        setPayRate(calculatePayRate());
        setProcessedOn(calculateProcessedOnDate());
        setAmountPaid(calculateAmountPaid());
    }

    /*@PrePersist
    public void calculatePayPeriods() {
        setPayPeriodStart(calculatePayPeriodStart());
        setPayPeriodEnd(calculatePayPeriodEnd());
    }

    @PrePersist
    public void determinePayRate() {
        setPayRate(calculatePayRate());
    }

    @PrePersist
    public void determineProcessOnDate() {
        setProcessedOn(calculateProcessedOnDate());
    }*/

    @Override
    public String toString() {
        return "EmployeePay{" +
                "date=" + date +
                ", employeeID=" + employeeID +
                ", reportID=" + reportID +
                ", jobGroup='" + jobGroup + '\'' +
                ", hoursWorked=" + hoursWorked +
                ", payRate=" + payRate +
                ", amountPaid=" + amountPaid +
                ", payPeriodStart=" + payPeriodStart +
                ", payPeriodEnd=" + payPeriodEnd +
                ", processedOn=" + processedOn +
                '}';
    }
}

interface MetricsWrapper {
    Long getReportID();
    Integer getTotalEmployees();
    Float getTotalAmountPaid();
}

interface MetaDataWrapper {
    Long getReportID();
    Integer getTotalEmployees();
    Float getTotalPaid();
    LocalDate getProcessedOn();
}

interface ReportWrapper {
    //SELECT employeeID, pay_Period_Start as payPeriodStart, pay_Period_End as payPeriodEnd, SUM(hours_worked)*pay_rate AS amountPaid, reportID
    Long getEmployeeID();
    Long getReportID();
    Float getAmountPaid();
    LocalDate getPayPeriodStart();
    LocalDate getPayPeriodEnd();
}

class DeployData implements Serializable {
    //SELECT employeeID, pay_Period_Start as payPeriodStart, pay_Period_End as payPeriodEnd, SUM(hours_worked)*pay_rate AS amountPaid, reportID
    LocalDate date;
    Long employeeID;
    Float hoursWorked;
    String jobGroup;
    Long reportID;

    public DeployData() {
    }

    public DeployData(LocalDate date, Long employeeID, Float hoursWorked, String jobGroup, Long reportID) {
        this.date = date;
        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
        this.jobGroup = jobGroup;
        this.reportID = reportID;
    }

    public DeployData(LocalDate date, Long employeeID, Float hoursWorked, String jobGroup) {
        this.date = date;
        this.employeeID = employeeID;
        this.hoursWorked = hoursWorked;
        this.jobGroup = jobGroup;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public Float getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(Float hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public Long getReportID() {
        return reportID;
    }

    public void setReportID(Long reportID) {
        this.reportID = reportID;
    }

    @Override
    public String toString() {
        return "DeployData{" +
                "date=" + date +
                ", employeeID=" + employeeID +
                ", hoursWorked=" + hoursWorked +
                ", jobGroup='" + jobGroup + '\'' +
                ", reportID=" + reportID +
                '}';
    }

}


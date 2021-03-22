package com.wave.payroll.EmployeePay;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeePayRepository extends JpaRepository<EmployeePay, Long> {

    @Query(value = "SELECT reportID, COUNT(employeeID) as totalEmployees, SUM(amount_paid) AS totalAmountPaid FROM employee_pay group by reportID", nativeQuery = true)
    List<MetricsWrapper> allReports();

    //SELECT reportID, COUNT(employeeID) as totalEmployees, SUM(amountPaid) AS totalAmountPaid, processedOn FROM employeepay group by reportID
    @Query(value = "SELECT reportID, COUNT(employeeID) as totalEmployees, SUM(amount_paid) AS totalPaid, processed_On as processedOn FROM employee_pay group by reportID", nativeQuery = true)
    List<MetaDataWrapper> generateMetaData();

    //SELECT employeeID, payPeriodStart, payPeriodEnd, SUM(amountPaid) AS amountPaid, reportID FROM employeepay where reportID=? group by employeeID, payPeriodStart, payPeriodEnd, reportID
    @Query(value = "SELECT employeeID, pay_Period_Start as payPeriodStart, pay_Period_End as payPeriodEnd, SUM(amount_paid) AS amountPaid, reportID FROM employee_pay where reportID=?1 group by employeeID, payPeriodStart, payPeriodEnd, reportID", nativeQuery = true)
    List<ReportWrapper> generateReport(String reportID);

    //@Query(value = "SELECT * FROM USERS WHERE EMAIL_ADDRESS = ?1", nativeQuery = true)
    //

}

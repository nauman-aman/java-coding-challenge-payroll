package com.wave.payroll.EmployeePay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class PayrollController {

    private final PayrollService payrollService;

    @Autowired
    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @GetMapping(path = "/metrics2")
    public List<EmployeePay> getPayroll(){
        return payrollService.getPayroll();
    }

    @GetMapping(path = "/metrics")
    public Map<String, Object> getMetrics(@RequestParam String reportID){

        System.out.println("====metrics=====");
        return payrollService.metricsService();
    }

    @GetMapping(path = "generate/metaData")
    public Map<String, Object> generateMetaData(){

        System.out.println("====metaData=====");
        return payrollService.generateMetaDataService();
    }

    @GetMapping(path = "generate")
    public Map<String, Object> generateReport(@RequestParam String reportID){

        System.out.println("====generateReport=====");
        return payrollService.generateReportService(reportID);
    }

    @PostMapping(path = "generate")
    public Map<String, Object> generateeReport(@RequestParam String reportID){

        System.out.println("====generateReport=====");
        return payrollService.generateReportService(reportID);
    }

    @PostMapping(path = "deploy")
    public Map<String, Object> deployPayrollFileData(@RequestParam(value = "reportID") String reportID,
                                      @RequestBody HashMap<String, Object> payrollData){
        Map<String, Object> results;
        System.out.println("====deployPayrollFile=====");
        results = payrollService.deployFileData(reportID, payrollData);
        System.out.println("====Finished deployPayrollFile=====");
        return results;
    }

    // metrics
    // amountPaidToDate, reportID[reportID: Report ID # 1 - TotalEmployees, totalPaid: row.totalAmountPaid]
    // reportID, COUNT(employeeID) as totalEmployees, SUM(amountPaid) AS totalAmountPaid FROM employeepay group by reportID', function(err, results) {
    // generate/metaData
    // reportID, totalPaid, totalEmployees, processedOn

}

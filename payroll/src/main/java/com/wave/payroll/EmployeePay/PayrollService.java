package com.wave.payroll.EmployeePay;

import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class PayrollService {

private final EmployeePayRepository employeePayRepository;

    @Autowired
    public PayrollService(EmployeePayRepository employeePayRepository) {
        this.employeePayRepository = employeePayRepository;
    }

    public List<EmployeePay> getPayroll() {
        return employeePayRepository.findAll();
    }

    public Map<String, Object> metricsService()
    {
        List<MetricsWrapper> repoResult = employeePayRepository.allReports();
        Map<String, Object> test = new HashMap<>();
        Map<String, Object> allMetrics = new HashMap<>();
        ArrayList<Object> reports = new ArrayList<>();
        Map<String, Object> singleReport = new HashMap<>();
        Float totalAmountPaid = 0F;
        System.out.println(repoResult);


        //"Report ID # "+row.reportID+" - "+row.totalEmployees+" total employees"

        System.out.println("===Starting itr===");
        /*Iterator<MetricsWrapper> itr = repoResult.iterator();
        while (itr.hasNext()) {
            itr.getReportID();
            System.out.println(itr.next());
        }*/
        for (int i = 0; i < repoResult.size(); i++) {
            singleReport.clear();

            singleReport.put("reportID", "Report ID # "+repoResult.get(i).getReportID()
                    +" - "+repoResult.get(i).getTotalEmployees()
                    +" total employees");
            singleReport.put("totalPaid", repoResult.get(i).getTotalAmountPaid());

            reports.add(singleReport);

            totalAmountPaid = totalAmountPaid+repoResult.get(i).getTotalAmountPaid();

            System.out.println(repoResult.get(i));
        }
        System.out.println("===Finished itr===");

        allMetrics.put("reports", reports);
        allMetrics.put("amountPaidToDate", totalAmountPaid);
        test.put("metrics", allMetrics);
        test.put("flag", true);
        return test;

        // amountPaidToDate, reportID[reportID: Report ID # 1 - TotalEmployees, totalPaid: row.totalAmountPaid]
        /*Map<String, Object> metrics = new HashMap<>();
        Map<String, Object> singleReport = new HashMap<>();
        metrics.put("amountPaidToDate", "0");

        //reportID: Report ID # 1 - TotalEmployees, totalPaid: row.totalAmountPaid
        singleReport.put("reportID", "Report ID # "+ 43 +" - " +5+" total employees");
        singleReport.put("totalPaid", 5000);

        // a loop
        reports.add(singleReport);

        metrics.put("flag", true);

        return metrics;*/
    }

    public Map<String, Object> generateMetaDataService()
    {
        List<MetaDataWrapper> repoResult = employeePayRepository.generateMetaData();
        Map<String, Object> test = new HashMap<>();
        System.out.println(repoResult);

        System.out.println("===Starting itr===");
        Iterator<MetaDataWrapper> itr = repoResult.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println("===Finished itr===");
        test.put("reports", repoResult);
        test.put("flag", true);
        return test;

        /*// reportID, totalPaid, totalEmployees, processedOn
        Map<String, Object> report = new HashMap<>();
        Map<String, Object> singleReport = new HashMap<>();
        ArrayList<Object> allReports = new ArrayList<>();

        // a loop
        singleReport.put("reportID", "43");
        singleReport.put("totalPaid", "5000");
        singleReport.put("totalEmployees", "5");
        singleReport.put("processedOn", "2021/01/01");

        allReports.add(singleReport);

        report.put("flag", true);
        report.put("reports", allReports);

        return report;*/
    }

    public Map<String, Object> generateReportService(String reportID)
    {
        List<ReportWrapper> repoResult = employeePayRepository.generateReport(reportID);
        Map<String, Object> test = new HashMap<>();
        System.out.println(repoResult);

        System.out.println("===Starting itr===");
        Iterator<ReportWrapper> itr = repoResult.iterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
        System.out.println("===Finished itr===");
        test.put("sql", repoResult);
        test.put("flag", true);
        return test;
    }

    public Map<String, Object> deployFileData(String reportID, HashMap<String, Object> payrollData) {
        Map<String, Object> report = new HashMap<>();
        HashMap<String, Object> hashMap;
        ArrayList<Object> data = new ArrayList<>();
        ArrayList<EmployeePay> employeePayArrayList = new ArrayList<>();
        EmployeePay employeePay;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        data = (ArrayList<Object>) payrollData.get("data");
        for (int x = 0; x < data.size(); x++) {
            hashMap = (HashMap<String, Object>) data.get(x);

            if (!hashMap.containsKey("job group"))
            {
                x = data.size();
                System.out.println("==Should exit loop==");
            }
            else if (!hashMap.get("date").equals("report id")) {

                employeePay = new EmployeePay(
                        LocalDate.parse(hashMap.get("date").toString(), formatter),
                        Long.parseLong(hashMap.get("employee id").toString()),
                        Long.parseLong(reportID),
                        (String) hashMap.get("job group"),
                        Float.parseFloat(hashMap.get("hours worked").toString())
                );
                employeePayArrayList.add(employeePay);

                System.out.println(hashMap.get("date").toString());
                System.out.println(hashMap.get("employee id").toString());
                System.out.println(hashMap.get("hours worked").toString());
                System.out.println(hashMap.get("job group").toString());
            }

            System.out.println("==== x "+x+": =====");
        }

        employeePayRepository.saveAll(employeePayArrayList);

        report.put("flag", true);
        report.put("message", "Successful");
        return report;

    }
}

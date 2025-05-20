package com.sarkhan.dtssystem.service.impl;

import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import com.sarkhan.dtssystem.mapper.CompanyMapper;
import com.sarkhan.dtssystem.model.company.Company;
import com.sarkhan.dtssystem.model.company.data.*;
import com.sarkhan.dtssystem.repository.company.CompanyRepository;
import com.sarkhan.dtssystem.service.CompanyService;
import com.sarkhan.dtssystem.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final FileStorageService fileStorageService;
    @Value("${base-url}")
    private String baseUrl;

    @Override
    public Company addCompany(CompanyRequest companyRequest, MultipartFile financialStatement, MultipartFile registerCertificate, MultipartFile propertyLawCertificate) throws IOException {
        Company company = companyMapper.toEntity(companyRequest);
        company.setCreatedDate(LocalDateTime.now());
        company.setFinancialNeeding(companyRequest.getFinancialNeeding());
        company.setPropertyLaw(companyRequest.getPropertyLaw());
        CompanyFiles companyFiles = new CompanyFiles();
        companyFiles.setRegisterCertificate(fileStorageService.saveFile(registerCertificate));
        companyFiles.setFinancialStatement(fileStorageService.saveFile(financialStatement));
        companyFiles.setPropertyLawCertificate(fileStorageService.saveFile(propertyLawCertificate));
        company.setCompanyFiles(companyFiles);
        return companyRepository.save(company);
    }

    @Override
    public byte[] exportCompaniesToExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Companies");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setFont(headerFont);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headerCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);

        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setWrapText(true);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);

        String[] columns = {
                "ID", "Company Name", "Register Number", "Create Year", "Address",
                "City & Region", "Website", "Contact Name", "Contact Email", "Contact Phone",
                "Worker Count", "Annual Turnover",
                "Data Is Real", "Permit Contact",
                "Digital Team/Lead", "Digital Path", "Digital Transformation Loyalty",
                "Digital Level", "Digital Tools", "Key Challenges", "Company Purpose",
                "Financial Need", "Needed Budget",
                "Business Operations", "Company Law Type", "Products",
                "Export Activity", "Export Bazaar",
                "Register Certificate", "Financial Statement", "Property Law Certificate",
                "Created Date"
        };

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        List<Company> companies = companyRepository.findAll();
        int rowNum = 1;
        for (Company company : companies) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;

            CompanyData data = company.getCompanyData();
            DeclarationConsent consent = company.getDeclarationConsent();
            DigitalLeadership leadership = company.getDigitalLeadership();
            DigitalReadiness readiness = company.getDigitalReadiness();
            FinancialNeeding need = company.getFinancialNeeding();
            PropertyLaw law = company.getPropertyLaw();
            CompanyFiles files = company.getCompanyFiles();

            row.createCell(colNum++).setCellValue(company.getId());
            row.createCell(colNum++).setCellValue(data.getCompanyName());
            row.createCell(colNum++).setCellValue(data.getCompanyRegisterNumber());
            row.createCell(colNum++).setCellValue(data.getCreateYear());
            row.createCell(colNum++).setCellValue(data.getAddress());
            row.createCell(colNum++).setCellValue(data.getCityAndRegion());
            row.createCell(colNum++).setCellValue(data.getWebsite());
            row.createCell(colNum++).setCellValue(data.getContactName());
            row.createCell(colNum++).setCellValue(data.getContactEmail());
            row.createCell(colNum++).setCellValue(data.getContactPhone());
            row.createCell(colNum++).setCellValue(data.getWorkerCount());
            row.createCell(colNum++).setCellValue(data.getAnnualTurnover() + " AZN");

            row.createCell(colNum++).setCellValue(consent.isDataIsReal());
            row.createCell(colNum++).setCellValue(consent.isPermitContact());

            row.createCell(colNum++).setCellValue(leadership.isDigitalTeamOrLead());
            row.createCell(colNum++).setCellValue(leadership.isDigitalPath());
            row.createCell(colNum++).setCellValue(leadership.isDigitalTransformationLoyality());

            row.createCell(colNum++).setCellValue(readiness.getDigitalLevel());
            row.createCell(colNum++).setCellValue(String.join(", ", readiness.getDigitalTools()));
            row.createCell(colNum++).setCellValue(String.join(", ", readiness.getKeyChallenges()));
            row.createCell(colNum++).setCellValue(readiness.getCompanyPurpose());

            row.createCell(colNum++).setCellValue(need.getFinancialNeed());

            row.createCell(colNum++).setCellValue(need.getNeededBudget() + " AZN");

            row.createCell(colNum++).setCellValue(law.getBusinessOperations());
            row.createCell(colNum++).setCellValue(law.getCompanyLawType());
            row.createCell(colNum++).setCellValue(law.getProducts());
            row.createCell(colNum++).setCellValue(law.getExportActivity());
            String exportBazaarText = String.join(", ", law.getExportBazaar());
            row.createCell(colNum++).setCellValue(exportBazaarText);
            String baseDownloadUrl = baseUrl + "/files/download/";
            CreationHelper createHelper = workbook.getCreationHelper();

// Register Certificate
            Cell cell1 = row.createCell(colNum++);
            cell1.setCellValue("Download");
            Hyperlink hyperlink1 = createHelper.createHyperlink(HyperlinkType.URL);
            hyperlink1.setAddress(baseDownloadUrl + files.getRegisterCertificate());
            cell1.setHyperlink(hyperlink1);

// Financial Statement
            Cell cell2 = row.createCell(colNum++);
            cell2.setCellValue("Download");
            Hyperlink hyperlink2 = createHelper.createHyperlink(HyperlinkType.URL);
            hyperlink2.setAddress(baseDownloadUrl + files.getFinancialStatement());
            cell2.setHyperlink(hyperlink2);

// Property Law Certificate
            Cell cell3 = row.createCell(colNum++);
            cell3.setCellValue("Download");
            Hyperlink hyperlink3 = createHelper.createHyperlink(HyperlinkType.URL);
            hyperlink3.setAddress(baseDownloadUrl + files.getPropertyLawCertificate());
            cell3.setHyperlink(hyperlink3);


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
            String formattedDate = company.getCreatedDate().format(formatter);

            row.createCell(colNum++).setCellValue(formattedDate);

            for (int i = 0; i < columns.length; i++) {
                row.getCell(i).setCellStyle(dataCellStyle);
            }
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }

}

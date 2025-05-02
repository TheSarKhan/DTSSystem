package com.sarkhan.dtssystem.service.impl;

import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import com.sarkhan.dtssystem.mapper.CompanyMapper;
import com.sarkhan.dtssystem.model.company.Company;
import com.sarkhan.dtssystem.model.company.data.CompanyFiles;
import com.sarkhan.dtssystem.repository.company.CompanyRepository;
import com.sarkhan.dtssystem.service.CloudinaryService;
import com.sarkhan.dtssystem.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CloudinaryService cloudinaryService;

    @Override
    public Company addCompany(CompanyRequest companyRequest, MultipartFile digitalTransformationPlans, MultipartFile financialStatement, MultipartFile registerCertificate) throws IOException {
        Company company = companyMapper.toEntity(companyRequest);
        company.setCreatedDate(LocalDateTime.now());
        company.setFinancialNeeding(companyRequest.getFinancialNeeding());
        CompanyFiles companyFiles = new CompanyFiles();
        companyFiles.setRegisterCertificate(cloudinaryService.uploadFile(registerCertificate, "Register Sertificates"));
        companyFiles.setFinancialStatement(cloudinaryService.uploadFile(financialStatement, "Financial Statements"));
        companyFiles.setDigitalTransformationPlans(cloudinaryService.uploadFile(digitalTransformationPlans, "Digital Transformation Plans"));
        company.setCompanyFiles(companyFiles);
        return companyRepository.save(company);
    }

    @Override
    public byte[] exportCompaniesToExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Companies");

        // 🔹 Header Font & Style
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

        // 🔹 Data Style
        CellStyle dataCellStyle = workbook.createCellStyle();
        dataCellStyle.setWrapText(true);
        dataCellStyle.setBorderBottom(BorderStyle.THIN);
        dataCellStyle.setBorderTop(BorderStyle.THIN);
        dataCellStyle.setBorderRight(BorderStyle.THIN);
        dataCellStyle.setBorderLeft(BorderStyle.THIN);

        // 🔹 Başlıklar
        String[] columns = {
                "ID", "Company Name", "Register Number", "Create Year", "Address",
                "City & Region", "Website", "Contact Name", "Contact Email", "Contact Phone",
                "Worker Count", "Annual Turnover",
                "Data Is Real", "Permit Contact",
                "Digital Team/Lead", "Digital Path", "Digital Transformation Loyalty",
                "Digital Level", "Digital Tools", "Key Challenges", "Company Purpose",
                "Financial Need", "Needed Budget",
                "Sector", "Products", "Export Activity", "Export Bazaar",
                "Company Type", "Ownership Percentage", "Company Owners"
        };

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // 🔹 Verileri yaz
        List<Company> companies = companyRepository.findAll();
        int rowNum = 1;
        for (Company company : companies) {
            Row row = sheet.createRow(rowNum++);

            int colNum = 0;
            row.createCell(colNum++).setCellValue(company.getId());
            row.createCell(colNum++).setCellValue(company.getCompanyData().getCompanyName());
            row.createCell(colNum++).setCellValue(company.getCompanyData().getCompanyRegisterNumber());
            row.createCell(colNum++).setCellValue(company.getCompanyData().getCreateYear());
            row.createCell(colNum++).setCellValue(company.getCompanyData().getAddress());
            row.createCell(colNum++).setCellValue(company.getCompanyData().getCityAndRegion());
            row.createCell(colNum++).setCellValue(company.getCompanyData().getWebsite());
            row.createCell(colNum++).setCellValue(company.getCompanyData().getContactName());
            row.createCell(colNum++).setCellValue(company.getCompanyData().getContactEmail());
            row.createCell(colNum++).setCellValue(company.getCompanyData().getContactPhone());

            row.createCell(colNum++).setCellValue(company.getCompanySize().getWorkerCount());
            row.createCell(colNum++).setCellValue(company.getCompanySize().getAnnualTurnover());

            row.createCell(colNum++).setCellValue(company.getDeclarationConsent().isDataIsReal());
            row.createCell(colNum++).setCellValue(company.getDeclarationConsent().isPermitContact());

            row.createCell(colNum++).setCellValue(company.getDigitalLeadership().isDigitalTeamOrLead());
            row.createCell(colNum++).setCellValue(company.getDigitalLeadership().isDigitalPath());
            row.createCell(colNum++).setCellValue(company.getDigitalLeadership().isDigitalTransformationLoyality());

            row.createCell(colNum++).setCellValue(company.getDigitalReadiness().getDigitalLevel());
            row.createCell(colNum++).setCellValue(company.getDigitalReadiness().getDigitalTools());

            // KeyChallenges -> Array olduğu için birleştiriyoruz
            String keyChallenges = String.join(", ", company.getDigitalReadiness().getKeyChallenges());
            row.createCell(colNum++).setCellValue(keyChallenges);

            row.createCell(colNum++).setCellValue(company.getDigitalReadiness().getCompanyPurpose());

            row.createCell(colNum++).setCellValue(company.getFinancialNeeding().isFinancialNeed());
            row.createCell(colNum++).setCellValue(company.getFinancialNeeding().getNeededBudget());

            row.createCell(colNum++).setCellValue(company.getIndustrialBusinessOperations().getSector());
            row.createCell(colNum++).setCellValue(company.getIndustrialBusinessOperations().getProducts());
            row.createCell(colNum++).setCellValue(company.getIndustrialBusinessOperations().isExportActivity());
            row.createCell(colNum++).setCellValue(company.getIndustrialBusinessOperations().getExportBazaar());

            row.createCell(colNum++).setCellValue(company.getOwnershipLegal().getCompanyType());
            row.createCell(colNum++).setCellValue(company.getOwnershipLegal().getPercentage());
            row.createCell(colNum++).setCellValue(company.getOwnershipLegal().getCompanyOwners());

            // Her hücreye stilleri uygula
            for (int i = 0; i < columns.length; i++) {
                row.getCell(i).setCellStyle(dataCellStyle);
            }
        }

        // 🔹 Kolon genişlikleri
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        return outputStream.toByteArray();
    }
}

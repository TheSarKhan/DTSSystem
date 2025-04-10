package com.sarkhan.dtssystem.service.impl;

import com.sarkhan.dtssystem.dto.request.CompanyRequest;
import com.sarkhan.dtssystem.mapper.CompanyMapper;
import com.sarkhan.dtssystem.model.company.Company;
import com.sarkhan.dtssystem.repository.company.CompanyRepository;
import com.sarkhan.dtssystem.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Company addCompany(CompanyRequest companyRequest) {
        Company company = companyMapper.toEntity(companyRequest);
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
                "ID", "Company Name", "Register Number", "Create Year","Address",
                "City & Region", "Website", "Contact Name", "Contact Email", "Contact Phone"
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
            row.createCell(0).setCellValue(company.getId());
            row.createCell(1).setCellValue(company.getCompanyName());
            row.createCell(2).setCellValue(company.getCompanyRegisterNumber());
            row.createCell(3).setCellValue(company.getCreateYear());
            row.createCell(4).setCellValue(company.getAddress());
            row.createCell(5).setCellValue(company.getCityAndRegion());
            row.createCell(6).setCellValue(company.getWebsite());
            row.createCell(7).setCellValue(company.getContactName());
            row.createCell(8).setCellValue(company.getContactEmail());
            row.createCell(9).setCellValue(company.getContactPhone());

            for (int i = 0; i < 10; i++) {
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

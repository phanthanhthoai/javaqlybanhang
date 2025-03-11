///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Util;
//
///**
// *
// * @author Admin
// */
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.*;
//
//public class InvoiceExporter {
//
//    private JTable table;
//    private JButton btnExport;
//
//    public InvoiceExporter(JTable table, JButton btnExport) {
//        this.table = table;
//        this.btnExport = btnExport;
//        btnExport.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                exportToPDF("invoice.pdf");
//            }
//        });
//    }
//
//    private void exportToPDF(String fileName) {
//        Document document = new Document();
//        try {
//            PdfWriter.getInstance(document, new FileOutputStream(fileName));
//            document.open();
//
//            // Tiêu đề hóa đơn
//            document.add(new Paragraph("HÓA ĐƠN BÁN HÀNG", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18)));
//            document.add(new Paragraph("\n"));
//
//            // Tạo bảng PDF
//            PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
//            for (int i = 0; i < table.getColumnCount(); i++) {
//                pdfTable.addCell(new PdfPCell(new Phrase(table.getColumnName(i))));
//            }
//            for (int rows = 0; rows < table.getRowCount(); rows++) {
//                for (int cols = 0; cols < table.getColumnCount(); cols++) {
//                    pdfTable.addCell(table.getValueAt(rows, cols).toString());
//                }
//            }
//            document.add(pdfTable);
//
//            // Tính tổng tiền
//            double total = 0;
//            for (int i = 0; i < table.getRowCount(); i++) {
//                total += Double.parseDouble(table.getValueAt(i, table.getColumnCount() - 1).toString());
//            }
//            document.add(new Paragraph("\nTổng tiền: " + total + " VND", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14)));
//            document.add(new Paragraph("\n"));
//
//            document.close();
//            JOptionPane.showMessageDialog(null, "Hóa đơn đã được xuất ra PDF!");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}

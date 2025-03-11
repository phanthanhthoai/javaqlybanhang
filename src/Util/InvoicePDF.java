package Util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InvoicePDF {

    public static void generateInvoice(String filePath, JTable jtbCthd, String maHoaDon, String ngayTao, String nguoiTao, String tongTien, int columnToRemove) {
        try {
            // Đặt kích thước trang là A5 (nửa tờ A4) và căn lề nhỏ hơn
            Document document = new Document(PageSize.A5, 20, 20, 20, 20);
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Load font hỗ trợ Tiếng Việt
            BaseFont bf = BaseFont.createFont("C:/Windows/Fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font fontTitle = new Font(bf, 14, Font.BOLD);
            Font fontNormal = new Font(bf, 10, Font.NORMAL);
            Font fontBold = new Font(bf, 10, Font.BOLD);

            // Tiêu đề hóa đơn
            Paragraph title = new Paragraph("HÓA ĐƠN THANH TOÁN", fontTitle);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);

            // Thông tin hóa đơn
            document.add(new Paragraph("Mã hóa đơn: " + maHoaDon, fontNormal));
            document.add(new Paragraph("Ngày tạo: " + ngayTao, fontNormal));
            document.add(new Paragraph("Người tạo: " + nguoiTao, fontNormal));
            document.add(new Paragraph("\nDanh sách sản phẩm:", fontBold));

            // Lấy số lượng cột và loại bỏ cột cần xóa
            int columnCount = jtbCthd.getColumnCount();
            int newColumnCount = columnCount - 1; // Vì loại bỏ 1 cột
            PdfPTable table = new PdfPTable(newColumnCount);
            table.setWidthPercentage(100);
            table.setSpacingBefore(5f);
            table.setSpacingAfter(5f);

            // Thêm tiêu đề cột (bỏ qua cột cần loại bỏ)
            for (int i = 0; i < columnCount; i++) {
                if (i == columnToRemove) {
                    continue; // Bỏ qua cột cần loại bỏ
                }
                PdfPCell cell = new PdfPCell(new Phrase(jtbCthd.getColumnName(i), fontBold));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                table.addCell(cell);
            }

            // Thêm dữ liệu từ JTable (bỏ qua cột cần loại bỏ)
            DefaultTableModel model = (DefaultTableModel) jtbCthd.getModel();
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < columnCount; j++) {
                    if (j == columnToRemove) {
                        continue; // Bỏ qua cột cần loại bỏ
                    }
                    PdfPCell cell = new PdfPCell(new Phrase(model.getValueAt(i, j).toString(), fontNormal));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);
                }
            }

            document.add(table);

            // Tổng tiền
            Paragraph total = new Paragraph("Tổng tiền: " + tongTien + " VND", fontBold);
            total.setAlignment(Element.ALIGN_RIGHT);
            total.setSpacingBefore(10);
            document.add(total);

            document.close();
            JOptionPane.showMessageDialog(null, "Hóa đơn đã được tạo thành công!");

        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi tạo hóa đơn!");
        }
    }

    public static void openPDF(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Desktop.getDesktop().open(file);
            } else {
                JOptionPane.showMessageDialog(null, "File không tồn tại.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

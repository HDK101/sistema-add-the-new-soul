package br.edu.ifsp.addthenewsoul.domain.usecases.report;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;

public class PDFReportWriter {
    protected int currentUsedHeight;
    protected PDDocument document;
    protected PDPage page;
    protected PDPageContentStream contentStream;
    protected int currentFontSize = 16;
    protected int currentLeading = 16;
    protected final int MARGIN = 20;
    protected final int PLUS_TOP_MARGIN = 40;

    protected int currentHeight = 0;
    protected int currentWidth = 0;

    private String filename;

    protected PDFReportWriter() {
        try {
            this.document = new PDDocument();
            this.page = new PDPage();
            this.contentStream = new PDPageContentStream(document, page);
            this.contentStream.setStrokingColor(0f, 0f, 0f);

            this.currentHeight = (int) page.getTrimBox().getHeight();
            this.currentWidth = (int) page.getTrimBox().getWidth();

            currentUsedHeight = currentHeight - MARGIN - PLUS_TOP_MARGIN;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    protected PDFReportWriter setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    private void createPageIfNecessary() throws IOException {
        if (currentUsedHeight < 200) {
            contentStream.endText();

            contentStream.stroke();
            contentStream.close();

            document.addPage(page);

            page = new PDPage();
            contentStream = new PDPageContentStream(document, page);

            setFontSize(currentFontSize);
            setLeading(currentLeading);

            currentUsedHeight = currentHeight - MARGIN - PLUS_TOP_MARGIN;

            contentStream.beginText();
            contentStream.newLineAtOffset(MARGIN, currentHeight - (MARGIN + PLUS_TOP_MARGIN));
        }
    }

    protected PDFReportWriter contentStart() throws IOException {
        contentStream.beginText();
        contentStream.newLineAtOffset(MARGIN, currentHeight - (MARGIN + PLUS_TOP_MARGIN));
        return this;
    }

    protected PDFReportWriter contentEnd() throws IOException {
        contentStream.endText();
        contentStream.stroke();
        contentStream.close();
        document.addPage(page);
        return this;
    }

    protected PDFReportWriter save() throws IOException {
        document.save(filename);
        return this;
    }

    protected PDFReportWriter addText(String text) throws IOException {
        contentStream.showText(text);

        this.currentUsedHeight -= currentLeading;

        this.createPageIfNecessary();
        return this.addNewLine();
    }

    protected PDFReportWriter addNewLine() throws IOException {
        contentStream.newLine();
        return this;
    }

    protected PDFReportWriter setFontSize(int size) throws IOException {
        this.contentStream.setFont(PDType1Font.TIMES_ROMAN, size);
        this.currentFontSize = size;
        return this;
    }

    protected PDFReportWriter setLeading(int leading) throws IOException {
        currentLeading = leading;
        this.contentStream.setLeading(leading);
        return this;
    }
}

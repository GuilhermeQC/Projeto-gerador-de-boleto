package org.example.pdf;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import org.example.boleto.boleto;

import java.io.IOException;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class geradorPDF {

    public void gerar(String caminho, boleto boleto) throws IOException {
        PdfWriter writer = new PdfWriter(caminho);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        DateTimeFormatter fmtData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NumberFormat fmtMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        // Título
        Paragraph titulo = new Paragraph("BOLETO BANCÁRIO")
                .setBold()
                .setFontSize(16);
        doc.add(titulo);

        // Tabela com 2 colunas simples
        Table tabela = new Table(2);

        tabela.addCell(celula("Banco:", true));
        tabela.addCell(celula(boleto.getBanco(), false));

        tabela.addCell(celula("Beneficiário:", true));
        tabela.addCell(celula(boleto.getBeneficiario().getNomeRazao(), false));

        tabela.addCell(celula("Documento Beneficiário:", true));
        tabela.addCell(celula(boleto.getBeneficiario().getCpfCnpj(), false));

        tabela.addCell(celula("Sacado:", true));
        tabela.addCell(celula(boleto.getSacado().getNomeRazao(), false));

        tabela.addCell(celula("Documento Sacado:", true));
        tabela.addCell(celula(boleto.getSacado().getCpfCnpj(), false));

        tabela.addCell(celula("Vencimento:", true));
        tabela.addCell(celula(boleto.getDados().getVencimento().format(fmtData), false));

        tabela.addCell(celula("Valor:", true));
        tabela.addCell(celula(fmtMoeda.format(boleto.getDados().getValor()), false));

        tabela.addCell(celula("Conta / Agência:", true));
        tabela.addCell(celula(
                boleto.getConta().getNumeroConta() + " / " +
                        boleto.getConta().getAgencia() + " / " +
                        boleto.getConta().getCarteira(), false));


        doc.add(tabela);

        // Linha Digitável
        doc.add(new Paragraph("\nLinha Digitável:")
                .setBold()
                .setFontSize(12));

        doc.add(new Paragraph(boleto.getLinhaDigitavel())
                .setFontSize(10));

        // Código de Barras
        doc.add(new Paragraph("\nCódigo de Barras:")
                .setBold()
                .setFontSize(12));

        doc.add(new Paragraph(boleto.getBarras())
                .setFontSize(10));

        doc.close();
    }

    private Cell celula(String texto, boolean negrito) {
        Paragraph p = new Paragraph(texto);
        if (negrito) p.setBold();
        return new Cell().add(p).setPadding(4f);
    }
}

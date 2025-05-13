package org.example; // Ajuste o pacote conforme necessário

import org.example.boleto.boleto;
import org.example.boleto.dadosBoleto;
import org.example.boleto.dadosConta;
import org.example.boleto.pessoa.pessoaFisicaJuridica;

import org.example.builders.BB_Builder;
import org.example.builders.BoletoBuilder;
import org.example.codBarras.codBarras;
import org.example.pdf.geradorPDF;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) {


        // Beneficiário
        pessoaFisicaJuridica beneficiario = new pessoaFisicaJuridica();
        beneficiario.setNomeRazao("Guido Industries TLDA");
        beneficiario.setCpfCnpj("11.222.333/0001-44");
        beneficiario.setEndereco("Rua da Vangança, 123");

        // Sacado
        pessoaFisicaJuridica sacado = new pessoaFisicaJuridica();
        sacado.setNomeRazao("Eleanor Rigby");
        sacado.setCpfCnpj("444.555.666-77");
        sacado.setEndereco("Rua do além, 456, Apto 789");

        // Dados da Conta
        dadosConta contaBeneficiario = new dadosConta();
        contaBeneficiario.setAgencia("1234");
        contaBeneficiario.setNumeroConta("567890");
        contaBeneficiario.setCarteira("18"); //

        // Dados do Boleto
        String numeroDocumentoOuNossoNumero = "0000000001";
        LocalDate dataVencimento = LocalDate.now().plusDays(15); //Data de vencimento
        double valorBoleto = 430; // Valor do boleto

        dadosBoleto detalhesBoleto = new dadosBoleto();
        detalhesBoleto.setNumBoleto(numeroDocumentoOuNossoNumero);
        detalhesBoleto.setVencimento(dataVencimento);
        detalhesBoleto.setValor(valorBoleto);


        BoletoBuilder builder = new BB_Builder();

        builder.buildBeneficiario(beneficiario);
        builder.buildSacado(sacado);

        builder.buildDadosBoleto(detalhesBoleto.getNumBoleto(), detalhesBoleto.getVencimento(), detalhesBoleto.getValor());
        builder.buildDadosConta(contaBeneficiario);
        builder.buildBanco();

        boleto meuBoleto = builder.getBoleto();

        String codigoDeBarras = codBarras.gerarCodigoBarras(meuBoleto, builder);

        // --- 5. Exibir as informações do Boleto Gerado ---
        System.out.println("--- Detalhes do Boleto Gerado ---");
        System.out.println("Banco: " + meuBoleto.getBanco());
        System.out.println("Beneficiário: " + meuBoleto.getBeneficiario().getNomeRazao());
        System.out.println("Sacado: " + meuBoleto.getSacado().getNomeRazao());

        System.out.println("Agência: " + meuBoleto.getConta().getAgencia());
        System.out.println("Conta: " + meuBoleto.getConta().getNumeroConta());
        System.out.println("Carteira: " + meuBoleto.getConta().getCarteira());
        System.out.println("Número do Boleto (em dadosBoleto): " + meuBoleto.getDados().getNumBoleto());
        System.out.println("Valor: " + String.format("%.2f", meuBoleto.getDados().getValor()));
        System.out.println("Vencimento: " + meuBoleto.getDados().getVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Número Único (gerado na classe Boleto, usado no campo livre): " + meuBoleto.getNumUnico());

        System.out.println("\nCódigo de Barras Gerado: " + codigoDeBarras);

        // Gerando o PDF com os dados do boleto
        geradorPDF generator = new geradorPDF();
        try {
            generator.gerar("boleto.pdf", meuBoleto);
            System.out.println("Boleto gerado com sucesso em: boletos/boleto.pdf");
        } catch (Exception e) {
            System.err.println("Erro ao gerar o boleto: " + e.getMessage());
        }



    }
}
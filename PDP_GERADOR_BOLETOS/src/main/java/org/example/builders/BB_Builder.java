package org.example.builders;

import org.example.boleto.pessoa.*;
import org.example.boleto.*;

import java.time.LocalDate;

public class BB_Builder implements BoletoBuilder{

    private boleto boleto;

    public BB_Builder(){
        this.boleto = new boleto();
    }

    public void buildBeneficiario(pessoaFisicaJuridica beneficiario){
        boleto.setBeneficiario(beneficiario);
    }

    public void buildSacado(pessoaFisicaJuridica sacado){
        boleto.setSacado(sacado);
    }

    public void buildDadosBoleto(String numBoleto, LocalDate vencimento, double valor){
        boleto.getDados().setNumBoleto(numBoleto);
        boleto.getDados().setVencimento(vencimento);
        boleto.getDados().setValor(valor);
    }
    public void buildDadosConta(dadosConta conta){
        boleto.setConta(conta);

    }

    public void buildBanco() {
        boleto.setBanco("001");
    }

    public boleto getBoleto() {
        return boleto;
    }

    public String novoCampoLivre(String agencia, String conta, String nossoNumero, String carteira) {
        return String.format("%04d%08d%011d%02d",
                Integer.parseInt(agencia),
                Integer.parseInt(conta),
                Long.parseLong(nossoNumero),
                Integer.parseInt(carteira));
    }

}

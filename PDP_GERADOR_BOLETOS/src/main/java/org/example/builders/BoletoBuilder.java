package org.example.builders;

import org.example.boleto.pessoa.*;
import org.example.boleto.*;


import java.time.LocalDate;

public interface BoletoBuilder {

    void buildBeneficiario(pessoaFisicaJuridica beneficiario);
    void buildSacado(pessoaFisicaJuridica sacado);
    void buildDadosBoleto(String numBoleto, LocalDate vencimento, double valor);
    void buildDadosConta(dadosConta conta);
    void buildBanco();
    boleto getBoleto();
    String novoCampoLivre(String agencia, String descricao, String numUnico, String carteira );

}

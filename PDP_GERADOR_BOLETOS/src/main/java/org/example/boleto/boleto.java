package org.example.boleto;

import org.example.boleto.pessoa.pessoaFisicaJuridica;

public class boleto {

    private pessoaFisicaJuridica beneficiario;
    private pessoaFisicaJuridica sacado;
    private dadosBoleto dados = new dadosBoleto();
    private dadosConta conta = new dadosConta();
    private String banco;
    private String linhaDigitavel;
    private String barras;
    private String numUnico = (int)(Math.random() * 99999) + 1 + "";

    public pessoaFisicaJuridica getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(pessoaFisicaJuridica beneficiario) {
        this.beneficiario = beneficiario;
    }

    public pessoaFisicaJuridica getSacado() {
        return sacado;
    }

    public void setSacado(pessoaFisicaJuridica sacado) {
        this.sacado = sacado;
    }

    public dadosBoleto getDados() {
        return dados;
    }

    public void setDados(dadosBoleto dados) {
        this.dados = dados;
    }

    public dadosConta getConta() {
        return conta;
    }

    public void setConta(dadosConta conta) {
        this.conta = conta;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getLinhaDigitavel() {
        return linhaDigitavel;
    }

    public void setLinhaDigitavel(String linhaDigitavel) {
        this.linhaDigitavel = linhaDigitavel;
    }

    public String getBarras() {
        return barras;
    }

    public void setBarras(String barras) {
        this.barras = barras;
    }

    public String getNumUnico() {
        return numUnico;
    }

    public void setNumUnico(String numUnico) {
        this.numUnico = numUnico;
    }
}

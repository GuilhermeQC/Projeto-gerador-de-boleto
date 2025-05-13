package org.example.codBarras;

import org.example.boleto.pessoa.*;
import org.example.boleto.*;
import org.example.builders.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class codBarras {

    public static String calcularFatorVencimento(LocalDate vencimento) {
        LocalDate base = LocalDate.of(1997, 10, 7);
        long dias = ChronoUnit.DAYS.between(base, vencimento);
        return String.format("%04d", dias);
    }

    public static String formatarValor(Double valor) {
        BigDecimal valorBig = BigDecimal.valueOf(valor).setScale(2, RoundingMode.HALF_EVEN);
        String valorFormatado = valorBig.toString().replace(".", "").replace(",", "");
        return String.format("%010d", Long.parseLong(valorFormatado));
    }

    public static int calcularDigitoVerificadorModulo11(String codigo) {
        int soma = 0;
        int peso = 2;
        for (int i = codigo.length() - 1; i >= 0; i--) {
            int num = Character.getNumericValue(codigo.charAt(i));
            soma += num * peso;
            peso = (peso == 9) ? 2 : peso + 1;
        }

        int resto = soma % 11;
        int digito = 11 - resto;
        if (digito == 0 || digito == 10 || digito == 11) {
            return 1;
        }
        return digito;
    }

    public static String gerarCodigoBarras(boleto boleto, BoletoBuilder boletoBuilder) {
        String banco = boleto.getBanco(); // ex: "001"
        String moeda = "9";
        String fatorVencimento = codBarras.calcularFatorVencimento(boleto.getDados().getVencimento());
        String valor = formatarValor(boleto.getDados().getValor());

        String campoLivre = boletoBuilder.novoCampoLivre(
                boleto.getConta().getAgencia(),
                boleto.getConta().getNumeroConta(),
                boleto.getNumUnico(),
                boleto.getConta().getCarteira()
        );

        String parcial = banco + moeda + fatorVencimento + valor + campoLivre;

        int digito = calcularDigitoVerificadorModulo11(parcial);

        return banco + moeda + digito + fatorVencimento + valor + campoLivre;
    }

}

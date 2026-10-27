package br.com.estudo.agenda;

import java.math.BigDecimal;
import java.math.RoundingMode;


public record Venda(BigDecimal valorBruto, BigDecimal percentualMdr) {

    private static final BigDecimal CEM = new BigDecimal("100");

    public Venda {
        if (valorBruto == null || valorBruto.signum() <= 0) {
            throw new IllegalArgumentException("valorBruto deve ser maior que zero");
        }
        
        if (percentualMdr == null || percentualMdr.signum() < 0) {
            throw new IllegalArgumentException("percentualMdr não pode ser negativo");
        }
    }

    public BigDecimal valorTaxa(){
        return valorBruto.multiply(percentualMdr).divide(CEM, 2, RoundingMode.HALF_UP);
    }

    public BigDecimal valorLiquido(){
        return valorBruto.subtract(valorTaxa());
    }
}
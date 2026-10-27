package br.com.estudo.agenda;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VendaTest {

    @Test
    void calculaTaxaELiquidoDeUmaVendaRedonda() {
        Venda venda = new Venda(new BigDecimal("100.00"), new BigDecimal("3.50"));

        assertEquals(new BigDecimal("3.50"), venda.valorTaxa());
        assertEquals(new BigDecimal("96.50"), venda.valorLiquido());
    }

    @ParameterizedTest
    @CsvSource({
            "37.90, 3.50, 1.33, 36.57",
            "10.00, 2.10, 0.21, 9.79",
            "0.01, 3.50, 0.00, 0.01"
    })
    void arredondaATaxaParaCentavos(String bruto, String mdr, String taxaEsperada, String liquidoEsperado) {
        Venda venda = new Venda(new BigDecimal(bruto), new BigDecimal(mdr));

        assertEquals(new BigDecimal(taxaEsperada), venda.valorTaxa());
        assertEquals(new BigDecimal(liquidoEsperado), venda.valorLiquido());
    }

    @Test
    void recusaVendaComValorNegativo() {
        IllegalArgumentException erro = assertThrows(IllegalArgumentException.class,
                () -> new Venda(new BigDecimal("-5.00"), new BigDecimal("3.50")));

        assertEquals("valorBruto deve ser maior que zero", erro.getMessage());
    }
}
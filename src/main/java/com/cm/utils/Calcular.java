package com.cm.utils;

import java.math.BigDecimal;

public class Calcular {
	
		public Calcular() {}
		public static BigDecimal calcularTotalPedido(BigDecimal frete, BigDecimal subTotal) {
			return frete.add(subTotal);
		}
}

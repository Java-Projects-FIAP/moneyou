package br.com.fiap.moneyou.model;

import java.time.LocalDate;

public record CofrinhoFilter(
                Long minBalance,
                String name,
                LocalDate startDate,
                LocalDate endDate) {
}
package br.com.fiap.moneyou.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.moneyou.model.Cofrinho;
import br.com.fiap.moneyou.CofrinnhoFilter;
import jakarta.persistence.criteria.Predicate;

public class CofrinhoSpecification {

    public static Specification<Cofrinho> withFilters(CofrinhoFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.name() != null) {
                predicates.add(
                        cb.like(root.get("name"), filter.name()));
            }

            if (filter.minBalance() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(root.get("balance"), filter.minBalance()));
            }

            if (filter.startDate() != null && filter.endDate() != null) {
                predicates.add(
                        cb.between(root.get("date"), filter.startDate(), filter.endDate()));
            }

            if (filter.startDate() != null && filter.endDate() == null) {
                predicates.add(
                        cb.equal(root.get("date"), filter.startDate()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

}
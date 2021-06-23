package com.example.task.specification;

import com.example.task.criteria.SearchCriteria;
import com.example.task.entity.Human;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HumanSpecification implements Specification<Human> {

    private List<SearchCriteria> criteriaList;

    public HumanSpecification() {
        this.criteriaList = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        criteriaList.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<Human> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : criteriaList) {
            switch (criteria.getOperation()) {
                case GREATER_THAN:
                    predicates.add(criteriaBuilder.greaterThan(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;

                case LESS_THAN:
                    predicates.add(criteriaBuilder.lessThan(
                            root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;

                case GREATER_THAN_EQUAL:
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                            root.get(criteria.getKey()), LocalDate.parse(criteria.getValue().toString())));
                    break;

                case LESS_THAN_EQUAL:
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(
                            root.get(criteria.getKey()),  LocalDate.parse(criteria.getValue().toString())));
                    break;

                case NOT_EQUAL:
                    predicates.add(criteriaBuilder.notEqual(
                            root.get(criteria.getKey()), criteria.getValue()));
                    break;

                case EQUAL:
                    predicates.add(criteriaBuilder.equal(
                            root.get(criteria.getKey()), criteria.getValue()));
                    break;

                case MATCH:
                    predicates.add(criteriaBuilder.like(
                            root.get(criteria.getKey()),
                            "%" + criteria.getValue().toString().toLowerCase() + "%"));
                    break;

                case MATCH_END:
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(criteria.getKey())),
                            "%" + criteria.getValue().toString().toLowerCase()));
                    break;

                case MATCH_START:
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get(criteria.getKey())),
                            criteria.getValue().toString().toLowerCase() + "%"));
                    break;

                case IN:
                    predicates.add(criteriaBuilder.in(root.get(criteria.getKey())).value(criteria.getValue()));
                    break;

                case NOT_IN:
                    predicates.add(criteriaBuilder.not(root.get(criteria.getKey())).in(criteria.getValue()));
                    break;
            }
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}

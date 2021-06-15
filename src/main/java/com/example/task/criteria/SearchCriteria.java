package com.example.task.criteria;

import com.example.task.operations.SearchOperation;

import javax.persistence.metamodel.SingularAttribute;

public class SearchCriteria {
    private String key;
    private SingularAttribute<?, ?> genericKey;
    private Object value;
    private SearchOperation operation;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, Object value, SearchOperation operation) {
        this.key = key;
        this.value = value;
        this.operation = operation;
    }

    public SearchCriteria(SingularAttribute<?, ?> genericKey, Object value, SearchOperation operation) {
        this.genericKey = genericKey;
        this.value = value;
        this.operation = operation;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public SearchOperation getOperation() {
        return operation;
    }

    public void setOperation(SearchOperation operation) {
        this.operation = operation;
    }

    public SingularAttribute<?, ?> getGenericKey() {
        return genericKey;
    }

    public void setGenericKey(SingularAttribute<?, ?> genericKey) {
        this.genericKey = genericKey;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "key='" + key + '\'' +
                ", value=" + value +
                ", operation=" + operation +
                '}';
    }
}

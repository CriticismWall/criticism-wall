package com.freedom.model.base;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class WeightAndDeletable extends Deletable {

    @Column(nullable = false)
    private Integer weight;

    public void exchange(WeightAndDeletable target) {
        int place = this.weight;
        this.weight = target.weight;
        target.weight = place;
    }

    public Integer getWeight() {
        return weight;
    }

    public WeightAndDeletable setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }
}

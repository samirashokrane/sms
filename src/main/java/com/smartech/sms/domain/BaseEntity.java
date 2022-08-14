package com.smartech.sms.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;


    @Version
    Long version= 0l;



}


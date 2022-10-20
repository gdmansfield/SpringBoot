package com.gdmatstaffs.validation_demo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name="copy_status")
public class CopyStatus
{
    @Id
    private int id;
    private String status;
}

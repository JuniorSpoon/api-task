package com.supercassa.apitask.entity;

import com.supercassa.apitask.api.model.Response;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "sk_example_table")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuperKassa {

    @Id
    private Integer id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(
            columnDefinition = "jsonb",
            name = "obj"
    )
    private Response response;
}

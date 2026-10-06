package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "categoria")
public class Categoria extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // coluna "serial" no Postgres
    public Long id;

    @Column(name = "descricao", nullable = false, length = 150)
    public String descricao;
}

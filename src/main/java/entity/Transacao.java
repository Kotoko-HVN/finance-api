package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "transacao")
public class Transacao extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "descricao", nullable = false, length = 500)
    public String descricao;

    @Column(name = "valor", nullable = false, precision = 12, scale = 2)
    public BigDecimal valor;

    // "R" = receita, "D" = despesa
    @Column(name = "tipo", nullable = false, length = 1)
    public String tipo;

    @Column(name = "datatransacao", nullable = false)
    public LocalDate dataTransacao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "conta_id", nullable = false)
    public Conta conta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    public Categoria categoria;
}

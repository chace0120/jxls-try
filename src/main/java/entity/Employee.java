package entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author chenx
 * @date 2018/4/20
 */
public class Employee {
    private String name;

    private LocalDate birthDate;

    private BigDecimal payment;

    private BigDecimal bonus;

    public Employee() {
    }

    public Employee(String name, LocalDate birthDate, BigDecimal payment, BigDecimal bonus) {
        this.name = name;
        this.birthDate = birthDate;
        this.payment = payment;
        this.bonus = bonus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public BigDecimal getPayment() {
        return payment;
    }

    public void setPayment(BigDecimal payment) {
        this.payment = payment;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }
}

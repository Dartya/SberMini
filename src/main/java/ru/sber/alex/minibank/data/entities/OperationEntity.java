package ru.sber.alex.minibank.data.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

/**
 * Класс сущности БД "Операция"
 */
@Entity
@Table(name="operations")
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="accounts_id")
    private int accountsId;
    @Column(name="secon_account_id")
    private Integer seconAccountId;
    @Column(name="dict_operation_id")
    private int dictOperationID;
    private BigDecimal summ;
    private Timestamp timestamp;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name="accounts_id", insertable = false, updatable = false)
    private AccountEntity accountId;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name="secon_account_id", insertable = false, updatable = false)
    private AccountEntity secondAccountId;

    @ManyToOne (optional=false, cascade=CascadeType.ALL)
    @JoinColumn(name="dict_operation_id", insertable = false, updatable = false)
    private DictOperationsEntity dictOperation;

//    Аннотации в комменте не проверялись, но пускай тут останутся для примера альтернативной связи ManyToMany
//    @ManyToMany(cascade = {
//            CascadeType.PERSIST,
//            CascadeType.MERGE
//    })
//    @JoinTable (name="logs",
//            joinColumns=@JoinColumn (name="operation_id"),
//            inverseJoinColumns=@JoinColumn(name="clients_id"))
    @ManyToMany(mappedBy = "operations")
    private List<ClientEntity> clients = new ArrayList<>();

    public List<ClientEntity> getClients(){
        return clients;
    }

    public void setClients(List<ClientEntity> operations) {
        this.clients = operations;
    }

    public void addClient(ClientEntity client) {
        clients.add(client);
        client.getOperations().add(this);
    }

    public void removeClient(ClientEntity client) {
        clients.remove(client);
        client.getOperations().remove(this);
    }

    public OperationEntity() {
    }

    public OperationEntity(int accountsId, Integer seconAccountId, int dictOperationID, BigDecimal summ, Timestamp timestamp) {
        this.accountsId = accountsId;
        this.seconAccountId = seconAccountId;
        this.dictOperationID = dictOperationID;
        this.summ = summ;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(int accountsId) {
        this.accountsId = accountsId;
    }

    public Integer getSeconAccountId() {
        return seconAccountId;
    }

    public void setSeconAccountId(Integer seconAccountId) {
        this.seconAccountId = seconAccountId;
    }

    public int getDictOperationID() {
        return dictOperationID;
    }

    public void setDictOperationID(int dictOperationID) {
        this.dictOperationID = dictOperationID;
    }

    public BigDecimal getSumm() {
        return summ;
    }

    public void setSumm(BigDecimal summ) {
        this.summ = summ;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public AccountEntity getAccountId() {
        return accountId;
    }

    public AccountEntity getSecondAccountId() {
        return secondAccountId;
    }

    public DictOperationsEntity getDictOperation() {
        return dictOperation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationEntity that = (OperationEntity) o;
        return id == that.id &&
                accountsId == that.accountsId &&
                dictOperationID == that.dictOperationID &&
                Objects.equals(seconAccountId, that.seconAccountId) &&
                Objects.equals(summ, that.summ) &&
                Objects.equals(timestamp, that.timestamp) &&
                Objects.equals(accountId, that.accountId) &&
                Objects.equals(secondAccountId, that.secondAccountId) &&
                Objects.equals(dictOperation, that.dictOperation) &&
                Objects.equals(clients, that.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountsId, seconAccountId, dictOperationID, summ, timestamp, accountId, secondAccountId, dictOperation, clients);
    }

    @Override
    public String toString() {
        return "OperationEntity{" +
                "id=" + id +
                ", accountsId=" + accountsId +
                ", seconAccountId=" + seconAccountId +
                ", dictOperationID=" + dictOperationID +
                ", summ=" + summ +
                ", timestamp=" + timestamp +
                ", accountId=" + accountId +
                ", secondAccountId=" + secondAccountId +
                ", dictOperation=" + dictOperation +
                ", clients=" + clients +
                '}';
    }
}

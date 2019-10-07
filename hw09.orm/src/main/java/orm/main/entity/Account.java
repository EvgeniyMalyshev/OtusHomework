package orm.main.entity;

import orm.main.annotation.Id;

import java.math.BigDecimal;

public class Account {

    @Id
    private final long no;
    private final String type;
    private final BigDecimal rest;

    public Account(long no, String type, BigDecimal rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    public long getNo() {
        return no;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getRest() {
        return rest;
    }

    @Override
    public String toString() {
        return "AccountRow [" +
                "no=" + no + " | " +
                "type=" + (type == null ? "null" : type) + " | " +
                "rest=" + (rest == null ? "null" : rest) + "]";
    }

}

package otus.main.java;

import com.google.common.base.Objects;

import static java.lang.System.out;

public class HelloOtus {
    String guava = "go-go guava";

    public String getGuava() {
        return guava;
    }

    public void setGuava(String guava) {
        this.guava = guava;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HelloOtus)) return false;
        HelloOtus helloOtus = (HelloOtus) o;
        return Objects.equal(getGuava(), helloOtus.getGuava());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getGuava());
    }

    public static void main(String[] args) {
        HelloOtus helloOtus = new HelloOtus();
        out.println("Guava say: " + String.join(", ", helloOtus.getGuava()));
    }
}

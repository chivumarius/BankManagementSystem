import java.io.Serializable;
import java.time.LocalDate;



// ▬ "Comparable" Implementation
//      → to Compare "Transactions" Objects
//      → & "Serializable" Interface Implementation
//      → (to "Write" the "Data" into a "File")
public class Transaction implements Comparable<Transaction>, Serializable {



    // ▼ "Variables" ▼
    private static int next = 1;
    int trsNo;
    Account acc;  // ◄ "Object"
    LocalDate date;
    char operation;
    double amount;



    // ▬ "Full / Parameterized Constructor" ▬
    public Transaction(Account acc, LocalDate date, char operation, double amount) {
        this.acc = acc;
        this.date = date;
        this.operation = operation;
        this.amount = amount;
        trsNo = next++;
    }


    // ▬ "compareTo()" Override Method ▬
    @Override
    public int compareTo(Transaction o) {
        return this.trsNo - o.trsNo;
    }



    // ▬ "toString()" Override Method ▬
    @Override
    public String toString() {
        return "Transaction{" +
                "trsNo=" + trsNo +
                ", acc=" + acc +
                ", date=" + date +
                ", operation=" + operation +
                ", amount=" + amount +
                '}';
    }




    // ▬ "Getters" Methods (Alt + Insert) ▬
    public int getTrsNo() {
        return trsNo;
    }

    public Account getAcc() {
        return acc;
    }

    public LocalDate getDate() {
        return date;
    }

    public char getOperation() {
        return operation;
    }

    public double getAmount() {
        return amount;
    }
}


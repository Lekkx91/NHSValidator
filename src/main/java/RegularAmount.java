/**
 * The Annotation {@link ValidRegularAmount} is applied to validate RegularAmount whenever it's used
 */
@ValidRegularAmount
public class RegularAmount {

    private Frequency frequency;
    private String amount;

    @ValidRegularAmount
    public RegularAmount(String amount, Frequency frequency) {
        this.frequency = frequency;
        this.amount = amount;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
